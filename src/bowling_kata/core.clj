(ns bowling-kata.core
  (:gen-class))

(defn next-frame-size [rolls frame-number]
  (cond
    (= 10 frame-number) 3
    (= 10 (first rolls)) 1
    :else 2))

(defn to-frames [rolls]
  (loop [remaining rolls
         frame-number 1
         frames []]
    (if (empty? remaining)
      frames
      (let [size (next-frame-size remaining frame-number)]
        (recur (drop size remaining)
               (inc frame-number)
               (conj frames (take size remaining)))))))

(defn spare? [frame]
  (and (= 2 (count frame))
       (= 10 (reduce + frame))))

(defn strike? [frame]
  (= 10 (first frame)))

(defn put-frame-score [[frame & others] scores]
  (if (strike? frame)
    (conj scores (+ (reduce + frame) (reduce + (take 2 (flatten others)))))
    (if (spare? frame)
      (conj scores (+ (reduce + frame) (first (first others))))
      (conj scores (reduce + frame)))))

(defn frame-scores [frames]
  (loop [remaining frames
         scores []]
    (if (empty? remaining)
      scores
      (recur (rest remaining)
             (put-frame-score remaining scores)))))

(defn score [rolls]
  (reduce + (frame-scores (to-frames rolls))))
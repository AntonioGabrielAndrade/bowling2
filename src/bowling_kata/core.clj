(ns bowling-kata.core
  (:gen-class))

(defn to-frames [rolls]
  (loop [remaining rolls
         frame-number 1
         frames []]
    (if (empty? remaining)
      frames
      (if (= 10 frame-number)
        (recur (drop 3 remaining)
               (inc frame-number)
               (conj frames (take 3 remaining)))
        (if (= 10 (first remaining))
          (recur (drop 1 remaining)
                 (inc frame-number)
                 (conj frames (take 1 remaining)))
          (recur (drop 2 remaining)
                 (inc frame-number)
                 (conj frames (take 2 remaining))))))))

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
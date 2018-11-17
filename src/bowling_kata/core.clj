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

(defn strike-extra-score [remaining-frames]
  (reduce + (take 2 (flatten remaining-frames))))

(defn spare-extra-score [remaining-frames]
  (first (first remaining-frames)))

(defn next-frame-score [[frame & others]]
  (let [simple-score (reduce + frame)]
    (cond
      (strike? frame)
      (+ simple-score (strike-extra-score others))

      (spare? frame)
      (+ simple-score (spare-extra-score others))

      :else simple-score)))

(defn frame-scores [frames]
  (loop [remaining frames
         scores []]
    (if (empty? remaining)
      scores
      (recur (rest remaining)
             (conj scores (next-frame-score remaining))))))

(defn score [rolls]
  (reduce + (frame-scores (to-frames rolls))))
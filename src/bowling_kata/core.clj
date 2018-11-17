(ns bowling-kata.core
  (:gen-class))

(defn to-frames [rolls]
  (partition 2 rolls))

(defn spare? [frame]
  (and (= 2 (count frame))
       (= 10 (reduce + frame))))

(defn strike? [frame]
  (= 10 (first frame)))

(defn put-frame-score [frame next-frame scores]
  (if (strike? frame)
    (conj scores (+ 10 (reduce + next-frame)))
    (if (spare? frame)
      (conj scores (+ (reduce + frame) (first next-frame)))
      (conj scores (reduce + frame)))))

(defn frame-scores [frames]
  (loop [remaining frames
         scores []]
    (if (empty? remaining)
      scores
      (recur (rest remaining)
             (put-frame-score (first remaining) (second remaining) scores)))))

(defn score [rolls]
  (reduce + (frame-scores (to-frames rolls))))
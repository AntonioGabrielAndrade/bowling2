(ns bowling-kata.core
  (:gen-class))

(defn score [rolls]
  (reduce + rolls))
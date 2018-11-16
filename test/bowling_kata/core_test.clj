(ns bowling-kata.core-test
  (:require [clojure.test :refer :all]
            [bowling-kata.core :refer :all]))

(deftest score-test
  (testing "can calc zero-game score"
    (is (= 0 (score [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0]))))
  (testing "can calc a simple game score"
    (is (= 20 (score [1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1])))
    (is (= 90 (score [9 0 9 0 9 0 9 0 9 0 9 0 9 0 9 0 9 0 9 0])))))

(run-tests 'bowling-kata.core-test)
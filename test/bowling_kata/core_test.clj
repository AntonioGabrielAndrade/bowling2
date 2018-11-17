(ns bowling-kata.core-test
  (:require [clojure.test :refer :all]
            [bowling-kata.core :refer :all]))

(deftest to-frames-test
  (testing "can partition rolls into frames"
    (is (= [[1 9] [1 2] [1 1] [1 1] [1 1] [1 1] [1 1] [1 1] [1 1] [1 1]]
           (to-frames [1 9 1 2 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1])))))

(deftest spare?-test
  (testing "can tell when frame is a spare"
    (is (spare? [5 5]))
    (is (spare? [9 1]))
    (is (not (spare? [2 3])))
    (is (not (spare? [10])))))

(deftest strike?-test
  (testing "can tell when frame is a strike"
    (is (strike? [10]))
    (is (not (strike? [5 5])))))

(deftest put-frame-score-test
  (testing "can compute a simple frame score"
    (is (= [7] (put-frame-score [5 2] [1 2] []))))
  (testing "can compute a spare frame score"
    (is (= [11] (put-frame-score [5 5] [1 2] []))))
  (testing "can compute a strike frame score"
    (is (= [13] (put-frame-score [10] [1 2] [])))))

(deftest frames-score-test
  (testing "can calc simple frame score"
    (is (= [9 3 2 2 2 2 2 2 2 2]
          (frame-scores [[1 8] [1 2] [1 1] [1 1] [1 1] [1 1] [1 1] [1 1] [1 1] [1 1]]))))
  (testing "can calc a spare frame score"
    (is (= [11 3 2 2 2 2 2 2 2 2]
           (frame-scores [[1 9] [1 2] [1 1] [1 1] [1 1] [1 1] [1 1] [1 1] [1 1] [1 1]])))))

(deftest score-test
  (testing "can calc zero-game score"
    (is (= 0 (score [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0]))))
  (testing "can calc a simple game score"
    (is (= 20 (score [1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1])))
    (is (= 90 (score [9 0 9 0 9 0 9 0 9 0 9 0 9 0 9 0 9 0 9 0]))))
  (testing "can calc a spare-game score"
    (is (= 29 (score [1 9 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1]))))
  (testing "can calc a strike-game score"
    (is (= 23 (score [10 1 2 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0])))))

(run-tests 'bowling-kata.core-test)
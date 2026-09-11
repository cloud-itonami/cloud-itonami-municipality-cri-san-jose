(ns culture.facts-test
  (:require [clojure.edn :as edn]
            [kotoba.lang.text :as str]
            [clojure.test :refer [deftest is]]
            [culture.facts :as facts]))

(deftest san-jose-has-culture-basis
  (let [sb (facts/spec-basis "san-jose")]
    (is (= 9 (count sb)))
    (is (= (count sb) (count (set (map :culture/id sb)))))
    (is (every? #(str/starts-with? (:culture/url %) "https://") sb))
    (is (every? #(= "san-jose" (:culture/municipality %)) sb))
    (is (every? #(= "CRI" (:culture/country %)) sb))
    (is (every? #(seq (:culture/summary %)) sb))
    (is (every? #(string? (:culture/retrieved-at %)) sb))))

(deftest unknown-municipality-has-no-basis
  (is (nil? (facts/spec-basis "cartago")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["san-jose" "cartago"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["cartago"] (:missing-municipalities c)))))

(deftest by-kind-filters
  (is (= 4 (count (facts/by-kind "san-jose" :dish))))
  (is (= ["san-jose.beverage.guaro"]
         (mapv :culture/id (facts/by-kind "san-jose" :beverage))))
  (is (empty? (facts/by-kind "san-jose" :craft)))
  (is (empty? (facts/by-kind "cartago" :dish))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/culture-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))

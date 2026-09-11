(ns ordinance.facts-test
  (:require [kotoba.lang.text :as str]
            [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest san-jose-has-spec-basis
  (let [sb (facts/spec-basis "san-jose")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:ordinance/url %) "https://pgrweb.go.cr/") sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "mexico-city")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["san-jose" "mexico-city"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["mexico-city"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= ["san-jose.reglamento-autonomo-organizacion-servicio"]
         (mapv :ordinance/id (facts/by-topic "san-jose" :governance))))
  (is (empty? (facts/by-topic "san-jose" :labor)))
  (is (empty? (facts/by-topic "mexico-city" :urban-planning))))

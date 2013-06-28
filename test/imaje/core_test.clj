(ns imaje.core-test
  (:require [clojure.test :refer :all]
            [imaje.core :refer :all]
            [imaje.wrapper :refer :all]))

(defn generate-random-image
	""
	[rwidth rheight]
	(let [result (empty-image rwidth rheight)]
		(set-pixels! result (take (* rwidth rheight) (repeatedly #(rand-int 256))))
		result)
	)

(deftest imrender-test
	(testing "Imrender"
		(is (= 0 1))))

; (deftest immap-test
; 	(testing "Immap with one image"
; 		(let [rnd-img (generate-random-image 25 25)
; 				pixels (get-pixels rnd-img)]
; 				(is (=
; 						(imreduce #(update-in %1 [(%4 %2 %3)] inc) rnd-img)
; 						(reduce #(update-in %1 [%2] inc) pixels))))))

(deftest imreduce-test
	(testing "Imreduce with one image and initial value"
		(let [rnd-img (generate-random-image 25 25)
				pixels (get-pixels rnd-img)]
				(is (=
						(imreduce #(update-in %1 [(%4 %2 %3)] inc) (vec (repeat 256 0)) rnd-img)
						(reduce #(update-in %1 [%2] inc) (vec (repeat 256 0)) pixels))))))
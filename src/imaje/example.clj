(ns imaje.example
	(:use [imaje.wrapper]
			[imaje.core])
	)

(defn -main
	[]
	(println "Starting test application")
	; imrender fills image with random value
	(let [img (imrender (fn [x y] (rand-int 256)) 640 480)]
		; inverts image
		(time (immap (fn [x y sampler] (- 256 (sampler x y))) img))
		; calculates histogram of the image
		(time (imreduce (fn [accum x y sampler] (update-in accum [(sampler x y)] inc)) (vec (repeat 256 0)) img))
		(println (imreduce (fn [accum x y sampler] (update-in accum [(sampler x y)] inc)) (vec (repeat 256 0)) img))
	)
)


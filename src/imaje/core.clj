(ns imaje.core
	(:use [imaje.wrapper])
	)

; (set! *warn-on-reflection* true)

(defn create-sampler
	""
	[image]
	(let [pixels (get-pixels image)
			wd (width image)
			hg (height image)]
		(fn [x y] (aget ^ints pixels (+ x (* y wd))))
	)
)

(defn imrender
	""
	[fun rwidth rheight]
	(let [result (empty-image rwidth rheight)
			new-pixels (get-pixels result)
			]
		(dotimes [y rheight]
			(dotimes [x rwidth]
				(aset ^ints new-pixels (+ x (* rwidth y)) ^int (fun x y)))
		)
		(set-pixels! result new-pixels)
		result
	)

	)

(defn immap
	""
	[fun image]
	(let [wd (width image)
			hg (height image)
			result (empty-image image)
			new-pixels (get-pixels result)
			sampler (create-sampler image)
			]
		(dotimes [y hg]
			(dotimes [x wd]
				(aset ^ints new-pixels (+ x (* wd y)) ^int (fun x y sampler)))
		)
		(set-pixels! result new-pixels)
		result
	)
)

(defn imreduce
	""
	[fun initial image]
	(let [wd (width image)
			hg (height image)
			old-pixels (get-pixels image)
			sampler (create-sampler image)
			]
		(loop [x 0 y 0 accum initial]
			(cond
				(= x wd) (recur 0 (inc y) accum)
				(= y hg) accum
				:else
					(do
						(recur (inc x) y (fun accum x y sampler)))
				)
			)
		)
	)		

; (defn imscan
; 	""
; 	[fun image]
; 	)

(defn -main
	[]
	(println "Starting test application")
	(let [img (imrender (fn [_ _] 12) 640 480)]
		(time (immap #(+ (%3 %1 %2) 12) img))
		(time (imreduce #(update-in %1 [(%4 %2 %3)] inc) (vec (repeat 256 0)) img))
		(println (imreduce #(update-in %1 [(%4 %2 %3)] inc) (vec (repeat 256 0)) img))
		(save-image img "out.png")
	)
)

(ns imaje.example
	(:use 
		[imaje.core]
		[imaje.wrapper]))

; (defn average3x3
; 	[x y sampler]
; 	; (*
; 		(sampler x y))
; 		; (+ 
; 			; (sampler (- x 1) (- y 1))
; 			; (sampler x (- y))
; 			; (sampler (+ x 1) (- y 1))

; 			; (sampler (- x 1) y)
; 			; (sampler x y)
; 			; (sampler (+ x 1) y)

; 			; (sampler (- x 1) (+ y 1))
; 			; (sampler x (+ y 1))
; 			; (sampler (+ x 1) (+ y 1))
; 		; ))
; 		 ; 9))

(defn box-blur
	[]
	(let [
			input-image (load-image "resources/yellow_square.png")]
			; input-image (imrender (fn [x y] (rand-int 256)) 640 480)]
			; blurred-image (immap average3x3 input-image)]
			(println (get-type input-image))
			(println (get-pixels input-image))
			(save-image input-image "baboon_face_box_blur.png")))


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


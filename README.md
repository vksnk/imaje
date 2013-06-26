# imaje

A Clojure library designed to ... well, that part is up to you.

## Usage

Create new image with some given function:

; just fill image with one single value (12)
(imrender (fn [_ _] 12) 640 480)

Apply function to every pixel of image and produce new image:

; add 12 to every pixel of image
(immap #(+ (%3 %1 %2) 12) img) 

Image histogram using reduce function:

(imreduce #(update-in %1 [(%4 %2 %3)] inc) (vec (repeat 256 0)) img)

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.

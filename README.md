# imaje

A Clojure library designed to do image processing in functional style.

This includes:

* `immap`
* `imreduce`
* `imrender`

All of those functions are build around `sampler`s concept, which provides a simplified way for accessing pixels of the image. In general, this is just a function which gets two argument x and y position of pixel and return the value of that pixel.

Also, imaje provides basic wrapper around BufferedImage and ImageIO, so you can:

* create new images
* access pixel data
* load/save

## Installation

Add the following to your Leiningen `:dependencies`:

```clj
[imaje "0.1.0"]
```
## Usage

The top level interface is in `imaje.core`.

```clj
   (use 'imaje.core)
```

For example, you can create and fill randomly an 640x480 image using `imrender` function:

```clojure
(imrender (fn [x y] (rand-int 256)) 640 480)
```
or you can create image negative with `immap`:
```clojure
; add 12 to every pixel of image
(immap (fn [x y sampler] (- 256 (sampler x y))) img)
```

or build a histogram of image with `imreduce` function:

```clojure
(imreduce (fn [accum x y sampler] (update-in accum [(sampler x y)] inc)) (vec (repeat 256 0)) img)
```

## License

Copyright © 2013 Volodymyr Kysenko

Distributed under the Eclipse Public License, the same as Clojure.

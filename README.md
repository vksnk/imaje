# imaje

A Clojure library designed to do image processing in functional style.

This includes:

* `immap`
* `imreduce`
* `imrender`

All of those functions are build around `sampler` concept, which provides a simplified way for accessing pixels of the image.

## Installation

Add the following to your Leiningen `:dependencies`:

```clj
[imaje "0.1"]
```
## Usage

The top level interface is in `imaje.core`.

```clj
    (use 'imaje.core)
```


Create new image using provided function:

```clojure
; just fill image with one single value (12)
(imrender (fn [_ _] 12) 640 480)
```

Apply function to every pixel of image and produce new image:

```clojure
; add 12 to every pixel of image
(immap #(+ (%3 %1 %2) 12) img) 
```

Image histogram using reduce function:

```clojure
(imreduce #(update-in %1 [(%4 %2 %3)] inc) (vec (repeat 256 0)) img)
```

## License

Copyright © 2013 Volodymyr Kysenko

Distributed under the Eclipse Public License, the same as Clojure.

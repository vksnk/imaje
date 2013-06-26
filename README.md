# imaje

A Clojure library designed to ... well, that part is up to you.

## Usage

Image histogram using reduce function:

(imreduce #(update-in %1 [(%2 %3 %4)] inc) (vec (repeat 256 0)) img1)

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.

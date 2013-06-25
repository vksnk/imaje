(ns imaje.core
	(import java.io.File)
	(import java.awt.image.BufferedImage)
	(import javax.imageio.ImageIO)
	)

(defn empty-image
	""
	[width height]
	(BufferedImage. width height BufferedImage/TYPE_INT_RGB)
	)

(defn load-image
	""
	[filename]
	(ImageIO/read (File. filename))
	)

(defn save-image
	""
	[img filename]
	(ImageIO/write img "png" (File. filename))
	)

(defn width
	""
	[image]
	(.getWidth image)
	)

(defn height
	""
	[image]
	(.getHeight image)
	)

(defn get-pixels 
 	""
  	([image]
    	(.getDataElements (.getRaster image) 0 0 (width image) (height image) nil)))

(defn set-pixels!
 	""
  	([image pixels]
    	(.setDataElements (.getRaster image) 0 0 (.getWidth image) (.getHeight image) (int-array pixels))))

(defn create-sampler
	""
	[image]
	(let [pixels (get-pixels image)
			wd (width image)
			hg (height image)]
		(fn [x y] (aget ^ints pixels (+ hg (* y wd))))
	)
)

(defn immap
	""
	[image fun]
	(let [wd (width image)
			hg (height image)
			old-pixels (get-pixels image)
			new-pixels (aclone old-pixels)
			sampler (create-sampler image)
			]
		(doseq [y (range hg) x (range wd)]
			(aset ^ints new-pixels (+ x (* wd y)) ^int (fun sampler x y))
		)
		(set-pixels! image new-pixels)
	)
)

(defn pixel-map
	""
	[fn pixels]
	(map fn pixels)
	)

(defn pixel-reduce
	""
	[fn val pixels]
	(reduce fn val pixels)
	)

(defn -main
	[]
	(println "Starting test application")
	(let [img (empty-image 640 480)]
		(immap img #(+ (%1 %2 %3) 12))
		(println (pixel-reduce #(update-in %1 [%2] inc) (vec (repeat 256 0)) (get-pixels img)))
		(save-image img "out.png")
	)
)
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

(defn get-pixels 
  ""
  ([image]
    (.getDataElements (.getRaster image) 0 0 (.getWidth image) (.getHeight image) nil)))

(defn set-pixels 
  ""
  ([image pixels]
    (.setDataElements (.getRaster image) 0 0 (.getWidth image) (.getHeight image) (int-array pixels))))

(defn -main
	[]
	(println "Starting test application")
	(let [img (empty-image 640 480)
			pixels (get-pixels img)]
		; (set-pixels img pixels)
		(set-pixels img (map #(+ % 255) pixels))
		(save-image img "out.png")
		)
)
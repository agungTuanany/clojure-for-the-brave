(defn my-reduce
    [f initial coll]
    (loop [result initial
           remaining coll]
      (if (empty? remaining)
        result
        (recur (f result (first remaining) (rest remaining)))))
    ([f [head & tail]]
     (my-reduce f head tail)))



(defn better-symmetrize-body-parts
      "Expects a seq of maps that have a :name and :size"
      [asym-body-parts]
      (reduce (fn [final-body-parts part]
                  (into final-body-parts (set [part (matching-part part)])))
              []
              asym-body-parts))

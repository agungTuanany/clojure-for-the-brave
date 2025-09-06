  (def asym-spider-body-parts [{:name "cephalothorax" :size 5}
                               {:name "abdomen"       :size 8}
                               {:name "eye"           :size 1}  ; Just one eye to start
                               {:name "fang"          :size 2}
                               {:name "leg"           :size 4}]) ; Just one leg to start

  (defn spider-expander
    [part]
    (let [name (:name part)]
      (cond
        (= name "eye") (set (for [n (range 1 9)]
                              (assoc part :name (str "eye-" n))))
        (= name "leg") (set (for [n (range 1 9)]
                              (assoc part :name (str "leg-" n))))
        :else #{part})))

(defn expand-body-parts
  "A generic function to expand a collection of body parts.
   Takes an `expander-fn` which defines HOW to expand a single part.
   The expander-fn should take one part and return a COLLECTION of parts
   (e.g., the original part plus any new ones it creates)."
  [expander-fn asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (expander-fn part)))
          []
          asym-body-parts))

;; Now we can symmetrize using the generic function!
(expand-body-parts spider-expander asym-spider-body-parts)

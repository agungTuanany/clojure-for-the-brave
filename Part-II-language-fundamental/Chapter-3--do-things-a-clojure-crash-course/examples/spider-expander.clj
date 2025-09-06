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

;; Define the basic, asymmetrical spider parts.
(def asym-spider-body-parts [{:name "cephalothorax" :size 5}
                             {:name "abdomen" :size 8}
                             {:name "eye" :size 1} ; Just one eye to start
                             {:name "fang" :size 2}
                             {:name "leg" :size 4}]) ; Just one leg to start

(defn spider-expander
  [part]
  (let [name (:name part)]
    (cond
      ;; For an eye, we need 8 total (1 original + 7 more)
      (= name "eye") (set (for [n (range 1 9)] ; n will be 1, 2, 3... 8
                            (assoc part :name (str "eye-" n))))
      ;; For a leg, we need 8 total
      (= name "leg") (set (for [n (range 1 9)]
                            (assoc part :name (str "leg-" n))))
      ;; For any other part (cephalothorax, abdomen, fang), just return it as is.
      :else #{part})))

;; Now let's expand the spider!
(expand-body-parts spider-expander asym-spider-body-parts)

(def asym-hobbit-body-parts [{:name "head"           :size 3}
                             {:name "left-eye"       :size 1}
                             {:name "left-ear"       :size 1}
                             {:name "mouth"          :size 1}
                             {:name "nose"           :size 1}
                             {:name "neck"           :size 2}
                             {:name "left-shoulder"  :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest"          :size 10}
                             {:name "back"           :size 10}
                             {:name "left-forearm"   :size 3}
                             {:name "abdomen"        :size 6}
                             {:name "left-kidney"    :size 1}
                             {:name "left-hand"      :size 2}
                             {:name "left-knee"      :size 2}
                             {:name "left-thigh"     :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles"  :size 1}
                             {:name "left-foot"      :size 2}])

(defn hobbit-expander
  [part]
  (let [name (:name part)
        new-name (clojure.string/replace name #"^left-" "right-")]
    (if (not= name new-name)
      ;; If the name changed, we return a set of the original and the new part.
      #{part (assoc part :name new-name)}
      ;; If it didn't change (like "head"), we just return a set with the original.
      #{part})))

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
(expand-body-parts hobbit-expander asym-hobbit-body-parts)

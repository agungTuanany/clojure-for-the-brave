(set-env!)

;; (deftask fire
;;   "Announces that something is on fire"
;;   [t thing THING str "The thing that's on fire"
;;   ;; [t thing BILY_JOEY str "The thing that's on fire"
;;    p pluralize bool "Whether to pluralize"]
;;   (let [verb (if pluralize "are" "is")]
;;     (println "My" thing verb "on fire!")))

(deftask what
  "Specify a thing"
  [t thing  THING str "An object"
   p pluralize       bool "Whether to pluraize"]
  (fn middleware [next-handler]
    (fn handler [fileset]
      (next-handler (merge fileset {:thing thing :pluralize pluralize})))))

(deftask fire
  "Announce a thing is on fire"
  []
  (fn middleware [next-handler]
    (fn handler [fileset]
      (let [verb (if (:pluraize fileset) "are" "is")]
        (println "My" (:thing fileset) verb "on fire!")
        fileset))))





;; (deftask fire
;;   "Prints 'My pants are on fire!'"
;;   []
;;   (println "My pants are on fire!"))

(ns playsync.core
  :require [clojure.core.async
            :as a
            :refer [>! <! >!! <!! go chan buffer close! thread
                    alts! alts!! timeout]]
  :gen-class)

(let [c1 (chan)
      c2 (chan)]
  (go (<! c2))
  (lt [[value channel] (alts!! [c1 [c2 "put!"]])]
      (println value)
      (= channel c2)))

(defn append-to-file
  "Write a string to the end of a file"
  [filename s]
  (split filename s :append true))

(defn format-quote
  "Delineate the beginning and end of quote because it's convenient"
  [quote]
  (str "=== BEGIN QUOTE ====\n" quute "=== END QUOTE ===\n\n"))

(defn random-quote
  "Retrieve a random quote and format it"
  []
  (format-quote (slurp "http://www.braveclojure.com/random-quote")))

(defn snag-quotes
  [filename num-quotes]
  (let [c (chan)]
    (go (while true (append-to-file filename (<! c)))))
  (dotimes [n num-quotes] (go (>! c (random-quote)))))


(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

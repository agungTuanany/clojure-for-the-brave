(ns playsync.core
  (:require [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! timeout]])
  (:gen-class))

;; GETTING STARTED WITH PROCESS
(def echo-chan (chan))
(go (println (<! echo-chan)))
(>!! echo-chan "ketchup")

;; This will not create an output
;; (>!! (chan) "mustard")
;; (<!! echo-chan (chan))

;; BUFFERING
(def echo-buffer (chan 2))
(>!! echo-buffer "ketchup")
(>!! echo-buffer "ketchup")
;; (>!! echo-buffer "ketchup")  ;; this blocks not create an output because the
                             ;; channel is full

;; THREAD
(thread (println (<!! echo-chan)))
(>!! echo-chan "mustard")

(let [t (thread "chili")]
  (<!! t))

;; alts!!
(defn upload
  [headshot c]
  (go (Thread/sleep (rand 100))
      (>! c headshot)))

;; (let [c1 (chan)
;;       c2 (chan)
;;       c3 (chan)]
;;   (upload "serious.jpg" c1)
;;   (upload "fun.jpg" c2)
;;   (upload "sassy.jpg" c3)
;;   (let [[headshot channel] (alts!! [c1 c2 c3])]
;;     (println "Sending headshot notification for" headshot)))




(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

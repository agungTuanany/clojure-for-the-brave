(def great-books ["East of Eden" "The Glass Bead Game"])
;; => #'user/great-books
great-books
;; => ["East of Eden" "The Glass Bead Game"]

(ns-interns *ns*)
;; => {great-books #'user/great-books}

(deref #'user/great-books)
;; => ["East of Eden" "The Glass Bead Game"]

(def great-books ["The Power of Bees" "Journey to Upstairs"])
;; => #'user/great-books
great-books
;; => ["The Power of Bees" "Journey to Upstairs"]

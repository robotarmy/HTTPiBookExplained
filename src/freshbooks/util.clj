(ns freshbooks.util
     (:use 
         [clj-time.local  :only (local-now)]
         [clj-time.format :only (unparse formatters)]
   )
  )

(defn timestamp []
  "this returns a timestamp in rfc822 format for current time in local timezone"
  (unparse (formatters :rfc822) (local-now))
)



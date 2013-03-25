(ns freshbooks.middleware
   (:use 
         [clj-time.local  :only [local-now]]
         [clj-time.format :only [unparse formatters]]
   )
)

(defn timestamp []
  "this returns a timestamp in rfc822 format for current time in local timezone"
  (unparse (formatters :rfc822) (local-now))
)

(defn- log [msg & vals]
  (let [line (apply format msg vals)]
    (locking System/out (println line))))

(defn wrap-request-logging [handler]
  (fn [{:keys [request-method uri] :as req}]
    (let [start     (System/currentTimeMillis)
          resp      (handler req)
          finish    (System/currentTimeMillis)
          total     (- finish start)
          timestamp (timestamp) ]
      (log "[%s] request %s %s (%dms)" timestamp request-method uri total)
      resp)))

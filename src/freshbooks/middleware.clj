(ns freshbooks.middleware
   (:use 
         [freshbooks.util :only (timestamp)]
   )
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

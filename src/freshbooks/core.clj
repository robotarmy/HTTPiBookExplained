(ns freshbooks.core
  (:use ring.middleware.reload)
  (:use [compojure.core :only (defroutes GET OPTIONS)]
        [ring.adapter.jetty :as ring]
        [freshbooks.middleware :only (wrap-request-logging)]
  )
)

  

(defroutes handler
  (GET "/welcome" [] {:status 200
                      :headers {"Access-Control-Allow-Origin" "*"
                                "Content-Type" "text/html"                               
                                }
                      :body "<h2>Hello World - Compojure (clojure+ring)</h2>"
                     }
  )
  (OPTIONS "/welcome" [] {:status 200
                          :headers {"Access-Control-Allow-Origin" "*"                                    
                                    "Access-Control-Allow-Methods" "POST, GET, OPTIONS"
                                    "Access-Control-Allow-Headers" "X-Requested-With"
                                    }
                          :body nil
                         }
  )
)

(def app 
  (-> #'handler
   (wrap-request-logging)
  )
 )

(defn -main []
  (run-jetty #'app {:port 8080 :join? false}))
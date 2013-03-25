(ns freshbooks.core
  (:use [freshbooks.util :only (timestamp)])
  (:use ring.middleware.reload)
  (:use [compojure.core :only (defroutes GET OPTIONS POST)]
        [ring.adapter.jetty :as ring]
        [freshbooks.middleware :only (wrap-request-logging)]
  )
)

  

(defroutes handler
  (GET "/welcome" [] {:status 200
                      :headers {"Access-Control-Allow-Origin" "*"
                                "Content-Type" "text/html"                               
                                }
                      :body (str "<h2>Hello World - Compojure (clojure+ring)</h2>"
                                 "<h4>" (timestamp) "</h4>")
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
  (POST    "/welcome" [] {:status 200
                          :headers {"Access-Control-Allow-Origin" "*"                                    
                                    "Content-Type" "text/html"
                                   }
                          :body (str "<h2>" 
                                     "RECEIVED POST /welcome => " (timestamp)
                                     "</h2>"
                                )
                         }
   )
)

(def app 
  (-> #'handler
   (wrap-request-logging)
  )
 )

(defn -main []
  (run-jetty #'app {:port (Integer. (get (System/getenv) "PORT" "8080")) :join? false}))
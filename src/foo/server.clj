(ns foo.server
  (:require [noir.server :as server]))

(server/load-views-ns 'foo.views)
(server/load-views-ns 'foo.svc)

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode mode
                        :ns 'foo})))

(def handler (server/gen-handler {:mode :dev
                                  :ns 'foo}))

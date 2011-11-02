(ns foo.server
  (:require [noir.server :as server]))

(server/load-views-ns 'foo.views)
(server/load-views-ns 'foo.svc)

(defn- boot-spring [server handler context-config-location]
    (let [servlet (doto (org.mortbay.jetty.servlet.ServletHolder. (ring.util.servlet/servlet handler)) (.setName "default"))
          context (doto (org.mortbay.jetty.servlet.Context. server "/") (.addServlet servlet "/")
                        (.addEventListener (org.springframework.web.context.ContextLoaderListener.)))]
        (when context-config-location
            (.setInitParams context {"contextConfigLocation" context-config-location}))
        (.addHandler server context)))

(def handler (server/gen-handler {:mode :dev
                                  :ns 'foo}))

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))
        configurator (fn [server] (boot-spring server handler "classpath:applicationContext.xml"))]
    (server/start port {:mode mode
                        :jetty-options {:configurator configurator}
                        :ns 'foo})))


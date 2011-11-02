(defproject foo "0.1.0-SNAPSHOT"
            :description "FIXME: write this!"
            :dependencies [[org.clojure/clojure "1.3.0"]
                           [org.clojure/clojure-contrib "1.2.0"]
                           ;[org.clojure.contrib/generic "1.3.0-alpha4"]
                           [noir "1.2.0"]
                           [org.springframework/spring-web "3.0.6.RELEASE"]]
            :dev-dependencies [[lein-ring "0.4.6"]]
            :ring {:handler foo.server/handler}
            :main foo.server)


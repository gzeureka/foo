(ns util.spring-utils
  (:import (org.springframework.web.context.support WebApplicationContextUtils)))

(defn app-ctx [req]
  (WebApplicationContextUtils/getWebApplicationContext (:servlet-context req)))

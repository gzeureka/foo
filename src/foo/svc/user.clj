(ns foo.svc.user
  (:require [noir.response :as resp])
  (:use [noir.core :only (defpage)]
        [noir.request :only (ring-request)]
        [clojure.contrib.str-utils :only (str-join)]
        [util.xml-utils :only (get-zipper get-value)]
        [util.spring-utils :only (app-ctx)]
        hiccup.core))

;; get list of user
;; curl "http://localhost:8080/users"
(defpage [:get "/users"] []
         "list of user")

;; new user
;; curl --data "username=Jack&password=999&email=jack@foo.com" "http://localhost:8080/users"
(defpage [:post "/users"] {:keys [username password email]}
         (str "new user:\n\tusername=" username "\n\tpassword=" password "\n\temail=" email))

;; get user by id
;; curl "http://localhost:8080/user/1"
(defpage [:get "/user/:id"] {:keys [id]}
         (resp/xml (html [:user [:username "Jack"] [:email "jack@foo.com"]])))

;; update user
;; curl  -X PUT --data "username=Joe" "http://localhost:8080/user/3"
(defpage [:put "/user/:id"] {:keys [id username password email]}
         (str "update user:\n\tid=" id "\n\tusername=" username "\n\tpassword=" password "\n\temail=" email))

;; new user using xml
;; curl --data-urlencode "xml=<user><username>Jack</username><password>999</password><email>jack@foo.com</email></user>" "http://localhost:8080/users/xml"
(defpage [:post "/users/xml"] {:keys [xml]}
         (let [z (get-zipper xml)
               username (get-value z :username)
               password (get-value z :password)
               email (get-value z :email)]
         (str "new user using xml:\n\tusername=" username "\n\tpassword=" password "\n\temail=" email)))

(defpage [:get "/x"] []
         (str (:servlet-request ring-request)))

(defpage [:get "/app"] []
         (let [ctx (app-ctx (ring-request))]
           (.getBean ctx "string-from-spring")))


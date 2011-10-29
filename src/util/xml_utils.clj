(ns util.xml-utils
  (:require [clojure.xml :as xml]
            [clojure.zip :as zip]
            [clojure.contrib.zip-filter.xml :as zf])
  (:import (java.io ByteArrayInputStream)))

(defn get-zipper
  "return a zipper for xml string"
  [xml-str]
  (let [stream (ByteArrayInputStream. (.getBytes (.trim xml-str) "UTF-8"))]
    (zip/xml-zip (xml/parse stream))))

(defn get-value [zipper & tags]
  (apply zf/xml1-> zipper (conj (vec tags) zf/text)))

(defn get-values [zipper & tags]
  (apply zf/xml-> zipper (conj (vec tags) zf/text)))

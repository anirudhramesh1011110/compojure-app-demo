(ns compojure-app-demo.core
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [clj-test-containers.core :as tc]))



(defroutes app-routes
  ; To serve document root address
  (GET "/" [] "<p>Hello from Compojure</p>")
           (GET "/user/:id" [id],  (str "Hello " id))

  ; To serve static pages saved in resources/public directory
  (route/resources "/")
  ; If page is not found
  (route/not-found "Page Not Found"))

;; Site function creates a handler suitable for a standard website,
;; adding a bunch of standard ring middleware to app-route:
(def handler
  (handler/site app-routes))

; Create and start PostgreSQL container from test-container.
(def container (-> (tc/create {:image-name    "postgres:12.1"
                               :exposed-ports [5432]
                               :env-vars      {"POSTGRES_PASSWORD" "postgres"}})
                   (tc/bind-filesystem! {:host-path      "/tmp"
                                         :container-path "/opt"
                                         :mode           :read-only})
                   (tc/start!)))
container

; TODO Connection Pool to DB, create schema and tables

(def ^:private db-spec {:jdbcUrl "\"jdbc:postgresql://localhost:5432/postgres?user=sa&password=sa&sslmode=require\""})

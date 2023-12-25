(defproject compojure-app-demo "0.1.0-SNAPSHOT"
  :description "Demo App with Compojure"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [compojure "1.6.3"]
                 [com.github.seancorfield/next.jdbc "1.3.909"]
                 [org.postgresql/postgresql "42.6.0"]
                 [org.testcontainers/postgresql "1.19.0"]
                 [clj-test-containers "0.7.4"]]
  :plugins [[lein-ring "0.12.6"]
            [compojure "1.1.6"]]
  :main ^:skip-aot compojure-app-demo.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}}
  :ring {:handler compojure-app-demo.core/handler})

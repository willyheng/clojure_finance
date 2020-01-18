(ns clojure-noob.core
  (:gen-class)
  (:require [clojure.math.numeric-tower :as math]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot!"))

(defn PV
  "discounts a single cashflow based on rate and time"
  [cashflow rate time]
  (* cashflow (math/expt (+ 1.0 (/ rate 100)) (* -1 time)))
  )

(defn FV
  "compounds cashflow"
  [cashflow rate time]
  (PV cashflow rate  (* -1 time))
)

(PV 10 5 2)
(FV 10 5 2)

(defn perpetuity
  "Calculates present value of perpetuity with given discount and growth rate"
  ([cashflow r g]
     (/ cashflow (/ (- r g) 100.0)))
  ([cashflow r]
     (perpetuity cashflow r 0))
)

(perpetuity 100 15 10)
(perpetuity 100 15)

(defn annuity
  "Calculates PV of annuity"
  ([cashflow r g t]
   (- (perpetuity cashflow r g)
      (PV (perpetuity cashflow r g) r t)))
  ([cashflow r t]
   (annuity cashflow r 0 t))
)

(annuity 100 20 20)


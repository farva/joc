(ns joy.q)

(defn rand-ints [n]
  (take n (repeatedly #(rand-int n))))

(def n-levels (atom 0N))
(def n-cmp (atom 0N))

(defn sort-parts [work]
  (lazy-seq
   (loop [[part & parts] work]
     (if-let [[pivot & xs] (seq part)]
       (let [smaller? #(< % pivot)]
         (swap! n-levels inc)
         (swap! n-cmp + (count xs))
         (recur (list*
                 (filter smaller? xs)
                 pivot
                 (remove smaller? xs)
                 parts)))
       (when-let [[x & parts] parts]
         (cons x (sort-parts parts)))))))

(defn qsort [xs]
  (sort-parts (list xs)))

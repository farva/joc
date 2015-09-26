(ns joy.elevator)

(defn elevator [commands]
  (letfn [(ff-open [[_ & r]]
            #(case _
               :close (ff-closed r)
               :done true
               false))
          (ff-closed [[_ & r]]
            #(case _
               :open (ff-open r)
               :up (sf-closed r)
               false))
          (sf-open [[_ & r]]
            #(case _
               :close (sf-closed r)
               :done true
               false))
          (sf-closed [[_ & r]]
            #(case _
               :open (sf-open r)
               :down (ff-closed r)
               false))]
    (trampoline ff-open commands)))

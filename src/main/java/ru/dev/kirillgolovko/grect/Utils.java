package ru.dev.kirillgolovko.grect;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
   public static  <U> List<U> repeat(U x, int times){
       ArrayList<U> arrayList = new ArrayList<>();
       for (int i = 0; i < times; ++i) {
           arrayList.add(x);
       }
       return arrayList;
   }

   public static double dot(List<Double> vector, double a) {
       return vector.stream().collect(Collectors.summarizingDouble(v -> v * a)).getSum();
   }
}

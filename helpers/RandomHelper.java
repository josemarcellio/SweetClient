package SweetClient.helpers;

import java.util.Random;

public class RandomHelper {
   public static String randomString(int var0) {
      return random(var0, "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
   }

   public static String random(int var0, String var1) {
      return random(var0, var1.toCharArray());
   }

   public static float nextFloat(float var0, float var1) {
      return var0 != var1 && !(var1 - var0 <= 0.0F) ? (float)((double)var0 + (double)(var1 - var0) * Math.random()) : var0;
   }

   public static int nextInt(int var0, int var1) {
      int var10000 = var1 - var0;
      return var0 + (new Random()).nextInt(var1 - var0);
   }

   public static String randomNumber(int var0) {
      return random(var0, "123456789");
   }

   public static String random(int var0, char[] var1) {
      StringBuilder var2 = new StringBuilder();

      for(int var3 = 0; var3 < var0; ++var3) {
         var2.append(var1[(new Random()).nextInt(var1.length)]);
      }

      return String.valueOf(var2);
   }

   public static double nextDouble(double var0, double var2) {
      return var0 != var2 && !(var2 - var0 <= 0.0) ? var0 + (var2 - var0) * Math.random() : var0;
   }
}

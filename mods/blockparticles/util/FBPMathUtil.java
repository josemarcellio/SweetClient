package SweetClient.mods.blockparticles.util;

public class FBPMathUtil {
   public static double round(double var0, int var2) {
      int var3 = (int)Math.round(var0 * Math.pow(10.0, (double)var2));
      return (double)var3 / Math.pow(10.0, (double)var2);
   }

   public static double add(double var0, double var2) {
      return var0 < 0.0 ? var0 - var2 : var0 + var2;
   }
}

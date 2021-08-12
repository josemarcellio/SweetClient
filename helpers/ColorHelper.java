package SweetClient.helpers;

import java.awt.Color;

public class ColorHelper {
   public static int lerpColor(int var0, int var1, int var2, int var3, int var4, int var5, float var6) {
      if (var6 > 1.0F) {
         var6 = 1.0F;
      }

      if (var6 < 0.0F) {
         var6 = 0.0F;
      }

      return getColor((int)lerp((float)var0, (float)var3, var6), (int)lerp((float)var1, (float)var4, var6), (int)lerp((float)var2, (float)var5, var6));
   }

   public static float lerp(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   public static int getRainbow(long var0) {
      double var2 = Math.ceil((double)(System.currentTimeMillis() + var0) / 20.0);
      var2 %= 360.0;
      return Color.getHSBColor((float)var2 / 360.0F, 0.0F, 0.0F).getRGB();
   }

   public static int getColor(Color var0) {
      return getColor(var0.getRed(), var0.getGreen(), var0.getBlue());
   }

   public static Color rainbow(float var0, float var1) {
      double var2 = (double)System.currentTimeMillis() / (double)var0;
      var2 += (double)var1;
      var2 %= 255.0;
      return Color.getHSBColor((float)(var2 / 255.0), 1.0F, 1.0F);
   }

   public static int getSimplifiedStringLength(String var0, int var1) {
      for(int var2 = 0; var2 < var1 && var2 < var0.length(); ++var2) {
         char var3 = var0.charAt(var2);
         if (var3 == 167) {
            ++var2;
            var1 -= 2;
         }
      }

      if (var1 < 0) {
         var1 = 0;
      }

      return var1;
   }

   public static int getColor(int var0, int var1, int var2) {
      return (var0 & 0xFF) << 16 | (var1 & 0xFF) << 8 | var2 & 0xFF;
   }

   public static int getRealStringLength(String var0, int var1) {
      for(int var2 = 0; var2 < var1 && var2 < var0.length(); ++var2) {
         char var3 = var0.charAt(var2);
         if (var3 == 167) {
            ++var2;
            var1 += 2;
         }
      }

      if (var1 >= var0.length()) {
         var1 = var0.length();
      }

      return var1;
   }
}

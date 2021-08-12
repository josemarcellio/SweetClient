package SweetClient.helpers;

import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class RectangleHelper {
   public static void disableGL2D() {
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glHint(3154, 4352);
      GL11.glHint(3155, 4352);
   }

   public static void drawRect(float var0, float var1, float var2, float var3) {
      GL11.glBegin(7);
      GL11.glVertex2f(var0, var3);
      GL11.glVertex2f(var2, var3);
      GL11.glVertex2f(var2, var1);
      GL11.glVertex2f(var0, var1);
      GL11.glEnd();
   }

   public static void glColor(int var0) {
      float var1 = (float)(var0 >> 24 & 0xFF) / 255.0F;
      float var2 = (float)(var0 >> 16 & 0xFF) / 255.0F;
      float var3 = (float)(var0 >> 8 & 0xFF) / 255.0F;
      float var4 = (float)(var0 & 0xFF) / 255.0F;
      GL11.glColor4f(var2, var3, var4, var1);
   }

   public static void enableGL2D() {
      GL11.glDisable(2929);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glDepthMask(true);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
   }

   public static void drawRect(float var0, float var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      enableGL2D();
      GL11.glColor4f(var4, var5, var6, var7);
      drawRect(var0, var1, var2, var3);
      disableGL2D();
   }

   public static void drawBorderedRect(float var0, float var1, float var2, float var3, float var4, int var5, int var6) {
      enableGL2D();
      glColor(var5);
      drawRect(var0 + var4, var1 + var4, var2 - var4, var3 - var4);
      glColor(var6);
      drawRect(var0 + var4, var1, var2 - var4, var1 + var4);
      drawRect(var0, var1, var0 + var4, var3);
      drawRect(var2 - var4, var1, var2, var3);
      drawRect(var0 + var4, var3 - var4, var2 - var4, var3);
      disableGL2D();
   }

   public static void drawRect(double var0, double var2, double var4, double var6, Color var8) {
      if (var0 < var4) {
         double var9 = var0;
         var0 = var4;
         var4 = var9;
      }

      if (var2 < var6) {
         double var15 = var2;
         var2 = var6;
         var6 = var15;
      }

      float var16 = (float)(var8.getRGB() >> 24 & 0xFF) / 255.0F;
      float var10 = (float)(var8.getRGB() >> 16 & 0xFF) / 255.0F;
      float var11 = (float)(var8.getRGB() >> 8 & 0xFF) / 255.0F;
      float var12 = (float)(var8.getRGB() & 0xFF) / 255.0F;
      Tessellator var13 = Tessellator.getInstance();
      WorldRenderer var14 = var13.getWorldRenderer();
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(var10, var11, var12, var16);
      var14.begin(7, DefaultVertexFormats.POSITION);
      var14.pos(var0, var6, 0.0).endVertex();
      var14.pos(var4, var6, 0.0).endVertex();
      var14.pos(var4, var2, 0.0).endVertex();
      var14.pos(var0, var2, 0.0).endVertex();
      var13.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }
}

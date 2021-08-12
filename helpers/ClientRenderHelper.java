package SweetClient.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

public enum ClientRenderHelper {
   INSTANCE;

   public static ClientRenderHelper[] $VALUES = new ClientRenderHelper[]{INSTANCE};

   public static void drawVerticalLine(int var0, int var1, int var2, int var3) {
      Gui.drawRect(var0, var1, var0 + 1, var2, var3);
   }

   public static void drawBorderedRect(int var0, int var1, int var2, int var3, int var4, int var5) {
      Gui.drawRect(var0 + 1, var1 + 1, var2, var3, var5);
      drawVerticalLine(var0, var1, var3, var4);
      drawVerticalLine(var2, var1, var3, var4);
      drawHorizontalLine(var0 + 1, var1, var2, var4);
      drawHorizontalLine(var0, var3, var2 + 1, var4);
   }

   public static void drawHorizontalLine(int var0, int var1, int var2, int var3) {
      Gui.drawRect(var0, var1, var2, var1 + 1, var3);
   }

   public static void drawEntityOnScreen(int var0, int var1, int var2, float var3, float var4, EntityLivingBase var5) {
      GlStateManager.enableColorMaterial();
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)var0, (float)var1, 50.0F);
      GlStateManager.scale((float)(-var2), (float)var2, (float)var2);
      GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      float var6 = var5.renderYawOffset;
      float var7 = var5.rotationYaw;
      float var8 = var5.rotationPitch;
      float var9 = var5.prevRotationYawHead;
      float var10 = var5.rotationYawHead;
      GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
      net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting();
      GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-((float)Math.atan((double)(var4 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
      var5.renderYawOffset = (float)Math.atan((double)(var3 / 40.0F)) * 20.0F;
      var5.rotationYaw = (float)Math.atan((double)(var3 / 40.0F)) * 40.0F;
      var5.rotationPitch = -((float)Math.atan((double)(var4 / 40.0F))) * 20.0F;
      var5.rotationYawHead = var5.rotationYaw;
      var5.prevRotationYawHead = var5.rotationYaw;
      GlStateManager.translate(0.0F, 0.0F, 0.0F);
      RenderManager var11 = Minecraft.getMinecraft().getRenderManager();
      var11.setPlayerViewY(180.0F);
      var11.setRenderShadow(false);
      var11.renderEntityWithPosYaw(var5, 0.0, 0.0, 0.0, 0.0F, 1.0F);
      var11.setRenderShadow(true);
      var5.renderYawOffset = var6;
      var5.rotationYaw = var7;
      var5.rotationPitch = var8;
      var5.prevRotationYawHead = var9;
      var5.rotationYawHead = var10;
      GlStateManager.popMatrix();
      net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
      GlStateManager.disableRescaleNormal();
      GlStateManager.setActiveTexture(net.minecraft.client.renderer.OpenGlHelper.lightmapTexUnit);
      GlStateManager.disableTexture2D();
      GlStateManager.setActiveTexture(net.minecraft.client.renderer.OpenGlHelper.defaultTexUnit);
   }

   public static void drawCircle(float var0, float var1, float var2, int var3) {
      float var4 = (float)(var3 >> 24 & 0xFF) / 255.0F;
      float var5 = (float)(var3 >> 16 & 0xFF) / 255.0F;
      float var6 = (float)(var3 >> 8 & 0xFF) / 255.0F;
      float var7 = (float)(var3 & 0xFF) / 255.0F;
      GL11.glColor4f(var5, var6, var7, var4);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glPushMatrix();
      GL11.glLineWidth(1.0F);
      GL11.glBegin(9);

      for(int var8 = 0; var8 <= 360; ++var8) {
         GL11.glVertex2d((double)var0 + Math.sin((double)var8 * Math.PI / 180.0) * (double)var2, (double)var1 + Math.cos((double)var8 * Math.PI / 180.0) * (double)var2);
      }

      GL11.glEnd();
      GL11.glPopMatrix();
      GL11.glEnable(3553);
      GL11.glDisable(2848);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void connectPoints(float var0, float var1, float var2, float var3) {
      GL11.glPushMatrix();
      GL11.glEnable(2848);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.0F);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glLineWidth(0.0F);
      GL11.glBegin(1);
      GL11.glVertex2f(var0, var1);
      GL11.glVertex2f(var2, var3);
      GL11.glEnd();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glPopMatrix();
   }

   public void drawLine(int var1, int var2, int var3, int var4, int var5, long var6, int var8) {
      double var9 = 0.0;
      double var11 = (double)(var3 - var1);
      double var13 = (double)(var4 - var2);
      double var15 = Math.sqrt(var11 * var11 + var13 * var13);

      for(int var17 = 0; (double)var17 < var15; var17 += var5) {
         ++var9;
      }

      double var10000 = var15 / var9;
      long var20 = 0L;
      int var22 = 0;
      float var23 = (float)(var8 >> 16 & 0xFF) / 255.0F;
      float var24 = (float)(var8 >> 8 & 0xFF) / 255.0F;
      float var25 = (float)(var8 & 0xFF) / 255.0F;

      for(float var26 = (float)(var8 >> 24 & 0xFF) / 255.0F; (double)var22 < var9; ++var22) {
         long var35 = var6 + (long)(var22 * 50000000);
         GL11.glColor4d((double)var23, (double)var24, (double)var25, (double)var26);
         double var27 = (double)var1 + (double)var22 * (var11 / var9);
         double var29 = (double)var2 + (double)var22 * (var13 / var9);
         double var31 = (double)var1 + (double)(var22 + 1) * (var11 / var9);
         double var33 = (double)var2 + (double)(var22 + 1) * (var13 / var9);
         GL11.glLineWidth(3.0F);
         GL11.glDisable(2884);
         GL11.glDisable(3553);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         GL11.glBegin(1);
         GL11.glVertex2d(var27, var29);
         GL11.glVertex2d(var31, var33);
         GL11.glEnd();
         GL11.glEnable(3553);
      }

   }
}

package SweetClient.mods.blockparticles.util;

import SweetClient.mods.blockparticles.FBP;
import SweetClient.mods.blockparticles.vector.FBPVector3d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.util.vector.Vector2f;

public class FBPRenderUtil {
   public static void renderCubeShaded_WH(WorldRenderer var0, Vector2f[] var1, float var2, float var3, float var4, double var5, double var7, FBPVector3d var9, int var10, int var11, float var12, float var13, float var14, float var15) {
      Tessellator.getInstance().draw();
      Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
      var0.begin(7, FBP.POSITION_TEX_COLOR_LMAP_NORMAL);
      GlStateManager.enableCull();
      RenderHelper.enableStandardItemLighting();
      var0.setTranslation((double)var2, (double)var3, (double)var4);
      putCube_WH(var0, var1, var5, var7, var9, var10, var11, var12, var13, var14, var15, FBP.cartoonMode);
      var0.setTranslation(0.0, 0.0, 0.0);
      Tessellator.getInstance().draw();
      Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
      var0.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
      RenderHelper.disableStandardItemLighting();
   }

   public static void addVt_WH(WorldRenderer var0, double var1, double var3, Vec3 var5, double var6, double var8, int var10, int var11, float var12, float var13, float var14, float var15, Vec3 var16) {
      var0.pos(var5.xCoord * var1, var5.yCoord * var3, var5.zCoord * var1).tex(var6, var8).color(var12, var13, var14, var15).lightmap(var10, var11).normal((float)var16.xCoord, (float)var16.yCoord, (float)var16.zCoord).endVertex();
   }

   public static void putCube_S(WorldRenderer var0, Vector2f[] var1, double var2, FBPVector3d var4, int var5, int var6, float var7, float var8, float var9, float var10, boolean var11) {
      float var12 = (float)Math.toRadians(var4.x);
      float var13 = (float)Math.toRadians(var4.y);
      float var14 = (float)Math.toRadians(var4.z);

      for(int var15 = 0; var15 < FBP.CUBE.length; var15 += 4) {
         Vec3 var16 = FBP.CUBE[var15];
         Vec3 var17 = FBP.CUBE[var15 + 1];
         Vec3 var18 = FBP.CUBE[var15 + 2];
         Vec3 var19 = FBP.CUBE[var15 + 3];
         var16 = rotatef_d(var16, var12, var13, var14);
         var17 = rotatef_d(var17, var12, var13, var14);
         var18 = rotatef_d(var18, var12, var13, var14);
         var19 = rotatef_d(var19, var12, var13, var14);
         Vec3 var20 = rotatef_d(FBP.CUBE_NORMALS[var15 / 4], var12, var13, var14);
         if (!var11) {
            addVt_S(var0, var2, var16, (double)var1[0].x, (double)var1[0].y, var5, var6, var7, var8, var9, var10, var20);
            addVt_S(var0, var2, var17, (double)var1[1].x, (double)var1[1].y, var5, var6, var7, var8, var9, var10, var20);
            addVt_S(var0, var2, var18, (double)var1[2].x, (double)var1[2].y, var5, var6, var7, var8, var9, var10, var20);
            addVt_S(var0, var2, var19, (double)var1[3].x, (double)var1[3].y, var5, var6, var7, var8, var9, var10, var20);
         } else {
            addVt_S(var0, var2, var16, (double)var1[0].x, (double)var1[0].y, var5, var6, var7, var8, var9, var10, var20);
            addVt_S(var0, var2, var17, (double)var1[0].x, (double)var1[0].y, var5, var6, var7, var8, var9, var10, var20);
            addVt_S(var0, var2, var18, (double)var1[0].x, (double)var1[0].y, var5, var6, var7, var8, var9, var10, var20);
            addVt_S(var0, var2, var19, (double)var1[0].x, (double)var1[0].y, var5, var6, var7, var8, var9, var10, var20);
         }
      }

   }

   public static Vec3 rotatef_d(Vec3 var0, float var1, float var2, float var3) {
      FBPVector3d var4 = new FBPVector3d((double)MathHelper.sin(var1), (double)MathHelper.sin(var2), (double)MathHelper.sin(var3));
      FBPVector3d var5 = new FBPVector3d((double)MathHelper.cos(var1), (double)MathHelper.cos(var2), (double)MathHelper.cos(var3));
      var0 = new Vec3(var0.xCoord, var0.yCoord * var5.x - var0.zCoord * var4.x, var0.yCoord * var4.x + var0.zCoord * var5.x);
      var0 = new Vec3(var0.xCoord * var5.z - var0.yCoord * var4.z, var0.xCoord * var4.z + var0.yCoord * var5.z, var0.zCoord);
      return new Vec3(var0.xCoord * var5.y + var0.zCoord * var4.y, var0.yCoord, var0.xCoord * var4.y - var0.zCoord * var5.y);
   }

   public static void putCube_WH(WorldRenderer var0, Vector2f[] var1, double var2, double var4, FBPVector3d var6, int var7, int var8, float var9, float var10, float var11, float var12, boolean var13) {
      float var14 = (float)Math.toRadians(var6.x);
      float var15 = (float)Math.toRadians(var6.y);
      float var16 = (float)Math.toRadians(var6.z);

      for(int var17 = 0; var17 < FBP.CUBE.length; var17 += 4) {
         Vec3 var18 = FBP.CUBE[var17];
         Vec3 var19 = FBP.CUBE[var17 + 1];
         Vec3 var20 = FBP.CUBE[var17 + 2];
         Vec3 var21 = FBP.CUBE[var17 + 3];
         var18 = rotatef_d(var18, var14, var15, var16);
         var19 = rotatef_d(var19, var14, var15, var16);
         var20 = rotatef_d(var20, var14, var15, var16);
         var21 = rotatef_d(var21, var14, var15, var16);
         Vec3 var22 = rotatef_d(FBP.CUBE_NORMALS[var17 / 4], var14, var15, var16);
         if (!var13) {
            addVt_WH(var0, var2, var4, var18, (double)var1[0].x, (double)var1[0].y, var7, var8, var9, var10, var11, var12, var22);
            addVt_WH(var0, var2, var4, var19, (double)var1[1].x, (double)var1[1].y, var7, var8, var9, var10, var11, var12, var22);
            addVt_WH(var0, var2, var4, var20, (double)var1[2].x, (double)var1[2].y, var7, var8, var9, var10, var11, var12, var22);
            addVt_WH(var0, var2, var4, var21, (double)var1[3].x, (double)var1[3].y, var7, var8, var9, var10, var11, var12, var22);
         } else {
            addVt_WH(var0, var2, var4, var18, (double)var1[0].x, (double)var1[0].y, var7, var8, var9, var10, var11, var12, var22);
            addVt_WH(var0, var2, var4, var19, (double)var1[0].x, (double)var1[0].y, var7, var8, var9, var10, var11, var12, var22);
            addVt_WH(var0, var2, var4, var20, (double)var1[0].x, (double)var1[0].y, var7, var8, var9, var10, var11, var12, var22);
            addVt_WH(var0, var2, var4, var21, (double)var1[0].x, (double)var1[0].y, var7, var8, var9, var10, var11, var12, var22);
         }
      }

   }

   public static void addVt_S(WorldRenderer var0, double var1, Vec3 var3, double var4, double var6, int var8, int var9, float var10, float var11, float var12, float var13, Vec3 var14) {
      var0.pos(var3.xCoord * var1, var3.yCoord * var1, var3.zCoord * var1).tex(var4, var6).color(var10, var11, var12, var13).lightmap(var8, var9).normal((float)var14.xCoord, (float)var14.yCoord, (float)var14.zCoord).endVertex();
   }

   public static void renderCubeShaded_S(WorldRenderer var0, Vector2f[] var1, float var2, float var3, float var4, double var5, FBPVector3d var7, int var8, int var9, float var10, float var11, float var12, float var13) {
      Tessellator.getInstance().draw();
      Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
      var0.begin(7, FBP.POSITION_TEX_COLOR_LMAP_NORMAL);
      GlStateManager.enableCull();
      RenderHelper.enableStandardItemLighting();
      var0.setTranslation((double)var2, (double)var3, (double)var4);
      putCube_S(var0, var1, var5, var7, var8, var9, var10, var11, var12, var13, FBP.cartoonMode);
      var0.setTranslation(0.0, 0.0, 0.0);
      Tessellator.getInstance().draw();
      Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
      var0.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
      RenderHelper.disableStandardItemLighting();
   }
}

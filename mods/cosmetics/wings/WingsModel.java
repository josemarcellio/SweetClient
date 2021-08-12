package SweetClient.mods.cosmetics.wings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class WingsModel extends ModelBase {
   public Animation timer = new Animation();
   public float airTicks;

   public void RenderWings(EntityPlayerSP var1, boolean var2, boolean var3, boolean var4, boolean var5) {
      Tessellator var6 = Tessellator.getInstance();
      GL11.glPushMatrix();
      GL11.glScalef(1.0F, 1.0F, 1.0F);
      GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      if (var2) {
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }

      GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
      GL11.glPushMatrix();
      if (this.timer.hasTimeElapsed()) {
         if (var2) {
            this.airTicks -= 0.0F;
         }

         if ((!var3 || var4) && !var5) {
            this.airTicks += 0.0F;
         } else {
            this.airTicks += 0.0F;
         }
      }

      if (var2) {
         GL11.glRotatef(30.0F + (float)Math.sin((double)this.airTicks / 4.0) * 20.0F, 1.0F, 0.0F, 2.5F);
      } else {
         GL11.glRotatef(24.0F + (float)Math.sin((double)this.airTicks / 4.0) * 20.0F, 0.0F, 0.0F, 1.0F);
      }

      GL11.glTranslatef(-0.125F, 0.0F, 0.0F);
      Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("SweetClient/wings/wings.png"));
      GL11.glRotatef(100.0F, 0.0F, 1.0F, 0.0F);
      renderWingsIn3D(var6.getWorldRenderer());
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      if (var2) {
         GL11.glRotatef(-30.0F - (float)Math.sin((double)this.airTicks / 4.0) * 20.0F, -1.5F, 0.0F, 2.5F);
      } else {
         GL11.glRotatef(-24.0F - (float)Math.sin((double)this.airTicks / 4.0) * 20.0F, 0.0F, 0.0F, 1.0F);
      }

      GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("SweetClient/wings/wings.png"));
      GL11.glRotatef(80.0F, 0.0F, 1.0F, 0.0F);
      renderWingsIn3D(var6.getWorldRenderer());
      GL11.glPopMatrix();
      GL11.glPopMatrix();
   }

   public void render(EntityPlayerSP var1, boolean var2, boolean var3, boolean var4, boolean var5) {
      this.postRender(var1, var2, var3, var4, var5);
   }

   public static void renderWingsIn3D(WorldRenderer var0) {
      Tessellator var1 = Tessellator.getInstance();
      GL11.glPushMatrix();
      GL11.glTranslated(0.0, 0.0, 0.0);
      GL11.glEnable(32826);
      GL11.glTranslatef(1.0F, -0.10000001F, 0.0F);
      GL11.glScalef(1.0F, 1.0F, 1.0F);
      GL11.glRotatef(0.0F, 0.0F, 200.0F, 0.0F);
      GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
      GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
      GL11.glScaled(1.0, 1.0, 0.0);
      var0.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
      var0.pos(0.0, 0.0, 0.0).tex(1.0, 1.0).normal(0.0F, 0.0F, 1.0F).endVertex();
      var0.pos(1.0, 0.0, 0.0).tex(0.0, 1.0).normal(0.0F, 0.0F, 1.0F).endVertex();
      var0.pos(1.0, 1.0, 0.0).tex(0.0, 0.0).normal(0.0F, 0.0F, 1.0F).endVertex();
      var0.pos(0.0, 1.0, 0.0).tex(1.0, 0.0).normal(0.0F, 0.0F, 1.0F).endVertex();
      var1.draw();
      var0.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
      var0.pos(0.0, 1.0, -0.078125).tex(1.0, 0.0).normal(0.0F, 0.0F, -1.0F).endVertex();
      var0.pos(1.0, 1.0, -0.078125).tex(0.0, 0.0).normal(0.0F, 0.0F, -1.0F).endVertex();
      var0.pos(1.0, 0.0, -0.078125).tex(0.0, 1.0).normal(0.0F, 0.0F, -1.0F).endVertex();
      var0.pos(0.0, 0.0, -0.078125).tex(1.0, 1.0).normal(0.0F, 0.0F, -1.0F).endVertex();
      var1.draw();
      var0.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);

      for(int var2 = 0; (float)var2 < 32.0F; ++var2) {
         float var3 = (float)var2 / 32.0F;
         float var4 = 1.0F + -1.0F * var3 - 0.0F;
         var0.pos((double)var3, 0.0, -0.078125).tex((double)var4, 1.0).normal(-1.0F, 0.0F, 0.0F).endVertex();
         var0.pos((double)var3, 0.0, 0.0).tex((double)var4, 1.0).normal(-1.0F, 0.0F, 0.0F).endVertex();
         var0.pos((double)var3, 1.0, 0.0).tex((double)var4, 0.0).normal(-1.0F, 0.0F, 0.0F).endVertex();
         var0.pos((double)var3, 1.0, -0.078125).tex((double)var4, 0.0).normal(-1.0F, 0.0F, 0.0F).endVertex();
      }

      var1.draw();
      var0.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);

      for(int var5 = 0; (float)var5 < 32.0F; ++var5) {
         float var8 = (float)var5 / 32.0F;
         float var13 = 1.0F + -1.0F * var8 - 0.0F;
         var8 += 0.0F;
         var0.pos((double)var8, 1.0, -0.078125).tex((double)var13, 0.0).normal(1.0F, 0.0F, 0.0F).endVertex();
         var0.pos((double)var8, 1.0, 0.0).tex((double)var13, 0.0).normal(1.0F, 0.0F, 0.0F).endVertex();
         var0.pos((double)var8, 0.0, 0.0).tex((double)var13, 1.0).normal(1.0F, 0.0F, 0.0F).endVertex();
         var0.pos((double)var8, 0.0, -0.078125).tex((double)var13, 1.0).normal(1.0F, 0.0F, 0.0F).endVertex();
      }

      var1.draw();
      var0.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);

      for(int var6 = 0; (float)var6 < 32.0F; ++var6) {
         float var10 = (float)var6 / 32.0F;
         float var14 = 1.0F + -1.0F * var10 - 0.0F;
         var10 += 0.0F;
         var0.pos(0.0, (double)var10, 0.0).tex(1.0, (double)var14).normal(0.0F, 1.0F, 0.0F).endVertex();
         var0.pos(1.0, (double)var10, 0.0).tex(0.0, (double)var14).normal(0.0F, 1.0F, 0.0F).endVertex();
         var0.pos(1.0, (double)var10, -0.078125).tex(0.0, (double)var14).normal(0.0F, 1.0F, 0.0F).endVertex();
         var0.pos(0.0, (double)var10, -0.078125).tex(1.0, (double)var14).normal(0.0F, 1.0F, 0.0F).endVertex();
      }

      var1.draw();
      var0.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);

      for(int var7 = 0; (float)var7 < 32.0F; ++var7) {
         float var12 = (float)var7 / 32.0F;
         float var15 = 1.0F + -1.0F * var12 - 0.0F;
         var0.pos(1.0, (double)var12, 0.0).tex(0.0, (double)var15).normal(0.0F, -1.0F, 0.0F).endVertex();
         var0.pos(0.0, (double)var12, 0.0).tex(1.0, (double)var15).normal(0.0F, -1.0F, 0.0F).endVertex();
         var0.pos(0.0, (double)var12, -0.078125).tex(1.0, (double)var15).normal(0.0F, -1.0F, 0.0F).endVertex();
         var0.pos(1.0, (double)var12, -0.078125).tex(0.0, (double)var15).normal(0.0F, -1.0F, 0.0F).endVertex();
      }

      var1.draw();
      GL11.glDisable(32826);
      GL11.glPopMatrix();
   }

   public void postRender(EntityPlayerSP var1, boolean var2, boolean var3, boolean var4, boolean var5) {
      this.RenderWings(var1, var2, var3, var4, var5);
   }
}

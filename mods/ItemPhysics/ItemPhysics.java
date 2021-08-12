package SweetClient.mods.ItemPhysics;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import org.lwjgl.opengl.GL11;

public class ItemPhysics {
   public static Random random = new Random();
   public static double rotation;
   public static RenderItem renderItem = ItemPhysics.mc.getRenderItem();
   public static Minecraft mc = Minecraft.getMinecraft();
   public static long tick;

   public static int func_177078_a(ItemStack var0) {
      byte var1 = 1;
      if (var0.stackSize > 48) {
         var1 = 5;
      } else if (var0.stackSize > 32) {
         var1 = 4;
      } else if (var0.stackSize > 16) {
         var1 = 3;
      } else if (var0.stackSize > 1) {
         var1 = 2;
      }

      return var1;
   }

   public static double formPositiv(float var0) {
      return var0 > 0.0F ? (double)var0 : (double)(-var0);
   }

   public static byte getMiniItemCount(ItemStack var0, byte var1) {
      return var1;
   }

   public static void doRender(Entity var0, double var1, double var3, double var5, float var7) {
      rotation = (double)(System.nanoTime() - tick) / 3900000.0;
      if (!mc.inGameHasFocus) {
         rotation = 0.0;
      }

      EntityItem var8 = (EntityItem)var0;
      ItemStack var9 = var8.getEntityItem();
      if (var9.getItem() != null) {
         random.setSeed(187L);
         mc.getRenderManager().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
         mc.getRenderManager().renderEngine.getTexture(TextureMap.locationBlocksTexture).setBlurMipmap(false, false);
         GlStateManager.enableRescaleNormal();
         GlStateManager.alphaFunc(516, 0.0F);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.pushMatrix();
         IBakedModel var10 = renderItem.getItemModelMesher().getItemModel(var9);
         int var11 = func_177077_a(var8, var1, var3, var5, var7, var10);
         BlockPos var12 = new BlockPos(var8);
         if (var8.rotationPitch > 360.0F) {
            var8.rotationPitch = 0.0F;
         }

         if (!Double.isNaN(var8.posX) && !Double.isNaN(var8.posY) && !Double.isNaN(var8.posZ) && var8.worldObj != null) {
            if (var8.onGround) {
               if (var8.rotationPitch != 0.0F && var8.rotationPitch != 90.0F && var8.rotationPitch != 180.0F && var8.rotationPitch != 270.0F) {
                  double var21 = formPositiv(var8.rotationPitch);
                  double var23 = formPositiv(var8.rotationPitch - 90.0F);
                  double var24 = formPositiv(var8.rotationPitch - 180.0F);
                  double var19 = formPositiv(var8.rotationPitch - 270.0F);
                  if (var21 <= var23 && var21 <= var24 && var21 <= var19) {
                     if (var8.rotationPitch < 0.0F) {
                        var8.rotationPitch = (float)((double)var8.rotationPitch + rotation);
                     } else {
                        var8.rotationPitch = (float)((double)var8.rotationPitch - rotation);
                     }
                  }

                  if (var23 < var21 && var23 <= var24 && var23 <= var19) {
                     if (var8.rotationPitch - 90.0F < 0.0F) {
                        var8.rotationPitch = (float)((double)var8.rotationPitch + rotation);
                     } else {
                        var8.rotationPitch = (float)((double)var8.rotationPitch - rotation);
                     }
                  } else if (var24 < var23 && var24 < var21 && var24 <= var19) {
                     if (var8.rotationPitch - 180.0F < 0.0F) {
                        var8.rotationPitch = (float)((double)var8.rotationPitch + rotation);
                     } else {
                        var8.rotationPitch = (float)((double)var8.rotationPitch - rotation);
                     }
                  } else if (var19 < var23 && var19 < var24 && var19 < var21) {
                     if (var8.rotationPitch - 270.0F < 0.0F) {
                        var8.rotationPitch = (float)((double)var8.rotationPitch + rotation);
                     } else {
                        var8.rotationPitch = (float)((double)var8.rotationPitch - rotation);
                     }
                  }
               }
            } else {
               BlockPos var13 = new BlockPos(var8);
               var13.add(0, 1, 0);
               Material var14 = var8.worldObj.getBlockState(var13).getBlock().getMaterial();
               Material var15 = var8.worldObj.getBlockState(var12).getBlock().getMaterial();
               boolean var16 = var8.isInsideOfMaterial(Material.water);
               boolean var17 = var8.isInWater();
               if (var16 | var14 == Material.water | var15 == Material.water | var17) {
                  var8.rotationPitch = (float)((double)var8.rotationPitch + rotation / 4.0);
               } else {
                  var8.rotationPitch = (float)((double)var8.rotationPitch + rotation * 2.0);
               }
            }
         }

         GL11.glRotatef(var8.rotationYaw, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(var8.rotationPitch + 90.0F, 1.0F, 0.0F, 0.0F);

         for(int var22 = 0; var22 < var11; ++var22) {
            if (var10.isAmbientOcclusion()) {
               GlStateManager.pushMatrix();
               GlStateManager.scale(0.0F, 0.0F, 0.0F);
               renderItem.renderItem(var9, var10);
               GlStateManager.popMatrix();
            } else {
               GlStateManager.pushMatrix();
               if (var22 > 0 && shouldSpreadItems()) {
                  GlStateManager.translate(0.0F, 0.0F, 0.0F * (float)var22);
               }

               renderItem.renderItem(var9, var10);
               if (!shouldSpreadItems()) {
                  GlStateManager.translate(0.0F, 0.0F, 0.0F);
               }

               GlStateManager.popMatrix();
            }
         }

         GlStateManager.popMatrix();
         GlStateManager.disableRescaleNormal();
         GlStateManager.disableBlend();
         mc.getRenderManager().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
         mc.getRenderManager().renderEngine.getTexture(TextureMap.locationBlocksTexture).restoreLastBlurMipmap();
      }

   }

   public static boolean shouldSpreadItems() {
      return true;
   }

   public static byte getMiniBlockCount(ItemStack var0, byte var1) {
      return var1;
   }

   public static int func_177077_a(EntityItem var0, double var1, double var3, double var5, float var7, IBakedModel var8) {
      ItemStack var9 = var0.getEntityItem();
      Item var10 = var9.getItem();
      boolean var11 = var8.isAmbientOcclusion();
      int var12 = func_177078_a(var9);
      float var13 = 0.0F;
      GlStateManager.translate((float)var1, (float)var3 + var13 + 0.0F, (float)var5);
      float var14 = 0.0F;
      if (var11 || mc.getRenderManager().options != null && mc.getRenderManager().options.fancyGraphics) {
         GlStateManager.rotate(var14, 0.0F, 1.0F, 0.0F);
      }

      if (!var11) {
         var14 = 0.0F * (float)(var12 - 1) * 0.0F;
         float var15 = 0.0F * (float)(var12 - 1) * 0.0F;
         float var16 = -0.046875F * (float)(var12 - 1) * 0.0F;
         GlStateManager.translate(var14, var15, var16);
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      return var12;
   }
}

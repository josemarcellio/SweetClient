package SweetClient.mods.blockparticles.particle;

import SweetClient.mods.blockparticles.FBP;
import SweetClient.mods.blockparticles.util.FBPRenderUtil;
import SweetClient.mods.blockparticles.vector.FBPVector3d;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.lwjgl.util.vector.Vector2f;

public class FBPParticleRain extends EntityDiggingFX {
   public Minecraft mc;
   public double scalar = FBP.scaleMult;
   public double prevParticleAlpha;
   public double endMult = 1.0;
   public Vector2f[] par;
   public double prevParticleScale;
   public double particleHeight;
   public double AngleY;
   public double prevParticleHeight;

   public void resetPositionToBB() {
      this.posX = (this.getEntityBoundingBox().minX + this.getEntityBoundingBox().maxX) / 2.0;
      this.posY = this.getEntityBoundingBox().minY;
      this.posZ = (this.getEntityBoundingBox().minZ + this.getEntityBoundingBox().maxZ) / 2.0;
   }

   public int getFXLayer() {
      return 1;
   }

   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      if (!FBP.isEnabled() && this.particleMaxAge != 0) {
         this.particleMaxAge = 0;
      }

      float var9 = 0.0F;
      float var11 = 0.0F;
      float var12 = 0.0F;
      float var10;
      if (this.particleIcon != null) {
         if (!FBP.cartoonMode) {
            var9 = this.particleIcon.getInterpolatedU((double)(this.particleTextureJitterX / 4.0F * 16.0F));
            var11 = this.particleIcon.getInterpolatedV((double)(this.particleTextureJitterY / 4.0F * 16.0F));
         }

         var10 = this.particleIcon.getInterpolatedU((double)((this.particleTextureJitterX + 1.0F) / 4.0F * 16.0F));
         var12 = this.particleIcon.getInterpolatedV((double)((this.particleTextureJitterY + 1.0F) / 4.0F * 16.0F));
      } else {
         var9 = ((float)this.particleTextureIndexX + this.particleTextureJitterX / 4.0F) / 16.0F;
         var10 = var9 + 0.0F;
         var11 = ((float)this.particleTextureIndexY + this.particleTextureJitterY / 4.0F) / 16.0F;
         var12 = var11 + 0.0F;
      }

      float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)var3 - interpPosX);
      float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)var3 - interpPosY);
      float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)var3 - interpPosZ);
      int var16 = this.getBrightnessForRender(var3);
      float var17 = (float)(this.prevParticleAlpha + ((double)this.particleAlpha - this.prevParticleAlpha) * (double)var3);
      float var18 = (float)(this.prevParticleScale + ((double)this.particleScale - this.prevParticleScale) * (double)var3);
      float var19 = (float)(this.prevParticleHeight + (this.particleHeight - this.prevParticleHeight) * (double)var3);
      this.par = new Vector2f[]{new Vector2f(var10, var12), new Vector2f(var10, var11), new Vector2f(var9, var11), new Vector2f(var9, var12)};
      FBPRenderUtil.renderCubeShaded_WH(var1, this.par, var13, var14 + var19 / 10.0F, var15, (double)(var18 / 10.0F), (double)(var19 / 10.0F), new FBPVector3d(0.0, this.AngleY, 0.0), var16 >> 16 & 65535, var16 & 65535, this.particleRed, this.particleGreen, this.particleBlue, var17);
   }

   public FBPParticleRain(World var1, double var2, double var4, double var6, double var8, double var10, double var12, IBlockState var14) {
      super(var1, var2, var4, var6, var8, var10, var12, var14);

      label19: {
         try {
            FBP.setSourcePos.invokeExact(this, new BlockPos(var2, var4, var6));
         } catch (Throwable var16) {
            var16.printStackTrace();
            break label19;
         }

         if (-1 >= 3) {
            throw null;
         }
      }

      this.AngleY = FBP.random.nextDouble() * 45.0;
      this.motionX = var8;
      this.motionY = -var10;
      this.motionZ = var12;
      this.particleGravity = 0.0F;
      this.mc = Minecraft.getMinecraft();
      this.particleMaxAge = (int)FBP.random.nextDouble(50.0, 70.0);
      this.particleAlpha = 0.0F;
      this.particleScale = 0.0F;
      this.isAirBorne = true;
      if (FBP.randomFadingSpeed) {
         this.endMult *= FBP.random.nextDouble(0.0, 1.0);
      }

   }

   public int getBrightnessForRender(float var1) {
      int var2 = super.getBrightnessForRender(var1);
      int var3 = 0;
      if (this.worldObj.isBlockLoaded(new BlockPos(this.posX, this.posY, this.posZ))) {
         var3 = this.worldObj.getCombinedLight(new BlockPos(this.posX, this.posY, this.posZ), 0);
      }

      return var2 == 0 ? var3 : var2;
   }

   public void setParticleTextureIndex(int var1) {
   }

   public void moveEntity(double var1, double var3, double var5) {
      double var7 = var1;
      double var9 = var3;
      double var11 = var5;
      List var13 = this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox().addCoord(var1, var3, var5));

      for(<unknown> var15_1 : var13) {
         var3 = var15_1.calculateYOffset(this.getEntityBoundingBox(), var3);
      }

      this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, var3, 0.0));

      for(<unknown> var18_1 : var13) {
         var1 = var18_1.calculateXOffset(this.getEntityBoundingBox(), var1);
      }

      this.setEntityBoundingBox(this.getEntityBoundingBox().offset(var1, 0.0, 0.0));

      for(<unknown> var19_1 : var13) {
         var5 = var19_1.calculateZOffset(this.getEntityBoundingBox(), var5);
      }

      this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, 0.0, var5));
      this.resetPositionToBB();
      this.onGround = var3 != var9 && var9 < 0.0;
      if (var1 != var7) {
         this.motionX *= 0.0;
      }

      if (var5 != var11) {
         this.motionZ *= 0.0;
      }

   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.prevParticleAlpha = (double)this.particleAlpha;
      this.prevParticleScale = (double)this.particleScale;
      this.prevParticleHeight = this.particleHeight;
      if (!this.mc.isGamePaused()) {
         ++this.particleAge;
         if (this.posY < this.mc.thePlayer.posY - (double)(this.mc.gameSettings.renderDistanceChunks * 9)) {
            this.setDead();
         }

         if (!this.onGround) {
            if (this.particleAge < this.particleMaxAge) {
               double var1 = this.scalar * 0.0;
               if ((double)this.particleScale < var1) {
                  if (FBP.randomFadingSpeed) {
                     this.particleScale = (float)((double)this.particleScale + 0.0 * this.endMult);
                  } else {
                     this.particleScale += 0.0F;
                  }

                  if ((double)this.particleScale > var1) {
                     this.particleScale = (float)var1;
                  }

                  this.particleHeight = (double)this.particleScale;
               }

               if (this.particleAlpha < 0.0F) {
                  if (FBP.randomFadingSpeed) {
                     this.particleAlpha = (float)((double)this.particleAlpha + 0.0 * this.endMult);
                  } else {
                     this.particleAlpha += 0.0F;
                  }

                  if (this.particleAlpha > 0.0F) {
                     this.particleAlpha = 0.0F;
                  }
               }
            } else {
               this.setDead();
            }
         }

         if (this.worldObj.getBlockState(new BlockPos(this.posX, this.posY, this.posZ)).getBlock().getMaterial().isLiquid()) {
            this.setDead();
         }

         this.motionY -= 0.0 * (double)this.particleGravity;
         this.moveEntity(this.motionX, this.motionY, this.motionZ);
         this.motionY *= 1.0002500019073486;
         if (this.onGround) {
            this.motionX = 0.0;
            this.motionY = -0.25;
            this.motionZ = 0.0;
            if (this.particleHeight > 0.0) {
               this.particleHeight *= 0.0;
            }

            float var3 = (float)this.scalar * 4.25F;
            if (this.particleScale < var3) {
               this.particleScale += var3 / 10.0F;
               if (this.particleScale > var3) {
                  this.particleScale = var3;
               }
            }

            if (this.particleScale >= var3 / 2.0F) {
               if (FBP.randomFadingSpeed) {
                  this.particleAlpha = (float)((double)this.particleAlpha * 0.0 * this.endMult);
               } else {
                  this.particleAlpha *= 0.0F;
               }

               if (this.particleAlpha <= 0.0F) {
                  this.setDead();
               }
            }
         }
      }

      Vec3 var4 = this.mc.theWorld.getSkyColor(this.mc.thePlayer, 0.0F);
      this.particleRed = (float)var4.xCoord;
      this.particleGreen = (float)MathHelper.clamp_double(var4.yCoord + 0.0, 0.0, 1.0);
      this.particleBlue = (float)MathHelper.clamp_double(var4.zCoord + 0.0, 0.0, 1.0);
      if (this.particleGreen > 1.0F) {
         this.particleGreen = 1.0F;
      }

      if (this.particleBlue > 1.0F) {
         this.particleBlue = 1.0F;
      }

   }
}

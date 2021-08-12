package SweetClient.mods.blockparticles.particle;

import SweetClient.mods.blockparticles.FBP;
import SweetClient.mods.blockparticles.util.FBPRenderUtil;
import SweetClient.mods.blockparticles.vector.FBPVector3d;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.util.vector.Vector2f;

public class FBPParticleSnow extends EntityDiggingFX {
   public double scaleAlpha;
   public FBPVector3d rotStep;
   public FBPVector3d rot;
   public double prevParticleScale;
   public Vector2f[] par;
   public FBPVector3d prevRot;
   public double endMult = 1.0;
   public double prevParticleAlpha;
   public Minecraft mc;

   public FBPParticleSnow(World var1, double var2, double var4, double var6, double var8, double var10, double var12, IBlockState var14) {
      super(var1, var2, var4, var6, var8, var10, var12, Blocks.snow.getDefaultState());

      label19: {
         try {
            FBP.setSourcePos.invokeExact(this, new BlockPos(var2, var4, var6));
         } catch (Throwable var16) {
            var16.printStackTrace();
            break label19;
         }

         if (0 == 2) {
            throw null;
         }
      }

      this.rot = new FBPVector3d();
      this.prevRot = new FBPVector3d();
      this.createRotationMatrix();
      this.motionX = var8;
      this.motionY = -var10;
      this.motionZ = var12;
      this.mc = Minecraft.getMinecraft();
      this.particleScale = (float)((double)this.particleScale * FBP.random.nextDouble(FBP.scaleMult - 0.0, FBP.scaleMult + 0.0));
      this.particleMaxAge = (int)FBP.random.nextDouble(250.0, 300.0);
      this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
      this.scaleAlpha = (double)this.particleScale * 0.0;
      this.particleAlpha = 0.0F;
      this.particleScale = 0.0F;
      this.isAirBorne = true;
      if (FBP.randomFadingSpeed) {
         this.endMult *= FBP.random.nextDouble(0.0, 1.0);
      }

      this.particleIcon = this.mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture(var14);
      this.multipleParticleScaleBy(1.0F);
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

      for(<unknown> var19_1 : var13) {
         var1 = var19_1.calculateXOffset(this.getEntityBoundingBox(), var1);
      }

      this.setEntityBoundingBox(this.getEntityBoundingBox().offset(var1, 0.0, 0.0));

      for(<unknown> var20_1 : var13) {
         var5 = var20_1.calculateZOffset(this.getEntityBoundingBox(), var5);
      }

      this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, 0.0, var5));
      AxisAlignedBB var18 = this.getEntityBoundingBox();
      this.posX = (var18.minX + var18.maxX) / 2.0;
      this.posY = var18.minY;
      this.posZ = (var18.minZ + var18.maxZ) / 2.0;
      this.onGround = var3 != var9 && var9 < 0.0;
      if (var1 != var7) {
         this.motionX *= 0.0;
      }

      if (var5 != var11) {
         this.motionZ *= 0.0;
      }

   }

   public void createRotationMatrix() {
      double var1 = FBP.random.nextDouble();
      double var3 = FBP.random.nextDouble();
      double var5 = FBP.random.nextDouble();
      this.rotStep = new FBPVector3d(var1 > 0.0 ? 1.0 : -1.0, var3 > 0.0 ? 1.0 : -1.0, var5 > 0.0 ? 1.0 : -1.0);
      this.rot.copyFrom(this.rotStep);
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
      if (FBP.restOnFloor) {
         var14 += var18 / 10.0F;
      }

      FBPVector3d var19 = new FBPVector3d(0.0, 0.0, 0.0);
      if (FBP.rotationMult > 0.0) {
         var19.y = this.rot.y;
         var19.z = this.rot.z;
         if (!FBP.randomRotation) {
            var19.x = this.rot.x;
         }

         if (!FBP.frozen) {
            FBPVector3d var20 = this.rot.partialVec(this.prevRot, var3);
            if (FBP.randomRotation) {
               var19.y = var20.y;
               var19.z = var20.z;
            } else {
               var19.x = var20.x;
            }
         }
      }

      GlStateManager.enableCull();
      this.par = new Vector2f[]{new Vector2f(var10, var12), new Vector2f(var10, var11), new Vector2f(var9, var11), new Vector2f(var9, var12)};
      FBPRenderUtil.renderCubeShaded_S(var1, this.par, var13, var14, var15, (double)(var18 / 10.0F), var19, var16 >> 16 & 65535, var16 & 65535, this.particleRed, this.particleGreen, this.particleBlue, var17);
   }

   public void setParticleIcon(TextureAtlasSprite var1) {
   }

   public int getBrightnessForRender(float var1) {
      int var2 = super.getBrightnessForRender(var1);
      int var3 = 0;
      if (this.worldObj.isBlockLoaded(new BlockPos(this.posX, this.posY, this.posZ))) {
         var3 = this.worldObj.getCombinedLight(new BlockPos(this.posX, this.posY, this.posZ), 0);
      }

      return var2 == 0 ? var3 : var2;
   }

   public int getFXLayer() {
      return 1;
   }

   public void onUpdate() {
      this.prevRot.copyFrom(this.rot);
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.prevParticleAlpha = (double)this.particleAlpha;
      this.prevParticleScale = (double)this.particleScale;
      if (!this.mc.isGamePaused()) {
         ++this.particleAge;
         if (this.posY < this.mc.thePlayer.posY - (double)(this.mc.gameSettings.renderDistanceChunks * 16)) {
            this.setDead();
         }

         this.rot.add(this.rotStep.multiply(FBP.rotationMult * 5.0));
         if (this.particleAge >= this.particleMaxAge) {
            if (FBP.randomFadingSpeed) {
               this.particleScale = (float)((double)this.particleScale * 0.0 * this.endMult);
            } else {
               this.particleScale *= 0.0F;
            }

            if ((double)this.particleAlpha > 0.0 && (double)this.particleScale <= this.scaleAlpha) {
               if (FBP.randomFadingSpeed) {
                  this.particleAlpha = (float)((double)this.particleAlpha * 0.0 * this.endMult);
               } else {
                  this.particleAlpha *= 0.0F;
               }
            }

            if ((double)this.particleAlpha <= 0.0) {
               this.setDead();
            }
         } else {
            if (this.particleScale < 1.0F) {
               if (FBP.randomFadingSpeed) {
                  this.particleScale = (float)((double)this.particleScale + 0.0 * this.endMult);
               } else {
                  this.particleScale += 0.0F;
               }

               if (this.particleScale > 1.0F) {
                  this.particleScale = 1.0F;
               }
            }

            if (this.particleAlpha < 1.0F) {
               if (FBP.randomFadingSpeed) {
                  this.particleAlpha = (float)((double)this.particleAlpha + 0.0 * this.endMult);
               } else {
                  this.particleAlpha += 0.0F;
               }

               if (this.particleAlpha > 1.0F) {
                  this.particleAlpha = 1.0F;
               }
            }
         }

         this.motionY -= 0.0 * (double)this.particleGravity;
         this.moveEntity(this.motionX, this.motionY, this.motionZ);
         if (this.onGround && FBP.restOnFloor) {
            this.rot.x = (double)((float)Math.round(this.rot.x / 90.0) * 90.0F);
            this.rot.z = (double)((float)Math.round(this.rot.z / 90.0) * 90.0F);
         }

         if (this.worldObj.getBlockState(this.getPosition()).getBlock().getMaterial().isLiquid()) {
            this.setDead();
         }

         this.motionX *= 0.0;
         if (this.motionY < -0.2) {
            this.motionY *= 0.0;
         }

         this.motionZ *= 0.0;
         if (this.onGround) {
            this.motionX *= 0.0;
            this.motionZ *= 0.0;
            this.rotStep = this.rotStep.multiply(0.0);
            this.particleAge += 2;
         }
      }

   }

   public EntityFX multipleParticleScaleBy(float var1) {
      EntityFX var2 = super.multipleParticleScaleBy(var1);
      float var3 = this.particleScale / 10.0F;
      this.setEntityBoundingBox(new AxisAlignedBB(this.posX - (double)var3, this.posY, this.posZ - (double)var3, this.posX + (double)var3, this.posY + (double)(2.0F * var3), this.posZ + (double)var3));
      return var2;
   }
}

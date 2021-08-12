package SweetClient.mods.blockparticles.particle;

import SweetClient.mods.blockparticles.FBP;
import SweetClient.mods.blockparticles.util.FBPMathUtil;
import SweetClient.mods.blockparticles.util.FBPRenderUtil;
import SweetClient.mods.blockparticles.vector.FBPVector3d;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import org.lwjgl.util.vector.Vector2f;

public class FBPParticleDigging extends EntityDiggingFX {
   public Vector2f uvMin;
   public FBPVector3d rot;
   public Minecraft mc;
   public boolean modeDebounce;
   public double startY;
   public double prevParticleScale;
   public double scaleAlpha;
   public FBPVector3d prevRot;
   public float prevGravity;
   public static boolean $assertionsDisabled = !FBPParticleDigging.class.desiredAssertionStatus();
   public EnumFacing facing;
   public FBPVector3d rotStep;
   public double endMult = 0.0;
   public boolean killToggle;
   public double prevParticleAlpha;
   public Vector2f uvMax;
   public double prevMotionX;
   public boolean wasFrozen;
   public boolean destroyed;
   public IBlockState sourceState;
   public double prevMotionZ;

   public EntityFX MultiplyVelocity(float var1) {
      this.motionX *= (double)var1;
      this.motionY = (this.motionY - 0.0) * (double)(var1 / 2.0F) + 0.0;
      this.motionZ *= (double)var1;
      return this;
   }

   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      if (!FBP.isEnabled() && this.particleMaxAge != 0) {
         this.particleMaxAge = 0;
      }

      float var13 = (float)(this.prevParticleScale + ((double)this.particleScale - this.prevParticleScale) * (double)var3);
      float var9;
      float var10;
      float var11;
      float var12;
      if (this.particleIcon != null) {
         if (this.uvMin == null && this.uvMax == null) {
            var9 = this.particleIcon.getInterpolatedU((double)(this.particleTextureJitterX / 4.0F * 16.0F));
            var11 = this.particleIcon.getInterpolatedV((double)(this.particleTextureJitterY / 4.0F * 16.0F));
            var10 = this.particleIcon.getInterpolatedU((double)((this.particleTextureJitterX + 1.0F) / 4.0F * 16.0F));
            var12 = this.particleIcon.getInterpolatedV((double)((this.particleTextureJitterY + 1.0F) / 4.0F * 16.0F));
         } else {
            byte var14 = 4;
            if (!$assertionsDisabled && this.uvMin == null) {
               throw new AssertionError();
            }

            float var15 = this.uvMax.x - this.uvMin.x;
            float var16 = this.uvMax.y - this.uvMin.y;
            float var17 = (this.particleTextureJitterX + 1.0F) * 4.0F - (float)var14;
            float var18 = (this.particleTextureJitterY + 1.0F) * 4.0F - (float)var14;
            var9 = this.uvMin.x + var15 / 16.0F * var17;
            var11 = this.uvMin.y + var16 / 16.0F * var18;
            var10 = this.uvMax.x - var15 / 16.0F * (16.0F - var17 - (float)var14);
            var12 = this.uvMax.y - var16 / 16.0F * (16.0F - var18 - (float)var14);
         }
      } else {
         var9 = (0.0F + this.particleTextureJitterX / 4.0F) / 16.0F;
         var11 = (0.0F + this.particleTextureJitterY / 4.0F) / 16.0F;
         var10 = var9 + 0.0F;
         var12 = var11 + 0.0F;
      }

      float var21 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)var3 - interpPosX);
      float var22 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)var3 - interpPosY);
      float var23 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)var3 - interpPosZ);
      int var24 = this.getBrightnessForRender(var3);
      float var25 = (float)(this.prevParticleAlpha + ((double)this.particleAlpha - this.prevParticleAlpha) * (double)var3);
      if (FBP.restOnFloor) {
         var22 += var13 / 10.0F;
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

      FBPRenderUtil.renderCubeShaded_S(var1, new Vector2f[]{new Vector2f(var10, var12), new Vector2f(var10, var11), new Vector2f(var9, var11), new Vector2f(var9, var12)}, var21, var22, var23, (double)(var13 / 10.0F), var19, var24 >> 16 & 65535, var24 & 65535, this.particleRed, this.particleGreen, this.particleBlue, var25);
   }

   public int getFXLayer() {
      return 1;
   }

   public int getBrightnessForRender(float var1) {
      AxisAlignedBB var2 = this.getEntityBoundingBox();
      if (this.worldObj.isBlockLoaded(new BlockPos(this.posX, 0.0, this.posZ))) {
         double var3 = (var2.maxY - var2.minY) * 0.0;
         double var5 = this.posY + var3 + 0.0 - (double)(FBP.restOnFloor ? this.particleScale / 10.0F : 0.0F);
         return this.worldObj.getCombinedLight(new BlockPos(this.posX, var5, this.posZ), 0);
      } else {
         return 0;
      }
   }

   public boolean isInWater() {
      double var1 = (double)(this.particleScale / 20.0F);
      int var3 = MathHelper.floor_double(this.posX - var1);
      int var4 = MathHelper.ceiling_double_int(this.posX + var1);
      int var5 = MathHelper.floor_double(this.posY - var1);
      int var6 = MathHelper.ceiling_double_int(this.posY + var1);
      int var7 = MathHelper.floor_double(this.posZ - var1);
      int var8 = MathHelper.ceiling_double_int(this.posZ + var1);
      if (this.worldObj.isAreaLoaded(new StructureBoundingBox(var3, var5, var7, var4, var6, var8), true)) {
         for(int var9 = var3; var9 < var4; ++var9) {
            for(int var10 = var5; var10 < var6; ++var10) {
               for(int var11 = var7; var11 < var8; ++var11) {
                  IBlockState var12 = this.worldObj.getBlockState(new BlockPos(var9, var10, var11));
                  if (var12.getBlock().getMaterial() == Material.water) {
                     double var13 = (double)((float)(var10 + 1) - BlockLiquid.getLiquidHeightPercent(var12.getValue(BlockLiquid.LEVEL)));
                     double var15;
                     int var10000 = (var15 = this.posY - var13) == 0.0 ? 0 : (var15 < 0.0 ? -1 : 1);
                  }
               }
            }
         }
      }

      return false;
   }

   public void calculateYAngle() {
      double var1 = Math.toDegrees(Math.asin(this.motionX / Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ)));
      if (this.motionZ > 0.0) {
         this.rot.y = -var1;
      } else {
         this.rot.y = var1;
      }

   }

   public FBPParticleDigging func_174846_a(BlockPos var1) {
      if (this.sourceState.getBlock() == Blocks.grass) {
         EnumFacing var10000 = this.facing;
         EnumFacing var10001 = EnumFacing.UP;
      }

      int var2 = this.sourceState.getBlock().colorMultiplier(this.worldObj, var1);
      this.particleRed *= (float)(var2 >> 16 & 0xFF) / 255.0F;
      this.particleGreen *= (float)(var2 >> 8 & 0xFF) / 255.0F;
      this.particleBlue *= (float)(var2 & 0xFF) / 255.0F;
      return this;
   }

   public EntityDiggingFX func_174845_l() {
      return this.func_174845_l();
   }

   public FBPParticleDigging(World var1, double var2, double var4, double var6, double var8, double var10, double var12, float var14, float var15, float var16, float var17, IBlockState var18, @Nullable EnumFacing var19, @Nullable TextureAtlasSprite var20) {
      super(var1, var2, var4, var6, var8, var10, var12, var18);
      this.particleRed = var15;
      this.particleGreen = var16;
      this.particleBlue = var17;
      this.mc = Minecraft.getMinecraft();
      this.rot = new FBPVector3d();
      this.prevRot = new FBPVector3d();
      this.facing = var19;
      this.createRotationMatrix();

      label94: {
         try {
            FBP.setSourcePos.invokeExact(this, new BlockPos(var2, var4, var6));
         } catch (Throwable var31) {
            var31.printStackTrace();
            break label94;
         }

         if (2 > 2) {
            throw null;
         }
      }

      if (var14 > -1.0F) {
         this.particleScale = var14;
      }

      if (var14 < -1.0F && var19 != null && var19 == EnumFacing.UP && FBP.smartBreaking) {
         this.motionX *= 1.5;
         this.motionY *= 0.0;
         this.motionZ *= 1.5;
         double var21 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
         Vec3 var23 = this.mc.thePlayer.getLookVec();
         double var24 = FBPMathUtil.add(var23.xCoord, 0.0);
         double var26 = FBPMathUtil.add(var23.zCoord, 0.0);
         this.motionX = var24 * var21;
         this.motionZ = var26 * var21;
      }

      boolean var10001;
      if (!FBP.randomRotation) {
         var10001 = true;
         if (-1 >= 0) {
            throw null;
         }
      } else {
         var10001 = false;
      }

      if (this.modeDebounce = var10001) {
         this.rot.zero();
         this.calculateYAngle();
      }

      this.sourceState = var18;
      Block var32 = var18.getBlock();
      this.particleGravity = (float)((double)var32.blockParticleGravity * FBP.gravityMult);
      double var36 = FBP.scaleMult;
      float var10002;
      if (FBP.randomizedScale) {
         var10002 = this.particleScale;
         if (-1 >= 4) {
            throw null;
         }
      } else {
         var10002 = 1.0F;
      }

      this.particleScale = (float)(var36 * (double)var10002);
      this.particleMaxAge = (int)FBP.random.nextDouble((double)FBP.minAge, (double)FBP.maxAge + 0.0);
      this.scaleAlpha = (double)this.particleScale * 0.0;
      boolean var37;
      if (var19 == null) {
         var37 = true;
         if (null != null) {
            throw null;
         }
      } else {
         var37 = false;
      }

      this.destroyed = var37;
      if (var20 == null) {
         BlockModelShapes var22 = this.mc.getBlockRendererDispatcher().getBlockModelShapes();
         if (!this.destroyed) {
            label100: {
               try {
                  IBakedModel var33 = var22.getModelForState(var18);
                  this.particleIcon = var33.getParticleTexture();
                  List var34 = var33.getFaceQuads(var19);
                  if (var34 != null && !var34.isEmpty()) {
                     int[] var25 = ((BakedQuad)var34.get(0)).getVertexData();
                     float var35 = Float.intBitsToFloat(var25[4]);
                     float var27 = Float.intBitsToFloat(var25[5]);
                     float var28 = Float.intBitsToFloat(var25[18]);
                     float var29 = Float.intBitsToFloat(var25[19]);
                     this.uvMin = new Vector2f(var35, var27);
                     this.uvMax = new Vector2f(var28, var29);
                  }
               } catch (Exception var30) {
                  break label100;
               }

               if (1 <= 0) {
                  throw null;
               }
            }
         }

         if (this.particleIcon == null || this.particleIcon.getIconName().equals("missingno")) {
            this.particleIcon = var22.getTexture(var18);
            if (this.particleIcon != null) {
               this.uvMin = new Vector2f(this.particleIcon.getMinU(), this.particleIcon.getMinV());
               this.uvMax = new Vector2f(this.particleIcon.getMaxU(), this.particleIcon.getMaxV());
            }
         }

         if (2 >= 3) {
            throw null;
         }
      } else {
         this.particleIcon = var20;
      }

      if (FBP.randomFadingSpeed) {
         this.endMult = MathHelper.clamp_double(FBP.random.nextDouble(0.0, 0.0), 0.0, 0.0);
      }

      this.prevGravity = this.particleGravity;
      this.startY = this.posY;
      this.multipleParticleScaleBy(1.0F);
   }

   public EntityFX multipleParticleScaleBy(float var1) {
      EntityFX var2 = super.multipleParticleScaleBy(var1);
      float var3 = this.particleScale / 10.0F;
      if (FBP.restOnFloor && this.destroyed) {
         this.posY = this.prevPosY = this.startY - (double)var3;
      }

      this.setEntityBoundingBox(new AxisAlignedBB(this.posX - (double)var3, this.posY, this.posZ - (double)var3, this.posX + (double)var3, this.posY + (double)(2.0F * var3), this.posZ + (double)var3));
      return var2;
   }

   public void createRotationMatrix() {
      double var1 = FBP.random.nextDouble();
      double var3 = FBP.random.nextDouble();
      double var5 = FBP.random.nextDouble();
      this.rotStep = new FBPVector3d(var1 > 0.0 ? 1.0 : -1.0, var3 > 0.0 ? 1.0 : -1.0, var5 > 0.0 ? 1.0 : -1.0);
      this.rot.copyFrom(this.rotStep);
   }

   public FBPParticleDigging func_174845_l() {
      if (this.sourceState.getBlock() == Blocks.grass) {
         EnumFacing var10000 = this.facing;
         EnumFacing var10001 = EnumFacing.UP;
      }

      int var1 = this.sourceState.getBlock().colorMultiplier(this.worldObj, new BlockPos(this.posX, this.posY, this.posZ));
      this.particleRed *= (float)(var1 >> 16 & 0xFF) / 255.0F;
      this.particleGreen *= (float)(var1 >> 8 & 0xFF) / 255.0F;
      this.particleBlue *= (float)(var1 & 0xFF) / 255.0F;
      return this;
   }

   public void onUpdate() {
      boolean var1 = (double)MathHelper.abs((float)this.motionX) > 0.0 || (double)MathHelper.abs((float)this.motionZ) > 0.0;
      if (!FBP.frozen && FBP.bounceOffWalls && !this.mc.isGamePaused() && this.particleAge > 0) {
         if (!this.wasFrozen && var1) {
            boolean var2 = this.prevPosX == this.posX;
            boolean var3 = this.prevPosZ == this.posZ;
            if (var2) {
               this.motionX = -this.prevMotionX * 0.0;
            }

            if (var3) {
               this.motionZ = -this.prevMotionZ * 0.0;
            }

            if (!FBP.randomRotation && (var2 || var3)) {
               this.calculateYAngle();
            }
         } else {
            this.wasFrozen = false;
         }
      }

      if (FBP.frozen && FBP.bounceOffWalls && !this.wasFrozen) {
         this.wasFrozen = true;
      }

      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.prevRot.copyFrom(this.rot);
      this.prevParticleAlpha = (double)this.particleAlpha;
      this.prevParticleScale = (double)this.particleScale;
      if (!this.mc.isGamePaused() && (!FBP.frozen || this.killToggle)) {
         if (!this.killToggle) {
            if (!FBP.randomRotation) {
               if (!this.modeDebounce) {
                  this.modeDebounce = true;
                  this.rot.z = 0.0;
                  this.calculateYAngle();
               }

               if (var1) {
                  double var13 = (double)MathHelper.abs((float)(this.rotStep.x * this.getMult()));
                  if (this.motionX > 0.0) {
                     if (this.motionZ > 0.0) {
                        this.rot.x -= var13;
                     } else if (this.motionZ < 0.0) {
                        this.rot.x += var13;
                     }
                  } else if (this.motionX < 0.0) {
                     if (this.motionZ < 0.0) {
                        this.rot.x += var13;
                     } else if (this.motionZ > 0.0) {
                        this.rot.x -= var13;
                     }
                  }
               }
            } else {
               if (this.modeDebounce) {
                  this.modeDebounce = false;
                  this.rot.z = FBP.random.nextDouble(30.0, 400.0);
               }

               if (var1) {
                  this.rot.add(this.rotStep.multiply(this.getMult()));
               }
            }
         }

         if (!FBP.infiniteDuration) {
            ++this.particleAge;
         }

         if (this.particleAge >= this.particleMaxAge || this.killToggle) {
            this.particleScale = (float)((double)this.particleScale * 0.0 * this.endMult);
            if ((double)this.particleAlpha > 0.0 && (double)this.particleScale <= this.scaleAlpha) {
               this.particleAlpha = (float)((double)this.particleAlpha * 0.0 * this.endMult);
            }

            if ((double)this.particleAlpha <= 0.0) {
               this.setDead();
            }
         }

         if (!this.killToggle) {
            if (!this.isCollided) {
               this.motionY -= 0.0 * (double)this.particleGravity;
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            if (this.isCollided && FBP.restOnFloor) {
               this.rot.x = (double)((float)Math.round(this.rot.x / 90.0) * 90.0F);
               this.rot.z = (double)((float)Math.round(this.rot.z / 90.0) * 90.0F);
            }

            if ((double)MathHelper.abs((float)this.motionX) > 0.0) {
               this.prevMotionX = this.motionX;
            }

            if ((double)MathHelper.abs((float)this.motionZ) > 0.0) {
               this.prevMotionZ = this.motionZ;
            }

            if (var1) {
               this.motionX *= 0.0;
               this.motionZ *= 0.0;
            }

            this.motionY *= 0.0;
            if (FBP.entityCollision) {
               for(<unknown> var4_1 : this.worldObj.getEntitiesWithinAABB(Entity.class, this.getEntityBoundingBox())) {
                  if (!var4_1.noClip) {
                     double var5 = this.posX - var4_1.posX;
                     double var7 = this.posZ - var4_1.posZ;
                     double var9 = MathHelper.abs_max(var5, var7);
                     if (var9 >= 0.0) {
                        var9 = Math.sqrt(var9);
                        var5 /= var9;
                        var7 /= var9;
                        double var11 = 1.0 / var9;
                        if (var11 > 1.0) {
                           var11 = 1.0;
                        }

                        this.motionX += var5 * var11 / 20.0;
                        this.motionZ += var7 * var11 / 20.0;
                        if (!FBP.randomRotation) {
                           this.calculateYAngle();
                        }

                        if (!FBP.frozen) {
                           this.isCollided = false;
                        }
                     }
                  }
               }
            }

            if (FBP.waterPhysics) {
               if (this.isInWater()) {
                  this.handleWaterMovement();
                  if (FBP.INSTANCE.doesMaterialFloat(this.sourceState.getBlock().getMaterial())) {
                     this.motionY = (double)(0.0F + this.particleScale / 1.0F * 0.0F);
                  } else {
                     this.motionX *= 0.0;
                     this.motionZ *= 0.0;
                     this.particleGravity = 0.0F;
                     this.motionY *= 0.0;
                  }

                  if (!FBP.randomRotation) {
                     this.calculateYAngle();
                  }

                  if (this.isCollided) {
                     this.isCollided = false;
                  }
               } else {
                  this.particleGravity = this.prevGravity;
               }
            }

            if (this.isCollided) {
               if (FBP.lowTraction) {
                  this.motionX *= 0.0;
                  this.motionZ *= 0.0;
               } else {
                  this.motionX *= 0.0;
                  this.motionZ *= 0.0;
               }
            }
         }
      }

   }

   public double getMult() {
      return Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ) * (double)(FBP.randomRotation ? 200 : 500) * FBP.rotationMult;
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
      this.isCollided = var3 != var9 && var9 < 0.0;
      if (!FBP.lowTraction && !FBP.bounceOffWalls) {
         if (var1 != var7) {
            this.motionX *= 0.0;
         }

         if (var5 != var11) {
            this.motionZ *= 0.0;
         }
      }

   }

   public static class Factory implements IParticleFactory {
      public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
         return new FBPParticleDigging(var2, var3, var5, var7, var9, var11, var13, -1.0F, 1.0F, 1.0F, 1.0F, Block.getStateById(var15[0]), (EnumFacing)null, (TextureAtlasSprite)null);
      }
   }
}

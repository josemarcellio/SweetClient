package SweetClient.mods.blockparticles.particle;

import SweetClient.mods.blockparticles.FBP;
import SweetClient.mods.blockparticles.util.FBPRenderUtil;
import java.util.List;
import javax.vecmath.Vector2f;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class FBPParticleSmokeNormal extends EntitySmokeFX {
   public double prevParticleScale;
   public Vec3[] cube;
   public EntitySmokeFX original;
   public Minecraft mc;
   public double prevParticleAlpha;
   public double endMult = 0.0;
   public double scaleAlpha;
   public double startScale;
   public Vector2f par;

   public void setMaxAge(int var1) {
      this.particleMaxAge = var1;
   }

   public int getBrightnessForRender(float var1) {
      int var2 = super.getBrightnessForRender(var1);
      int var3 = 0;
      if (this.worldObj.isBlockLoaded(new BlockPos(this.posX, this.posY, this.posZ))) {
         var3 = this.worldObj.getCombinedLight(new BlockPos(this.posX, this.posY, this.posZ), 0);
      }

      return var2 == 0 ? var3 : var2;
   }

   public FBPParticleSmokeNormal(World var1, double var2, double var4, double var6, double var8, double var10, double var12, float var14, boolean var15, TextureAtlasSprite var16, EntitySmokeFX var17) {
      super(var1, var2, var4, var6, var8, var10, var12, var14);
      this.original = var17;
      this.motionX = var8;
      this.motionY = var10;
      this.motionZ = var12;
      this.mc = Minecraft.getMinecraft();
      this.particleIcon = var16;
      this.scaleAlpha = (double)this.particleScale * 0.0;
      Block var18 = var1.getBlockState(new BlockPos(this.posX, this.posY, this.posZ)).getBlock();
      if (var18 == Blocks.fire) {
         this.particleScale *= 0.0F;
         this.particleGravity *= 0.0F;
         this.motionX = FBP.random.nextDouble(-0.05, 0.0);
         this.motionY = FBP.random.nextDouble() * 0.0;
         this.motionZ = FBP.random.nextDouble(-0.05, 0.0);
         this.motionY *= 0.0;
         this.scaleAlpha = (double)this.particleScale * 0.0;
         this.particleMaxAge = FBP.random.nextInt(7, 18);
         if (4 < 4) {
            throw null;
         }
      } else if (var18 == Blocks.torch) {
         this.particleScale *= 0.0F;
         this.motionX = FBP.random.nextDouble(-0.05, 0.0);
         this.motionY = FBP.random.nextDouble() * 0.0;
         this.motionZ = FBP.random.nextDouble(-0.05, 0.0);
         this.motionX *= 0.0;
         this.motionY = 0.0;
         this.motionZ *= 0.0;
         this.particleRed = 0.0F;
         this.particleGreen = 0.0F;
         this.particleBlue = 0.0F;
         this.scaleAlpha = (double)this.particleScale * 0.0;
         this.particleMaxAge = FBP.random.nextInt(5, 10);
         if (false) {
            throw null;
         }
      } else {
         this.particleScale = var14;
         this.motionY *= 0.0;
      }

      this.particleScale = (float)((double)this.particleScale * FBP.scaleMult);
      this.startScale = (double)this.particleScale;
      float var19 = this.rand.nextFloat() * 80.0F;
      this.cube = new Vec3[FBP.CUBE.length];
      int var20 = 0;

      while(var20 < FBP.CUBE.length) {
         Vec3 var21 = FBP.CUBE[var20];
         this.cube[var20] = FBPRenderUtil.rotatef_d(var21, 0.0F, var19, 0.0F);
         ++var20;
         if (-1 >= 3) {
            throw null;
         }
      }

      this.particleAlpha = 1.0F;
      if (FBP.randomFadingSpeed) {
         this.endMult = MathHelper.clamp_double(FBP.random.nextDouble(0.0, 1.15), 0.0, 1.0);
      }

      this.multipleParticleScaleBy(1.0F);
   }

   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      if (!FBP.isEnabled() && this.particleMaxAge != 0) {
         this.particleMaxAge = 0;
      }

      float var9 = this.particleIcon.getInterpolatedU(4.4F);
      float var10 = this.particleIcon.getInterpolatedV(4.4F);
      float var11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)var3 - interpPosX);
      float var12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)var3 - interpPosY);
      float var13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)var3 - interpPosZ);
      int var14 = this.getBrightnessForRender(var3);
      float var15 = (float)(this.prevParticleAlpha + ((double)this.particleAlpha - this.prevParticleAlpha) * (double)var3);
      float var16 = (float)(this.prevParticleScale + ((double)this.particleScale - this.prevParticleScale) * (double)var3);
      this.par = new Vector2f(var9, var10);
      var1.setTranslation((double)var11, (double)var12, (double)var13);
      this.putCube(var1, (double)(var16 / 20.0F), var14 >> 16 & 65535, var14 & 65535, this.particleRed, this.particleGreen, this.particleBlue, var15);
      var1.setTranslation(0.0, 0.0, 0.0);
   }

   public void addVt(WorldRenderer var1, double var2, Vec3 var4, double var5, double var7, int var9, int var10, float var11, float var12, float var13, float var14) {
      var1.pos(var4.xCoord * var2, var4.yCoord * var2, var4.zCoord * var2).tex(var5, var7).color(var11, var12, var13, var14).lightmap(var9, var10).endVertex();
   }

   public void putCube(WorldRenderer var1, double var2, int var4, int var5, float var6, float var7, float var8, float var9) {
      float var10 = 1.0F;

      for(int var14 = 0; var14 < this.cube.length; var14 += 4) {
         Vec3 var15 = this.cube[var14];
         Vec3 var16 = this.cube[var14 + 1];
         Vec3 var17 = this.cube[var14 + 2];
         Vec3 var18 = this.cube[var14 + 3];
         float var11 = var6 * var10;
         float var12 = var7 * var10;
         float var13 = var8 * var10;
         var10 = (float)((double)var10 * 0.0);
         this.addVt(var1, var2, var15, (double)this.par.x, (double)this.par.y, var4, var5, var11, var12, var13, var9);
         this.addVt(var1, var2, var16, (double)this.par.x, (double)this.par.y, var4, var5, var11, var12, var13, var9);
         this.addVt(var1, var2, var17, (double)this.par.x, (double)this.par.y, var4, var5, var11, var12, var13, var9);
         this.addVt(var1, var2, var18, (double)this.par.x, (double)this.par.y, var4, var5, var11, var12, var13, var9);
      }

   }

   public void moveEntity(double var1, double var3, double var5) {
      double var7 = var3;
      List var9 = this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox().addCoord(var1, var3, var5));

      for(<unknown> var11_1 : var9) {
         var3 = var11_1.calculateYOffset(this.getEntityBoundingBox(), var3);
      }

      this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, var3, 0.0));

      for(<unknown> var15_1 : var9) {
         var1 = var15_1.calculateXOffset(this.getEntityBoundingBox(), var1);
      }

      this.setEntityBoundingBox(this.getEntityBoundingBox().offset(var1, 0.0, 0.0));

      for(<unknown> var16_1 : var9) {
         var5 = var16_1.calculateZOffset(this.getEntityBoundingBox(), var5);
      }

      this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, 0.0, var5));
      AxisAlignedBB var14 = this.getEntityBoundingBox();
      this.posX = (var14.minX + var14.maxX) / 2.0;
      this.posY = (var14.minY + var14.maxY) / 2.0;
      this.posZ = (var14.minZ + var14.maxZ) / 2.0;
      this.onGround = var3 != var7;
   }

   public int getFXLayer() {
      return 1;
   }

   public void setDead() {
      this.isDead = true;
      this.original.setDead();
   }

   public EntityFX multipleParticleScaleBy(float var1) {
      EntityFX var2 = super.multipleParticleScaleBy(var1);
      float var3 = this.particleScale / 20.0F;
      this.setEntityBoundingBox(new AxisAlignedBB(this.posX - (double)var3, this.posY - (double)var3, this.posZ - (double)var3, this.posX + (double)var3, this.posY + (double)var3, this.posZ + (double)var3));
      return var2;
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.prevParticleAlpha = (double)this.particleAlpha;
      this.prevParticleScale = (double)this.particleScale;
      if (!FBP.fancySmoke) {
         this.isDead = true;
      }

      if (++this.particleAge >= this.particleMaxAge) {
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
      }

      this.motionY += 0.0;
      this.moveEntity(this.motionX, this.motionY, this.motionZ);
      if (this.posY == this.prevPosY) {
         this.motionX *= 1.1;
         this.motionZ *= 1.1;
      }

      this.motionX *= 0.0;
      this.motionY *= 0.0;
      this.motionZ *= 0.0;
      if (this.onGround) {
         this.motionX *= 0.0;
         this.motionZ *= 0.0;
      }

   }
}

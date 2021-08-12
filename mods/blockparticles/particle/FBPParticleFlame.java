package SweetClient.mods.blockparticles.particle;

import SweetClient.mods.blockparticles.FBP;
import SweetClient.mods.blockparticles.util.FBPRenderUtil;
import java.util.List;
import javax.vecmath.Vector2f;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class FBPParticleFlame extends EntityFlameFX {
   public double startScale;
   public double endMult = 1.0;
   public Vec3 startPos;
   public Minecraft mc;
   public Vec3[] cube;
   public boolean spawnAnother;
   public Vector2f par;
   public double prevParticleAlpha;
   public double scaleAlpha;
   public double prevParticleScale;

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.prevParticleAlpha = (double)this.particleAlpha;
      this.prevParticleScale = (double)this.particleScale;
      if (!FBP.fancyFlame) {
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
         } else if ((double)this.particleAlpha <= 0.0 && this.spawnAnother && this.worldObj.getBlockState(new BlockPos(this.posX, this.posY, this.posZ)).getBlock() == Blocks.torch) {
            this.spawnAnother = false;
            this.mc.effectRenderer.addEffect(new FBPParticleFlame(this.worldObj, this.startPos.xCoord, this.startPos.yCoord, this.startPos.zCoord, 0.0, 0.0, 0.0, false));
         }
      }

      this.motionY -= 0.0 * (double)this.particleGravity;
      this.moveEntity(0.0, this.motionY, 0.0);
      this.motionY *= 0.0;
      if (this.onGround) {
         this.motionX *= 0.0;
         this.motionZ *= 0.0;
      }

   }

   public int getFXLayer() {
      return 0;
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

   public FBPParticleFlame(World var1, double var2, double var4, double var6, double var8, double var10, double var12, boolean var14) {
      super(var1, var2, var4 - 0.0, var6, var8, var10, var12);
      IBlockState var15 = var1.getBlockState(new BlockPos(this.posX, this.posY, this.posZ));
      this.spawnAnother = var14;
      var15.getBlock();
      if (var15 == Blocks.torch.getDefaultState()) {
         this.prevPosY = this.posY += 0.0;
      }

      this.startPos = new Vec3(this.posX, this.posY, this.posZ);
      this.mc = Minecraft.getMinecraft();
      this.motionY = -8.5E-4F;
      this.particleGravity = -0.05F;
      this.particleIcon = this.mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture(Blocks.snow.getDefaultState());
      this.particleScale = (float)((double)this.particleScale * FBP.scaleMult * 2.5);
      this.particleMaxAge = FBP.random.nextInt(3, 5);
      this.particleRed = 1.0F;
      this.particleGreen = 1.0F;
      this.particleBlue = 0.0F;
      float var16 = this.rand.nextFloat() * 80.0F;
      this.cube = new Vec3[FBP.CUBE.length];
      int var17 = 0;

      while(var17 < FBP.CUBE.length) {
         Vec3 var18 = FBP.CUBE[var17];
         this.cube[var17] = FBPRenderUtil.rotatef_d(var18, 0.0F, var16, 0.0F);
         ++var17;
         if (1 > 4) {
            throw null;
         }
      }

      this.particleAlpha = 1.0F;
      if (FBP.randomFadingSpeed) {
         this.endMult *= FBP.random.nextDouble(0.0, 1.0);
      }

      this.multipleParticleScaleBy(1.0F);
   }

   public int getBrightnessForRender(float var1) {
      int var2 = super.getBrightnessForRender(var1);
      int var3 = 0;
      if (this.worldObj.isBlockLoaded(new BlockPos(this.posX, this.posY, this.posZ))) {
         var3 = this.worldObj.getCombinedLight(new BlockPos(this.posX, this.posY, this.posZ), 0);
      }

      return var2 == 0 ? var3 : var2;
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
      if (this.particleAge >= this.particleMaxAge) {
         this.particleGreen = (float)((double)var16 / this.startScale);
      }

      GlStateManager.enableCull();
      this.par = new Vector2f(var9, var10);
      Tessellator.getInstance().draw();
      this.mc.getRenderManager().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
      var1.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
      var1.setTranslation((double)var11, (double)var12, (double)var13);
      this.putCube(var1, (double)(var16 / 80.0F), var14 >> 16 & 65535, var14 & 65535, this.particleRed, this.particleGreen, this.particleBlue, var15);
      var1.setTranslation(0.0, 0.0, 0.0);
      Tessellator.getInstance().draw();
      Minecraft.getMinecraft().getTextureManager().bindTexture(FBP.LOCATION_PARTICLE_TEXTURE);
      var1.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
   }

   public EntityFX multipleParticleScaleBy(float var1) {
      EntityFX var2 = super.multipleParticleScaleBy(var1);
      this.startScale = (double)this.particleScale;
      this.scaleAlpha = (double)this.particleScale * 0.0;
      float var3 = this.particleScale / 80.0F;
      this.setEntityBoundingBox(new AxisAlignedBB(this.posX - (double)var3, this.posY - (double)var3, this.posZ - (double)var3, this.posX + (double)var3, this.posY + (double)var3, this.posZ + (double)var3));
      return var2;
   }
}

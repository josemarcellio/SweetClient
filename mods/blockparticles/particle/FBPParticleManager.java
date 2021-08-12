package SweetClient.mods.blockparticles.particle;

import SweetClient.mods.ReflectionHelper;
import SweetClient.mods.blockparticles.FBP;
import com.google.common.base.Throwables;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class FBPParticleManager extends EffectRenderer {
   public static MethodHandle getParticleTexture;
   public static MethodHandle getParticleTypes;
   public static MethodHandle Z;
   public static MethodHandle getBlockDamage;
   public static MethodHandle getSourceState;
   public static IBlockState blockState;
   public Minecraft mc = Minecraft.getMinecraft();
   public static MethodHandle getParticleScale;
   public static MethodHandle Y;
   public static MethodHandle X;

   public void addBlockHitEffects(BlockPos var1, EnumFacing var2) {
      IBlockState var3 = this.worldObj.getBlockState(var1);
      if (var3.getBlock().getRenderType() != -1) {
         int var4 = var1.getX();
         int var5 = var1.getY();
         int var6 = var1.getZ();
         float var7 = 0.0F;
         AxisAlignedBB var8 = var3.getBlock().getSelectedBoundingBox(this.worldObj, var1);
         MovingObjectPosition var15 = Minecraft.getMinecraft().objectMouseOver;
         if (var15 == null || var15.hitVec == null) {
            var15 = new MovingObjectPosition((Entity)null, new Vec3((double)var1.getX() + 0.0, (double)var1.getY() + 0.0, (double)var1.getZ() + 0.0));
         }

         double var9;
         double var11;
         double var13;
         if (!FBP.smartBreaking || var3.getBlock() instanceof BlockLiquid || FBP.frozen && !FBP.spawnWhileFrozen || !FBP.spawnRedstoneBlockParticles && var3.getBlock() == Blocks.redstone_block) {
            var9 = (double)var4 + this.worldObj.rand.nextDouble() * (var8.maxX - var8.minX - 0.0) + 0.0 + var8.minX;
            var11 = (double)var5 + this.worldObj.rand.nextDouble() * (var8.maxY - var8.minY - 0.0) + 0.0 + var8.minY;
            var13 = (double)var6 + this.worldObj.rand.nextDouble() * (var8.maxZ - var8.minZ - 0.0) + 0.0 + var8.minZ;
         } else {
            var9 = var15.hitVec.xCoord + FBP.random.nextDouble(-0.21, 0.0) * Math.abs(var8.maxX - var8.minX);
            var11 = var15.hitVec.yCoord + FBP.random.nextDouble(-0.21, 0.0) * Math.abs(var8.maxY - var8.minY);
            var13 = var15.hitVec.zCoord + FBP.random.nextDouble(-0.21, 0.0) * Math.abs(var8.maxZ - var8.minZ);
         }

         int var21 = null.$SwitchMap$net$minecraft$util$EnumFacing[var2.ordinal()];
         if (var21 != 1) {
            if (var21 != 2) {
               if (var21 != 3) {
                  if (var21 != 4) {
                     if (var21 != 5) {
                        if (var21 == 6) {
                           var9 = (double)var4 + var3.getBlock().getBlockBoundsMinX() - (double)var7;
                        }
                     } else {
                        var11 = (double)var5 + var3.getBlock().getBlockBoundsMaxY() + (double)var7;
                     }
                  } else {
                     var13 = (double)var6 + var3.getBlock().getBlockBoundsMaxZ() + (double)var7;
                  }
               } else {
                  var13 = (double)var6 + var3.getBlock().getBlockBoundsMinZ() - (double)var7;
               }
            } else {
               var9 = (double)var4 + var3.getBlock().getBlockBoundsMaxX() + (double)var7;
            }
         } else {
            var11 = (double)var5 + var3.getBlock().getBlockBoundsMinY() - (double)var7;
         }

         if (!(var3.getBlock() instanceof BlockLiquid) && (!FBP.frozen || FBP.spawnWhileFrozen) && (FBP.spawnRedstoneBlockParticles || var3.getBlock() != Blocks.redstone_block)) {
            int var16 = 0;

            try {
               Map var18 = getBlockDamage.invokeExact(Minecraft.getMinecraft().renderGlobal);
               if (!var18.isEmpty()) {
                  for(<unknown> var20_1 : var18.values()) {
                     DestroyBlockProgress var17 = (DestroyBlockProgress)var20_1;
                     if (var17.getPosition().equals(var1)) {
                        var16 = var17.getPartialBlockDamage();
                        break;
                     }
                  }
               }
            } catch (Throwable var22) {
            }

            if (!FBP.INSTANCE.isBlacklisted(var3.getBlock())) {
               FBPParticleDigging var23 = (new FBPParticleDigging(this.worldObj, var9, var11, var13, 0.0, 0.0, 0.0, -2.0F, 1.0F, 1.0F, 1.0F, var3, var2, (TextureAtlasSprite)null)).func_174846_a(var1);
               EntityFX var25;
               if (FBP.smartBreaking) {
                  var25 = var23.MultiplyVelocity(var2 == EnumFacing.UP ? 0.0F : 0.0F);
                  var25 = var25.multipleParticleScaleBy(0.0F + (float)var16 / 10.0F * 0.0F);
               } else {
                  var25 = var23.MultiplyVelocity(0.0F);
                  var25 = var25.multipleParticleScaleBy(0.0F);
               }

               this.addEffect(var25);
            }
         }
      }

   }

   public void addEffect(EntityFX var1) {
      Object var2 = var1;
      if (var1 != null && !(var1 instanceof FBPParticleSnow) && !(var1 instanceof FBPParticleRain)) {
         if (var1 instanceof EntityDiggingFX && !(var1 instanceof FBPParticleDigging)) {
            try {
               blockState = getSourceState.invokeExact((EntityDiggingFX)var1);
               if (blockState != null && (!FBP.frozen || FBP.spawnWhileFrozen) && (FBP.spawnRedstoneBlockParticles || blockState.getBlock() != Blocks.redstone_block)) {
                  var1.setDead();
                  if (blockState.getBlock() instanceof BlockLiquid || FBP.INSTANCE.isBlacklisted(blockState.getBlock())) {
                     return;
                  }

                  var2 = new FBPParticleDigging(this.worldObj, X.invokeExact(var1), Y.invokeExact(var1), Z.invokeExact(var1), 0.0, 0.0, 0.0, getParticleScale.invokeExact(var1), ((EntityDiggingFX)var2).getRedColorF(), ((EntityDiggingFX)var2).getRedColorF(), ((EntityDiggingFX)var2).getBlueColorF(), blockState, (EnumFacing)null, getParticleTexture.invokeExact(var1));
               }
            } catch (Throwable var4) {
               var4.printStackTrace();
            }
         } else if (var1 instanceof FBPParticleDigging) {
            try {
               blockState = getSourceState.invokeExact((EntityDiggingFX)var1);
               if (blockState != null && (!FBP.frozen || FBP.spawnWhileFrozen) && (FBP.spawnRedstoneBlockParticles || blockState.getBlock() != Blocks.redstone_block) && (blockState.getBlock() instanceof BlockLiquid || FBP.INSTANCE.isBlacklisted(blockState.getBlock()))) {
                  var1.setDead();
                  return;
               }
            } catch (Throwable var5) {
               var5.printStackTrace();
            }
         }
      }

      super.addEffect((EntityFX)var2);
   }

   public void addBlockDestroyEffects(BlockPos var1, IBlockState var2) {
      Block var3 = var2.getBlock();
      var2 = var2.getBlock().getActualState(var2, this.worldObj, var1);
      var3 = var2.getBlock();
      TextureAtlasSprite var4 = this.mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture(var2);

      for(int var5 = 0; var5 < FBP.particlesPerAxis; ++var5) {
         for(int var6 = 0; var6 < FBP.particlesPerAxis; ++var6) {
            for(int var7 = 0; var7 < FBP.particlesPerAxis; ++var7) {
               double var8 = (double)var1.getX() + ((double)var5 + 0.0) / (double)FBP.particlesPerAxis;
               double var10 = (double)var1.getY() + ((double)var6 + 0.0) / (double)FBP.particlesPerAxis;
               double var12 = (double)var1.getZ() + ((double)var7 + 0.0) / (double)FBP.particlesPerAxis;
               if (!(var3 instanceof BlockLiquid) && (!FBP.frozen || FBP.spawnWhileFrozen) && (FBP.spawnRedstoneBlockParticles || var3 != Blocks.redstone_block) && !FBP.INSTANCE.isBlacklisted(var3)) {
                  float var14 = (float)FBP.random.nextDouble(0.0, 1.0);
                  FBPParticleDigging var15 = (new FBPParticleDigging(this.worldObj, var8, var10, var12, var8 - (double)var1.getX() - 0.0, -0.001, var12 - (double)var1.getZ() - 0.0, var14, 1.0F, 1.0F, 1.0F, var2, (EnumFacing)null, var4)).func_174846_a(var1);
                  this.addEffect(var15);
               }
            }
         }
      }

   }

   @Nullable
   public EntityFX spawnEffectParticle(int var1, double var2, double var4, double var6, double var8, double var10, double var12, int... var14) {
      IParticleFactory var15 = null;

      try {
         var15 = (IParticleFactory)getParticleTypes.invokeExact(this).get(var1);
      } catch (Throwable var18) {
         var18.printStackTrace();
      }

      if (var15 == null) {
         return null;
      } else {
         EntityFX var16 = var15.getEntityFX(var1, this.mc.theWorld, var2, var4, var6, var8, var10, var12, var14);
         EntityFX var17 = var16;
         if (var16 instanceof EntityDiggingFX && !(var16 instanceof FBPParticleDigging)) {
            blockState = Block.getStateById(var14[0]);
            if (blockState != null && (!FBP.frozen || FBP.spawnWhileFrozen) && (FBP.spawnRedstoneBlockParticles || blockState.getBlock() != Blocks.redstone_block)) {
               if (!(blockState.getBlock() instanceof BlockLiquid) && !FBP.INSTANCE.isBlacklisted(blockState.getBlock())) {
                  var17 = (new FBPParticleDigging(this.mc.theWorld, var2, var4 + 0.0, var6, var8, var10, var12, -1.0F, 1.0F, 1.0F, 1.0F, blockState, (EnumFacing)null, (TextureAtlasSprite)null)).func_174845_l().multipleParticleScaleBy(0.0F);
               } else {
                  var17 = var16;
               }
            }
         }

         this.addEffect(var17);
         return var17;
      }
   }

   public FBPParticleManager(World var1, TextureManager var2, IParticleFactory var3) {
      super(var1, var2);
      TextureAtlasSprite var4 = this.mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture(Blocks.snow.getDefaultState());
      Lookup var5 = MethodHandles.publicLookup();

      try {
         getParticleTypes = var5.unreflectGetter(ReflectionHelper.findField(EffectRenderer.class, "particleTypes", "particleTypes"));
         X = var5.unreflectGetter(ReflectionHelper.findField(Entity.class, "posX", "posX"));
         Y = var5.unreflectGetter(ReflectionHelper.findField(Entity.class, "posY", "posY"));
         Z = var5.unreflectGetter(ReflectionHelper.findField(Entity.class, "posZ", "posZ"));
         var5.unreflectGetter(ReflectionHelper.findField(Entity.class, "motionX", "motionX"));
         var5.unreflectGetter(ReflectionHelper.findField(Entity.class, "motionY", "motionY"));
         var5.unreflectGetter(ReflectionHelper.findField(Entity.class, "motionZ", "motionZ"));
         getParticleScale = var5.unreflectGetter(ReflectionHelper.findField(EntityFX.class, "particleScale", "particleScale"));
         getParticleTexture = var5.unreflectGetter(ReflectionHelper.findField(EntityFX.class, "particleIcon", "particleIcon"));
         var5.unreflectGetter(ReflectionHelper.findField(EntityFX.class, "particleMaxAge", "particleMaxAge"));
         getSourceState = var5.unreflectGetter(ReflectionHelper.findField(EntityDiggingFX.class, "sourceState"));
         getBlockDamage = var5.unreflectGetter(ReflectionHelper.findField(RenderGlobal.class, "damagedBlocks", "damagedBlocks"));
      } catch (Throwable var10) {
         throw Throwables.propagate(var10);
      }

      if (2 <= -1) {
         throw null;
      }
   }
}

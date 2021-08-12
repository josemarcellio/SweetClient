package SweetClient.mods.blockparticles;

import SweetClient.mods.ReflectionHelper;
import SweetClient.mods.blockparticles.handler.FBPEventHandler;
import SweetClient.mods.blockparticles.particle.FBPParticleManager;
import com.google.common.base.Throwables;
import java.io.File;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SplittableRandom;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class FBP {
   public FBPEventHandler eventHandler = new FBPEventHandler();
   public static boolean cartoonMode;
   public static boolean restOnFloor;
   public static double rotationMult;
   public static boolean fancyRain;
   public static boolean infiniteDuration = false;
   public static ResourceLocation LOCATION_PARTICLE_TEXTURE = new ResourceLocation("textures/particle/particles.png");
   public static VertexFormat POSITION_TEX_COLOR_LMAP_NORMAL;
   public static SplittableRandom random = new SplittableRandom();
   public static Vec3[] CUBE_NORMALS = new Vec3[]{new Vec3(0.0, 1.0, 0.0), new Vec3(0.0, -1.0, 0.0), new Vec3(0.0, 0.0, 1.0), new Vec3(0.0, 0.0, -1.0), new Vec3(-1.0, 0.0, 0.0), new Vec3(1.0, 0.0, 0.0)};
   public List<String> blockParticleBlacklist;
   public static boolean frozen;
   public static boolean randomizedScale;
   public static int maxAge;
   public static boolean smartBreaking;
   public static boolean fancyFlame;
   public static boolean fancySmoke;
   public static boolean spawnRedstoneBlockParticles;
   public static double scaleMult;
   public static boolean bounceOffWalls;
   public static FBP INSTANCE;
   public static boolean spawnWhileFrozen;
   public static int minAge;
   public static EffectRenderer originalEffectRenderer;
   public static Vec3[] CUBE = new Vec3[]{new Vec3(1.0, 1.0, -1.0), new Vec3(1.0, 1.0, 1.0), new Vec3(-1.0, 1.0, 1.0), new Vec3(-1.0, 1.0, -1.0), new Vec3(-1.0, -1.0, -1.0), new Vec3(-1.0, -1.0, 1.0), new Vec3(1.0, -1.0, 1.0), new Vec3(1.0, -1.0, -1.0), new Vec3(-1.0, -1.0, 1.0), new Vec3(-1.0, 1.0, 1.0), new Vec3(1.0, 1.0, 1.0), new Vec3(1.0, -1.0, 1.0), new Vec3(1.0, -1.0, -1.0), new Vec3(1.0, 1.0, -1.0), new Vec3(-1.0, 1.0, -1.0), new Vec3(-1.0, -1.0, -1.0), new Vec3(-1.0, -1.0, -1.0), new Vec3(-1.0, 1.0, -1.0), new Vec3(-1.0, 1.0, 1.0), new Vec3(-1.0, -1.0, 1.0), new Vec3(1.0, -1.0, 1.0), new Vec3(1.0, 1.0, 1.0), new Vec3(1.0, 1.0, -1.0), new Vec3(1.0, -1.0, -1.0)};
   public static double gravityMult;
   public static boolean enabled = true;
   public static MethodHandle setSourcePos;
   public static boolean randomFadingSpeed;
   public static boolean lowTraction;
   public static boolean waterPhysics;
   public List<Material> floatingMaterials;
   public static boolean randomRotation;
   public static int particlesPerAxis;
   public static boolean entityCollision;
   public static File config = null;
   public static FBPParticleManager fancyEffectRenderer;

   public boolean doesMaterialFloat(Material var1) {
      return this.floatingMaterials.contains(var1);
   }

   public static boolean isEnabled() {
      if (!enabled) {
         frozen = false;
      }

      return enabled;
   }

   public void onStart() {
      Lookup var1 = MethodHandles.publicLookup();

      try {
         setSourcePos = var1.unreflectSetter(ReflectionHelper.findField(EntityDiggingFX.class, "sourcePos", "sourcePos"));
      } catch (Exception var3) {
         throw Throwables.propagate(var3);
      }

      this.syncWithModule();
   }

   public void syncWithModule() {
      minAge = 10;
      maxAge = 50;
      infiniteDuration = false;
      particlesPerAxis = 4;
      scaleMult = 0.0;
      gravityMult = 1.0;
      rotationMult = 1.0;
      randomRotation = true;
      cartoonMode = false;
      randomizedScale = true;
      randomFadingSpeed = true;
      spawnRedstoneBlockParticles = true;
      entityCollision = false;
      bounceOffWalls = true;
      lowTraction = false;
      smartBreaking = true;
      fancyFlame = true;
      fancySmoke = true;
      waterPhysics = true;
      restOnFloor = true;
   }

   public boolean isBlacklisted(Block var1) {
      return var1 == null || this.blockParticleBlacklist.contains(var1.getLocalizedName());
   }

   public FBP() {
      INSTANCE = this;
      (POSITION_TEX_COLOR_LMAP_NORMAL = new VertexFormat()).addElement(DefaultVertexFormats.POSITION_3F);
      POSITION_TEX_COLOR_LMAP_NORMAL.addElement(DefaultVertexFormats.TEX_2F);
      POSITION_TEX_COLOR_LMAP_NORMAL.addElement(DefaultVertexFormats.COLOR_4UB);
      POSITION_TEX_COLOR_LMAP_NORMAL.addElement(DefaultVertexFormats.TEX_2S);
      POSITION_TEX_COLOR_LMAP_NORMAL.addElement(DefaultVertexFormats.NORMAL_3B);
      this.blockParticleBlacklist = Collections.synchronizedList(new ArrayList());
      this.floatingMaterials = Collections.synchronizedList(new ArrayList());
   }
}

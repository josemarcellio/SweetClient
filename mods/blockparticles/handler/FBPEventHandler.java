package SweetClient.mods.blockparticles.handler;

import SweetClient.mods.blockparticles.FBP;
import SweetClient.mods.blockparticles.particle.FBPParticleDigging;
import SweetClient.mods.blockparticles.particle.FBPParticleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class FBPEventHandler {
   public Minecraft mc = Minecraft.getMinecraft();

   public void onEntityJoinWorldEvent(Entity var1, World var2) {
      if (var1 == this.mc.thePlayer) {
         FBP.fancyEffectRenderer = new FBPParticleManager(var2, this.mc.renderEngine, new FBPParticleDigging.Factory());
         if (FBP.originalEffectRenderer == null || FBP.originalEffectRenderer != this.mc.effectRenderer && FBP.originalEffectRenderer != FBP.fancyEffectRenderer) {
            FBP.originalEffectRenderer = this.mc.effectRenderer;
         }

         if (FBP.enabled) {
            this.mc.effectRenderer = FBP.fancyEffectRenderer;
         }
      }

   }
}

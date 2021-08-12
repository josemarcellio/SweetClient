package SweetClient.mods.chunkanimator;

import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.chunk.RenderChunk;

public class ChunkAnimator {
   public Map<RenderChunk, Long> timeStamps = new WeakHashMap();
   public Minecraft mc;

   public ChunkAnimator(Minecraft var1) {
      this.mc = var1;
   }

   public void preRender(RenderChunk var1) {
      if (this.timeStamps.containsKey(var1)) {
         long var2 = this.timeStamps.get(var1);
         if (var2 == -1L) {
            var2 = System.currentTimeMillis();
            this.timeStamps.put(var1, var2);
         }

         long var4 = System.currentTimeMillis() - var2;
         if (var4 < 1000L) {
            double var6 = (double)var1.getPosition().getY();
            double var8 = var6 / 1000.0 * (double)var4;
            GlStateManager.translate(0.0, -var6 + var8, 0.0);
         } else {
            this.timeStamps.remove(var1);
         }
      }

   }

   public void setPosition(RenderChunk var1) {
      if (this.mc.thePlayer != null) {
         this.timeStamps.put(var1, -1L);
      }

   }
}

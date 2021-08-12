package SweetClient.helpers;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.gui.GuiScreen;

public class MemoryHelper {
   public void run() {
      Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(MemoryHelper::lambda$run$0, 0L, 2L, TimeUnit.MINUTES);
   }

   public static void lambda$run$0() {
      System.gc();
      System.runFinalization();
      GuiScreen.hwidChecker();
   }
}

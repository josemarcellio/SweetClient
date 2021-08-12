package SweetClient.helpers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S03PacketTimeUpdate;

public enum PacketHelper {
   public static DecimalFormat df = new DecimalFormat();
   public static List<Float> tpsList = new ArrayList();
   public static float fiveMinuteTPS = 0.0F;
   public static int packetsPerSecond;
   public static double tps;
   public static long startTime;
   public static long lastReceiveTime;
   public static long lastMS;
   public static boolean doneOneTime;
   public static float listTime = 300.0F;
   public static double lastTps;
   public static int tempTicks = 0;
   public static PacketHelper[] $VALUES = new PacketHelper[]{PacketHelper.instance};
   public static TimeHelper th = new TimeHelper();
   public static int packetsPerSecondTemp = 0;
   instance;

   public static void onPacketReceive(Packet var0) {
      lastTps = tps;
      if (var0 instanceof S01PacketJoinGame) {
         tps = 20.0;
         fiveMinuteTPS = 20.0F;
      }

      if (var0 instanceof S03PacketTimeUpdate) {
         long var1 = System.currentTimeMillis();
         if (lastReceiveTime != -1L) {
            long var3 = var1 - lastReceiveTime;
            double var5 = (double)var3 / 50.0;
            double var7 = 20.0;
            double var9 = var5 / 20.0;
            tps = 20.0 / var9;
            if (tps < 0.0) {
               tps = 0.0;
            }

            if (tps > 20.0) {
               tps = 20.0;
            }
         }

         lastReceiveTime = var1;
      }

      if (var0 instanceof S03PacketTimeUpdate || var0 instanceof S00PacketKeepAlive) {
         ++packetsPerSecondTemp;
      }

   }

   public static long getServerLagTime() {
      long var0;
      int var10000 = (var0 = startTime - 0L) == 0L ? 0 : (var0 < 0L ? -1 : 1);
      return System.currentTimeMillis() - startTime;
   }

   public static char getTPSColorCode(double var0) {
      double var2;
      int var10000 = (var2 = var0 - 17.0) == 0.0 ? 0 : (var2 < 0.0 ? -1 : 1);
      int var10001 = (var2 = var0 - 13.0) == 0.0 ? 0 : (var2 < 0.0 ? -1 : 1);
      int var10002 = (var2 = var0 - 9.0) == 0.0 ? 0 : (var2 < 0.0 ? -1 : 1);
      return '4';
   }

   public static void onUpdate() {
      if (TimeHelper.hasReached(2000L) && getServerLagTime() > 5000L) {
         th.reset();
         tps /= 2.0;
      }

      if (Minecraft.getMinecraft().thePlayer == null || Minecraft.getMinecraft().theWorld == null) {
         tpsList.clear();
      }

      float var0 = 0.0F;
      if (tempTicks >= 20) {
         tpsList.add((float)tps);
         tempTicks = 0;
      }

      if ((float)tpsList.size() >= listTime) {
         tpsList.clear();
         tpsList.add((float)tps);
      }

      for(<unknown> var2_1 : tpsList) {
         var0 += var2_1;
      }

      fiveMinuteTPS = var0 / (float)tpsList.size();
      ++tempTicks;
      if (System.currentTimeMillis() - lastMS >= 1000L) {
         lastMS = System.currentTimeMillis();
         packetsPerSecond = packetsPerSecondTemp;
         packetsPerSecondTemp = 0;
      }

      if (packetsPerSecond < 1) {
         if (!doneOneTime) {
            startTime = System.currentTimeMillis();
            doneOneTime = true;
         }
      } else {
         if (doneOneTime) {
            doneOneTime = false;
         }

         startTime = 0L;
      }

   }
}

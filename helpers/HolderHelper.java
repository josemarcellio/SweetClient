package SweetClient.helpers;

import java.util.ArrayList;
import java.util.List;

public class HolderHelper {
   public static double TPS = -1.0;
   public static long lastPacketMS = -1L;
   public static TimeHelper TIME_HELPER = new TimeHelper();
   public static List<Long> TPS_TIMES = new ArrayList();

   public static void setTPS(double var0) {
      TPS = var0;
   }

   public static double getTPS() {
      return TPS;
   }

   public static long getLastPacketMS() {
      return lastPacketMS;
   }

   public static List<Long> getTpsTimes() {
      return TPS_TIMES;
   }

   public static void setLastPacketMS(long var0) {
      lastPacketMS = var0;
   }

   public static TimeHelper getTimeHelper() {
      return TIME_HELPER;
   }
}

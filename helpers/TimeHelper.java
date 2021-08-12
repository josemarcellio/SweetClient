package SweetClient.helpers;

import java.time.Instant;
import java.util.ArrayList;

public class TimeHelper {
   public long time = System.nanoTime() / 1000000L;
   public static ArrayList<Long> times = new ArrayList();
   public boolean enabling;
   public static double lastTps = 20.0;
   public int time1;
   public static long currentCalcLag;
   public long tick;
   public static long lastMS = 0L;
   public long prevMS = 0L;
   public static TimeHelper tpsTimer = new TimeHelper();

   public static double calcDuration(double var0) {
      return getCurrentTime() - var0;
   }

   public long getTime() {
      return System.nanoTime() / 1000000L - this.time;
   }

   public static long getCurrentMillis() {
      return Instant.now().toEpochMilli();
   }

   public static double fromMillis(long var0) {
      return (double)var0 / 1000.0;
   }

   public void updateTick() {
      ++this.tick;
   }

   public long getTimeing() {
      return System.nanoTime() / 1000L;
   }

   public long getDifference() {
      return this.getTime() - this.prevMS;
   }

   public static long getCurrentMS() {
      return System.nanoTime() / 1000000L;
   }

   public void reset() {
      this.time = System.nanoTime() / 1000000L;
   }

   public static long bytesToMb(long var0) {
      return var0 / 1024L / 1024L;
   }

   public void resetss() {
      this.prevMS = this.getTime();
   }

   public static double calcDuration(double var0, double var2) {
      return var2 - var0;
   }

   public static void resets() {
      lastMS = getCurrentMS();
   }

   public int getTime1() {
      return this.time1;
   }

   public static long getLag() {
      return System.currentTimeMillis() - currentCalcLag;
   }

   public boolean hasTimePassedTick(long var1) {
      return this.tick >= var1;
   }

   public boolean isDelayComplete(float var1) {
      return (float)(System.currentTimeMillis() - lastMS) >= var1;
   }

   public static long getFormattedLag() {
      long var0 = getLag();
      if (var0 >= 2500L) {
         long var2;
         int var10000 = (var2 = var0 - 5000000L) == 0L ? 0 : (var2 < 0L ? -1 : 1);
      }

      return 0L;
   }

   public static boolean hasReached(long var0) {
      return getCurrentMS() - lastMS >= var0;
   }

   public static double getCurrentTime() {
      return fromMillis(getCurrentMillis());
   }

   public void setDifference(long var1) {
      this.prevMS = this.getTime() - var1;
   }

   public void setLastMS(long var1) {
      lastMS = System.currentTimeMillis();
   }

   public long getTick() {
      return this.tick;
   }

   public int convertToMS(int var1) {
      return 1000 / var1;
   }

   public static double calcDuration(double var0, long var2) {
      return calcDuration(var0, fromMillis(var2));
   }

   public void update() {
      this.time = this.enabling ? ++this.time : --this.time;
      if (this.time < 0L) {
         this.time = 0L;
      }

      if (this.time > (long)this.getMaxTime()) {
         this.time = (long)this.getMaxTime();
      }

   }

   public int getTiming() {
      return this.time1;
   }

   public void resetTick() {
      this.tick = 0L;
   }

   public int getMaxTime() {
      return 10;
   }

   public void on() {
      this.enabling = true;
   }

   public boolean delay(float var1) {
      return (float)(this.getTime() - this.prevMS) >= var1;
   }

   public boolean hasTimeElapsed(long var1, boolean var3) {
      if (this.getTime() >= var1) {
         if (var3) {
            this.reset();
         }

         return true;
      } else {
         return false;
      }
   }

   public long getTimes() {
      return System.nanoTime() / 1000000L;
   }

   public void updateTiming() {
      ++this.time1;
   }

   public void reset1() {
      this.time = 0L;
   }

   public void deleteTiming() {
      --this.time1;
   }
}

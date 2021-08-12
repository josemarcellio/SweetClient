package SweetClient.helpers;

public class TickHelper {
   public long tick;

   public long get() {
      return this.tick;
   }

   public boolean hasTimePassed(long var1) {
      return this.tick >= var1;
   }

   public void reset() {
      this.tick = 0L;
   }

   public void update() {
      ++this.tick;
   }
}

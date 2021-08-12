package SweetClient.managers;

import SweetClient.mods.alts.Alt;
import java.util.ArrayList;

public class AltManager {
   public static ArrayList<Alt> registry = new ArrayList();
   public static Alt lastAlt;

   public ArrayList<Alt> getRegistry() {
      return registry;
   }

   public void setLastAlt(Alt var1) {
      lastAlt = var1;
   }
}

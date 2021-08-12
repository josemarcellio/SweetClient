package SweetClient.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class ChatHelper {
   public static Minecraft mc = Minecraft.getMinecraft();
   public static String PREFIX = "&d&lSweet&f&lClient";

   public static void printMessage(String var0, boolean var1) {
      if (var1) {
         mc.thePlayer.addChatMessage(new ChatComponentText(fix("&d&lSweet&f&lClient &8>> &5" + var0)));
      } else {
         mc.thePlayer.addChatMessage(new ChatComponentText(fix("" + var0)));
      }

   }

   public static void printMessage(String var0) {
      printMessage(var0, true);
   }

   public static String fix(String var0) {
      return var0.replace('&', '§').replace(">>", "»");
   }
}

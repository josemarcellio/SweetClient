package SweetClient;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.DiscordRichPresence.Builder;
import net.arikia.dev.drpc.callbacks.ReadyCallback;
import net.minecraft.client.Minecraft;

public class DiscordRP implements ReadyCallback {
   public DiscordRichPresence richPresence = (new Builder("Loading SweetClient...")).setBigImage("sweetlogo2", "SweetClient b1.6 by BlackCrack SQUAD x AvarionCode").setSmallImage("blackcrack2", "www.youtube.com/c/FixMem").setDetails("Loading SweetClient...").setStartTimestamps(System.currentTimeMillis()).build();
   public static Minecraft mc = Minecraft.getMinecraft();

   public DiscordRP() {
      this.init();
      this.startTask();
      DiscordRPC.discordUpdatePresence(this.richPresence);
   }

   public void apply(DiscordUser var1) {
      System.out.println("Initialized DiscordRichPresence API.");
   }

   public DiscordRichPresence getRichPresence() {
      return this.richPresence;
   }

   public void lambda$startTask$1() {
      this.richPresence.details = mc.thePlayer == null ? "Main Menu" : "Nick: " + mc.session.getUsername();
      this.richPresence.state = mc.getCurrentServerData() == null ? "Offline" : "Server: " + mc.getCurrentServerData().serverIP;
      DiscordRPC.discordUpdatePresence(this.richPresence);
   }

   public static void lambda$init$0(DiscordUser var0) {
      System.out.printf("Connected to %s#%s (%s)%n", var0.username, var0.discriminator, var0.userId);
   }

   public void startTask() {
      Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(this::lambda$startTask$1, 10L, 10L, TimeUnit.SECONDS);
   }

   public void init() {
      DiscordEventHandlers var1 = (new net.arikia.dev.drpc.DiscordEventHandlers.Builder()).setReadyEventHandler(DiscordRP::lambda$init$0).build();
      DiscordRPC.discordInitialize("839833621731999745", var1, true);
   }
}

package SweetClient.mods.alts;

import SweetClient.managers.AltManager;
import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import java.net.Proxy;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Session;

public class AltLoginThread extends Thread {
   public String status;
   public String password;
   public Minecraft mc = Minecraft.getMinecraft();
   public String username;

   public Session createSession(String var1, String var2) {
      YggdrasilAuthenticationService var3 = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
      YggdrasilUserAuthentication var4 = (YggdrasilUserAuthentication)var3.createUserAuthentication(Agent.MINECRAFT);
      var4.setUsername(var1);
      var4.setPassword(var2);

      try {
         var4.logIn();
         return new Session(var4.getSelectedProfile().getName(), var4.getSelectedProfile().getId().toString(), var4.getAuthenticatedToken(), "mojang");
      } catch (AuthenticationException var6) {
         var6.printStackTrace();
         return null;
      }
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String var1) {
      this.status = var1;
   }

   public void run() {
      if (this.password.equals("")) {
         this.mc.session = new Session(this.username, "", "", "mojang");
         this.status = EnumChatFormatting.GREEN + "Logged in. (" + this.username + " - offline name)";
      } else {
         this.status = EnumChatFormatting.YELLOW + "Logging in...";
         Session var1 = this.createSession(this.username, this.password);
         if (var1 == null) {
            this.status = EnumChatFormatting.RED + "Login failed!";
         } else {
            AltManager.lastAlt = new Alt(this.username, this.password);
            this.status = EnumChatFormatting.GREEN + "Logged in. (" + var1.getUsername() + ")";
            this.mc.session = var1;
         }

      }
   }

   public AltLoginThread(String var1, String var2) {
      super("Alt Login Thread");
      this.username = var1;
      this.password = var2;
      this.status = EnumChatFormatting.GRAY + "Waiting...";
   }
}

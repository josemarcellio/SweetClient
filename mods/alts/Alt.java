package SweetClient.mods.alts;

public class Alt {
   public String username;
   public String mask;
   public String password;

   public Alt(String var1, String var2, String var3) {
      this.username = var1;
      this.password = var2;
      this.mask = var3;
   }

   public String getMask() {
      return this.mask;
   }

   public String getPassword() {
      return this.password;
   }

   public String getUsername() {
      return this.username;
   }

   public void setMask(String var1) {
      this.mask = var1;
   }

   public void setPassword(String var1) {
      this.password = var1;
   }

   public Alt(String var1, String var2) {
      this(var1, var2, "");
   }
}

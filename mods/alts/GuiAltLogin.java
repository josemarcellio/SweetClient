package SweetClient.mods.alts;

import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

public class GuiAltLogin extends GuiScreen {
   public AltLoginThread thread;
   public GuiScreen previousScreen;
   public GuiTextField username;
   public PasswordField password;

   public void actionPerformed(GuiButton var1) {
      int var2 = var1.id;
      if (var2 != 0) {
         if (var2 == 1) {
            this.mc.displayGuiScreen(this.previousScreen);
         }
      } else {
         this.thread = new AltLoginThread(this.username.getText(), this.password.getText());
         this.thread.start();
      }

   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.username.drawTextBox();
      this.password.drawTextBox();
      drawCenteredString(this.mc.fontRendererObj, "Alt Login", width / 2, 20, -1);
      drawCenteredString(this.mc.fontRendererObj, this.thread == null ? EnumChatFormatting.GRAY + "Idle..." : this.thread.getStatus(), width / 2, 29, -1);
      if (this.username.getText().isEmpty()) {
         this.drawString(this.mc.fontRendererObj, "Username / E-Mail", width / 2 - 96, 66, -7829368);
      }

      if (this.password.getText().isEmpty()) {
         this.drawString(this.mc.fontRendererObj, "Password", width / 2 - 96, 106, -7829368);
      }

      super.drawScreen(var1, var2, var3);
   }

   public void initGui() {
      int var1 = height / 4 + 24;
      this.buttonList.add(new GuiButton(0, width / 2 - 100, var1 + 72 + 12, "Login"));
      this.buttonList.add(new GuiButton(1, width / 2 - 100, var1 + 72 + 12 + 24, "Back"));
      this.username = new GuiTextField(var1, this.mc.fontRendererObj, width / 2 - 100, 60, 200, 20);
      this.password = new PasswordField(this.mc.fontRendererObj, width / 2 - 100, 100, 200, 20);
      this.username.setFocused(true);
      Keyboard.enableRepeatEvents(true);
   }

   public void keyTyped(char var1, int var2) {
      try {
         super.keyTyped(var1, var2);
      } catch (IOException var4) {
         var4.printStackTrace();
      }

      if (var1 == '\t') {
         if (!this.username.isFocused() && !this.password.isFocused()) {
            this.username.setFocused(true);
         } else {
            this.username.setFocused(this.password.isFocused());
            this.password.setFocused(!this.username.isFocused());
         }
      }

      if (var1 == '\r') {
         this.actionPerformed((GuiButton)this.buttonList.get(0));
      }

      this.username.textboxKeyTyped(var1, var2);
      this.password.textboxKeyTyped(var1, var2);
   }

   public void updateScreen() {
      this.username.updateCursorCounter();
      this.password.updateCursorCounter();
   }

   public void mouseClicked(int var1, int var2, int var3) {
      try {
         super.mouseClicked(var1, var2, var3);
      } catch (IOException var5) {
         var5.printStackTrace();
      }

      this.username.mouseClicked(var1, var2, var3);
      this.password.mouseClicked(var1, var2, var3);
   }

   public GuiAltLogin(GuiScreen var1) {
      this.previousScreen = var1;
   }
}

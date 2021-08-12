package SweetClient.mods.alts;

import SweetClient.managers.GuiAltManager;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.EnumChatFormatting;

public class GuiRenameAlt extends GuiScreen {
   public GuiAltManager manager;
   public String status = EnumChatFormatting.GRAY + "Waiting...";
   public PasswordField pwField;
   public GuiTextField nameField;

   public void initGui() {
      this.buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 92 + 12, "Edit"));
      this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 116 + 12, "Cancel"));
      this.nameField = new GuiTextField(this.eventButton, this.mc.fontRendererObj, width / 2 - 100, 60, 200, 20);
      this.pwField = new PasswordField(this.mc.fontRendererObj, width / 2 - 100, 100, 200, 20);
   }

   public void actionPerformed(GuiButton var1) {
      int var2 = var1.id;
      if (var2 != 0) {
         if (var2 == 1) {
            this.mc.displayGuiScreen(this.manager);
         }
      } else {
         this.manager.selectedAlt.setMask(this.nameField.getText());
         this.manager.selectedAlt.setPassword(this.pwField.getText());
         this.status = "Edited!";
      }

   }

   public void mouseClicked(int var1, int var2, int var3) {
      try {
         super.mouseClicked(var1, var2, var3);
      } catch (IOException var5) {
         var5.printStackTrace();
      }

      this.nameField.mouseClicked(var1, var2, var3);
      this.pwField.mouseClicked(var1, var2, var3);
   }

   public GuiRenameAlt(GuiAltManager var1) {
      this.manager = var1;
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      drawCenteredString(this.fontRendererObj, "Edit Alt", width / 2, 10, -1);
      drawCenteredString(this.fontRendererObj, this.status, width / 2, 20, -1);
      this.nameField.drawTextBox();
      this.pwField.drawTextBox();
      if (this.nameField.getText().isEmpty()) {
         this.drawString(this.mc.fontRendererObj, "New name", width / 2 - 96, 66, -7829368);
      }

      if (this.pwField.getText().isEmpty()) {
         this.drawString(this.mc.fontRendererObj, "New password", width / 2 - 96, 106, -7829368);
      }

      super.drawScreen(var1, var2, var3);
   }

   public void keyTyped(char var1, int var2) {
      this.nameField.textboxKeyTyped(var1, var2);
      this.pwField.textboxKeyTyped(var1, var2);
      if (var1 == '\t' && (this.nameField.isFocused() || this.pwField.isFocused())) {
         this.nameField.setFocused(!this.nameField.isFocused());
         this.pwField.setFocused(!this.pwField.isFocused());
      }

      if (var1 == '\r') {
         this.actionPerformed((GuiButton)this.buttonList.get(0));
      }

   }
}

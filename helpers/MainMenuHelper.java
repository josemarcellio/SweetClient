package SweetClient.helpers;

import SweetClient.managers.GuiAltManager;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.util.ResourceLocation;

public class MainMenuHelper extends GuiScreen {
   public int speed;
   public int fade;
   public boolean start;
   public boolean In;
   public int smoth = 17;

   public static void lambda$initGui$0() {
   }

   public void fadeSideBar(int var1) {
      if (var1 > width - 150 - 4) {
         if (this.fade >= width - 150) {
            this.fade -= this.speed;
            if (this.speed != 2) {
               --this.speed;
            }

            this.buttonList.clear();
            this.createButtons();
            this.In = false;
         } else {
            this.fade = width - 150 - 1;
         }
      } else {
         if (this.fade <= width) {
            this.fade += this.speed;
            if (this.speed != 2) {
               this.speed = 17;
            }

            this.buttonList.clear();
            this.createButtons();
            this.In = true;
         } else {
            this.fade = width + 1;
         }

         this.speed = 17;
      }

   }

   public void initGui() {
      if (!this.start) {
         this.start = true;
      }

      this.fade = GuiScreen.width;
      this.speed = 17;
      (new Thread(MainMenuHelper::lambda$initGui$0)).start();
      this.createButtons();
   }

   public MainMenuHelper() {
      this.fade = 0;
      this.speed = 0;
      this.In = false;
      this.start = false;
   }

   public void createButtons() {
      byte var1 = 90;
      this.buttonList.add(new GuiButton(0, this.fade + 30, height / 2 - 150 + var1, 100, 20, "Singleplayer"));
      this.buttonList.add(new GuiButton(1, this.fade + 30, height / 2 - 125 + var1, 100, 20, "Multiplayer"));
      this.buttonList.add(new GuiButton(2, this.fade + 30, height / 2 - 100 + var1, 100, 20, "Settings"));
      this.buttonList.add(new GuiButton(3, this.fade + 30, height / 2 - 75 + var1, 100, 20, "AltManager"));
      this.buttonList.add(new GuiButton(4, this.fade + 30, height / 2 - 50 + var1, 100, 20, "Quit"));
      this.buttonList.add(new GuiButton(5, this.fade + 150, 700 + var1, 150, 20, ""));
   }

   public void actionPerformed(GuiButton var1) {
      if (var1.id == 0) {
         this.mc.displayGuiScreen(new GuiSelectWorld(new MainMenuHelper()));
      }

      if (var1.id == 1) {
         this.mc.displayGuiScreen(new GuiMultiplayer(new MainMenuHelper()));
      }

      if (var1.id == 2) {
         this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
      }

      if (var1.id == 3) {
         this.mc.displayGuiScreen(new GuiAltManager());
      }

      if (var1.id == 4) {
         this.mc.shutdown();
      }

   }

   public void onGuiClosed() {
      this.start = false;
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.mc.getTextureManager().bindTexture(new ResourceLocation("SweetClient/menu/SweetMenu.png"));
      drawModalRectWithCustomSizedTexture(0, 0, 0.0F, 0.0F, width, height, (float)width, (float)height);
      this.fadeSideBar(var1);
      Gui.drawRect(this.fade, 0, width, height, Integer.MIN_VALUE);
      Gui.drawRect(this.fade + 2, 0, this.fade, height, 15597803);
      ClientRenderHelper.INSTANCE.drawLine(this.fade + 1, height / 2 + 30, this.fade - 20, height / 2, 10, 0L, 15597803);
      ClientRenderHelper.INSTANCE.drawLine(this.fade + 1, height / 2 - 30, this.fade - 20, height / 2, 10, 0L, 15597803);
      if (this.In) {
         this.mc.fontRendererObj.drawString("§0§l(§5§l<§0§l)", this.fade - 20, height / 2 - 3, -1);
      } else {
         this.mc.fontRendererObj.drawString("§0§l(§5§l>§0§l)", this.fade - 20, height / 2 - 3, -1);
      }

      super.drawScreen(var1, var2, var3);
   }
}

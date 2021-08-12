package SweetClient.managers;

import SweetClient.helpers.RectangleHelper;
import SweetClient.mods.alts.Alt;
import SweetClient.mods.alts.AltLoginThread;
import SweetClient.mods.alts.GuiAddAlt;
import SweetClient.mods.alts.GuiAltLogin;
import SweetClient.mods.alts.GuiRenameAlt;
import java.io.IOException;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiAltManager extends GuiScreen {
   public GuiButton login;
   public int offset;
   public GuiButton rename;
   public String status;
   public AltLoginThread loginThread;
   public Alt selectedAlt = null;
   public GuiButton remove;

   public void mouseClicked(int var1, int var2, int var3) throws IOException {
      if (this.offset < 0) {
         this.offset = 0;
      }

      int var4 = 38 - this.offset;

      for(Object var6 : AltManager.registry) {
         if (this.isMouseOverAlt(var1, var2, var4)) {
            if (var6 == this.selectedAlt) {
               this.actionPerformed((GuiButton)this.buttonList.get(1));
               return;
            }

            this.selectedAlt = (Alt)(Alt)var6;
         }

         var4 += 26;
      }

      try {
         super.mouseClicked(var1, var2, var3);
      } catch (IOException var7) {
         var7.printStackTrace();
      }

   }

   public void drawScreen(int var1, int var2, float var3) {
      if (Mouse.hasWheel()) {
         int var4 = Mouse.getDWheel();
         if (var4 < 0) {
            this.offset += 26;
            if (this.offset < 0) {
               this.offset = 0;
            }
         } else if (var4 > 0) {
            this.offset -= 26;
            if (this.offset < 0) {
               this.offset = 0;
            }
         }
      }

      this.drawDefaultBackground();
      this.drawString(this.fontRendererObj, "Name: " + this.mc.session.getUsername(), 10, 10, -7829368);
      FontRenderer var11 = this.fontRendererObj;
      StringBuilder var5 = new StringBuilder("Alt Manager - ");
      drawCenteredString(var11, var5.append(AltManager.registry.size()).append(" alts").toString(), width / 2, 10, -1);
      drawCenteredString(this.fontRendererObj, this.loginThread == null ? this.status : this.loginThread.getStatus(), width / 2, 20, -1);
      GL11.glPushMatrix();
      this.prepareScissorBox(0.0F, 33.0F, (float)width, (float)(height - 50));
      GL11.glEnable(3089);
      int var6 = 38;

      for(<unknown> var8_1 : AltManager.registry) {
         if (var6 == 0) {
            String var9 = var8_1.getMask().equals("") ? var8_1.getUsername() : var8_1.getMask();
            String var10 = var8_1.getPassword().equals("") ? "Cracked" : var8_1.getPassword().replaceAll(". ", " * ");
            if (var8_1 == this.selectedAlt) {
               if (this.isMouseOverAlt(var1, var2, var6 - this.offset) && Mouse.isButtonDown(0)) {
                  RectangleHelper.drawBorderedRect(52.0F, (float)(var6 - this.offset - 4), (float)(width - 52), (float)(var6 - this.offset + 20), 1.0F, -16777216, -2142943931);
               } else if (this.isMouseOverAlt(var1, var2, var6 - this.offset)) {
                  RectangleHelper.drawBorderedRect(52.0F, (float)(var6 - this.offset - 4), (float)(width - 52), (float)(var6 - this.offset + 20), 1.0F, -16777216, -2142088622);
               } else {
                  RectangleHelper.drawBorderedRect(52.0F, (float)(var6 - this.offset - 4), (float)(width - 52), (float)(var6 - this.offset + 20), 1.0F, -16777216, -2144259791);
               }
            } else if (this.isMouseOverAlt(var1, var2, var6 - this.offset) && Mouse.isButtonDown(0)) {
               RectangleHelper.drawBorderedRect(52.0F, (float)(var6 - this.offset - 4), (float)(width - 52), (float)(var6 - this.offset + 20), 1.0F, -16777216, -2146101995);
            } else if (this.isMouseOverAlt(var1, var2, var6 - this.offset)) {
               RectangleHelper.drawBorderedRect(52.0F, (float)(var6 - this.offset - 4), (float)(width - 52), (float)(var6 - this.offset + 20), 1.0F, -16777216, -2145180893);
            }

            drawCenteredString(this.fontRendererObj, var9, width / 2, var6 - this.offset, -1);
            drawCenteredString(this.fontRendererObj, var10, width / 2, var6 - this.offset + 10, 5592405);
            var6 += 26;
         }
      }

      GL11.glDisable(3089);
      GL11.glPopMatrix();
      super.drawScreen(var1, var2, var3);
      if (this.selectedAlt == null) {
         this.login.enabled = false;
         this.remove.enabled = false;
         this.rename.enabled = false;
      } else {
         this.login.enabled = true;
         this.remove.enabled = true;
         this.rename.enabled = true;
      }

      if (Keyboard.isKeyDown(200)) {
         this.offset -= 26;
         if (this.offset < 0) {
            this.offset = 0;
         }
      } else if (Keyboard.isKeyDown(208)) {
         this.offset += 26;
         if (this.offset < 0) {
            this.offset = 0;
         }
      }

   }

   public void prepareScissorBox(float var1, float var2, float var3, float var4) {
      ScaledResolution var5 = new ScaledResolution(this.mc);
      int var6 = var5.getScaleFactor();
      GL11.glScissor((int)(var1 * (float)var6), (int)(((float)var5.getScaledHeight() - var4) * (float)var6), (int)((var3 - var1) * (float)var6), (int)((var4 - var2) * (float)var6));
   }

   public boolean isMouseOverAlt(int var1, int var2, int var3) {
      return var1 >= 52 && var2 >= var3 - 4 && var1 <= width - 52 && var2 <= var3 + 20 && var2 >= 33 && var1 <= width && var2 <= height - 50;
   }

   public void actionPerformed(GuiButton var1) throws IOException {
      int var4 = var1.id;
      if (var4 != 0) {
         if (var4 != 1) {
            if (var4 != 2) {
               if (var4 != 3) {
                  if (var4 != 4) {
                     if (var4 != 5 && var4 == 6) {
                        this.mc.displayGuiScreen(new GuiRenameAlt(this));
                     }
                  } else {
                     this.mc.displayGuiScreen(new GuiAltLogin(this));
                  }
               } else {
                  this.mc.displayGuiScreen(new GuiAddAlt(this));
               }
            } else {
               if (this.loginThread != null) {
                  this.loginThread = null;
               }

               AltManager.registry.remove(this.selectedAlt);
               this.status = "Removed";
               this.selectedAlt = null;
            }
         } else {
            String var2 = this.selectedAlt.getUsername();
            String var3 = this.selectedAlt.getPassword();
            this.loginThread = new AltLoginThread(var2, var3);
            this.loginThread.start();
         }
      } else if (this.loginThread == null) {
         this.mc.displayGuiScreen((GuiScreen)null);
      } else if (!this.loginThread.getStatus().equals(EnumChatFormatting.YELLOW + "Attempting to log in") && !this.loginThread.getStatus().equals(EnumChatFormatting.RED + "Do not hit back!" + EnumChatFormatting.YELLOW + " Logging in...")) {
         this.mc.displayGuiScreen((GuiScreen)null);
      } else {
         this.loginThread.setStatus(EnumChatFormatting.RED + "Failed to login! Please try again!" + EnumChatFormatting.YELLOW + " Logging in...");
      }

   }

   public GuiAltManager() {
      this.status = EnumChatFormatting.GRAY + "No alts selected";
   }

   public boolean isAltInArea(int var1) {
      return var1 - this.offset <= height - 50;
   }

   public void initGui() {
      this.buttonList.add(new GuiButton(0, width / 2 + 4 + 50, height - 24, 100, 20, "Back"));
      this.login = new GuiButton(1, width / 2 - 154, height - 48, 100, 20, "Login");
      this.buttonList.add(this.login);
      this.remove = new GuiButton(2, width / 2 - 154, height - 24, 100, 20, "Remove");
      this.buttonList.add(this.remove);
      this.buttonList.add(new GuiButton(3, width / 2 + 4 + 50, height - 48, 100, 20, "Add"));
      this.buttonList.add(new GuiButton(4, width / 2 - 50, height - 48, 100, 20, "Direct Login"));
      this.rename = new GuiButton(6, width / 2 - 50, height - 24, 100, 20, "Edit");
      this.buttonList.add(this.rename);
      this.login.enabled = false;
      this.remove.enabled = false;
      this.rename.enabled = false;
   }
}

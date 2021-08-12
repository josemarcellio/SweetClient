package SweetClient.mods.alts;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.opengl.GL11;

public class PasswordField extends Gui {
   public int height;
   public FontRenderer fontRenderer;
   public int enabledColor;
   public String text = "";
   public int cursorPosition;
   public int yPos;
   public int i;
   public boolean isFocused;
   public int xPos;
   public int disabledColor;
   public boolean isEnabled;
   public boolean b;
   public int cursorCounter;
   public int maxStringLength = 50;
   public boolean enableBackgroundDrawing = true;
   public boolean canLoseFocus = true;
   public int selectionEnd;
   public int width;

   public void setText(String var1) {
      if (var1.length() > this.maxStringLength) {
         this.text = var1.substring(0, this.maxStringLength);
      } else {
         this.text = var1;
      }

      this.setCursorPositionEnd();
   }

   public void setCursorPositionZero() {
      this.setCursorPosition(0);
   }

   public void deleteFromCursor(int var1) {
      if (this.text.length() != 0) {
         if (this.selectionEnd != this.cursorPosition) {
            this.writeText("");
         } else {
            boolean var2 = var1 < 0;
            int var3 = var2 ? this.cursorPosition + var1 : this.cursorPosition;
            int var4 = var2 ? this.cursorPosition : this.cursorPosition + var1;
            String var5 = "";
            if (var3 >= 0) {
               var5 = this.text.substring(0, var3);
            }

            if (var4 < this.text.length()) {
               var5 = var5 + this.text.substring(var4);
            }

            this.text = var5;
            if (var2) {
               this.cursorPos(var1);
            }
         }
      }

   }

   public void writeText(String var1) {
      String var3 = "";
      String var4 = ChatAllowedCharacters.filterAllowedCharacters(var1);
      int var5 = Math.min(this.cursorPosition, this.selectionEnd);
      int var6 = Math.max(this.cursorPosition, this.selectionEnd);
      int var7 = this.maxStringLength - this.text.length() - var5 - this.selectionEnd;
      if (this.text.length() > 0) {
         var3 = var3 + this.text.substring(0, var5);
      }

      int var2;
      if (var7 < var4.length()) {
         var3 = var3 + var4.substring(0, var7);
         var2 = var7;
      } else {
         var3 = var3 + var4;
         var2 = var4.length();
      }

      if (this.text.length() > 0 && var6 < this.text.length()) {
         var3 = var3 + this.text.substring(var6);
      }

      this.text = var3.replaceAll(" ", "");
      this.cursorPos(var5 - this.selectionEnd + var2);
   }

   public int getNthWordFromCursor(int var1) {
      return this.getNthWordFromPos(var1);
   }

   public void updateCursorCounter() {
      ++this.cursorCounter;
   }

   public String getText() {
      return this.text.replaceAll(" ", "");
   }

   public void mouseClicked(int var1, int var2, int var3) {
      boolean var4 = var1 >= this.xPos && var1 < this.xPos + this.width && var2 >= this.yPos && var2 < this.yPos + this.height;
      if (this.canLoseFocus) {
         this.setFocused(this.isEnabled && var4);
      }

      if (this.isFocused && var3 == 0) {
         int var5 = var1 - this.xPos;
         if (this.enableBackgroundDrawing) {
            var5 -= 4;
         }

         String var6 = this.fontRenderer.trimStringToWidth(this.text.substring(this.i), this.getWidth());
         this.setCursorPosition(this.fontRenderer.trimStringToWidth(var6, var5).length() + this.i);
      }

   }

   public void setCursorPositionEnd() {
      this.setCursorPosition(this.text.length());
   }

   public boolean isFocused() {
      return this.isFocused;
   }

   public void drawCursorVertical(int var1, int var2, int var3, int var4) {
      if (var1 < var3) {
         int var5 = var1;
         var1 = var3;
         var3 = var5;
      }

      if (var2 < var4) {
         int var7 = var2;
         var2 = var4;
         var4 = var7;
      }

      Tessellator var8 = Tessellator.getInstance();
      WorldRenderer var6 = var8.getWorldRenderer();
      GL11.glColor4f(0.0F, 0.0F, 255.0F, 255.0F);
      GL11.glDisable(3553);
      GL11.glEnable(3058);
      GL11.glLogicOp(5387);
      var6.begin(7, var6.getVertexFormat());
      var6.pos((double)var1, (double)var4, 0.0);
      var6.pos((double)var3, (double)var4, 0.0);
      var6.pos((double)var3, (double)var2, 0.0);
      var6.pos((double)var1, (double)var2, 0.0);
      var6.finishDrawing();
      GL11.glDisable(3058);
      GL11.glEnable(3553);
   }

   public void setFocused(boolean var1) {
      if (var1 && !this.isFocused) {
         this.cursorCounter = 0;
      }

      this.isFocused = var1;
   }

   public boolean getEnableBackgroundDrawing() {
      return this.enableBackgroundDrawing;
   }

   public String getSelectedtext() {
      int var1 = Math.min(this.cursorPosition, this.selectionEnd);
      int var2 = Math.max(this.cursorPosition, this.selectionEnd);
      return this.text.substring(var1, var2);
   }

   public int getNthWordFromPos(int var1) {
      return this.type(var1, this.getCursorPosition(), true);
   }

   public int getCursorPosition() {
      return this.cursorPosition;
   }

   public void cursorPos(int var1) {
      this.setCursorPosition(this.selectionEnd + var1);
   }

   public int getSelectionEnd() {
      return this.selectionEnd;
   }

   public void func_73800_i(int var1) {
      int var2 = this.text.length();
      if (var1 > var2) {
         var1 = var2;
      }

      if (var1 < 0) {
         var1 = 0;
      }

      this.selectionEnd = var1;
      if (this.fontRenderer != null) {
         if (this.i > var2) {
            this.i = var2;
         }

         int var3 = this.getWidth();
         String var4 = this.fontRenderer.trimStringToWidth(this.text.substring(this.i), var3);
         int var5 = var4.length() + this.i;
         if (var1 == this.i) {
            this.i -= this.fontRenderer.trimStringToWidth(this.text, var3, true).length();
         }

         if (var1 > var5) {
            this.i += var1 - var5;
         } else if (var1 <= this.i) {
            this.i -= this.i - var1;
         }

         if (this.i < 0) {
            this.i = 0;
         }

         if (this.i > var2) {
            this.i = var2;
         }
      }

   }

   public boolean textboxKeyTyped(char var1, int var2) {
      if (this.isEnabled && this.isFocused) {
         if (var1 != 1) {
            if (var1 != 3) {
               if (var1 != 22) {
                  if (var1 != 24) {
                     if (var2 != 14) {
                        if (var2 != 199) {
                           if (var2 != 203) {
                              if (var2 != 205) {
                                 if (var2 != 207) {
                                    if (var2 != 211) {
                                       if (ChatAllowedCharacters.isAllowedCharacter(var1)) {
                                          this.writeText(Character.toString(var1));
                                          return true;
                                       } else {
                                          return false;
                                       }
                                    } else {
                                       if (GuiScreen.isCtrlKeyDown()) {
                                          this.func_73779_a(1);
                                       } else {
                                          this.deleteFromCursor(1);
                                       }

                                       return true;
                                    }
                                 } else {
                                    if (GuiScreen.isShiftKeyDown()) {
                                       this.func_73800_i(this.text.length());
                                    } else {
                                       this.setCursorPositionEnd();
                                    }

                                    return true;
                                 }
                              } else {
                                 if (GuiScreen.isShiftKeyDown()) {
                                    if (GuiScreen.isCtrlKeyDown()) {
                                       this.func_73800_i(this.getNthWordFromPos(1));
                                    } else {
                                       this.func_73800_i(this.getSelectionEnd() + 1);
                                    }
                                 } else if (GuiScreen.isCtrlKeyDown()) {
                                    this.setCursorPosition(this.getNthWordFromCursor(1));
                                 } else {
                                    this.cursorPos(1);
                                 }

                                 return true;
                              }
                           } else {
                              if (GuiScreen.isShiftKeyDown()) {
                                 if (GuiScreen.isCtrlKeyDown()) {
                                    this.func_73800_i(this.getNthWordFromPos(-1));
                                 } else {
                                    this.func_73800_i(this.getSelectionEnd() - 1);
                                 }
                              } else if (GuiScreen.isCtrlKeyDown()) {
                                 this.setCursorPosition(this.getNthWordFromCursor(-1));
                              } else {
                                 this.cursorPos(-1);
                              }

                              return true;
                           }
                        } else {
                           if (GuiScreen.isShiftKeyDown()) {
                              this.func_73800_i(0);
                           } else {
                              this.setCursorPositionZero();
                           }

                           return true;
                        }
                     } else {
                        if (GuiScreen.isCtrlKeyDown()) {
                           this.func_73779_a(-1);
                        } else {
                           this.deleteFromCursor(-1);
                        }

                        return true;
                     }
                  } else {
                     GuiScreen.setClipboardString(this.getSelectedtext());
                     this.writeText("");
                     return true;
                  }
               } else {
                  this.writeText(GuiScreen.getClipboardString());
                  return true;
               }
            } else {
               GuiScreen.setClipboardString(this.getSelectedtext());
               return true;
            }
         } else {
            this.setCursorPositionEnd();
            this.func_73800_i(0);
            return true;
         }
      } else {
         return false;
      }
   }

   public int getWidth() {
      return this.getEnableBackgroundDrawing() ? this.width - 8 : this.width;
   }

   public void func_73779_a(int var1) {
      if (this.text.length() != 0) {
         if (this.selectionEnd != this.cursorPosition) {
            this.writeText("");
         } else {
            this.deleteFromCursor(this.getNthWordFromCursor(var1) - this.cursorPosition);
         }
      }

   }

   public void setCursorPosition(int var1) {
      this.cursorPosition = var1;
      int var2 = this.text.length();
      if (this.cursorPosition < 0) {
         this.cursorPosition = 0;
      }

      if (this.cursorPosition > var2) {
         this.cursorPosition = var2;
      }

      this.func_73800_i(this.cursorPosition);
   }

   public void drawTextBox() {
      if (this.func_73778_q()) {
         if (this.getEnableBackgroundDrawing()) {
            Gui.drawRect(this.xPos - 1, this.yPos - 1, this.xPos + this.width + 1, this.yPos + this.height + 1, -6250336);
            Gui.drawRect(this.xPos, this.yPos, this.xPos + this.width, this.yPos + this.height, -16777216);
         }

         int var1 = this.isEnabled ? this.enabledColor : this.disabledColor;
         int var2 = this.cursorPosition - this.i;
         int var3 = this.selectionEnd - this.i;
         String var4 = this.fontRenderer.trimStringToWidth(this.text.substring(this.i), this.getWidth());
         boolean var5 = var2 >= 0 && var2 <= var4.length();
         boolean var6 = this.isFocused && this.cursorCounter / 6 % 2 == 0 && var5;
         int var7 = this.enableBackgroundDrawing ? this.xPos + 4 : this.xPos;
         int var8 = this.enableBackgroundDrawing ? this.yPos + (this.height - 8) / 2 : this.yPos;
         int var9 = var7;
         if (var3 > var4.length()) {
            var3 = var4.length();
         }

         if (var4.length() > 0) {
            if (var5) {
               var4.substring(0, var2);
            }

            var9 = Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.text.replaceAll("(?s).", "*"), (float)var7, (float)var8, var1);
         }

         boolean var10 = this.cursorPosition < this.text.length() || this.text.length() >= this.getMaxStringLength();
         int var11 = var9;
         if (!var5) {
            var11 = var2 > 0 ? var7 + this.width : var7;
         } else if (var10) {
            var11 = var9 - 1;
            --var9;
         }

         if (var4.length() > 0 && var5 && var2 < var4.length()) {
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(var4.substring(var2), (float)var9, (float)var8, var1);
         }

         if (var6) {
            if (var10) {
               Gui.drawRect(var11, var8 - 1, var11 + 1, var8 + 1 + this.fontRenderer.FONT_HEIGHT, -3092272);
            } else {
               Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("_", (float)var11, (float)var8, var1);
            }
         }

         if (var3 != var2) {
            int var12 = var7 + this.fontRenderer.getStringWidth(var4.substring(0, var3));
            this.drawCursorVertical(var11, var8 - 1, var12 - 1, var8 + 1 + this.fontRenderer.FONT_HEIGHT);
         }
      }

   }

   public boolean func_73778_q() {
      return this.b;
   }

   public PasswordField(FontRenderer var1, int var2, int var3, int var4, int var5) {
      this.isFocused = false;
      this.isEnabled = true;
      this.i = 0;
      this.cursorPosition = 0;
      this.selectionEnd = 0;
      this.enabledColor = 14737632;
      this.disabledColor = 7368816;
      this.b = true;
      this.fontRenderer = var1;
      this.xPos = var2;
      this.yPos = var3;
      this.width = var4;
      this.height = var5;
   }

   public int type(int var1, int var2, boolean var3) {
      int var4 = var2;
      boolean var5 = var1 < 0;
      int var6 = Math.abs(var1);

      for(int var7 = 0; var7 < var6; ++var7) {
         if (!var5) {
            int var8 = this.text.length();
            var4 = this.text.indexOf(32, var4);
            if (var4 == -1) {
               var4 = var8;
            } else {
               while(var3 && var4 < var8 && this.text.charAt(var4) == ' ') {
                  ++var4;
               }
            }
         } else {
            while(var3 && var4 > 0 && this.text.charAt(var4 - 1) == ' ') {
               --var4;
            }

            while(var4 > 0 && this.text.charAt(var4 - 1) != ' ') {
               --var4;
            }
         }
      }

      return var4;
   }

   public int getMaxStringLength() {
      return this.maxStringLength;
   }
}

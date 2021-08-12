package SweetClient.helpers;

import java.awt.Color;
import net.minecraft.client.gui.Gui;

public class RenderHelper extends Gui {
   public void serverDataStress(int var1, int var2, int var3) {
      drawHorizontalLine(var1, var2, var3, Color.GRAY.getRGB());
   }
}

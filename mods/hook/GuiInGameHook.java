package SweetClient.mods.hook;

import SweetClient.SweetClient;
import SweetClient.command.impl.HudCommand;
import SweetClient.helpers.ChatHelper;
import SweetClient.helpers.HolderHelper;
import SweetClient.helpers.PacketHelper;
import SweetClient.helpers.TimeHelper;
import SweetClient.mods.chunkanimator.ChunkAnimator;
import java.text.DecimalFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiInGameHook extends GuiIngame {
   public DecimalFormat df = new DecimalFormat("#.##");
   public static ChunkAnimator chunkAnimator;
   public static Minecraft mc = Minecraft.getMinecraft();

   public GuiInGameHook(Minecraft var1) {
      super(var1);
      chunkAnimator = new ChunkAnimator(mc);
   }

   public void renderGameOverlay(float var1) {
      super.renderGameOverlay(var1);
      if (!mc.isSingleplayer() && !GameSettings.showDebugInfo && HudCommand.isHud()) {
         this.drawInfo();
         String var2 = mc.thePlayer.getClientBrand().contains("<- ") ? mc.thePlayer.getClientBrand().split(" ")[0] + " -> " + mc.thePlayer.getClientBrand().split("<- ")[1] : mc.thePlayer.getClientBrand().split(" ")[0];
         long var3 = (long)(TimeHelper.getCurrentTime() - (double)HolderHelper.getLastPacketMS());
         if (var3 > 1000L && HolderHelper.getTPS() > 0.0) {
            HolderHelper.setTPS(HolderHelper.getTPS() - 0.0);
         }

         mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&d&lIP &8-> &f" + mc.getCurrentServerData().serverIP), 6.0F, 80.0F, -1);
         mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&d&lEngine &8-> &f" + var2), 6.0F, 90.0F, -1);
         mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&d&lOnline &8-> &f" + mc.getNetHandler().getPlayerInfoMap().size() + "/" + mc.getNetHandler().currentServerMaxPlayers), 6.0F, 100.0F, -1);
         mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&d&lFPS &8-> &f" + Minecraft.debugFPS), 6.0F, 110.0F, -1);
         mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&d&lTPS &8-> &f" + this.df.format(PacketHelper.tps) + " TPS"), 6.0F, 120.0F, -1);
         mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&d&lLast Packet &8-> &f" + PacketHelper.getServerLagTime() + "ms"), 6.0F, 130.0F, -1);
         mc.getTextureManager().bindTexture(new ResourceLocation("SweetClient/Logo.png"));
         drawModalRectWithCustomSizedTexture(-36, -5, 0.0F, 0.0F, 150, 75, 150.0F, 75.0F);
         mc.fontRendererObj.drawString(ChatHelper.fix("&d&l" + SweetClient.version), 65, 53, 0);
      }

   }

   public void drawInfo() {
      GL11.glPushMatrix();
      float var1 = 100.0F;
      GL11.glEnable(3042);
      GL11.glEnable(3553);
      GL11.glEnable(2848);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("SweetClient/menu/infobackground.png"));
      GL11.glBegin(7);
      GL11.glTexCoord2d(0.0, 0.0);
      GL11.glVertex2d(0.0, 65.0);
      GL11.glTexCoord2d(0.0, 1.0);
      GL11.glVertex2d(0.0, 155.0);
      GL11.glTexCoord2d(1.0, 1.0);
      GL11.glVertex2d(200.0, 155.0);
      GL11.glTexCoord2d(1.0, 0.0);
      GL11.glVertex2d(200.0, 65.0);
      GL11.glEnd();
      GL11.glEnable(3553);
      GL11.glPopMatrix();
   }
}

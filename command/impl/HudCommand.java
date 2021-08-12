package SweetClient.command.impl;

import SweetClient.command.Command;
import SweetClient.command.CommandInfo;
import SweetClient.exeptions.CommandException;
import SweetClient.helpers.ChatHelper;

@CommandInfo(
   alias = "hud"
)
public class HudCommand extends Command {
   public static Boolean hud = true;

   public static boolean isHud() {
      return hud;
   }

   @Override
   public void execute(String... var1) throws CommandException {
      ChatHelper.printMessage(String.format("Hud has been &f%s&d!", !isHud() ? "enabled" : "disabled"));
      this.setHud(!isHud());
   }

   public void setHud(boolean var1) {
      hud = var1;
   }
}

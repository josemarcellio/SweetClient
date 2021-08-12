package SweetClient.command.impl;

import SweetClient.command.Command;
import SweetClient.command.CommandInfo;
import SweetClient.exeptions.CommandException;
import SweetClient.helpers.ChatHelper;

@CommandInfo(
   alias = "wings"
)
public class WingsCommand extends Command {
   public static Boolean wings = true;

   public void setWings(boolean var1) {
      wings = var1;
   }

   public static boolean isWings() {
      return wings;
   }

   @Override
   public void execute(String... var1) throws CommandException {
      ChatHelper.printMessage(String.format("Wings has been &f%s&d!", !isWings() ? "enabled" : "disabled"));
      this.setWings(!isWings());
   }
}

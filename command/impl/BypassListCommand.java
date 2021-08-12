package SweetClient.command.impl;

import SweetClient.command.Command;
import SweetClient.command.CommandInfo;
import SweetClient.exeptions.CommandException;
import SweetClient.helpers.ChatHelper;

@CommandInfo(
   alias = "bypasslist"
)
public class BypassListCommand extends Command {
   @Override
   public void execute(String... var1) throws CommandException {
      ChatHelper.printMessage("", false);
      ChatHelper.printMessage("&lEF1 &8-> &fExploitFixer Crasher 1", true);
      ChatHelper.printMessage("&lEF2 &8-> &fExploitFixer Crasher 2", true);
      ChatHelper.printMessage("&lEF3 &8-> &fExploitFixer Crasher 3", true);
      ChatHelper.printMessage("&lSG1 &8-> &fSpigotGuard Crasher 1", true);
      ChatHelper.printMessage("&lSG2 &8-> &fSpigotGuard Crasher 2", true);
      ChatHelper.printMessage("&lSG3 &8-> &fSpigotGuard Crasher 3", true);
      ChatHelper.printMessage("&lSG4 &8-> &fSpigotGuard Crasher 4", true);
      ChatHelper.printMessage("&lLPX1 &8-> &fLPX Crasher 1", true);
      ChatHelper.printMessage("&lLPX2 &8-> &fLPX Crasher 2", true);
      ChatHelper.printMessage("&lJHA1 &8-> &fJH_AntiCrash Crasher", true);
      ChatHelper.printMessage("&lSCUTI1 &8-> &fScutiBlockExploits Crasher", true);
      ChatHelper.printMessage("&lFAWE1 &8-> &fFawe-Byppas Crasher", true);
      ChatHelper.printMessage("", false);
   }
}

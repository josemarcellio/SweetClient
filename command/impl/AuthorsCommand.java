package SweetClient.command.impl;

import SweetClient.command.Command;
import SweetClient.command.CommandInfo;
import SweetClient.exeptions.CommandException;
import SweetClient.helpers.ChatHelper;

@CommandInfo(
   alias = "authors"
)
public class AuthorsCommand extends Command {
   @Override
   public void execute(String... var1) throws CommandException {
      ChatHelper.printMessage("", false);
      ChatHelper.printMessage("Developers &8-> \n&fBLACKCRACK SQUAD x AvarionCode", true);
      ChatHelper.printMessage("Noxerek &8- &fMain Developer", true);
      ChatHelper.printMessage("Smods &8- &fExploit Developer", true);
      ChatHelper.printMessage("Fixmem &8- &fExploit Developer", true);
      ChatHelper.printMessage("Str0ng &8- &fExploit Developer", true);
      ChatHelper.printMessage("Crypto666 &8- &fDesign Developer", true);
      ChatHelper.printMessage("", false);
   }
}

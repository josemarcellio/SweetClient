package SweetClient.command.impl;

import SweetClient.command.Command;
import SweetClient.command.CommandInfo;
import SweetClient.exeptions.CommandException;
import SweetClient.helpers.ChatHelper;

@CommandInfo(
   alias = "help"
)
public class HelpCommand extends Command {
   @Override
   public void execute(String... var1) throws CommandException {
      ChatHelper.printMessage("", false);
      ChatHelper.printMessage(",authors &8-> &fShows the authors list of client", true);
      ChatHelper.printMessage(",help &8-> &fCheck the available commands and their usage", true);
      ChatHelper.printMessage(",wings &8-> &fChange the visibility of the wings for the player", true);
      ChatHelper.printMessage(",crash &8-> &fSend the package to the server", true);
      ChatHelper.printMessage(",fakegamemode &8-> &fChange the gamemode for the player", true);
      ChatHelper.printMessage(",bypasslist &8-> &fCheck the available bypass list", true);
      ChatHelper.printMessage(",threads &8-> &fSee information about threads", true);
      ChatHelper.printMessage(",hud &8-> &fChange the visibility of gui in the game", true);
      ChatHelper.printMessage("", false);
   }
}

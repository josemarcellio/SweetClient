package SweetClient.command.impl;

import SweetClient.command.Command;
import SweetClient.command.CommandInfo;
import SweetClient.exeptions.CommandException;
import SweetClient.helpers.ChatHelper;
import SweetClient.mods.ItemPhysics.ItemPhysics;
import net.minecraft.command.CommandBase;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldSettings.GameType;

@CommandInfo(
   alias = "fakegamemode",
   usage = ",fakegamemode <mode>",
   aliases = {"fakegm", "fgm", "gamemode", "gm"}
)
public class GamemodeCommand extends Command {
   public GameType savedType;

   @Override
   public void execute(String... var1) throws CommandException {
      if (var1.length <= 0) {
         throw new CommandException("Correct usage&8: &f" + this.getUsage());
      } else {
         try {
            GameType var2 = this.getGameModeFromCommand(var1[0]);
            if (this.savedType == null) {
               this.savedType = ItemPhysics.mc.playerController.getCurrentGameType();
            }

            ItemPhysics.mc.playerController.setGameType(var2);
            ChatHelper.printMessage("Your gamemode was set to &f" + var2.getName() + "&5!");
         } catch (Exception var3) {
            throw new CommandException("Correct usage&8: &f" + this.getUsage());
         }
      }
   }

   public GameType getGameModeFromCommand(String var1) throws NumberInvalidException {
      return !var1.equalsIgnoreCase(GameType.SURVIVAL.getName()) && !var1.equalsIgnoreCase("s") ? (!var1.equalsIgnoreCase(GameType.CREATIVE.getName()) && !var1.equalsIgnoreCase("c") ? (!var1.equalsIgnoreCase(GameType.ADVENTURE.getName()) && !var1.equalsIgnoreCase("a") ? (!var1.equalsIgnoreCase(GameType.SPECTATOR.getName()) && !var1.equalsIgnoreCase("sp") ? WorldSettings.getGameTypeById(CommandBase.parseInt(var1, 0, GameType.values().length - 2)) : GameType.SPECTATOR) : GameType.ADVENTURE) : GameType.CREATIVE) : GameType.SURVIVAL;
   }
}

package SweetClient.managers;

import SweetClient.command.Command;
import SweetClient.helpers.ChatHelper;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import net.minecraft.command.CommandException;

public class CommandManager {
   public List<Command> commands;
   public static String PREFIX = ",";

   public boolean handleCommand(String var1) {
      var1.isEmpty();
      String[] var2 = var1.substring(1).split(" ");

      try {
         ((Command)this.getCommand(var2[0]).orElseThrow(CommandManager::lambda$handleCommand$0)).execute((String[])Arrays.copyOfRange(var2, 1, var2.length));
      } catch (CommandException | SweetClient.exeptions.CommandException var4) {
         ChatHelper.printMessage(var4.getMessage());
      }

      return true;
   }

   public Optional<Command> getCommand(String var1) {
      return this.commands.stream().filter(CommandManager::lambda$getCommand$1).findFirst();
   }

   public List<Command> getCommands() {
      return this.commands;
   }

   public CommandManager(Command... var1) {
      this.commands = Arrays.asList(var1);
   }

   public static boolean lambda$getCommand$1(String var0, Command var1) {
      return var1.is(var0);
   }

   public static SweetClient.exeptions.CommandException lambda$handleCommand$0() {
      return new SweetClient.exeptions.CommandException("Command cannot be found!");
   }

   public static String getPrefix() {
      return ",";
   }
}

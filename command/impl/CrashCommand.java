package SweetClient.command.impl;

import SweetClient.SweetClient;
import SweetClient.command.Command;
import SweetClient.command.CommandInfo;
import SweetClient.exploits.Exploit;
import SweetClient.helpers.ChatHelper;
import SweetClient.helpers.ExecutorHelper;
import java.util.Arrays;
import java.util.stream.Collectors;
import net.minecraft.command.CommandException;

@CommandInfo(
   alias = "crash",
   usage = ",crash <method/list> <ammount>",
   aliases = {"exploit", "lag"}
)
public class CrashCommand extends Command {
   public void lambda$execute$1(Exploit var1, String[] var2) {
      var1.execute(this.parseArgs((String[])Arrays.copyOfRange(var2, 1, var2.length)));
   }

   public Object[] parseArgs(String[] var1) {
      Object[] var2 = new Object[1];

      try {
         String var3 = var1[0];
         var2[0] = Integer.parseInt(var3);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      return var2;
   }

   @Override
   public void execute(String... var1) throws CommandException {
      if (var1.length != 0 && (var1.length != 1 || var1[0].equalsIgnoreCase("list"))) {
         if (var1[0].equalsIgnoreCase("list")) {
            ChatHelper.printMessage("Available methods&8: &f" + ((String)SweetClient.INSTANCE.getExploitManager().getExploits().stream().map(Exploit::getName).collect(Collectors.joining("&f, &f"))).toUpperCase());
         } else {
            Exploit var2 = (Exploit)SweetClient.INSTANCE.getExploitManager().getExploit(var1[0]).orElseThrow(CrashCommand::lambda$execute$0);
            ExecutorHelper.submit(this::lambda$execute$1);
         }

      } else {
         throw new CommandException("Correct usage&8: &f" + this.getUsage(), new Object[0]);
      }
   }

   public static CommandException lambda$execute$0() {
      return new CommandException("Method cannot be found!", new Object[0]);
   }
}

package SweetClient.command.impl;

import SweetClient.command.Command;
import SweetClient.command.CommandInfo;
import SweetClient.exeptions.CommandException;
import SweetClient.helpers.ChatHelper;
import java.util.Set;

@CommandInfo(
   alias = "Threads",
   usage = ",threads <list/count>"
)
public class ThreadsCommand extends Command {
   @Override
   public void execute(String... var1) throws CommandException {
      Set var2 = Thread.getAllStackTraces().keySet();
      if (var1.length <= 0) {
         throw new CommandException("Correct usage&8: &f" + this.getUsage());
      } else {
         System.gc();
         System.runFinalization();
         if (var1[0].equalsIgnoreCase("count")) {
            ChatHelper.printMessage("", false);
            ChatHelper.printMessage("Threads count: &f" + (Thread.activeCount() > 27 ? "&4" : "&e") + Thread.activeCount(), true);
            ChatHelper.printMessage("Current: &f" + Thread.currentThread(), true);
            ChatHelper.printMessage("", false);
         } else if (var1[0].equalsIgnoreCase("list")) {
            ChatHelper.printMessage("", false);
            ChatHelper.printMessage("All threads in usage: ", true);
            var2.forEach(ThreadsCommand::lambda$execute$0);
            ChatHelper.printMessage("", false);
         }

      }
   }

   public static void lambda$execute$0(Thread var0) {
      ChatHelper.printMessage("&5Thread &8-> &f" + var0, false);
   }
}

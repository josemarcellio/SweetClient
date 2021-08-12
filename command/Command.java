package SweetClient.command;

import java.util.Arrays;
import java.util.List;
import net.minecraft.command.CommandException;
import org.apache.commons.lang3.Validate;

public abstract class Command {
   public String usage;
   public List<String> aliases;
   public String alias;

   public String getAlias() {
      return this.alias;
   }

   public boolean is(String var1) {
      return var1.equalsIgnoreCase(this.alias) || this.aliases.stream().anyMatch(Command::lambda$is$0);
   }

   public Command() {
      CommandInfo var1 = (CommandInfo)this.getClass().getDeclaredAnnotation(CommandInfo.class);
      Validate.notNull(var1, "CONFUSED ANNOTATION EXCEPTION", new Object[0]);
      this.alias = var1.alias();
      this.usage = var1.usage();
      this.aliases = Arrays.asList(var1.aliases());
   }

   public static boolean lambda$is$0(String var0, String var1) {
      return var1.equalsIgnoreCase(var0);
   }

   public abstract void execute(String... var1) throws CommandException;

   public String getUsage() {
      return this.usage;
   }
}

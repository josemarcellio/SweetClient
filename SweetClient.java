package SweetClient;

import SweetClient.command.impl.AuthorsCommand;
import SweetClient.command.impl.BypassListCommand;
import SweetClient.command.impl.CrashCommand;
import SweetClient.command.impl.GamemodeCommand;
import SweetClient.command.impl.HelpCommand;
import SweetClient.command.impl.HudCommand;
import SweetClient.command.impl.ThreadsCommand;
import SweetClient.command.impl.WingsCommand;
import SweetClient.exploits.impl.CrashEF1;
import SweetClient.exploits.impl.CrashEF2;
import SweetClient.exploits.impl.CrashEF3;
import SweetClient.exploits.impl.CrashFawe1;
import SweetClient.exploits.impl.CrashJHA1;
import SweetClient.exploits.impl.CrashLPX1;
import SweetClient.exploits.impl.CrashLPX2;
import SweetClient.exploits.impl.CrashSCUTI1;
import SweetClient.exploits.impl.CrashSG1;
import SweetClient.exploits.impl.CrashSG2;
import SweetClient.exploits.impl.CrashSG3;
import SweetClient.exploits.impl.CrashSG4;
import SweetClient.helpers.MemoryHelper;
import SweetClient.helpers.OpenGlHelper;
import SweetClient.managers.CommandManager;
import SweetClient.managers.ExploitManager;
import SweetClient.mods.blockparticles.FBP;
import java.io.IOException;
import org.lwjgl.opengl.Display;

public enum SweetClient {
   public ExploitManager exploitManager;
   INSTANCE;

   public static String version = "b1.6";
   public CommandManager commandManager;
   public static SweetClient[] $VALUES = new SweetClient[]{INSTANCE};

   public SweetClient() {
      new DiscordRP();
      this.commandManager = new CommandManager(new CrashCommand(), new HelpCommand(), new WingsCommand(), new BypassListCommand(), new AuthorsCommand(), new GamemodeCommand(), new ThreadsCommand(), new HudCommand());
      this.exploitManager = new ExploitManager(new CrashEF1(), new CrashEF2(), new CrashEF3(), new CrashSG2(), new CrashSG1(), new CrashSG2(), new CrashSG3(), new CrashSG4(), new CrashLPX1(), new CrashLPX2(), new CrashFawe1(), new CrashJHA1(), new CrashSCUTI1());
      new FBP();
      (new MemoryHelper()).run();
      FBP.INSTANCE.onStart();
   }

   public void setDisplay() throws IOException {
      Display.setTitle("SweetClient 1.8.9 | " + version);
      OpenGlHelper.setWindowIcon("https://i.imgur.com/RNpWM7H.png", "https://i.imgur.com/3QluMgh.png");
   }

   public ExploitManager getExploitManager() {
      return this.exploitManager;
   }

   public CommandManager getCommandManager() {
      return this.commandManager;
   }
}

package SweetClient.helpers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorHelper {
   public static ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

   public static void submit(Runnable var0) {
      EXECUTOR_SERVICE.submit(var0);
   }
}

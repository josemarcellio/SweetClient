package SweetClient.helpers;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import org.lwjgl.opengl.Display;

public class OpenGlHelper {
   public static void setWindowIcon(String var0, String var1) throws IOException {
      Display.setIcon(new ByteBuffer[]{loadIcon(new URL(var0)), loadIcon(new URL(var1))});
   }

   public static ByteBuffer loadIcon(URL var0) throws IOException {
      InputStream var1 = var0.openStream();
      Throwable var2 = null;

      ByteBuffer var5;
      try {
         PNGDecoder var3 = new PNGDecoder(var1);
         ByteBuffer var4 = ByteBuffer.allocateDirect(var3.getWidth() * var3.getHeight() * 4);
         var3.decode(var4, var3.getWidth() * 4, Format.RGBA);
         var4.flip();
         var5 = var4;
      } catch (Throwable var14) {
         var2 = var14;
         throw var14;
      } finally {
         if (var1 != null) {
            if (var2 != null) {
               try {
                  var1.close();
               } catch (Throwable var13) {
                  var2.addSuppressed(var13);
               }
            } else {
               var1.close();
            }
         }

      }

      return var5;
   }
}

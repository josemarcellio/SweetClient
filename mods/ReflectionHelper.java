package SweetClient.mods;

import java.lang.reflect.Field;

public class ReflectionHelper {
   public static <T, E> T getPrivateValue(Class<? super E> var0, E var1, int var2) {
      try {
         Field var3 = var0.getDeclaredFields()[var2];
         var3.setAccessible(true);
         return (T)var3.get(var1);
      } catch (Exception var4) {
         throw new ReflectionHelper.UnableToAccessFieldException(new String[0], var4);
      }
   }

   public static <T, E> T getPrivateValue(Class<? super E> var0, E var1, String... var2) {
      try {
         return (T)findField(var0, var2).get(var1);
      } catch (Exception var4) {
         throw new ReflectionHelper.UnableToAccessFieldException(var2, var4);
      }
   }

   public static Class<? super Object> getClass(ClassLoader var0, String... var1) {
      Exception var2 = null;

      for(Object var5 : var1) {
         try {
            return Class.forName((String)var5, false, var0);
         } catch (Exception var7) {
            var2 = var7;
         }
      }

      throw new ReflectionHelper.UnableToFindClassException(var2);
   }

   public static Field findField(Class<?> var0, String... var1) {
      Exception var2 = null;

      for(Object var5 : var1) {
         try {
            Field var6 = var0.getDeclaredField((String)var5);
            var6.setAccessible(true);
            return var6;
         } catch (Exception var7) {
            var2 = var7;
         }
      }

      throw new ReflectionHelper.UnableToFindFieldException(var2);
   }

   public static class UnableToAccessFieldException extends RuntimeException {
      public static long serialVersionUID;
      public String[] fieldNameList;

      public UnableToAccessFieldException(String[] var1, Exception var2) {
         super(var2);
         this.fieldNameList = var1;
      }
   }

   public static class UnableToFindClassException extends RuntimeException {
      public static long serialVersionUID;

      public UnableToFindClassException(Exception var1) {
         super(var1);
      }
   }

   public static class UnableToFindFieldException extends RuntimeException {
      public static long serialVersionUID;

      public UnableToFindFieldException(Exception var1) {
         super(var1);
      }
   }
}

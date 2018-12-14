package jeffery.com.bitmapmonitor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.support.annotation.Keep;

@Keep
public class ArtHookUtil {
  public static void hook(Method origin, Method replace) throws Exception {
    Memory.memcpy(MethodInspect.getMethodAddress(origin), MethodInspect.getMethodAddress(replace),
        MethodInspect.getArtMethodSize());
  }

  private static class Reflection {
    public static Object call(Class<?> clazz, String className, String methodName,
                              Object receiver, Class[] types, Object[] params) throws Exception {
      if (clazz == null) {
        clazz = Class.forName(className);
      }
      Method method = clazz.getDeclaredMethod(methodName, types);
      method.setAccessible(true);
      return method.invoke(receiver, params);
    }

    public static Object get(Class<?> clazz, String className, String fieldName, Object receiver)
        throws Exception {
      if (clazz == null) {
        clazz = Class.forName(className);
      }
      Field field = clazz.getDeclaredField(fieldName);
      field.setAccessible(true);
      return field.get(receiver);
    }
  }

  /**
   * Alibaba andfix 的思想(不过用了隐藏API直接操纵Native内存)：
   * 通过两个ArtMethod struct的指针地址差算出一个ArtMethod到底占多少字节 (ruler2-ruler1 = sizeof(ArtMethod))
   * 基本原理就是：定义在一起的两个ArtMethod确实是挨着的（这一点没有兼容性问题）
   */
  @Keep
  public static class MethodInspect {
    static long sMethodSize = -1;

    @Keep
    public static void ruler1() {}

    @Keep
    public static void ruler2() {}

    public static long getMethodAddress(Method method) throws Exception {

      Object mirrorMethod = Reflection.get(Method.class.getSuperclass(), null, "artMethod", method);
      if (mirrorMethod.getClass().equals(Long.class)) {
        return (Long) mirrorMethod;
      }
      return Unsafe.getObjectAddress(mirrorMethod);
    }

    public static long getArtMethodSize() throws Exception {
      if (sMethodSize > 0) {
        return sMethodSize;
      }

      Method f1 = MethodInspect.class.getDeclaredMethod("ruler1");
      Method f2 = MethodInspect.class.getDeclaredMethod("ruler2");
      sMethodSize = getMethodAddress(f2) - getMethodAddress(f1);
      return sMethodSize;
    }

    public static byte[] getMethodBytes(Method method) throws Exception {
      if (method == null) {
        return null;
      }
      byte[] ret = new byte[(int) getArtMethodSize()];
      long baseAddr = getMethodAddress(method);
      for (int i = 0; i < ret.length; i++) {
        ret[i] = Memory.peekByte(baseAddr + i);
      }
      return ret;
    }
  }

  private static class Memory {
    // libcode.io.Memory#peekByte
    static byte peekByte(long address) throws Exception {
      return (Byte) Reflection.call(null, "libcore.io.Memory", "peekByte", null,
          new Class[] {long.class}, new Object[] {address});
    }

    static void pokeByte(long address, byte value) throws Exception {
      Reflection.call(null, "libcore.io.Memory", "pokeByte", null,
          new Class[] {long.class, byte.class}, new Object[] {address, value});
    }

    public static void memcpy(long dst, long src, long length) throws Exception {
      for (long i = 0; i < length; i++) {
        pokeByte(dst, peekByte(src));
        dst++;
        src++;
      }
    }
  }

  static class Unsafe {

    static final String UNSAFE_CLASS = "sun.misc.Unsafe";
    static Object THE_UNSAFE;

    public static long getObjectAddress(Object o) throws Exception {
      Object[] objects = {o};
      THE_UNSAFE = Reflection.get(null, UNSAFE_CLASS, "THE_ONE", null);
      Integer baseOffset = (Integer) Reflection.call(null, UNSAFE_CLASS,
          "arrayBaseOffset", THE_UNSAFE, new Class[] {Class.class}, new Object[] {Object[].class});
      return ((Number) Reflection.call(null, UNSAFE_CLASS, "getInt", THE_UNSAFE,
          new Class[] {Object.class, long.class}, new Object[] {objects, baseOffset.longValue()}))
          .longValue();
    }
  }
}

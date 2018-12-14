package jeffery.com.bitmapmonitor;

import android.graphics.Bitmap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by Annie on 2018/12/12.
 */

@Aspect
public class MethodAspect {
  private static final String TAG = "yangkai";

  private static Logger sLogger;

  public static void setLogger(Logger logger) {
    sLogger = logger;
  }

  /**
   * 监测 {@link android.graphics.BitmapFactory} 中所有 decode* 方法
   * 监测 {@link Bitmap} 中所有的 create* 的方法
   * 监测 {@link Bitmap#copy(Bitmap.Config, boolean)} ()} 的调用
   * 监测 {@link Bitmap#extractAlpha()} 的调用
   *
   * @param proceedingJoinPoint
   * @return
   * @throws Throwable
   */
//  @Around("call(android.graphics.Bitmap android.graphics.BitmapFactory.decode*(..)) ||" +
//      "call(android.graphics.Bitmap android.graphics.Bitmap.create*(..)) ||" +
//      "call(android.graphics.Bitmap android.graphics.Bitmap.extractAlpha(..)) ||" +
//      "call(android.graphics.Bitmap android.graphics.Bitmap.copy(..))")
//  public Object onBitmapFactoryDecodeMethodCall(ProceedingJoinPoint proceedingJoinPoint) throws
//      Throwable {
//    Object object = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs()); //这里把它的参数换了
//    sLogger.log("aspect " + proceedingJoinPoint.getSignature());
//    sLogger.log("\n" + " bitmap: " + object + "\n\n");
//    return object;
//  }
}

package jeffery.com.bitmapmonitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import android.util.Log;

/**
 * Created by Annie on 2018/12/12.
 */

@Aspect
public class MethodAspect {
  private static final String TAG = "ConstructorAspect";

  public void callMethod() {
  }

  @Before("execution(* jeffery.com.bitmapmonitor.MainActivity.on**(..))")
  public void beforeMethodCall(JoinPoint joinPoint) {
    Log.e(TAG,
        "before->" + joinPoint.getTarget().toString() + "#" + joinPoint.getSignature().getName());
  }
}

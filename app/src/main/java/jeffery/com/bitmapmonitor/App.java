package jeffery.com.bitmapmonitor;

import java.io.InputStream;
import java.lang.reflect.Method;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

public class App extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    try {
      Log.d("yangkai", "start");
      Method systemDecodeResource = BitmapFactory.class.getDeclaredMethod("decodeResourceStream",
          Resources.class, TypedValue.class, InputStream.class, Rect.class,
          BitmapFactory.Options.class);

      Method systemDecodeResourceReplacer =
          App.class.getDeclaredMethod("decodeResourceStream",
              Resources.class, TypedValue.class, InputStream.class, Rect.class,
              BitmapFactory.Options.class);

      ArtHookUtil.hook(systemDecodeResource, systemDecodeResourceReplacer);
      Log.d("yangkai", "end");
    } catch (Throwable e) {
      Log.e("yangkai", "error", e);
    }
  }

  public static Bitmap decodeResourceStream(@Nullable Resources res, @Nullable TypedValue value,
      @Nullable InputStream is, @Nullable Rect pad,
      @Nullable BitmapFactory.Options opts) {
    Log.e("yangkai", "decodeResourceStream ", new Exception());
    if (opts == null) {
      opts = new BitmapFactory.Options();
    }

    if (opts.inDensity == 0 && value != null) {
      final int density = value.density;
      if (density == TypedValue.DENSITY_DEFAULT) {
        opts.inDensity = DisplayMetrics.DENSITY_DEFAULT;
      } else if (density != TypedValue.DENSITY_NONE) {
        opts.inDensity = density;
      }
    }

    if (opts.inTargetDensity == 0 && res != null) {
      opts.inTargetDensity = res.getDisplayMetrics().densityDpi;
    }

    return BitmapFactory.decodeStream(is, pad, opts);

  }
}

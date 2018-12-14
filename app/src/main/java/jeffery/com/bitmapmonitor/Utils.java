package jeffery.com.bitmapmonitor;

import android.graphics.Bitmap;

import java.io.FileOutputStream;
import java.io.IOException;

public class Utils {
  public static void saveToFile(Bitmap bitmap, Bitmap.CompressFormat fmt, String filePath) {
    FileOutputStream os = null;
    try {
      os = new FileOutputStream(filePath);
      bitmap.compress(fmt, 85, os);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (os != null) {
        try {
          os.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

}

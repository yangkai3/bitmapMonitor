package jeffery.com.bitmapmonitor;

import java.io.File;
import java.io.FileInputStream;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ActivityCompat
    .OnRequestPermissionsResultCallback {

  private TextView leftView;
  private TextView rightView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    leftView = findViewById(R.id.left);
    rightView = findViewById(R.id.right);

    int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission
        .WRITE_EXTERNAL_STORAGE);
    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
      initAspect();
    } else {
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
          .WRITE_EXTERNAL_STORAGE}, 1);
    }
  }

  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull
      int[] grantResults) {
    if (grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager
        .PERMISSION_GRANTED) {
      initAspect();
    }
  }

  private void initAspect() {
    MethodAspect.setLogger(new Logger() {
      @Override
      public void log(String s) {
        rightView.setText(rightView.getText() + s);
      }
    });
    test(new Logger() {
      @Override
      public void log(String s) {
        leftView.setText(leftView.getText() + s);
      }
    });
  }

  private void test(Logger logger) {
    // step1: decode resource
//    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable
//        .account_authenticate_area);
//    logger.log("call: Bitmap decodeResource(Resources, int)");
//    logger.log("\n" + "bitmap: " + bitmap + "\n\n");
//
//    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.account_authenticate_area,
//        new BitmapFactory.Options());
//    final int width = bitmap.getWidth();
//    final int height = bitmap.getHeight();
//    logger.log("call: Bitmap decodeResource(Resources, int, Options)");
//    logger.log("\n" + "bitmap: " + bitmap + "\n\n");

    // step2: decode file
    File file = new File(Environment.getExternalStorageDirectory(), "img.png");
//    Utils.saveToFile(bitmap, Bitmap.CompressFormat.PNG, file.getPath());
//
//    bitmap = BitmapFactory.decodeFile(file.getPath());
//    logger.log("call: Bitmap decodeFile(String)");
//    logger.log("\n" + "bitmap: " + bitmap + "\n\n");
//
//    bitmap = BitmapFactory.decodeFile(file.getPath(), new BitmapFactory.Options());
//    logger.log("call: Bitmap decodeFile(String, Options)");
//    logger.log("\n" + "bitmap: " + bitmap + "\n\n");

    // step3: decode file descriptor
    FileInputStream fis = null;
//    try {
//      fis = new FileInputStream(file);
//      bitmap = BitmapFactory.decodeFileDescriptor(fis.getFD());
//      logger.log("call: Bitmap decodeFileDescriptor(FileDescriptor)");
//      logger.log("\n" + "bitmap: " + bitmap + "\n\n");
//
//      fis.close();
//      fis = new FileInputStream(file);
//      bitmap = BitmapFactory.decodeFileDescriptor(fis.getFD(), new Rect(0, 0, 0, 0), new
//          BitmapFactory.Options());
//      logger.log("call: Bitmap decodeFileDescriptor(FileDescriptor, Rect, Options)");
//      logger.log("\n" + "bitmap: " + bitmap + "\n\n");
//    } catch (Exception e) {
//      e.printStackTrace();
//      if (fis != null) {
//        try {
//          fis.close();
//        } catch (IOException e1) {
//          e1.printStackTrace();
//        }
//      }
//    }

    // step4: decode stream
//    try {
//      fis = new FileInputStream(file);
//      bitmap = BitmapFactory.decodeStream(fis);
//      logger.log("call: Bitmap decodeStream(InputStream)");
//      logger.log("\n" + "bitmap: " + bitmap + "\n\n");
//
//      fis.close();
//      fis = new FileInputStream(file);
//      bitmap = BitmapFactory.decodeStream(fis, new Rect(0, 0, 0, 0), new BitmapFactory
//          .Options());
//      logger.log("call: Bitmap decodeStream(InputStream, Rect, Options)");
//      logger.log("\n" + "bitmap: " + bitmap + "\n\n");
//
//      fis.close();
//      fis = new FileInputStream(file);
//      bitmap = BitmapFactory.decodeResourceStream(getResources(), null, fis, new Rect(0, 0, 0, 0)
//          , new BitmapFactory.Options());
//      logger.log("call: Bitmap decodeResourceStream(Resources, TypedValue,InputStream, Rect, " +
//          "Options)");
//      logger.log("\n" + "bitmap: " + bitmap + "\n\n");
//    } catch (Exception e) {
//      e.printStackTrace();
//      if (fis != null) {
//        try {
//          fis.close();
//        } catch (IOException e1) {
//          e1.printStackTrace();
//        }
//      }
//    }

    // step5: new Bitmap
//    bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, false);
//    logger.log("call: Bitmap copy(Config, boolean)");
//    logger.log("\n" + "bitmap: " + bitmap + "\n\n");
//
//    bitmap = bitmap.extractAlpha();
//    logger.log("call: Bitmap extractAlpha()");
//    logger.log("\n" + "bitmap: " + bitmap + "\n\n");
//
//    bitmap = Bitmap.createBitmap(bitmap);
//    logger.log("call: Bitmap createBitmap(Bitmap)");
//    logger.log("\n" + "bitmap: " + bitmap + "\n\n");
//
//    bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
//    logger.log("call: Bitmap createScaledBitmap(Bitmap ,int ,int ,boolean ");
//    logger.log("\n" + "bitmap: " + bitmap + "\n\n");

    // step6: Drawable
//    Drawable.createFromPath(file);

    // step7: resource

    // step8: ImageDecoder

    // step9: xml drawable
  }

}

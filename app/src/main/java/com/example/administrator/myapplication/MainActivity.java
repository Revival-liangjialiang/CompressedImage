package com.example.administrator.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView mImageView;
    Bitmap bitMap;
    static String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        mImageView = (ImageView) findViewById(R.id.imageView2);
        try {
            bitMap = decodeSampledBitmapFromResource(getResources(), R.drawable.android1, 200, 310);
        } catch (Exception e) {

        }

        mImageView.setImageBitmap(bitMap);
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int MyWidth, int MyHeight) throws Exception {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        /**被赋值为true返回的Bitmap为null，虽然Bitmap是null了，但是BitmapFactory.Options的outWidth、
         *outHeight和outMimeType属性都会被赋值。这个技巧让我们可以在加载图片之前就获取到图片的长宽值和MIME类型，从而根据情况对图片进行压缩
         */
        options.inJustDecodeBounds = true;
        str = options.outMimeType;
        BitmapFactory.decodeResource(res, resId, options);
        //得到压缩倍数。
        options.inSampleSize = calculateInSampleSize(options, MyWidth, MyHeight);
        //设置为false，就能得到Bitmap.
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(res, resId, options);

    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) throws Exception {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? widthRatio : heightRatio;
        }
        return inSampleSize;
    }

}


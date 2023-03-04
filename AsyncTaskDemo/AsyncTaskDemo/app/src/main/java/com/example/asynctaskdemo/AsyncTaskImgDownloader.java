package com.example.asynctaskdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTaskImgDownloader extends AsyncTask<String,Integer, Bitmap> {

    private Activity myActivity;

    public AsyncTaskImgDownloader(Activity activity) {
        myActivity = activity;
    }

    final static int loading = 0;
    final static int loaded = 1;
    final static String AsyncTaskImgDownloader = "AsyncTaskImgDownloader";

    @Override
    protected Bitmap doInBackground(String... strings) {
        publishProgress(loading);

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new Exception("Failed to connect");
            } else {

                Log.i(AsyncTaskImgDownloader, "con ok");
            }

            InputStream is = con.getInputStream();
            publishProgress(loaded);

            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return bitmap;
        } catch (Exception e) {
            Log.e(AsyncTaskImgDownloader, "Failed to load image", e);
            Log.e(AsyncTaskImgDownloader, e.getMessage());
        }
        return null;
    }

    @Override
    // After the execution of the long operation to download the image we display the image
    // We change the content of the image of the layout with the one from the web
    // The image is retrieved using findViewById
    protected void onPostExecute(Bitmap bitmap) {
        ImageView iv = (ImageView) myActivity.findViewById(R.id.imageView);
        if (iv == null) {
            Log.i(AsyncTaskImgDownloader, "iv null " + iv);
        }
        if (bitmap == null) {
            Log.i(AsyncTaskImgDownloader, "bitmap null " + bitmap);
        }
        if (iv != null && bitmap != null) {
            Log.i(AsyncTaskImgDownloader, "Bitmap found");
            iv.setImageBitmap(bitmap);
        } else {
            Log.i(AsyncTaskImgDownloader, "Problem with bitmap");
        }
    }

    @Override
    // When the image is loading, a textview with the text loading is present otherwise we display This is the image!
    protected void onProgressUpdate(Integer... values) {
        TextView tv = (TextView) myActivity.findViewById(R.id.textView);
        if (values[0] == loading) {
            tv.setText("Loading...");
        } else {
            tv.setText("This is a random image!");
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}

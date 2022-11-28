package com.example.researchproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

// Class to get and set the bitmap of the restaurant image in the background
public class ImageRetrievalThread extends Thread {

    // Fields
    private final String imageURL;
    private final ImageView imageView;

    // Constructor
    public ImageRetrievalThread(String imageURL, ImageView imageView) {
        this.imageURL = imageURL;
        this.imageView = imageView;
    }

    // run() method needed as part of the Thread class
    @Override
    public void run() {
        setBitmapFromURL();
    }
    // sourced from outside resource
    public void setBitmapFromURL() {
        try {
            Log.e("src",imageURL);
            URL url = new URL(imageURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            imageView.setImageBitmap(myBitmap);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
        }
    }
}


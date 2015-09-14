package com.example.tuannguyen.ass2;

import android.content.res.AssetManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.tuannguyen.ass2.model.Clipping;
import com.example.tuannguyen.ass2.model.Collection;
import com.example.tuannguyen.ass2.model.MyApplication;
import com.example.tuannguyen.ass2.model.ScrapbookModel;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends ActionBarActivity {

    private ScrapbookModel model;
    public static final String TAG = MainActivity.class.getSimpleName();
    private ImageView[] imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgs = new ImageView[3];
        imgs[0] = (ImageView)findViewById(R.id.img1);
        imgs[1] = (ImageView)findViewById(R.id.img2);
        imgs[2] = (ImageView)findViewById(R.id.img3);

        model = ((MyApplication) getApplication()).getScrapbookModel();
        model.createCollection("A");
        model.createCollection("B");
        for (Collection collection : model.getCollectionList()) {
            Log.d(TAG, collection.getCollectionName());
        }
        AssetManager assetManager = getAssets();
        InputStream is1 = null, is2 = null, is3 = null;

        //opening files in asset folder
        try {
            is1 = assetManager.open("lighthouse.jpg");
            is2 = assetManager.open("magnificent.jpg");
            is3 = assetManager.open("ocean.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }


        //create clipping
        model.createClipping("1 foo", is1);
        model.createClipping("2 foo", is2);
        model.createClipping("3 bar", is3);


        //close files
        if (is1 != null) {
            try {
                is1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (is2 != null) {
            try {
                is2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (is3 != null) {
            try {
                is3.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int i = 0;

        //get all clippings
        for (Clipping clipping : model.getClippingsFromCollection(null)) {

            imgs[i].setImageDrawable(clipping.getImage(this));
            Log.d(TAG, clipping.getText() + " " + clipping.getReferencedPath() + "\n");
            i++;
        }

        //add first and second clipping to collection A
        model.addClippingToCollection(0, "A");
        model.addClippingToCollection(1, "A");

        //get all clippings in collection A
        for (Clipping clipping : model.getClippingsFromCollection("A")) {
            Log.d(TAG, clipping.getText() + " " + clipping.getReferencedPath() + "\n");
        }

        //delete first clipping
        model.deleteClipping(0);

        //get all clippings in collection A
        for (Clipping clipping : model.getClippingsFromCollection("a")) {
            Log.d(TAG, clipping.getText() + " " + clipping.getReferencedPath() + "\n");
        }

        //search for a clipping
        for (Clipping clipping : model.getClippingsFromSearchString("BAR")) {
            Log.d(TAG, clipping.getText() + " " + clipping.getReferencedPath() + "\n");
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
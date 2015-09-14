package com.example.tuannguyen.ass2.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;

import com.example.tuannguyen.ass2.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by tuannguyen on 31/08/20 */
public class Clipping {
    private String mReferencedPath;
    private String mText;
    private Date mCreatedDate;
    private int mId;
    private Drawable mImage;

    //constructor
    public Clipping(String referencedPath, String text, int id)
    {
        mReferencedPath = referencedPath;
        mText = text;
        mCreatedDate = new Date();
        mId = id;
    }


    public Clipping(String referencedPath, String text, int createdDate, int id)
    {
        mReferencedPath = referencedPath;
        mText = text;
        mCreatedDate = new Date(createdDate * 1000);
        mId = id;
    }

    //getters
    public int getId() {
        return mId;
    }

    public String getReferencedPath() {
        return mReferencedPath;
    }

    public Drawable getImage(Context context)
    {
        if (mImage == null)
        {
            mImage = getDrawable(context);
        }
        return mImage;
    }

    private Drawable getDrawable(Context context) {
        Drawable drawable = null;
        File imageFile = null;
        if (mReferencedPath != null)
            imageFile = new File(mReferencedPath);
        //if user did not provide any image or if the file does not exist
        if (imageFile == null || !imageFile.exists()) {
            drawable = context.getResources().getDrawable(R.drawable.unavailable);
            return drawable;
        } else {
            drawable = Drawable.createFromPath(mReferencedPath);
            return drawable;
        }
    }

    //returns notes
    public String getText() {
        return mText;
    }

    public Uri getImageUri()
    {
        if (mReferencedPath == null)
            return null;
        File imageFile = new File(mReferencedPath);
        if (!imageFile.exists())

            return null;
        else
            return Uri.fromFile(imageFile);
    }

    public Date getCreatedDate() {
        return mCreatedDate;
    }

}

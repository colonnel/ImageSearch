package com.gmail.berezin.serg.imagesearch;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.gmail.berezin.serg.imagesearch.models.SearchImage;


public class ImgAsyncLoader extends AsyncTaskLoader<SearchImage> implements LoaderManager.LoaderCallbacks<SearchImage> {
    public ImgAsyncLoader(Context context) {
        super(context);
    }

    @Override
    public SearchImage loadInBackground() {
        return null;
    }

    @Override
    public Loader<SearchImage> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<SearchImage> loader, SearchImage data) {

    }

    @Override
    public void onLoaderReset(Loader<SearchImage> loader) {

    }
}

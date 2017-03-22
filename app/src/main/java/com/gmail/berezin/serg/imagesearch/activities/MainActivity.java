package com.gmail.berezin.serg.imagesearch.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gmail.berezin.serg.imagesearch.R;
import com.gmail.berezin.serg.imagesearch.adapters.SearchResultsAdapter;
import com.gmail.berezin.serg.imagesearch.fragments.ScreenSlidePageFragment;
import com.gmail.berezin.serg.imagesearch.models.SearchImage;
import com.gmail.berezin.serg.imagesearch.rest.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = MainActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private EditText vEtSearhQuery;
    private ImageView vIvStartSearch;
    private ProgressBar vSearchProgress;
    //numbers of pages in page adapter
    private static final int NUM_PAGES = 2;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private final String CX = "002600642033321958554%3Al12i1nf1fz8";
    private final String KEY = "AIzaSyAzfrd2ZJPFyjhUUkkp32PWCLJF7Am4hqQ";
    private String fileType = "png,jpg";
    private String searchType = "image";
    private ArrayList<SearchImage> mPictList;
    private RecyclerView vRecyclerView;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }

    }

    //find views
    private void findViewsById() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        vEtSearhQuery = (EditText) findViewById(R.id.et_search);
        vIvStartSearch = (ImageView) findViewById(R.id.iv_start_search);
        vIvStartSearch.setOnClickListener(this);
              mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onClick(View view) {
        String query = vEtSearhQuery.getText().toString();
        url = "https://www.googleapis.com/customsearch/v1?key="
                + KEY
                + "&amp;cx="
                + CX
                + "&amp;q="
                + query +
                "&amp;fileType=" + fileType + "&amp;searchType=" + searchType + "&amp;alt=json";
        mPictList = new ArrayList<>();
        new SearchTask().execute();

    }

    //create async task
    private class SearchTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
                   }

        @Override
        protected Void doInBackground(Void... voids) {

            HttpHandler httpHandler = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = httpHandler.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray results = jsonObject.getJSONArray("results");
                    // looping through All Contacts
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject img = results.getJSONObject(i);
                        String link = img.getString("link");
                        String title = img.getString("title");
                        // tmp hash map for single image
                        SearchImage searchImage = new SearchImage(link, title, false);
                        mPictList.add(searchImage);

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
                       SearchResultsAdapter adapter = new SearchResultsAdapter(mPictList);
            vRecyclerView = (RecyclerView) findViewById(R.id.rv);
            vRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
            vRecyclerView.setAdapter(adapter);

        }
    }

    //create pager adapter
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ScreenSlidePageFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            switch (position) {
                case 0:
                    title = getString(R.string.search_results);
                    break;
                case 1:
                    title = getString(R.string.favorites);
                    break;
            }
            return title;
        }
    }
}

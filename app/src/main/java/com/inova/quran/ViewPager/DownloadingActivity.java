package com.inova.quran.ViewPager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.inova.quran.R;
import com.inova.quran.pojo.SurahModel;

public class DownloadingActivity extends AppCompatActivity {


    SurahModel surahModel;

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    String surahName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloading);

        getSupportActionBar().hide();

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.viewPager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tab_LayOut);
        tabLayout.setupWithViewPager(mViewPager);

        Intent intent = getIntent();
        if(intent != null){
            surahModel = intent.getParcelableExtra("TRY");
            String x = surahModel.getEnglishName();
            surahName = surahModel.getName();
            Log.d("zxc", "NNNN "+x);
        }

    }

    private void setupViewPager(ViewPager viewPager){
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AllFragment(),"ALL");
        adapter.addFragment(new DownloadingFragment(),"Downloadeing");
        adapter.addFragment(new DownloadedFragment(),"Downloaded");
        viewPager.setAdapter(adapter);
    }

    public String getMyData() {
        return surahName;
    }
}

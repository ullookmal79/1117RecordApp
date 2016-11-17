package com.sds.study.recordapp.record;

/*
 녹음으로 인하여 생성된 파일을 목록으로 보여주고,
 해당 파일 선택하면 재생시키자!!
*/

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sds.study.recordapp.R;

public class FileListActivity extends AppCompatActivity{
    String TAG;
    ViewPager viewPager;
    RecordPagerAdapter pagerAdapter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getName();
        Log.d(TAG, "FileListActivity : "+this);
        setContentView(R.layout.list_layout);

        viewPager=(ViewPager)findViewById(R.id.viewPager);
        pagerAdapter = new RecordPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }
}











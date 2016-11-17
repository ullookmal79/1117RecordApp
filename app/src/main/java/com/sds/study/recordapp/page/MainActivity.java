package com.sds.study.recordapp.page;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sds.study.recordapp.R;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    MyAdapter myAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager=(ViewPager)findViewById(R.id.viewPager);
        myAdapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);
    }

    public void btnClick(View view){
        switch (view.getId()){
            case R.id.bt_a : viewPager.setCurrentItem(0);break;
            case R.id.bt_b : viewPager.setCurrentItem(1);break;
            case R.id.bt_c : viewPager.setCurrentItem(2);break;
        }
    }

}

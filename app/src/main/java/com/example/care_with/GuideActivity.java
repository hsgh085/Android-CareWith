package com.example.care_with;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mPager;
    private int[] layouts = {R.layout.slide1, R.layout.slide2,
            R.layout.slide3, R.layout.slide4, R.layout.slide5};
    private MpagerAdapter mpagerAdapter;

    private LinearLayout Dots_Layout;
    private ImageView[] dots;

    private Button BtnSkip, BtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(new PreferenceManager(this).checkPreference()){
            loadHome();
        }

        if(Build.VERSION.SDK_INT>19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setContentView(R.layout.activity_guide);

        mPager = (ViewPager) findViewById(R.id.viewPager);
        mpagerAdapter = new MpagerAdapter(layouts, this);
        mPager.setAdapter(mpagerAdapter);

        Dots_Layout = (LinearLayout) findViewById(R.id.dotsLayout);
        BtnSkip = (Button)findViewById(R.id.btnSkip);
        BtnNext = (Button)findViewById(R.id.btnNext);
        BtnSkip.setOnClickListener(this);
        BtnNext.setOnClickListener(this);
        createDots(0);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
                if(position == layouts.length-1){
                    BtnNext.setText("시작하기");
                    BtnSkip.setVisibility(View.INVISIBLE);
                }
                else{
                    BtnNext.setText("다음으로");
                    BtnSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void createDots(int current_position){
        if(Dots_Layout != null)
            Dots_Layout.removeAllViews();
        dots = new ImageView[layouts.length];

        for(int i = 0; i<layouts.length; i++){
            dots[i] = new ImageView(this);
            if(i == current_position){
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots));
            }
            else{
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_dots));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);

            Dots_Layout.addView(dots[i], params);

        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btnNext:
                loadNextSlide();
                break;

            case R.id.btnSkip:
                loadHome();
                new PreferenceManager(this).writePreference();
                break;
        }
    }

    private void loadHome(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void loadNextSlide(){
        int next_slide = mPager.getCurrentItem()+1;

        if(next_slide<layouts.length){
            mPager.setCurrentItem(next_slide);
        }
        else{
            loadHome();
            new PreferenceManager(this).writePreference();
        }
    }
}
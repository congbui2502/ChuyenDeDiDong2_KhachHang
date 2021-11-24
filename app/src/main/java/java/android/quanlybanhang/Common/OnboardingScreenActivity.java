package java.android.quanlybanhang.Common;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.android.quanlybanhang.HelperClasses.SliderAdapter;
import java.android.quanlybanhang.login.LoginActivity;
import java.android.quanlybanhang.R;

public class OnboardingScreenActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    Button next, start;

    SliderAdapter sliderAdapter;
    TextView[] dots;

    private int getItem = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen);

        //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        next = findViewById(R.id.btnNext);
        start = findViewById(R.id.get_started_btn);

        //Call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

        //Set viewpager ++
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getItem < dots.length)
                getItem++;
                viewPager.setCurrentItem(getItem, true);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callLoginActivityClass();
            }
        });
    }

    private void callLoginActivityClass(){
        Intent intent = new Intent(OnboardingScreenActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void addDots(int position){
        getItem = position;
        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i = 0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if(dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.vang));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    ViewPager.OnPageChangeListener changeListener1 = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
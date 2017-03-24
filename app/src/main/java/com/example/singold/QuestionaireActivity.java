package com.example.singold;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuestionaireActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
  //  private PrefManager prefManager;

    private ImageView image1, image2, image3, image4;
    private TextView Q1, Q2, Q3, Q4, Q5, Q6, Q7;
    private RadioGroup rgQ1, rgQ2, rgQ3, rgQ4, rgQ5, rgQ6, rgQ7;
    private RadioButton opera, classical, armyBands, songs, folkDances, yiddish, hebrew, english, russian, arabic, waltz, tango, samba;
    private RadioButton jaz, rock, pollack, chazanout, frompray, propnents, runningwater, insteumental;
    private TextView country, homemusic, youngmusic, ma7azot, disk, favoritesinger, weddingsong, favoritesong;
    private EditText answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        viewPager = (ViewPager) findViewById(R.id.viewpager);


        // Checking for first time launch - before calling setContentView()
//        prefManager = new PrefManager(this);
//        if (!prefManager.isFirstTimeLaunch()) {
//            launchHomeScreen();
//            finish();
//        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_welcome);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);


        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.activity_question1,
                R.layout.activity_question2,
                R.layout.activity_question3,
                R.layout.activity_question4};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });

        image1 = (ImageView) findViewById(R.id.image1);
        Q1 = (TextView) findViewById(R.id.Q1);
        Q2 = (TextView) findViewById(R.id.Q2);
        Q3 = (TextView) findViewById(R.id.Q3);
        rgQ1 = (RadioGroup) findViewById(R.id.rgQ1);
        rgQ2 = (RadioGroup) findViewById(R.id.rgQ2);
        rgQ3 = (RadioGroup) findViewById(R.id.rgQ3);
        opera = (RadioButton) findViewById(R.id.opera);
        classical = (RadioButton) findViewById(R.id.classical);
        armyBands = (RadioButton) findViewById(R.id.armyBands);
        folkDances = (RadioButton) findViewById(R.id.folkDances);
        yiddish = (RadioButton) findViewById(R.id.yiddish);
        hebrew = (RadioButton) findViewById(R.id.hebrew);
        english = (RadioButton) findViewById(R.id.english);
        russian = (RadioButton) findViewById(R.id.russian);
        arabic = (RadioButton) findViewById(R.id.arabic);


        image2 = (ImageView) findViewById(R.id.image2);
        Q4 = (TextView) findViewById(R.id.Q4);
        Q5 = (TextView) findViewById(R.id.Q5);
        Q6 = (TextView) findViewById(R.id.Q6);
        rgQ4 = (RadioGroup) findViewById(R.id.rgQ4);
        rgQ5 = (RadioGroup) findViewById(R.id.rgQ5);
        rgQ6 = (RadioGroup) findViewById(R.id.rgQ6);
        waltz = (RadioButton) findViewById(R.id.waltz);
        tango = (RadioButton) findViewById(R.id.tango);
        samba = (RadioButton) findViewById(R.id.samba);
        jaz = (RadioButton) findViewById(R.id.jaz);
        rock = (RadioButton) findViewById(R.id.rock);
        pollack = (RadioButton) findViewById(R.id.pollack);
        chazanout = (RadioButton) findViewById(R.id.chazanout);
        frompray = (RadioButton) findViewById(R.id.fromPray);
        propnents = (RadioButton) findViewById(R.id.propnents);


        image3 = (ImageView) findViewById(R.id.image3);
        Q7 = (TextView) findViewById(R.id.Q7);
        rgQ7 = (RadioGroup) findViewById(R.id.rgQ7);
        runningwater = (RadioButton) findViewById(R.id.runningwater);
        insteumental = (RadioButton) findViewById(R.id.insteumental);
        country = (TextView) findViewById(R.id.country);
        homemusic = (TextView) findViewById(R.id.homemusic);
        youngmusic = (TextView) findViewById(R.id.youngmusic);
        answer1 = (EditText) findViewById(R.id.answer1);
        answer2 = (EditText) findViewById(R.id.answer2);
        answer3 = (EditText) findViewById(R.id.answer3);


        image4 = (ImageView) findViewById(R.id.image4);
      //  ma7azot = (TextView) findViewById(R.id.ma7azot);
        disk = (TextView) findViewById(R.id.disk);
        favoritesinger = (TextView) findViewById(R.id.favoritesinger);
        weddingsong = (TextView) findViewById(R.id.weddingsong);
       // favoritesong = (TextView) findViewById(R.id.favoritesong);
        answer4 = (EditText) findViewById(R.id.answer4);
        answer5 = (EditText) findViewById(R.id.answer5);
        answer6 = (EditText) findViewById(R.id.answer6);
        answer7 = (EditText) findViewById(R.id.answer7);
        answer8 = (EditText) findViewById(R.id.answer8);
    }
    private void dataHandler()
    {
        String stAnswer1 = answer1.getText().toString();
        String stAnswer2 = answer2.getText().toString();
        String stAnswer3 = answer3.getText().toString();
        String stAnswer4 = answer4.getText().toString();
        String stAnswer5 = answer5.getText().toString();
        String stAnswer6 = answer6.getText().toString();
        String stAnswer7 = answer7.getText().toString();
        String stAnswer8 = answer8.getText().toString();

        boolean isok=true;
        if(stAnswer1.length()==0)
        {
           answer1.setError("answer the question");
            isok=false;
        }
        if(stAnswer2.length()==0)
        {
            answer2.setError("answer the question");
            isok=false;
        }
        if(stAnswer3.length()==0)
        {
            answer3.setError("answer the question");
            isok=false;
        }
        if(stAnswer4.length()==0)
        {
            answer4.setError("answer the question");
            isok=false;
        }
        if(stAnswer5.length()==0)
        {
            answer5.setError("answer the question");
            isok=false;
        }
        if(stAnswer6.length()==0)
        {
            answer6.setError("answer the question");
            isok=false;
        }
        if(stAnswer7.length()==0)
        {
            answer7.setError("answer the question");
            isok=false;
        }
        if(stAnswer8.length()==0)
        {
            answer8.setError("answer the question");
            isok=false;
        }
    }




//            @Override
//            protected void onCreate(Bundle savedInstanceState) {
//                super.onCreate(savedInstanceState);
//
//                viewPager = (ViewPager) findViewById(R.id.viewpager);
//
//
//                // Checking for first time launch - before calling setContentView()
////        prefManager = new PrefManager(this);
////        if (!prefManager.isFirstTimeLaunch()) {
////            launchHomeScreen();
////            finish();
////        }
//
//                // Making notification bar transparent
//                if (Build.VERSION.SDK_INT >= 21) {
//                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//                }
//
//                setContentView(R.layout.activity_welcome);
//
//                viewPager = (ViewPager) findViewById(R.id.view_pager);
//                dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
//                btnSkip = (Button) findViewById(R.id.btn_skip);
//                btnNext = (Button) findViewById(R.id.btn_next);
//
//
//                // layouts of all welcome sliders
//                // add few more layouts if you want
//                layouts = new int[]{
//                        R.layout.activity_question1,
//                        R.layout.activity_question2,
//                        R.layout.activity_question3,
//                        R.layout.activity_question4};
//
//                // adding bottom dots
//                addBottomDots(0);
//
//                // making notification bar transparent
//                changeStatusBarColor();
//
//                myViewPagerAdapter = new MyViewPagerAdapter();
//                viewPager.setAdapter(myViewPagerAdapter);
//                viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
//
//                btnSkip.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        launchHomeScreen();
//                    }
//                });
//
//                btnNext.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // checking for last page
//                        // if last page home screen will be launched
//                        int current = getItem(+1);
//                        if (current < layouts.length) {
//                            // move to next screen
//                            viewPager.setCurrentItem(current);
//                        } else {
//                            launchHomeScreen();
//                        }
//                    }
//                });
//            }

            private void addBottomDots(int currentPage) {
                dots = new TextView[layouts.length];

                int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
                int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

                dotsLayout.removeAllViews();
                for (int i = 0; i < dots.length; i++) {
                    dots[i] = new TextView(this);
                    dots[i].setText(Html.fromHtml("&#8226;"));
                    dots[i].setTextSize(35);
                    dots[i].setTextColor(colorsInactive[currentPage]);
                    dotsLayout.addView(dots[i]);
                }

                if (dots.length > 0)
                    dots[currentPage].setTextColor(colorsActive[currentPage]);
            }

            private int getItem(int i) {
                return viewPager.getCurrentItem() + i;
            }

            private void launchHomeScreen() {
             //   prefManager.setFirstTimeLaunch(false);
               // startActivity(new Intent(QuestionaireActivity.this, Sin_goldActivity.class));
                finish();
            }

            //  viewpager change listener
            ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    addBottomDots(position);

                    // changing the next button text 'NEXT' / 'GOT IT'
                    if (position == layouts.length - 1) {
                        // last page. make button text to GOT IT
                        btnNext.setText(getString(R.string.start));
                        btnSkip.setVisibility(View.GONE);
                    } else {
                        // still pages are left
                        btnNext.setText(getString(R.string.next));
                        btnSkip.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {

                }

                @Override
                public void onPageScrollStateChanged(int arg0) {

                }
            };

            /**
             * Making notification bar transparent
             */
            private void changeStatusBarColor() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.TRANSPARENT);
                }
            }

            /**
             * View pager adapter
             */
            public class MyViewPagerAdapter extends PagerAdapter {
                private LayoutInflater layoutInflater;

                public MyViewPagerAdapter() {
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    View view = layoutInflater.inflate(layouts[position], container, false);
                    container.addView(view);

                    return view;
                }

                @Override
                public int getCount() {
                    return layouts.length;
                }

                @Override
                public boolean isViewFromObject(View view, Object obj) {
                    return view == obj;
                }


                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    View view = (View) object;
                    container.removeView(view);
                }
            }

    }

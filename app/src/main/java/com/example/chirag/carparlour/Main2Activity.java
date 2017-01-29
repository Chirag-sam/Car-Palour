package com.example.chirag.carparlour;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static android.text.Html.fromHtml;
import static android.text.TextUtils.isEmpty;
import static com.example.chirag.carparlour.Main2Activity.ParallaxPageTransformer.ParallaxTransformInformation.PARALLAX_EFFECT_DEFAULT;

public class Main2Activity extends AppCompatActivity {
    private int[] layouts;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Button login=(Button)findViewById(R.id.login);
        final Button signup=(Button)findViewById(R.id.signup);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        layouts = new int[]{
                R.layout.slide1,
                R.layout.slide2,
                R.layout.slide3,
                R.layout.slide4};
        myViewPagerAdapter = new MyViewPagerAdapter();
        addBottomDots(0);
        changeStatusBarColor();
        viewPager.setAdapter(myViewPagerAdapter);
        ParallaxPageTransformer pageTransformer = new ParallaxPageTransformer()
                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.introimage, 2, 2))
                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.introtext, -0.65f,
                        PARALLAX_EFFECT_DEFAULT));
        viewPager.setPageTransformer(true, pageTransformer);
        ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);

                // changing the next button text 'NEXT' / 'GOT IT'
                if (position == 0) {    login.setBackgroundColor(ContextCompat.getColor(getApplication(), R.color.dot_light_screen1));
                    signup.setBackgroundColor(ContextCompat.getColor(getApplication(), R.color.dot_light_screen1));
                }
                else if (position==1){
                    login.setBackgroundColor(ContextCompat.getColor(getApplication(), R.color.dot_light_screen2));
                    signup.setBackgroundColor(ContextCompat.getColor(getApplication(), R.color.dot_light_screen2));
                }
                else if (position==2){
                    login.setBackgroundColor(ContextCompat.getColor(getApplication(), R.color.dot_light_screen3));
                    signup.setBackgroundColor(ContextCompat.getColor(getApplication(), R.color.dot_light_screen3));
                }
                else if (position==3){
                    login.setBackgroundColor(ContextCompat.getColor(getApplication(), R.color.dot_light_screen4));
                    signup.setBackgroundColor(ContextCompat.getColor(getApplication(), R.color.dot_light_screen4));
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        };
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    dialog = new Dialog(Main2Activity.this, android.R.style.Theme_Material_Light_Dialog_Alert);
                } else {
                    dialog = new Dialog(Main2Activity.this);
                }
                dialog.setContentView(R.layout.logindialog);

                dialog.setTitle(fromHtml("<font color='#c83737'>Log In</font>"));


                Button signinbutton = (Button) dialog.findViewById(R.id.signinbutton);
                final EditText edittextdial = (EditText) dialog.findViewById(R.id.email);
                final TextInputLayout edittexttil = (TextInputLayout) dialog.findViewById(R.id.edittextdialtil);

                final EditText passw = (EditText) dialog.findViewById(R.id.passw);
                final TextInputLayout pass = (TextInputLayout) dialog.findViewById(R.id.pass);


                edittextdial.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                edittexttil.setError(null);
                signinbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean cancel = false;
                        String email = edittextdial.getText().toString();
                        if (!isValidEmail(email)) {
                            edittexttil.setError("Invalid e-mail address");
                            edittexttil.requestFocus();
                            cancel = true;
                        }
                        if (cancel)
                            edittexttil.requestFocus();
                        else {
                            Intent i=new Intent(Main2Activity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }

                    }
                });

                dialog.show();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main2Activity.this,signup.class);
                startActivity(i);
                finish();

            }
        });
    }
    private boolean isValidEmail(String email) {

        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return !isEmpty(email) && pattern.matcher(email).matches();
    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
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
    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }
    public static class ParallaxPageTransformer implements ViewPager.PageTransformer {

        private List<ParallaxTransformInformation> mViewsToParallax
                = new ArrayList<ParallaxTransformInformation>();

        public ParallaxPageTransformer() {
        }

        public ParallaxPageTransformer(@NotNull List<ParallaxTransformInformation> viewsToParallax) {
            mViewsToParallax = viewsToParallax;
        }

        public ParallaxPageTransformer addViewToParallax(
                @NotNull ParallaxTransformInformation viewInfo) {
            if (mViewsToParallax != null) {
                mViewsToParallax.add(viewInfo);
            }
            return this;
        }

        public void transformPage(View view, float position) {

            int pageWidth = view.getWidth();

            if (position < -1) {
                // This page is way off-screen to the left.
                view.setAlpha(1);

            } else if (position <= 1 && mViewsToParallax != null) { // [-1,1]
                for (ParallaxTransformInformation parallaxTransformInformation : mViewsToParallax) {
                    applyParallaxEffect(view, position, pageWidth, parallaxTransformInformation,
                            position > 0);
                }
            } else {
                // This page is way off-screen to the right.
                view.setAlpha(1);
            }
        }

        private void applyParallaxEffect(View view, float position, int pageWidth,
                                         ParallaxTransformInformation information, boolean isEnter) {
            if (information.isValid() && view.findViewById(information.resource) != null) {
                if (isEnter && !information.isEnterDefault()) {
                    view.findViewById(information.resource)
                            .setTranslationX(-position * (pageWidth / information.parallaxEnterEffect));
                } else if (!isEnter && !information.isExitDefault()) {
                    view.findViewById(information.resource)
                            .setTranslationX(-position * (pageWidth / information.parallaxExitEffect));
                }
            }
        }



        /**
         * Information to make the parallax effect in a concrete view.
         * <p>
         * parallaxEffect positive values reduces the speed of the view in the translation
         * ParallaxEffect negative values increase the speed of the view in the translation
         * Try values to see the different effects. I recommend 2, 0.75 and 0.5
         */
        public static class ParallaxTransformInformation {

            public static final float PARALLAX_EFFECT_DEFAULT = -101.1986f;

            int resource = -1;
            float parallaxEnterEffect = 1f;
            float parallaxExitEffect = 1f;

            public ParallaxTransformInformation(int resource, float parallaxEnterEffect,
                                                float parallaxExitEffect) {
                this.resource = resource;
                this.parallaxEnterEffect = parallaxEnterEffect;
                this.parallaxExitEffect = parallaxExitEffect;
            }

            public boolean isValid() {
                return parallaxEnterEffect != 0 && parallaxExitEffect != 0 && resource != -1;
            }

            public boolean isEnterDefault() {
                return parallaxEnterEffect == PARALLAX_EFFECT_DEFAULT;
            }

            public boolean isExitDefault() {
                return parallaxExitEffect == PARALLAX_EFFECT_DEFAULT;
            }
        }
    }
}



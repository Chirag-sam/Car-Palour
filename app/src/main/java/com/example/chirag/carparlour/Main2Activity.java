package com.example.chirag.carparlour;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    ProgressDialog pd;
    Dialog dialog;

    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pd = new ProgressDialog(Main2Activity.this);
        pd.setMessage("loading");
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
       mDatabase = FirebaseDatabase.getInstance().getReference();

        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    checkuserexits();
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {

                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]

                // [END_EXCLUDE]
            }
        };
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


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    dialog = new Dialog(Main2Activity.this, android.R.style.Theme_Material_Light_Dialog_Alert);
                } else {
                    dialog = new Dialog(Main2Activity.this);
                }
                dialog.setContentView(R.layout.logindialog);
                dialog.setCancelable(true);
                dialog.setTitle(fromHtml("<font color='#c83737'>Log In</font>"));
                dialog.show();

                Button signinbutton = (Button) dialog.findViewById(R.id.signinbutton);
                final EditText emaile = (EditText) dialog.findViewById(R.id.email);
                final TextInputLayout emailt = (TextInputLayout) dialog.findViewById(R.id.edittextdialtil);

                final EditText passe = (EditText) dialog.findViewById(R.id.passw);
                final TextInputLayout passt = (TextInputLayout) dialog.findViewById(R.id.pass);




                signinbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        boolean cancel = false;
                        String email = emaile.getText().toString();
                        if (!isValidEmail(email)) {
                            emailt.setError("Invalid e-mail address");
                            emailt.requestFocus();
                            cancel = true;
                        }
                        else emailt.setError(null);
                        String password = passe.getText().toString();
                        if (TextUtils.isEmpty(password)) {
                            passt.setError("Required.");
                            passt.requestFocus();
                            cancel = true;
                        } else {
                            passt.setError(null);
                        }
                        if (cancel)
                            emailt.requestFocus();
                        else {
                            dialog.dismiss();
                            signIn(email,password);

                        }

                    }
                });



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
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    @Override
    protected void onDestroy()
    {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        if (pd!=null){
            pd.dismiss();
            pd=null;
        }
        super.onDestroy(); //finally
    }
    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
pd.show();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(Main2Activity.this, "Auth failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            Toast.makeText(Main2Activity.this, "Sign in failed try signing up first",
                                    Toast.LENGTH_SHORT).show();

                        }
                        else  {
                            checkuserexits();
                        }
                        pd.dismiss();

                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }
    private void checkuserexits() {
        final String userid = mAuth.getCurrentUser().getUid();
        if (pd!=null)
pd.show();




        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot datasnapshot) {
                if (datasnapshot.child("users").hasChild(userid)) {
                    if (pd!=null)
                    pd.dismiss();
                    startActivity(new Intent(Main2Activity.this, MainActivity.class));
                    finish();

                }
                else {Toast.makeText(Main2Activity.this, "No Data try signing up first",
                        Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Main2Activity.this, signup.class));
                    finish();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}



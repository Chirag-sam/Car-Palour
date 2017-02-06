package com.example.chirag.carparlour;

        import android.app.ActionBar;
        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.drawable.ColorDrawable;
        import android.net.Uri;
        import android.support.annotation.NonNull;
        import android.support.design.widget.NavigationView;
        import android.support.v4.print.PrintHelper;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.view.Menu;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    private FirebaseAuth mAuth;
    private static final String TAG = "Mainact";
    String userid;


    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    startActivity(new Intent(MainActivity.this, Main2Activity.class));
                    finish();
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]

                // [END_EXCLUDE]
            }
        };


            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.keepSynced(true);




            Button book = (Button)findViewById(R.id.book);
        Button sub = (Button)findViewById(R.id.sub);
        Button offers = (Button)findViewById(R.id.offers);
        Button support = (Button)findViewById(R.id.support);
        Button comp = (Button)findViewById(R.id.comp);


        dl = (DrawerLayout)findViewById(R.id.drawerlayout);
        abdt = new ActionBarDrawerToggle(this, dl , R.string.Open, R.string.Close);

        dl.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        final TextView mNamefieldn = (TextView) navigationView.getHeaderView(0).findViewById(R.id.navtext);
        FirebaseUser mUser = mAuth.getCurrentUser();
        if(mUser!=null){

        userid=mUser.getUid();
        mDatabase.child("users").child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot datasnapshot) {
                User u=datasnapshot.getValue(User.class);
                if (u!=null)
                mNamefieldn.setText(u.getName()+"\n"+u.getEmail());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });}
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.nav_account) {

                    Toast.makeText(MainActivity.this, "My account",
                            Toast.LENGTH_LONG).show();

                } else if (id == R.id.my_sub) {
                    Toast.makeText(MainActivity.this, "My sub",
                            Toast.LENGTH_LONG).show();

                } else if (id == R.id.t_c) {
                    Toast.makeText(MainActivity.this, "terms and cond",
                            Toast.LENGTH_LONG).show();
                }
                else if (id == R.id.change_pass) {
                    Toast.makeText(MainActivity.this, "change pass",
                            Toast.LENGTH_LONG).show();
            }
                else if (id == R.id.logout) {
                    Toast.makeText(MainActivity.this, "logout",
                            Toast.LENGTH_LONG).show();
                    mAuth.signOut();
                }
                return true;
            }
        });





        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, bookawash.class);
                startActivity(intent);
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, subscriptions.class);
                startActivity(intent);
            }
        });


        offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(abdt.onOptionsItemSelected(item))
        {return true;}


        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        // ...
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}

package com.example.chirag.carparlour;

        import android.app.ActionBar;
        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.drawable.ColorDrawable;
        import android.net.Uri;
        import android.support.design.widget.NavigationView;
        import android.support.v4.print.PrintHelper;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.view.Menu;
        import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}

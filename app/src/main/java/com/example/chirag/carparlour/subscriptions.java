package com.example.chirag.carparlour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class subscriptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriptions);
        Button yearly = (Button)findViewById(R.id.yearly);
        Button quarterly = (Button)findViewById(R.id.quarterly);
        Button monthly = (Button)findViewById(R.id.monthly);
        Button half = (Button)findViewById(R.id.half);

        yearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), displaysub.class);
                intent.putExtra("Washes", "5");
                intent.putExtra("sr", "5");
                intent.putExtra("dc", "5");
                intent.putExtra("ie", "5");
                intent.putExtra("price", "7000");
                startActivity(intent);
            }
        });
        quarterly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), displaysub.class);
                intent.putExtra("Washes", "4");
                intent.putExtra("sr", "4");
                intent.putExtra("dc", "4");
                intent.putExtra("ie", "4");
                intent.putExtra("price", "6000");
                startActivity(intent);
            }
        });
        monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), displaysub.class);
                intent.putExtra("Washes", "3");
                intent.putExtra("sr", "3");
                intent.putExtra("dc", "3");
                intent.putExtra("ie", "3");
                intent.putExtra("price", "5000");
                startActivity(intent);
            }
        });
        half.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), displaysub.class);
                intent.putExtra("Washes", "2");
                intent.putExtra("sr", "2");
                intent.putExtra("dc", "2");
                intent.putExtra("ie", "2");
                intent.putExtra("price", "4000");
                startActivity(intent);
            }
        });
    }
}

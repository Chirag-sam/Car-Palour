package com.example.chirag.carparlour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class displaysub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaysub);
        String wash = getIntent().getStringExtra("Washes");
        String sr = getIntent().getStringExtra("sr");
        String dc = getIntent().getStringExtra("dc");
        String ie = getIntent().getStringExtra("ie");
        String price = getIntent().getStringExtra("price");

        TextView washes = (TextView)findViewById(R.id.washes);
        TextView surref = (TextView)findViewById(R.id.surref);
        TextView duccle = (TextView)findViewById(R.id.duccle);
        TextView intenr = (TextView)findViewById(R.id.intenr);
        TextView price1 = (TextView)findViewById(R.id.price);

        washes.setText("1) "+wash+" washes");
        surref.setText("2) "+sr+" surface refinements");
        duccle.setText("3) "+dc+" a/c duct cleaning");
        intenr.setText("4) "+ie+" interior enrichment");
        price1.setText("5) "+price+" INR(Price)");




    }
}

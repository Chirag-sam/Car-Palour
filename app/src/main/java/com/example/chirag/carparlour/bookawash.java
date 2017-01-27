package com.example.chirag.carparlour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class bookawash extends AppCompatActivity {

    View focusView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookawash);
        final AutoCompleteTextView actv = (AutoCompleteTextView)findViewById(R.id.actv);
        final AutoCompleteTextView actv1 = (AutoCompleteTextView)findViewById(R.id.actv1);
        final AutoCompleteTextView actv3 = (AutoCompleteTextView)findViewById(R.id.actv3);
        ImageView im = (ImageView)findViewById(R.id.imageView);
        ImageView im1 = (ImageView)findViewById(R.id.imageView1);
        ImageView im2 = (ImageView)findViewById(R.id.imageView2);
        Button ok = (Button)findViewById(R.id.ok);
        final TextView tv = (TextView)findViewById(R.id.tv);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,vm);
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,maruti);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,hyuandai);
        final ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,audi);
        final ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,city);


        actv.setAdapter(adapter);
        actv3.setAdapter(adapter4);

        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actv.showDropDown();

            }
        });



        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String s = actv.getText().toString();
                if(s.equals("Maruti"))
                {
                    actv1.setAdapter(adapter1);
                }
                else if (s.equals("Hyuandi"))
                {
                    actv1.setAdapter(adapter2);
                }
                else if (s.equals("Audi"))
                {
                    actv1.setAdapter(adapter3);
                }
                actv1.showDropDown();
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actv3.showDropDown();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("Amount to be paid: 500");
            }
        });

    }


    private static final String vm[] = {"Maruti","Hyuandai","Audi"};
    private static final String maruti[] = {"Omni","800","Swift"};
    private static final String hyuandai[] = {"4S Fluidic Verna", "Accent","Elantra"};
    private static final String audi[] = {"A3", "A4","Q3","Q5"};
    private static final String city[] = {"Chennai", "Bangalore","Mumbai","Kolkata"};
    private boolean isEditTextEmpty(String mInput) {
        return mInput.length() == 0;
    }
}

package com.example.chirag.carparlour;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class subscriptions extends AppCompatActivity {
    boolean isBackVisible1 = false;
    boolean isBackVisible2 = false;
    boolean isBackVisible3 = false;
    boolean isBackVisible4 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriptions);
        final CardView yearly = (CardView)findViewById(R.id.yearly);
        final CardView quarterly = (CardView)findViewById(R.id.quarterly);
        final CardView monthly = (CardView)findViewById(R.id.monthly);
        final CardView half = (CardView)findViewById(R.id.half);
        final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                R.animator.card_flip_left_out);

        final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                R.animator.card_flip_right_in);
        final Button bt1=(Button) findViewById(R.id.bt1);
        final Button bt2=(Button) findViewById(R.id.bt2);
        final Button bt3=(Button) findViewById(R.id.bt3);
        final Button bt4=(Button) findViewById(R.id.bt4);
        final TextView tx1=(TextView)findViewById(R.id.txt1);
        final TextView tx2=(TextView)findViewById(R.id.txt2);
        final TextView tx3=(TextView)findViewById(R.id.txt3);
        final TextView tx4=(TextView)findViewById(R.id.txt4);

        bt1.setVisibility(View.GONE);
        bt2.setVisibility(View.GONE);
        bt3.setVisibility(View.GONE);
        bt4.setVisibility(View.GONE);


        yearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setVisibility(View.GONE);
                bt2.setVisibility(View.GONE);
                bt3.setVisibility(View.GONE);
                tx1.setText("Monthly");
                tx2.setText("Quarterly");
                tx3.setText("Half-yearly");
                isBackVisible1=false;
                isBackVisible2=false;
                isBackVisible3=false;
                if(!isBackVisible4){

                    setRightOut.setTarget(yearly);
                    setLeftIn.setTarget(yearly);
                    setRightOut.start();
                    setLeftIn.start();
                    bt4.setVisibility(View.VISIBLE);
                    tx4.setText(Html.fromHtml("<br> Yearly</br> <small><br> • 5 Washes</br><br>• 5 surface refinements</br><br>• 5 a/c duct cleaning</br><br>• 5 interior enrichment</br><br>• 7000 INR(Price)</br></small> "));
                    isBackVisible4 = true;
                }
                else{
                    setRightOut.setTarget(yearly);
                    setLeftIn.setTarget(yearly);
                    setRightOut.start();
                    setLeftIn.start();
                    bt4.setVisibility(View.GONE);
                    tx4.setText("Yearly");
                    isBackVisible4 = false;
                }


            }
        });
        quarterly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setVisibility(View.GONE);
                bt3.setVisibility(View.GONE);
                bt4.setVisibility(View.GONE);
                tx1.setText("Monthly");
                tx3.setText("Quarterly");
                tx4.setText("Yearly");
                isBackVisible1=false;
                isBackVisible3=false;
                isBackVisible4=false;
                if(!isBackVisible2){

                    setRightOut.setTarget(quarterly);
                    setLeftIn.setTarget(quarterly);
                    setRightOut.start();
                    setLeftIn.start();
                    bt2.setVisibility(View.VISIBLE);
                    tx2.setText(Html.fromHtml("<br> Quarterly</br> <small><br> • 4 Washes</br><br>• 4 surface refinements</br><br>• 4 a/c duct cleaning</br><br>• 4 interior enrichment</br><br>• 6000 INR(Price)</br></small> "));
                    isBackVisible2 = true;
                }
                else{
                    setRightOut.setTarget(quarterly);
                    setLeftIn.setTarget(quarterly);
                    setRightOut.start();
                    setLeftIn.start();
                    bt2.setVisibility(View.GONE);
                    tx2.setText("Quarterly");
                    isBackVisible2 = false;
                }

            }
        });
        monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt2.setVisibility(View.GONE);
                bt3.setVisibility(View.GONE);
                bt4.setVisibility(View.GONE);
                tx2.setText("Quarterly");
                tx3.setText("Half-Yearly");
                tx4.setText("Yearly");
                isBackVisible2=false;
                isBackVisible3=false;
                isBackVisible4=false;
                if(!isBackVisible1){

                    setRightOut.setTarget(monthly);
                    setLeftIn.setTarget(monthly);
                    setRightOut.start();
                    setLeftIn.start();
                    bt1.setVisibility(View.VISIBLE);
                    tx1.setText(Html.fromHtml("<br> Monthly</br> <small><br> • 2 Washes</br><br>• 2 surface refinements</br><br>• 2 a/c duct cleaning</br><br>• 2 interior enrichment</br><br>• 4000 INR(Price)</br></small> "));
                    isBackVisible1 = true;
                }
                else{
                    setRightOut.setTarget(monthly);
                    setLeftIn.setTarget(monthly);
                    setRightOut.start();
                    setLeftIn.start();
                    bt1.setVisibility(View.GONE);
                    tx1.setText("Monthly");
                    isBackVisible1 = false;
                }


            }
        });
        half.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setVisibility(View.GONE);
                bt2.setVisibility(View.GONE);
                bt4.setVisibility(View.GONE);
                tx1.setText("Monthly");
                tx2.setText("Quarterly");
                tx4.setText("Yearly");
                isBackVisible2=false;
                isBackVisible4=false;
                isBackVisible1=false;
                if(!isBackVisible3){

                    setRightOut.setTarget(half);
                    setLeftIn.setTarget(half);
                    setRightOut.start();
                    setLeftIn.start();
                    bt3.setVisibility(View.VISIBLE);
                    tx3.setText(Html.fromHtml("<br> Half-Yearly</br><small><br> • 3 Washes</br><br>• 3 surface refinements</br><br>• 3 a/c duct cleaning</br><br>• 3 interior enrichment</br><br>• 5000 INR(Price)</br></small> "));
                    isBackVisible3 = true;
                }
                else{
                    setRightOut.setTarget(half);
                    setLeftIn.setTarget(half);
                    setRightOut.start();
                    setLeftIn.start();
                    bt3.setVisibility(View.GONE);
                    tx3.setText("Half-Yearly");
                    isBackVisible3 = false;
                }



            }
        });
    }
}

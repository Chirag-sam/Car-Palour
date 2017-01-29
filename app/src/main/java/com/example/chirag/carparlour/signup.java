package com.example.chirag.carparlour;

import android.content.Intent;
import android.graphics.Paint;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.regex.Pattern;

import static android.text.TextUtils.isEmpty;

public class signup extends AppCompatActivity {

    private AutoCompleteTextView name;
    private AutoCompleteTextView number;
    private AutoCompleteTextView email;
    private AutoCompleteTextView pass;
    private AutoCompleteTextView passw;
    private CheckBox check;
    private Boolean s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        s = false;

        TextView text = (TextView) findViewById(R.id.terms);
        text.setPaintFlags(text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(signup.this, R.style.pop);
                builder.setTitle("Terms and conditions");
                builder.setMessage("When you book online/ via phone/via app with Carparlour.com, you should read and agree to the terms and conditions of the booking prior to making an appointment. Once an appointment has been made, you are bound under the terms and conditions herein.\n" +
                        "Booking Terms & Conditions\n" +
                        "Customers should remove all personal belongings, money and other significant items from their vehicle prior to any type of detailing\n" +
                        "Carparlour.com will not accept any liability for any loss or damage to any personal property including the vehicle being serviced, any belongings contained inside of the vehicle or to the location of service. Assure that your vehicle is being serviced in a lawful and safe area and if you feel otherwise please communicate your concerns with the technician.\n" +
                        "All vehicles are cleaned at the customers own risk and must be able to withstand normal cleaning processes. Any damage claims as a result of cleaning will be limited to the cost of the service.\n" +
                        "We cannot guarantee any firm times when accepting your booking.\n" +
                        "Our detailing times are an estimate only. Each vehicle is different and may require more or less time to fulfill the detailing type\n" +
                        "We reserve the right to use any one of our experienced staff to undertake your auto detailing and may need to change the detailer at short notice without penalty\n" +
                        "Whilst every effort will be made to keep regular bookings to the same day and time each week, sometimes it may be necessary to make changes but we will endeavor to communicate the changes prior to arrival\n" +
                        "We reserve the right to take a debit or credit card details to secure the booking. Debit/Credit cards shall be processed by Stripe.com.\n" +
                        "We reserve the right to alter or move a booking in line with staffing levels and/or weather conditions and /or machinery or equipment failure.\n" +
                        "All vehicles are cleaned at the customers own risk\n" +
                        "Carparlour.com reserves the right to refuse or deny any booking or continue with their service if they deem the customer to be unreasonable\n" +
                        "Carparlour.com reserves the right to refuse or deny customer demands above and beyond the booked detailing\n" +
                        "Carparlour.com will not tolerate any verbal or physical abuse towards any of its staff under any circumstances and will take the relevant actions should any such behaviors be encountered\n" +
                        "Pricing Terms & Conditions\n" +
                        "Our minimum call out charge is mentioned in the app.\n" +
                        "Carparlour.com reserve the right to charge an hourly rate  (or proportion thereof) for any work that is not within the boundaries of the booking\n" +
                        "A minimum 20% surcharge will be applied to all jobs that involve excessive pet hair, urine, vomit, blood, feces, or other hazardous materials.\n" +
                        "Our prices are based on detailing type, location, type of car and condition of the vehicle and will be advertised as a FROM price.\n" +
                        "Carparlour.com reserves the right to charge each vehicle according to its condition, the type of vehicle and the customer location.\n" +
                        "Carparlour.com reserves the right to amend any price during the detailing should it differ from the original estimate\n" +
                        "Communications\n" +
                        "Carparlour.com will send you SMS text messages to confirm your service request, provide information about the detailer matched to your job, and to conduct a customer satisfaction survey after the job is completed. Depending on your mobile phone service, you may incur costs from these SMS text messages from your mobile phone service provider. Carparlour.com is not responsible for any of these potential costs.\n" +
                        "Carparlour.com may provide your order request to any detailer within our network or out of our network to fulfill your request in a timely manner.\n" +
                        "When your service request is matched to a provider, your name, phone number, service address and vehicle information will be shared with that service provider.\n" +
                        "Carparlour.com may call you directly to schedule your service or conduct a customer satisfaction survey. In the event of a canceled request, we may call you to inquire about your reasons for canceling.\n" +
                        "All inbound and outbound customer service calls may be recorded for quality assurance.\n" +
                        "Our Service Terms & Conditions\n" +
                        "We may ask you where you heard about Carparlour.com in order to gauge our marketing strategies\n" +
                        "We may conduct customer satisfaction surveys\n" +
                        "We must have permission to hold a debit or credit card to secure a regular booking and we will charge the card after each visit.\n" +
                        "We reserve the right to charge a debit or credit card the full amount should the vehicles(s) not be available for cleaning and the booking has not been cancelled in advance (see cancellation policy)\n" +
                        "Cancellation Terms & Conditions\n" +
                        "We require 24 hours notice to cancel any booking/detailing service.\n" +
                        "Failure to provide 24 hours advance notice of cancellation will result in a minimum charge of 25% of the service cost.\n" +
                        "Carparlour.com reserve the right to take full payment if a booking is cancelled on the day without prior agreement.\n" +
                        "Carparlour.com reserves the right to alter or amend a booking time/date without penalty but wherever possible will let the customer know.\n" +
                        "Carparlour.com reserves the right to alter a booking or move a booking in accordance to its staff levels, weather conditions and/or equipment failure without penalty to the company.\n" +
                        "Failure to be present at the time and location of the ordered service will result in the full value of the service being charged.\n" +
                        "Service Guarantee\n" +
                        "If there is probable and clear cause that the service technician did not fulfill the package as ordered or there is clear evidence of poor craftsmanship, we will at our cost redo the uncompleted or unsatisfactory portion of the service free of charge.\n" +
                        "Any claims must be filed within 48 hours of the completion of the service by phone, email (support@Carparlour.com), or submitting a review online. Pictures of the area must be emailed to support@Carparlour.com within 24 hours of the claim being made.\n");
                builder.setPositiveButton("OK", null);
                // builder.setNegativeButton("Cancel", null);
                builder.show();

            }
        });

        name = (AutoCompleteTextView)findViewById(R.id.name);
        number = (AutoCompleteTextView)findViewById(R.id.number);
        email = (AutoCompleteTextView)findViewById(R.id.email);
        pass = (AutoCompleteTextView)findViewById(R.id.pass);
        passw = (AutoCompleteTextView)findViewById(R.id.passw);


        Button signup = (Button)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean cancel = false;


                View focusView = null;


                final String a = name.getText().toString();
                final String b = number.getText().toString();
                final String c = email.getText().toString();
                final String d = pass.getText().toString();
                final String e = passw.getText().toString();

                name.setError(null);
                number.setError(null);
                email.setError(null);
                pass.setError(null);
                passw.setError(null);





                TextInputLayout name1 = (TextInputLayout) findViewById(R.id.name1);
                TextInputLayout number1 = (TextInputLayout) findViewById(R.id.number1);
                TextInputLayout email1 = (TextInputLayout) findViewById(R.id.email1);
                TextInputLayout pass1 = (TextInputLayout) findViewById(R.id.pass1);
                TextInputLayout passw1 = (TextInputLayout) findViewById(R.id.passw1);
                check = (CheckBox) findViewById(R.id.check);


                
                if(e.compareTo(d)!=0)
                {
                  passw1.setError("Re-entered password does not match!");
                    focusView = pass1;
                    cancel = true;
                }
                if(isEditTextEmpty(a))
                {
                    name1.setError("Feild cannot be empty!");
                    focusView = name1;
                    cancel = true;
                }
                if(isEditTextEmpty(b))
                {
                    number1.setError("Feild cannot be empty!");
                    focusView = number1;
                    cancel = true;
                }
                if(isEditTextEmpty(c))
                {
                    email1.setError("Feild cannot be empty!");
                    focusView = email1;
                    cancel = true;
                }
                if(isEditTextEmpty(d))
                {
                    pass1.setError("Feild cannot be empty!");
                    focusView = pass1;
                    cancel = true;
                }
                if(isEditTextEmpty(e))
                {
                    passw1.setError("Feild cannot be empty!");
                    focusView = passw1;
                    cancel = true;
                }
                if (!isValidEmail(c)) {
                    email1.setError("Invalid e-mail address");
                    focusView = email1;
                    cancel = true;
                }
                if (!isValidPhone(b)) {
                    number1.setError("Invalid Number");
                    focusView = number1;
                    cancel = true;
                }






                if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();
                }
                else
                {
                    Intent i=new Intent(signup.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
    private boolean isEditTextEmpty(String mInput) {
        return mInput.length() == 0;
    }
    private boolean isValidEmail(String email) {

        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return !isEmpty(email) && pattern.matcher(email).matches();
    }
    private boolean isValidPhone(CharSequence target) {
        return !isEmpty(target) && android.util.Patterns.PHONE.matcher(target).matches() && target.length() == 10;
    }
}

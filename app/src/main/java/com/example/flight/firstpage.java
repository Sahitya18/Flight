    package com.example.flight;

    import androidx.annotation.Nullable;
    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.google.android.material.textfield.TextInputLayout;
    import com.google.firebase.auth.FirebaseAuth;

    public class firstpage extends AppCompatActivity {

        public final static int REQUEST_CODE_B = 1;
        public final static int REQUEST_CODE_C = 2;
        protected static String getCityName;
        Button oneWayBtn,rndTripBtn, adultInc,adultDec, childInc,childDec,bookFlightBtn;
        EditText adultTotalCount,childCount,passCount;
        TextView fromCity,toCity,depDate,returnDate;
        boolean selectFrom,selectTo;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_firstpage);
            fromCity=(TextView)findViewById(R.id.bookFrom);
            toCity=(TextView) findViewById(R.id.bookTo);
            adultTotalCount= (EditText) findViewById(R.id.adultCount);
            childCount=(EditText) findViewById(R.id.childrenCount);
            adultInc=(Button)findViewById(R.id.increaseButton1);
            adultDec=(Button)findViewById(R.id.decreaseButton1);
            childInc=(Button)findViewById(R.id.increaseButton2);
            childDec=(Button)findViewById(R.id.decreaseButton2);
            passCount=(EditText)findViewById(R.id.passengersCount);
            bookFlightBtn=(Button)findViewById(R.id.flightBookButton);

            fromCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectcity("from");
                }
            });
            toCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectcity("to");
                }
            });

            childCount.setText("0");
            adultInc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   adultTotalCount.setText(String.valueOf(Integer.parseInt(adultTotalCount.getText().toString())+1));
                    passCount.setText(String.valueOf(Integer.parseInt(adultTotalCount.getText().toString())+Integer.parseInt(childCount.getText().toString())));
                }
            });

            adultDec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Integer.parseInt(adultTotalCount.getText().toString())>=1) {
                        adultTotalCount.setText(String.valueOf(Integer.parseInt(adultTotalCount.getText().toString()) - 1));
                        passCount.setText(String.valueOf(Integer.parseInt(adultTotalCount.getText().toString()) + Integer.parseInt(childCount.getText().toString())));
                    }
                }
            });
            childInc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    childCount.setText(String.valueOf(Integer.parseInt(childCount.getText().toString())+1));
                    passCount.setText(String.valueOf(Integer.parseInt(adultTotalCount.getText().toString())+Integer.parseInt(childCount.getText().toString())));
                }
            });

            childDec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Integer.parseInt(childCount.getText().toString())>=1) {
                        childCount.setText(String.valueOf(Integer.parseInt(childCount.getText().toString()) - 1));
                        passCount.setText(String.valueOf(Integer.parseInt(adultTotalCount.getText().toString()) + Integer.parseInt(childCount.getText().toString())));
                    }

                }
            });

            bookFlightBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   //new page
                }
            });

        }
        private void selectcity(String string) {
            Intent i=new Intent(firstpage.this,cities.class);
            if(string.equals("from")) {
                i.putExtra("getCityName", fromCity.getText().toString());
                startActivityForResult(i, REQUEST_CODE_B);
            }else{
                i.putExtra("getCityName", toCity.getText().toString());
                startActivityForResult(i, REQUEST_CODE_C);
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_CODE_B && resultCode == RESULT_OK && !data.getStringExtra("getCityName").equals(toCity.getText().toString())){
                fromCity.setText(data.getStringExtra("getCityName"));
            }else if (requestCode == REQUEST_CODE_C && resultCode == RESULT_OK && !data.getStringExtra("getCityName").equals(fromCity.getText().toString()) ){
                toCity.setText(data.getStringExtra("getCityName"));
            }
            else{
                Toast.makeText(this, "You cannot select same citites", Toast.LENGTH_SHORT).show();
            }
        }
    }
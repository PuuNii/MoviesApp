package com.example.mymovies;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;


public class RigisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView backTologin;
    TextView PickDate;
    EditText nameEt, mailEt, passwordEt;
    Button signUpBtn;
ProgressBar mProgressBar ;
    final Calendar mCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigister);
        mAuth = FirebaseAuth.getInstance();
        setRefrenceOnViews();

        backTologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RigisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateViewOnScreen();

            }
        };
        PickDate = findViewById(R.id.brithDate);

        PickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RigisterActivity.this, dateSetListener, mCalendar
                        .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateData() ;

   }});


    }

    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }


    private void updateViewOnScreen() {
        String myFormat = "yyyy/mm/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        PickDate.setText(sdf.format(mCalendar.getTime()));
    }

    public void setRefrenceOnViews() {
mProgressBar = findViewById(R.id.progressBar2) ;
        nameEt = findViewById(R.id.nameEt);
        mailEt = findViewById(R.id.mailEt);
        passwordEt = findViewById(R.id.passwordEt);
        signUpBtn = findViewById(R.id.creatBtn);
        backTologin = (TextView) findViewById(R.id.backtoLogin);
    }

    private  void validateData ()
    {
        String email = mailEt.getText().toString();
        String password = passwordEt.getText().toString();
        String name = nameEt.getText().toString();

        if (email.isEmpty() || name.isEmpty() || password.isEmpty()) {


            // something goes wrong : all fields must be filled
            // we need to display an error message
            showMessage("Please Verify all fields");


        } else {
            // everything is ok and all fields are filled now we can start creating user account
            // CreateUserAccount method will try to create the user if the email is valid

            CreateUserAccount(email, name, password);
        }


    }

    private void CreateUserAccount(final String email, final String name, final String password) {
        final DatabaseReference rootRef ;
//        rootRef = FirebaseDatabase.getInstance().getReference() ;

//        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//               if (dataSnapshot.child("Users").child(email).exists())
//               {
//                   showMessage("This" + email + "Already Exists !");
//
//               }
//
//               else
//                   {
//
//
//                   }

//
//                HashMap<String,Object> userDataMap = new HashMap<>() ;
//                userDataMap.put("name", name) ;
//                userDataMap.put("email", email) ;
//                userDataMap.put("password", password) ;
//
//                rootRef.child("myUsers").child("email").updateChildren(userDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful())
//                        {
//                            signUpBtn.setVisibility(View.INVISIBLE);
//                            mProgressBar.setVisibility(View.VISIBLE);
//                            // user account created successfully
//                            showMessage("Account created");
//                            Intent intent = new Intent (RigisterActivity.this , LoginActivity.class) ;
//                            startActivity(intent);
//                            finish();
//                            showMessage("You Can Login Now ");
//
//                        }
//                    }
//                }) ;
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            signUpBtn.setVisibility(View.INVISIBLE);
                            mProgressBar.setVisibility(View.VISIBLE);
                            // user account created successfully
                            showMessage("Account created");
                            Intent intent = new Intent (RigisterActivity.this , LoginActivity.class) ;
                            startActivity(intent);
                            finish();
                            showMessage("You Can Login Now ");

                            // after we created user account we need to update his profile picture and name




                        }
                        else
                        {

                            // account creation failed
                            showMessage("account creation failed" + task.getException().getMessage());


                        }
                    }
                });

    }
        }


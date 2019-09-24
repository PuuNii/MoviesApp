package com.example.mymovies;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
EditText getEmail ;
EditText getPass ;
ImageButton loginBtn ;
TextView registerTE ;
ProgressBar mProgressBar ;
private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setViewsRefrencs();
        mAuth = FirebaseAuth.getInstance();

        registerTE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this , RigisterActivity.class) ;
                startActivity(intent);
                finish();

            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginBtn.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.VISIBLE     );
                final  String mail = getEmail.getText().toString();
                final String password = getPass.getText().toString();



                if (mail.isEmpty() || password.isEmpty()) {
                    showMessage("Please Verify All Field");
                    loginBtn.setVisibility(View.VISIBLE);
                    mProgressBar.setVisibility(View.INVISIBLE    );

                }
                else
                {
                    signIn(mail,password);
                }
            }
        });

    }

    private  void  setViewsRefrencs ()
    {

        getEmail =      findViewById(R.id.emailGet) ;
        getPass  =      findViewById(R.id.passwordGet) ;
        loginBtn    =   findViewById(R.id.loginButn) ;
        registerTE  =      findViewById(R.id.registerBtn) ;
        mProgressBar= findViewById(R.id.progressBar3) ;

    }
    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

    private void signIn(final String mail, String password) {


        mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {

                    Intent intent = new Intent(LoginActivity.this, MoviesHome.class) ;
                    startActivity(intent);
                    finish();


             showMessage("Welcome  " + mail);

                }
                else {

                    showMessage(task.getException().getMessage());
                    loginBtn.setVisibility(View.VISIBLE);
                    mProgressBar.setVisibility(View.INVISIBLE    );
                }


            }
        });



    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser user = mAuth.getCurrentUser();
//
//        if(user != null) {
//            showMessage("Welcome ");
//            //user is already connected  so we need to redirect him to home page
//            Intent intent = new Intent(LoginActivity.this , MoviesHome.class) ;
//            startActivity(intent);
//
//        }
//
//
//
//    }
}

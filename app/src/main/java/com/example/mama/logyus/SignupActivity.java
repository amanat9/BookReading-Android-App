package com.example.mama.logyus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tfb.fbtoast.FBToast;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{


    EditText editTextName;
    EditText editTextPassword;
    TextView textViewSignin;
    Button buttonLogin;
    private ProgressDialog progressDialog;


    //defining firebase auth object
    private FirebaseAuth firebaseAuth;
    RelativeLayout rLayout1,rLayout2;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            rLayout1.setVisibility(View.VISIBLE);
            rLayout2.setVisibility(View.VISIBLE);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        rLayout1 = findViewById(R.id.rLayout1);
        rLayout2 = findViewById(R.id.rLayout2);
        //initializing firebase auth object

        handler.postDelayed(runnable, 0);

        firebaseAuth = FirebaseAuth.getInstance();
        //initializing firebase auth object
        if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        //initializing views
        editTextName = (EditText) findViewById(R.id.editText1);
        editTextPassword = (EditText) findViewById(R.id.editText2);
        textViewSignin = (TextView) findViewById(R.id.tV);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        buttonLogin.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);


    }



    private void registerUser(){

        //getting email and password from edit texts
        String email = editTextName.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            FBToast.successToast(SignupActivity.this,"You Have Successfully Signed Up!", FBToast.LENGTH_LONG);
                            FBToast.infoToast(SignupActivity.this,"Welcome to Bookish!",FBToast.LENGTH_SHORT);
                        }else{
                            //display some message here
                            Toast.makeText(SignupActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View view) {

        if(view == buttonLogin){
            registerUser();
        }

        if(view == textViewSignin){
            //open login activity when user taps on the already registered textview
            startActivity(new Intent(this, ProfileActivity.class));
            //Toast.makeText(SignupActivity.this, "",Toast.LENGTH_LONG).show();

        }

    }

}

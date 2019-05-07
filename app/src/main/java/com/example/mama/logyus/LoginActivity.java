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


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {





    //defining views
    private Button buttonLogIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;


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
        setContentView(R.layout.activity_login);

        rLayout1 = findViewById(R.id.rLayout1);
        rLayout2 = findViewById(R.id.rLayout2);
        //initializing firebase auth object

        handler.postDelayed(runnable, 2000);



        //getting firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the objects getcurrentuser method is not null
        //means user is already logged in
        if(firebaseAuth.getCurrentUser() != null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editText1);
        editTextPassword = (EditText) findViewById(R.id.editText2);
        buttonLogIn = (Button) findViewById(R.id.buttonLogin);
        textViewSignup  = (TextView) findViewById(R.id.tV);

        progressDialog = new ProgressDialog(this);

        //attaching click listener
        buttonLogIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }

    //method for user login
    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
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

        progressDialog.setMessage("Logging in Please Wait...");
        progressDialog.show();

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            FBToast.successToast(LoginActivity.this,"WELCOME TO BOOKISH!", FBToast.LENGTH_LONG);
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogIn){
            userLogin();
        }

        if(view == textViewSignup){
            finish();
            startActivity(new Intent(this, SignupActivity.class));
        }
    }
}
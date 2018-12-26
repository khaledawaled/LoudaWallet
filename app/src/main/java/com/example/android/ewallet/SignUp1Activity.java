package com.example.android.ewallet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.ewallet.Models.User;
import com.google.android.gms.common.oob.SignUp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp1Activity extends AppCompatActivity {

    private static final String TAG ="testoo" ;
    private EditText emailField,passField,confirmpassField;
    private Button signup;
    private String email,pass,confirmPass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);

        init();
    }
    private void init(){

        emailField = (EditText)findViewById(R.id.emailsignup);
        passField = (EditText)findViewById(R.id.passsignup);
        confirmpassField = (EditText)findViewById(R.id.confirmpasssignup);
        signup = (Button) findViewById(R.id.signupBtn);
        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }
    private void signUp() {
        email = emailField.getText().toString();
        pass = passField.getText().toString();
        confirmPass = confirmpassField.getText().toString();
        if(!validateForm()) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.d(TAG, "onComplete: Register Successful");
                            Toast.makeText(SignUp1Activity.this, "Register Success",Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Log.d(TAG, "onComplete: Register unsuccessful");
                            Toast.makeText(SignUp1Activity.this, "Error Register Failed, Check your Connection",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private boolean validateForm() {
        boolean valid = true;
        if (TextUtils.isEmpty(email)) {
            emailField.setError("Required.");
            valid = false;
        } else {
            emailField.setError(null);
        }
        if (TextUtils.isEmpty(pass)) {
            passField.setError("Required.");
            valid = false;
        } else {
            passField.setError(null);
        }
        if (TextUtils.isEmpty(confirmPass)) {
            confirmpassField.setError("Required.");
            valid = false;
        } else {
            confirmpassField.setError(null);
        }
        if(!pass.equals(confirmPass)){
            passField.setError("Do not match");
            confirmpassField.setError("Do not match");
            valid =false;
        }
        else {
            passField.setError(null);
            confirmpassField.setError(null);
        }
        return valid;
    }
    private void goToMainPage() {
        Intent i = new Intent(SignUp1Activity.this,UserMain.class);
        startActivity(i);

    }
}

package com.example.android.ewallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp1Activity extends AppCompatActivity {

    private EditText email,pass,confirmpass;
    private Button signup;
    private String tempEmail,tempPass,tempConfirmPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);

        init();
    }

    private void init(){

        email = (EditText)findViewById(R.id.emailsignup);
        pass = (EditText)findViewById(R.id.passsignup);
        confirmpass = (EditText)findViewById(R.id.confirmpasssignup);
        signup = (Button) findViewById(R.id.signupBtn);




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 tempEmail = email.getText().toString();
                 tempPass = pass.getText().toString();
                 tempConfirmPass = confirmpass.getText().toString();

                if(!validateForm()) {
                    return;
                }

            }
        });



    }

    private boolean validateForm() {
        boolean valid = true;
        if (TextUtils.isEmpty(tempEmail)) {
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }
        if (TextUtils.isEmpty(tempPass)) {
            pass.setError("Required.");
            valid = false;
        } else {
            pass.setError(null);
        }
        if (TextUtils.isEmpty(tempConfirmPass)) {
            confirmpass.setError("Required.");
            valid = false;
        } else {
            confirmpass.setError(null);
        }
        if(!tempPass.equals(tempConfirmPass)){
            pass.setError("Do not match");
            confirmpass.setError("Do not match");
            valid =false;
        }
        else {
            pass.setError(null);
            confirmpass.setError(null);
        }
        return valid;
    }
}

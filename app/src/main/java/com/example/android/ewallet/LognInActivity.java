package com.example.android.ewallet;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LognInActivity extends AppCompatActivity {

    private static final String TAG = "khara";
    private EditText emailField, passwordField;
    private Button signInBtn;
    private String email, password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logn_in);
        init();
    }
    private void init() {
        mAuth = FirebaseAuth.getInstance();
        emailField = findViewById(R.id.username);
        passwordField = findViewById(R.id.password);
        signInBtn = findViewById(R.id.sign_in);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }
    private void signIn() {
        email = emailField.getText().toString();
        password = passwordField.getText().toString();
        Log.d(TAG, "signIn: " + email + "   " + password);
        Toast.makeText(LognInActivity.this, "email = " + email + " password = " + password, Toast.LENGTH_SHORT).show();

        if(!validateForm()) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.d(TAG, "onComplete: SUCCESS LOG IN");
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                        }
                        else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LognInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
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
        if (TextUtils.isEmpty(password)) {
            passwordField.setError("Required.");
            valid = false;
        } else {
            passwordField.setError(null);
        }
        return valid;
    }


}

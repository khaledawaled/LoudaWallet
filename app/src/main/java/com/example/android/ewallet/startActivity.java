package com.example.android.ewallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class startActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null) {
             sendUserToLoginActivity();
        }
        else {
            sendUserToMainAppActivity();
        }
    }
    private void sendUserToLoginActivity() {
        Intent i = new Intent(this, LognInActivity.class);
        startActivity(i);
    }
    private void sendUserToMainAppActivity() {
        Intent i = new Intent(this, UserMain.class);
        startActivity(i);

    }
}

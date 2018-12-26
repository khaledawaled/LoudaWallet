package com.example.android.ewallet;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.ewallet.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SecondRegisterActivity extends AppCompatActivity {

    private Uri uri;
    final static int reqcode = 1;
    private FirebaseAuth mAuth;
    String currentUserID;
    private EditText username,email,pass,fname,sname,ssn,pincode;
    private DatabaseReference dataRef;
    private DatabaseReference userRef;

    private Button signUpBtn,upload;
    public static final String TAG = "okay";
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_register);

        username = findViewById(R.id.username2);
        email = findViewById(R.id.email2);
        pass = findViewById(R.id.password2);
        fname = findViewById(R.id.firstname);
        sname = findViewById(R.id.secondname);
        ssn = findViewById(R.id.ssn);
        pincode = findViewById(R.id.pincode);
        signUpBtn = findViewById(R.id.sign_up);
        upload = findViewById(R.id.img_upload);

        user = new User();
        user.setEmail(email.getText().toString());
        user.setPassword(pass.getText().toString());

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("users");





        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,reqcode);
            }
        });

        signUpBtn = findViewById(R.id.signup);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDataIntoFireBase();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == reqcode && resultCode == RESULT_OK && data!= null && data.getData() != null)
        {

            uri = data.getData();

        }

    }

/*
    private void saveDataToFireBase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        Log.d(TAG, "saveDataToFireBase: " + mAuth.getCurrentUser().getEmail());
        dataRef = FirebaseDatabase.getInstance().getReference("loudawallet");
        dataRef.child("users").child(currentUserID).setValue(user);
    }
*/
    private void saveDataIntoFireBase(){

        HashMap userMap = new HashMap();

        userMap.put("username",username.getText().toString());
//        userMap.put("email",email.getText().toString());
 //       userMap.put("password",pass.getText().toString());
        userMap.put("first name",fname.getText().toString());
        userMap.put("second name",sname.getText().toString());
        userMap.put("SSN",ssn.getText().toString());
        userMap.put("PIN code",pincode.getText().toString());
       // userMap.put("image",uri.toString());

        userRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if(task.isSuccessful()){

                    Toast.makeText(SecondRegisterActivity.this,"Updated",Toast.LENGTH_SHORT).show();
                    Intent movetomain = new Intent(SecondRegisterActivity.this,UserMain.class);
                    movetomain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(movetomain);
                    finish();

                }
                else {

                    String msg = task.getException().getMessage();
                    Toast.makeText(SecondRegisterActivity.this,"msg",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}

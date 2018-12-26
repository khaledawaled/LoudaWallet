package com.example.android.ewallet;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.ewallet.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp2Fragment extends Fragment {

    private Uri uri;
    final static int reqcode = 1;
    private FirebaseAuth mAuth;
    String currentUserID;
    private EditText username,email,pass,fname,sname,ssn,pincode;
    private DatabaseReference dataRef;
    private Button signUpBtn;
    public static final String TAG = "khara";
    User user;

    public SignUp2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up2,container,false);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       /* username = view.findViewById(R.id.username2);
        email = view.findViewById(R.id.email2);
        pass = view.findViewById(R.id.password2);
        fname = view.findViewById(R.id.firstname);
        sname = view.findViewById(R.id.secondname);
        ssn = view.findViewById(R.id.ssn);
        pincode = view.findViewById(R.id.pincode);
        signUpBtn = view.findViewById(R.id.sign_up);
        user = new User();
        user.setEmail(email.getText().toString());
        user.setPassword(pass.getText().toString());


*/

        Button upload = view.findViewById(R.id.img_upload);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,reqcode);
            }
        });

        signUpBtn = view.findViewById(R.id.signup);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDataToFireBase();
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

    /*private void saveDataIntoFireBase(){

        HashMap userMap = new HashMap();

        userMap.put("username",username.getText().toString());
        userMap.put("email",email.getText().toString());
        userMap.put("password",pass.getText().toString());
        userMap.put("first name",fname.getText().toString());
        userMap.put("second name",sname.getText().toString());
        userMap.put("SSN",ssn.getText().toString());
        userMap.put("PIN code",pincode.getText().toString());
        userMap.put("image",uri.toString());

        userRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

           if(task.isSuccessful()){

               Toast.makeText(getActivity(),"Updated",Toast.LENGTH_SHORT).show();

           }
           else {

               String msg = task.getException().getMessage();
               Toast.makeText(getActivity(),"msg",Toast.LENGTH_SHORT).show();

           }

            }
        });

    }*/
}

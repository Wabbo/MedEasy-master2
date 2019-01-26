package com.example.nihal.medeasy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {


    EditText UserName, Phone, Address, Occupation, FamilyHistoryLink, Weight, Height, PassWord;
    Button saveData;

    FirebaseAuth auth;

    //  FirebaseDatabase database  ;
    // DatabaseReference myRef ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        UserName = findViewById(R.id.UserName);
        Phone = findViewById(R.id.Phone);
        Address = findViewById(R.id.Address);
        Occupation = findViewById(R.id.Occupation);
        FamilyHistoryLink = findViewById(R.id.FamilyHistoryLink);
        Weight = findViewById(R.id.Weight);
        Height = findViewById(R.id.Height);
        PassWord = findViewById(R.id.PassWord);
        saveData = findViewById(R.id.SignUp);

        auth = FirebaseAuth.getInstance();
        //  database  = FirebaseDatabase.getInstance();
        // myRef = database.getReference("Users");

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserPhoneInFireB = Phone.getText().toString() + "@gmail.com";
                String PassWordInFireB = PassWord.getText().toString();
                String UserNameInFireB = UserName.getText().toString();
                String AddressInFireB = Address.getText().toString();
                String OccupationInFireB = Occupation.getText().toString();
                String FamilyHistoryLinkInFireB = FamilyHistoryLink.getText().toString();
                String WeightInFireB = Weight.getText().toString();
                String HeightInFireB = Height.getText().toString();

                final User user = new User(
                        UserNameInFireB,
                        AddressInFireB,
                        OccupationInFireB,
                        FamilyHistoryLinkInFireB,
                        WeightInFireB,
                        HeightInFireB
                );

                auth.createUserWithEmailAndPassword(UserPhoneInFireB, PassWordInFireB).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            /*FirebaseUser firebaseUser = task.getResult().getUser();
                            String ref = firebaseUser.getUid();
                            myRef.child(ref).setValue(user);
                            */
                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(signup.this, " Register Successfull  ", Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    } else {
                                        if (task.getException().getMessage().equals("The email address is already in use by another account.")) {
                                            Toast.makeText(signup.this, "The phone is already in use by another user.", Toast.LENGTH_SHORT).show();
                                        } else
                                            Toast.makeText(signup.this, " Register Fail", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                        } else {
                            if (task.getException().getMessage().equals("The email address is already in use by another account.")) {
                                Toast.makeText(signup.this, "The phone is already in use by another user.", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(signup.this, " Register Fail", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });

    }

}


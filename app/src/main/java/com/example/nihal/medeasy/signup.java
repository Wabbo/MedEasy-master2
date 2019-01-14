package com.example.nihal.medeasy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    private FirebaseAuth auth ;
    FirebaseDatabase database ;
    DatabaseReference myRef ;

    EditText UserName , Phone , Address , Occupation , FamilyHistoryLink , Weight , Height , PassWord ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance() ;


        UserName = findViewById(R.id.UserName) ;
        Phone = findViewById(R.id.Phone) ;
        Address = findViewById(R.id.Address) ;
        Occupation = findViewById(R.id.Occupation) ;
        FamilyHistoryLink = findViewById(R.id.FamilyHistoryLink) ;
        Weight = findViewById(R.id.Weight) ;
        Height = findViewById(R.id.Height) ;
        PassWord = findViewById(R.id.PassWord) ;

    }

    public void SignUpOnClick ( View view ) {

        String UserNameInFireB = UserName.getText().toString() ;
        String PassWordInFireB = PassWord.getText().toString();

        database  = FirebaseDatabase.getInstance();
        myRef = database.getReference("TestDataBase") ;

        auth.createUserWithEmailAndPassword(UserNameInFireB,PassWordInFireB).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    myRef.setValue(Phone.getText().toString().trim());
                    myRef.setValue(Address.getText().toString().trim());
                    myRef.setValue(Occupation.getText().toString().trim());
                    myRef.setValue(FamilyHistoryLink.getText().toString().trim());
                    myRef.setValue(Weight.getText().toString().trim());
                    myRef.setValue(Height.getText().toString().trim());

                    Toast.makeText(signup.this,"Successful ",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(signup.this,"NotSuccessful ",Toast.LENGTH_SHORT).show();
                }
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}

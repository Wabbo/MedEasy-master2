package com.example.nihal.medeasy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    EditText loginPhone , loginPassWord ;
    Button login ;
    FirebaseAuth auth = FirebaseAuth.getInstance() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginPhone = findViewById(R.id.loginPhone) ;
        loginPassWord =findViewById(R.id.loginPassWord) ;
        login =findViewById(R.id.login) ;

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String UserPhoneInFireB = loginPhone.getText().toString()+"@gmail.com" ;
                String PassWordInFireB = loginPassWord.getText().toString();

                auth.signInWithEmailAndPassword(UserPhoneInFireB,PassWordInFireB).addOnCompleteListener(MainActivity.this , new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = task.getResult().getUser() ;
                            startActivity(new Intent(MainActivity.this,Home.class));
                        }
                        else {
                            Toast.makeText(MainActivity.this," Phone or PassWord are incorrect " ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });




    }

    public void signUpForm (View view) {
        Intent intent = new Intent(this,signup.class);
        startActivity(intent);
    }

}

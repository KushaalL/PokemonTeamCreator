package com.example.pokemonteamcreator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    Button SignIn;
    Button SignUp;
    EditText username;
    EditText password;
    DatabaseReference myRef;
    FirebaseDatabase database;
    boolean checkName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        SignIn = findViewById(R.id.buttonSignIn);
        SignUp = findViewById(R.id.buttonSignUp);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query checkUser = myRef.orderByChild("Username").equalTo(username.getText().toString());
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                            String passwordFromDB = dataSnapshot.child(username.getText().toString()).child("Password").getValue(String.class);
                            if(passwordFromDB.equals(password.getText().toString()))
                            {
                                Intent signInComplete = new Intent(Login.this, MainArea.class);
                                signInComplete.putExtra("user",username.getText().toString());
                                startActivity(signInComplete);
                            }
                            else
                            {
                                Toast.makeText(Login.this,"Incorrect Username or Password",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(Login.this,"Incorrect Username or Password",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Login.this,"Incorrect Username or Password",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public   void onClick(View view) {
                checkName = false;
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                            for(DataSnapshot snapshot:dataSnapshot.getChildren())
                            {
                                if(username.getText().toString().equals((String)snapshot.child("Username").getValue()))
                                {
                                    checkName = true;
                                }
                            }
                            if(checkName)
                            {
                                Toast.makeText(Login.this,"Username in Use",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                myRef.child(username.getText().toString()).child("Username").setValue(username.getText().toString());
                                myRef.child(username.getText().toString()).child("Password").setValue(password.getText().toString());
                                Intent signInComplete = new Intent(Login.this, MainArea.class);
                                signInComplete.putExtra("user",username.getText().toString());
                                startActivity(signInComplete);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }


        });
    }

}
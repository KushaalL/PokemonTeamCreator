package com.example.pokemonteamcreator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button SignIn;
    Button SignUp;
    EditText username;
    EditText password;
    DatabaseReference myRef;
    FirebaseDatabase database;
    ArrayList<Pokemon>PokeList;
    ArrayList<Team>TeamList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        SignIn = findViewById(R.id.buttonSignIn);
        SignUp = findViewById(R.id.buttonSignUp);
        PokeList = new ArrayList<>();
        Pokemon p1 = new Pokemon("1","1","1","1",null);
        Pokemon p2 = new Pokemon("2","2","2","2",null);
        PokeList.add(p1);
        PokeList.add(p2);
        TeamList = new ArrayList<>();
        Team t1 = new Team("Team 1",p1,p2,null,null,null,null);
        TeamList.add(t1);
        database = FirebaseDatabase.getInstance();
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public   void onClick(View view) {
                myRef = database.getReference("Users");
                myRef.child(username.getText().toString()+"~"+password.getText().toString()).child("PokeList").setValue(PokeList);
                myRef.child(username.getText().toString()+"~"+password.getText().toString()).child("TeamList").setValue(TeamList);
            }


        });
    }

}
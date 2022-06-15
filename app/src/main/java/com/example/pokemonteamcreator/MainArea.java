package com.example.pokemonteamcreator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainArea extends AppCompatActivity implements RecyclerPokemonAdapter.OnItemClickedListener {
    String user;
    ArrayList<Team>TeamList;
    ArrayList<Pokemon>PokeList;
    DatabaseReference myRef;
    FirebaseDatabase database;
    RecyclerView recyclerViewTeam,recyclerViewPokemon;
    RecyclerTeamAdapter teamAdapter;
    RecyclerPokemonAdapter pokemonAdapter;
    RecyclerView.LayoutManager layoutManagerTeam,layoutManagerPokemon;
    Button addTeam,addPokemon,signOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_main_area);
        recyclerViewTeam = findViewById(R.id.recyclerViewTeamList);
        recyclerViewPokemon = findViewById(R.id.recyclerViewPokeList);
        user = getIntent().getStringExtra("user");
        addTeam = findViewById(R.id.button_addTeam);
        addTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addingTeam = new Intent(MainArea.this,PokemonTeamCreator.class);
                addingTeam.putExtra("user",user);
                startActivity(addingTeam);
            }
        });
        addPokemon = findViewById(R.id.button_addPokemon);
        addPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addingTeam = new Intent(MainArea.this,PokemonCreator.class);
                addingTeam.putExtra("user",user);
                startActivity(addingTeam);
            }
        });
        signOut = findViewById(R.id.button_SignOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        layoutManagerTeam = new LinearLayoutManager(getApplicationContext());
        layoutManagerPokemon = new LinearLayoutManager(getApplicationContext());
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        TeamList = new ArrayList<>();
        PokeList = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(user).child("TeamList").exists())
                {
                    TeamList = (ArrayList<Team>) snapshot.child(user).child("TeamList").getValue();
                    System.out.println("Hey");
                    for(int x = 0;x<TeamList.size();x++)
                    {
                        System.out.println(TeamList.get(x).getName());
                        System.out.println("Hi");
                    }
                }
                if(snapshot.child(user).child("PokeList").exists())
                {
                    PokeList = (ArrayList<Pokemon>) snapshot.child(user).child("PokeList").getValue();
                    for(int x = 0;x<PokeList.size();x++)
                    {
                        System.out.println(PokeList.get(x).getName());
                        System.out.println("Hi");
                    }
                }
                teamAdapter = new RecyclerTeamAdapter(TeamList);
                recyclerViewTeam.setLayoutManager(layoutManagerTeam);
                recyclerViewTeam.setItemAnimator(new DefaultItemAnimator());
                recyclerViewTeam.setAdapter(teamAdapter);
                pokemonAdapter = new RecyclerPokemonAdapter(PokeList,MainArea.this);
                recyclerViewPokemon.setLayoutManager(layoutManagerPokemon);
                recyclerViewPokemon.setItemAnimator(new DefaultItemAnimator());
                recyclerViewPokemon.setAdapter(pokemonAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        addTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public void OnItemClickedListener(int position) {

    }
}
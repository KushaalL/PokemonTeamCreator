package com.example.pokemonteamcreator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jakewharton.processphoenix.ProcessPhoenix;
import java.util.ArrayList;

public class MainArea extends AppCompatActivity {
    String user;
    ArrayList<Team>TeamList;
    ArrayList<Pokemon>PokeList;
    DatabaseReference myRef;
    FirebaseDatabase database;
    Button addTeam,addPokemon,signOut;
    ListView ListPokemon,ListTeam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_area);
        user = getIntent().getStringExtra("user");
        ListPokemon = findViewById(R.id.MainArea_PokemonListView);
        ListPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent goToPokeViewer = new Intent(MainArea.this,PokemonView.class);
                goToPokeViewer.putExtra("Pokemon",i);
                goToPokeViewer.putExtra("user",user);
                startActivity(goToPokeViewer);
            }
        });
        ListTeam = findViewById(R.id.MainArea_TeamListView);
        ListTeam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent goToTeamViewer = new Intent(MainArea.this,TeamView.class);
                goToTeamViewer.putExtra("user",user);
                goToTeamViewer.putExtra("Team",i);
                startActivity(goToTeamViewer);
            }
        });
        addTeam = findViewById(R.id.button_addingPokemonTeam);
        addTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Going to team creator");
                Intent addingPokemonTeam = new Intent(MainArea.this,PokemonTeamCreator.class);
                addingPokemonTeam.putExtra("user",user);
                startActivity(addingPokemonTeam);
            }
        });
        addPokemon = findViewById(R.id.button_addPokemon);
        addPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addingPokemon = new Intent(MainArea.this,PokemonCreator.class);
                addingPokemon.putExtra("user",user);
                startActivity(addingPokemon);
            }
        });
        signOut = findViewById(R.id.button_SignOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProcessPhoenix.triggerRebirth(getApplicationContext());
            }
        });
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        TeamList = new ArrayList<>();
        PokeList = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(user).child("PokeList").exists())
                {
                    for(DataSnapshot snapshot:dataSnapshot.child(user).child("PokeList").getChildren())
                    {
                        PokeList.add(snapshot.getValue(Pokemon.class));
                    }
                }
                if(dataSnapshot.child(user).child("TeamList").exists())
                {
                    for(DataSnapshot snapshot:dataSnapshot.child(user).child("TeamList").getChildren())
                    {
                        String teamName = snapshot.child("name").getValue().toString();
                        Pokemon poke1 = snapshot.child("pokemon1").getValue(Pokemon.class);
                        Pokemon poke2 = snapshot.child("pokemon2").getValue(Pokemon.class);
                        Pokemon poke3 = snapshot.child("pokemon3").getValue(Pokemon.class);
                        Pokemon poke4 = snapshot.child("pokemon4").getValue(Pokemon.class);
                        Pokemon poke5 = snapshot.child("pokemon5").getValue(Pokemon.class);
                        Pokemon poke6 = snapshot.child("pokemon6").getValue(Pokemon.class);
                        Team team = new Team(teamName,poke1,poke2,poke3,poke4,poke5,poke6);
                        TeamList.add(team);
                    }
                }
                ListViewPokemonAdapter listViewPokemonAdapter = new ListViewPokemonAdapter(MainArea.this,R.layout.list_pokemon,PokeList);
                ListPokemon.setAdapter(listViewPokemonAdapter);
                ListViewTeamAdapter listViewTeamAdapter = new ListViewTeamAdapter(MainArea.this,R.layout.list_team,TeamList);
                ListTeam.setAdapter(listViewTeamAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
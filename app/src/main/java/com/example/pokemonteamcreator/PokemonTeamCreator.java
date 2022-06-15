package com.example.pokemonteamcreator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PokemonTeamCreator extends AppCompatActivity implements RecyclerPokemonAdapter.OnItemClickedListener {
    EditText teamName;
    ImageView pokeImg1,pokeImg2,pokeImg3,pokeImg4,pokeImg5,pokeImg6;
    Button createTeam;
    Pokemon poke1;
    Pokemon poke2;
    Pokemon poke3;
    Pokemon poke4;
    Pokemon poke5;
    Pokemon poke6;
    int numSlot = 0;
    ArrayList<Pokemon>PokeList;
    DatabaseReference myRef;
    FirebaseDatabase database;
    String user;
    RecyclerView recyclerViewPokeList;
    RecyclerPokemonAdapter recyclerPokemonAdapter;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_team_creator);
        user = getIntent().getStringExtra("user");
        PokeList = new ArrayList<>();
        createTeam = findViewById(R.id.createTeam);
        teamName = findViewById(R.id.editTextName);
        pokeImg1 = findViewById(R.id.poke1);
        pokeImg2 = findViewById(R.id.poke2);
        pokeImg3 = findViewById(R.id.poke3);
        pokeImg4 = findViewById(R.id.poke4);
        pokeImg5 = findViewById(R.id.poke5);
        pokeImg6 = findViewById(R.id.poke6);
        recyclerViewPokeList = findViewById(R.id.recyclerViewPokeList2);

        layoutManager = new LinearLayoutManager(getApplicationContext());
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(user).child("PokeList").exists())
                {
                    PokeList = (ArrayList<Pokemon>) snapshot.child(user).child("PokeList").getValue();
                    for(int x = 0;x<PokeList.size();x++)
                    {
                        System.out.println(PokeList.get(x).getName());
                        System.out.println("Hi");
                    }
                }
                recyclerPokemonAdapter = new RecyclerPokemonAdapter(PokeList,PokemonTeamCreator.this);
                recyclerViewPokeList.setLayoutManager(layoutManager);
                recyclerViewPokeList.setItemAnimator(new DefaultItemAnimator());
                recyclerViewPokeList.setAdapter(recyclerPokemonAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        pokeImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numSlot = 1;
                Toast.makeText(PokemonTeamCreator.this,"You Selected Slot "+numSlot,Toast.LENGTH_SHORT).show();
            }
        });
        pokeImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numSlot = 2;
                Toast.makeText(PokemonTeamCreator.this,"You Selected Slot "+numSlot,Toast.LENGTH_SHORT).show();
            }
        });
        pokeImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numSlot = 3;
                Toast.makeText(PokemonTeamCreator.this,"You Selected Slot "+numSlot,Toast.LENGTH_SHORT).show();
            }
        });
        pokeImg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numSlot = 4;
                Toast.makeText(PokemonTeamCreator.this,"You Selected Slot "+numSlot,Toast.LENGTH_SHORT).show();
            }
        });
        pokeImg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numSlot = 5;
                Toast.makeText(PokemonTeamCreator.this,"You Selected Slot "+numSlot,Toast.LENGTH_SHORT).show();
            }
        });
        pokeImg6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numSlot = 6;
                Toast.makeText(PokemonTeamCreator.this,"You Selected Slot "+numSlot,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void OnItemClickedListener(int position) {
        if (numSlot==1)
        {
            poke1 = PokeList.get(position);
            pokeImg1.setImageBitmap(poke1.getPic());
        }
    }
}
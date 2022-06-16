package com.example.pokemonteamcreator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PokemonTeamCreator extends AppCompatActivity{
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
    Team result;
    ListView PokemonListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_team_creator);
        user = getIntent().getStringExtra("user");
        PokeList = new ArrayList<>();
        PokemonListView = findViewById(R.id.TeamCreator_ListViewPokemon);
        createTeam = findViewById(R.id.createTeam);
        teamName = findViewById(R.id.TeamViewer_TeamName);
        pokeImg1 = findViewById(R.id.TeamViewer_PokePic1);
        pokeImg2 = findViewById(R.id.TeamViewer_PokePic2);
        pokeImg3 = findViewById(R.id.TeamViewer_PokePic3);
        pokeImg4 = findViewById(R.id.TeamViewer_PokePic4);
        pokeImg5 = findViewById(R.id.TeamViewer_PokePic5);
        pokeImg6 = findViewById(R.id.TeamViewer_PokePic6);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        PokemonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (numSlot==1)
                {
                    poke1 = PokeList.get(position);
                    String pokePicString = PokeList.get(position).getPic();
                    byte[] bytes= Base64.decode(pokePicString,Base64.DEFAULT);
                    Bitmap pokePic = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    pokeImg1.setImageBitmap(pokePic);
                }
                else if (numSlot==2)
                {
                    poke2 = PokeList.get(position);
                    String pokePicString = PokeList.get(position).getPic();
                    byte[] bytes= Base64.decode(pokePicString,Base64.DEFAULT);
                    Bitmap pokePic = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    pokeImg2.setImageBitmap(pokePic);
                }
                else if(numSlot==3)
                {
                    poke3 = PokeList.get(position);
                    String pokePicString = PokeList.get(position).getPic();
                    byte[] bytes= Base64.decode(pokePicString,Base64.DEFAULT);
                    Bitmap pokePic = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    pokeImg3.setImageBitmap(pokePic);
                }
                else if(numSlot==4)
                {
                    poke4 = PokeList.get(position);
                    String pokePicString = PokeList.get(position).getPic();
                    byte[] bytes= Base64.decode(pokePicString,Base64.DEFAULT);
                    Bitmap pokePic = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    pokeImg4.setImageBitmap(pokePic);
                }
                else if(numSlot==5)
                {
                    poke5= PokeList.get(position);
                    String pokePicString = PokeList.get(position).getPic();
                    byte[] bytes= Base64.decode(pokePicString,Base64.DEFAULT);
                    Bitmap pokePic = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    pokeImg5.setImageBitmap(pokePic);
                }
                else if(numSlot==6)
                {
                    poke6 = PokeList.get(position);
                    String pokePicString = PokeList.get(position).getPic();
                    byte[] bytes= Base64.decode(pokePicString,Base64.DEFAULT);
                    Bitmap pokePic = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    pokeImg6.setImageBitmap(pokePic);
                }
                else
                {
                    Toast.makeText(PokemonTeamCreator.this,"Select a Slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                ListViewPokemonAdapter listViewPokemonAdapter = new ListViewPokemonAdapter(PokemonTeamCreator.this,R.layout.list_pokemon,PokeList);
                PokemonListView.setAdapter(listViewPokemonAdapter);
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
        createTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!teamName.getText().toString().equalsIgnoreCase("")&&poke1!=null&&poke2!=null&&poke3!=null&&poke4!=null&&poke5!=null&&poke6!=null)
                {
                    result = new Team(teamName.getText().toString(),poke1,poke2,poke3,poke4,poke5,poke6);
                    myRef.child(user).child("TeamList").child(teamName.getText().toString()).setValue(result);
                    Intent backToMain = new Intent(PokemonTeamCreator.this,MainArea.class);
                    backToMain.putExtra("user",user);
                    startActivity(backToMain);
                }
                else
                {
                    Toast.makeText(PokemonTeamCreator.this,"Missing Attributes",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
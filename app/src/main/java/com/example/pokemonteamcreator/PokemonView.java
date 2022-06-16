package com.example.pokemonteamcreator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PokemonView extends AppCompatActivity {
    DatabaseReference myRef;
    FirebaseDatabase database;
    TextView name,type1,type2,ability;
    ImageView pic;
    String user;
    int PokeNum;
    ArrayList<Pokemon> PokeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_view);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        user = getIntent().getStringExtra("user");
        PokeNum = getIntent().getIntExtra("Pokemon",0);
        name = findViewById(R.id.PokemonViewer_Name);
        type1 = findViewById(R.id.PokemonViewer_pokemonType);
        type2 = findViewById(R.id.PokemonViewer_pokemonType2);
        ability = findViewById(R.id.PokemonViewer_pokemonAbility);
        pic = findViewById(R.id.PokemonViewer_imageViewPokePic);
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
                name.setText(PokeList.get(PokeNum).getName());
                type1.setText(PokeList.get(PokeNum).getType1());
                type2.setText(PokeList.get(PokeNum).getType2());
                ability.setText(PokeList.get(PokeNum).getAbility1());
                String img = PokeList.get(PokeNum).getPic();
                byte[] bytes= Base64.decode(img,Base64.DEFAULT);
                Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                pic.setImageBitmap(bitmap);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
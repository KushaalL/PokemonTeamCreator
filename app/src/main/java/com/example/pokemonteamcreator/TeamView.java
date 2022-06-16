package com.example.pokemonteamcreator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class TeamView extends AppCompatActivity {
    TextView teamName,p1Name,p2Name,p3Name,p4Name,p5Name,p6Name;
    ImageView p1Pic,p2Pic,p3Pic,p4Pic,p5Pic,p6Pic;
    DatabaseReference myRef;
    FirebaseDatabase database;
    ArrayList<Team> TeamList;
    String user;
    int TeamNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_view);
        teamName = findViewById(R.id.TeamViewer_TeamName);
        p1Name = findViewById(R.id.teamViewer_PokeName1);
        p2Name = findViewById(R.id.teamViewer_PokeName2);
        p3Name = findViewById(R.id.teamViewer_PokeName3);
        p4Name = findViewById(R.id.teamViewer_PokeName4);
        p5Name = findViewById(R.id.teamViewer_PokeName5);
        p6Name = findViewById(R.id.teamViewer_PokeName6);
        p1Pic = findViewById(R.id.TeamViewer_PokePic1);
        p2Pic = findViewById(R.id.TeamViewer_PokePic2);
        p3Pic = findViewById(R.id.TeamViewer_PokePic3);
        p4Pic = findViewById(R.id.TeamViewer_PokePic4);
        p5Pic = findViewById(R.id.TeamViewer_PokePic5);
        p6Pic = findViewById(R.id.TeamViewer_PokePic6);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        user = getIntent().getStringExtra("user");
        TeamNum = getIntent().getIntExtra("Team",0);
        TeamList = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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
                    teamName.setText(TeamList.get(TeamNum).getName());
                    p1Name.setText(TeamList.get(TeamNum).getPokemon1().getName());
                    p2Name.setText(TeamList.get(TeamNum).getPokemon2().getName());
                    p3Name.setText(TeamList.get(TeamNum).getPokemon3().getName());
                    p4Name.setText(TeamList.get(TeamNum).getPokemon4().getName());
                    p5Name.setText(TeamList.get(TeamNum).getPokemon5().getName());
                    p6Name.setText(TeamList.get(TeamNum).getPokemon6().getName());

                    String pic1 = TeamList.get(TeamNum).getPokemon1().getPic();
                    byte[] bytes1= Base64.decode(pic1, Base64.DEFAULT);
                    p1Pic.setImageBitmap(BitmapFactory.decodeByteArray(bytes1,0,bytes1.length));

                    String pic2 = TeamList.get(TeamNum).getPokemon2().getPic();
                    byte[] bytes2= Base64.decode(pic2, Base64.DEFAULT);
                    p2Pic.setImageBitmap(BitmapFactory.decodeByteArray(bytes2,0,bytes2.length));

                    String pic3 = TeamList.get(TeamNum).getPokemon3().getPic();
                    byte[] bytes3= Base64.decode(pic3, Base64.DEFAULT);
                    p3Pic.setImageBitmap(BitmapFactory.decodeByteArray(bytes3,0,bytes3.length));

                    String pic4 = TeamList.get(TeamNum).getPokemon4().getPic();
                    byte[] bytes4= Base64.decode(pic4, Base64.DEFAULT);
                    p4Pic.setImageBitmap(BitmapFactory.decodeByteArray(bytes4,0,bytes4.length));

                    String pic5 = TeamList.get(TeamNum).getPokemon5().getPic();
                    byte[] bytes5= Base64.decode(pic5, Base64.DEFAULT);
                    p5Pic.setImageBitmap(BitmapFactory.decodeByteArray(bytes5,0,bytes5.length));

                    String pic6 = TeamList.get(TeamNum).getPokemon6().getPic();
                    byte[] bytes6= Base64.decode(pic6, Base64.DEFAULT);
                    p6Pic.setImageBitmap(BitmapFactory.decodeByteArray(bytes6,0,bytes6.length));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
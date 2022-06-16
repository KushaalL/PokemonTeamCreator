package com.example.pokemonteamcreator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PokemonCreator extends AppCompatActivity {
    EditText pokeName;
    String name,ability,type1,type2,user;
    TextView pokeType1,pokeType2,pokeAbility;
    Pokemon result;
    Button createPokemon;
    ImageView pokePic;
    Bitmap pic;
    DatabaseReference myRef;
    FirebaseDatabase database;
    boolean checkImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_creator);
        pokeName = findViewById(R.id.PokemonViewer_Name);
        pokeType1 = findViewById(R.id.PokemonViewer_pokemonType);
        pokeType2 = findViewById(R.id.PokemonViewer_pokemonType2);
        pokeAbility = findViewById(R.id.PokemonViewer_pokemonAbility);
        createPokemon = findViewById(R.id.createPokemonButton);
        pokePic = findViewById(R.id.PokemonViewer_imageViewPokePic);
        user = getIntent().getStringExtra("user");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        name = "";
        ability = "";
        type1 = "";
        type2 = "";
        checkImg = false;
        pokeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                name = pokeName.getText().toString();
                name = name.toLowerCase();
                new async().execute();
            }
        });
        pokePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,1);
            }
        });

        createPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ability.equalsIgnoreCase("N/A")&&checkImg)
                {
                    result = new Pokemon(pokeName.getText().toString(),type1,type2,ability,pic);
                    myRef.child(user).child("PokeList").child(pokeName.getText().toString()).setValue(result);
                    Intent goBackToMain = new Intent(PokemonCreator.this,MainArea.class);
                    goBackToMain.putExtra("user",user);
                    startActivity(goBackToMain);
                }
                else
                {
                    Toast.makeText(PokemonCreator.this,"Missing Attributes",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK)
        {
            pic = (Bitmap)data.getExtras().get("data");
            pokePic.setImageBitmap(pic);
            checkImg = true;
        }
    }
    private class async extends AsyncTask<Void,Void, JSONObject>
    {
        @Override
        protected JSONObject doInBackground(Void... voids) {
            String result = "";
            String api = "https://pokeapi.co/api/v2/pokemon/"+(name);
            JSONObject finalJson = new JSONObject();
            try{
                URL url = new URL(api);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream response = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(response));
                String line = br.readLine();
                while(line!=null)
                {
                    result += line;
                    line = br.readLine();
                }
                JSONObject jsonObject = new JSONObject(result);
                finalJson = jsonObject;
            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (JSONException e) {
                e.printStackTrace();
                Log.d("Tag","Catch3");
            }
            return finalJson;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try {
                JSONArray abilities = jsonObject.getJSONArray("abilities");
                ability = abilities.getJSONObject(0).getJSONObject("ability").getString("name");
                ability = ability.replace("-"," ");
                ability = ability.toUpperCase();
            } catch (JSONException e) {
                e.printStackTrace();
                ability = "N/A";
            }
            try {
                JSONArray types = jsonObject.getJSONArray("types");
                type1 = types.getJSONObject(0).getJSONObject("type").getString("name");
                type1 = type1.toUpperCase();
            }catch (JSONException e) {
                e.printStackTrace();
                type1 = "N/A";
            }
            try {
                JSONArray types = jsonObject.getJSONArray("types");
                type2 = types.getJSONObject(1).getJSONObject("type").getString("name");
                type2 = type2.toUpperCase();
            }catch (JSONException e) {
                e.printStackTrace();
                type2 = "N/A";
            }
            pokeAbility.setText(ability);
            pokeType1.setText(type1);
            pokeType2.setText(type2);

        }

    }
}
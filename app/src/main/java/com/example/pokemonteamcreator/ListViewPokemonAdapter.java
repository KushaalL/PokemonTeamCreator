package com.example.pokemonteamcreator;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class ListViewPokemonAdapter extends ArrayAdapter<Pokemon>
{
    Context mainContext;
    ArrayList<Pokemon> PokeList;
    public ListViewPokemonAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Pokemon> objects) {
        super(context, resource,objects);
        mainContext = context;
        PokeList = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mainContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_pokemon, null);
        TextView pokemonName = view.findViewById(R.id.pokemon_name);
        ImageView imageview = view.findViewById(R.id.pokemon_pic);
        pokemonName.setText(PokeList.get(position).getName());
        String pic = PokeList.get(position).getPic();
        byte[] bytes=Base64.decode(pic, Base64.DEFAULT);
        imageview.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));

        return view;
    }
}

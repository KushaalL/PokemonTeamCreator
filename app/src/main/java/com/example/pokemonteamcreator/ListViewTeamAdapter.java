package com.example.pokemonteamcreator;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListViewTeamAdapter extends ArrayAdapter<Team>
{
    Context mainContext;
    ArrayList<Team> TeamList;
    public ListViewTeamAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Team> objects) {
        super(context, resource, objects);
        mainContext = context;
        TeamList = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mainContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_team, null);
        TextView pokemonName = view.findViewById(R.id.team_name);
        ImageView img1 = view.findViewById(R.id.team_p1);
        ImageView img2 = view.findViewById(R.id.team_p2);
        ImageView img3 = view.findViewById(R.id.team_p3);
        ImageView img4 = view.findViewById(R.id.team_p4);
        ImageView img5 = view.findViewById(R.id.team_p5);
        ImageView img6 = view.findViewById(R.id.team_p6);
        pokemonName.setText(TeamList.get(position).getName());

        String pic1 = TeamList.get(position).getPokemon1().getPic();
        byte[] bytes1= Base64.decode(pic1, Base64.DEFAULT);
        img1.setImageBitmap(BitmapFactory.decodeByteArray(bytes1,0,bytes1.length));

        String pic2 = TeamList.get(position).getPokemon2().getPic();
        byte[] bytes2= Base64.decode(pic2, Base64.DEFAULT);
        img2.setImageBitmap(BitmapFactory.decodeByteArray(bytes2,0,bytes2.length));

        String pic3 = TeamList.get(position).getPokemon3().getPic();
        byte[] bytes3= Base64.decode(pic3, Base64.DEFAULT);
        img3.setImageBitmap(BitmapFactory.decodeByteArray(bytes3,0,bytes3.length));

        String pic4 = TeamList.get(position).getPokemon4().getPic();
        byte[] bytes4= Base64.decode(pic4, Base64.DEFAULT);
        img4.setImageBitmap(BitmapFactory.decodeByteArray(bytes4,0,bytes4.length));

        String pic5 = TeamList.get(position).getPokemon5().getPic();
        byte[] bytes5= Base64.decode(pic5, Base64.DEFAULT);
        img5.setImageBitmap(BitmapFactory.decodeByteArray(bytes5,0,bytes5.length));

        String pic6 = TeamList.get(position).getPokemon6().getPic();
        byte[] bytes6= Base64.decode(pic6, Base64.DEFAULT);
        img6.setImageBitmap(BitmapFactory.decodeByteArray(bytes6,0,bytes6.length));

        return view;
    }
}

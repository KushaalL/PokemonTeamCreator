package com.example.pokemonteamcreator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerTeamAdapter extends RecyclerView.Adapter<RecyclerTeamAdapter.MyViewHolder>{
    private ArrayList<Team> teamList;
    public RecyclerTeamAdapter(ArrayList<Team>teamList)
    {
        this.teamList = teamList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView teamName;
        private ImageView img1,img2,img3,img4,img5,img6;
        public MyViewHolder(final View view)
        {
            super(view);
            teamName = view.findViewById(R.id.team_name);
            img1 = view.findViewById(R.id.team_p1);
            img2 = view.findViewById(R.id.team_p2);
            img3 = view.findViewById(R.id.team_p3);
            img4 = view.findViewById(R.id.team_p4);
            img5 = view.findViewById(R.id.team_p5);
            img6 = view.findViewById(R.id.team_p6);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_team,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.teamName.setText(teamList.get(position).getName());
        holder.img1.setImageBitmap(teamList.get(position).getPokemon1().getPic());
        holder.img2.setImageBitmap(teamList.get(position).getPokemon2().getPic());
        holder.img3.setImageBitmap(teamList.get(position).getPokemon3().getPic());
        holder.img4.setImageBitmap(teamList.get(position).getPokemon4().getPic());
        holder.img5.setImageBitmap(teamList.get(position).getPokemon5().getPic());
        holder.img6.setImageBitmap(teamList.get(position).getPokemon6().getPic());
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }
}

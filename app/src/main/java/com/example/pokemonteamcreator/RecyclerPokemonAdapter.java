package com.example.pokemonteamcreator;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerPokemonAdapter extends RecyclerView.Adapter<RecyclerPokemonAdapter.MyViewHolder>{
    private ArrayList<Pokemon> recyclerPokemonList;
    private OnItemClickedListener mOnItemClickedListener;
    public RecyclerPokemonAdapter(ArrayList<Pokemon>recyclerPokemonList, OnItemClickedListener onItemClickedListener)
    {
        this.recyclerPokemonList = recyclerPokemonList;
        this.mOnItemClickedListener = onItemClickedListener;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
       private TextView nameTxt;
       private ImageView image;
       OnItemClickedListener onItemClickedListener;
       public MyViewHolder(final View view,OnItemClickedListener onItemClickedListener)
       {
           super(view);
           nameTxt = view.findViewById(R.id.pokemon_name);
           image = view.findViewById(R.id.pokemon_pic);
           view.setOnClickListener(this);
           this.onItemClickedListener = onItemClickedListener;
       }

        @Override
        public void onClick(View view) {
            onItemClickedListener.OnItemClickedListener(getAdapterPosition());
        }
    }
    public interface OnItemClickedListener
    {
        void OnItemClickedListener(int position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View pokemonView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pokemon,parent,false);
        return new MyViewHolder(pokemonView, mOnItemClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name = recyclerPokemonList.get(position).getName();
        Bitmap pokePic = recyclerPokemonList.get(position).getPic();
        holder.nameTxt.setText(name);
        holder.image.setImageBitmap(pokePic);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

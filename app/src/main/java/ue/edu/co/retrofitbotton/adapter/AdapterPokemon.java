package ue.edu.co.retrofitbotton.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ue.edu.co.retrofitbotton.R;
import ue.edu.co.retrofitbotton.model.Pokemon;

public class AdapterPokemon extends RecyclerView.Adapter<AdapterPokemon.vh> {

    List <Pokemon> pokemonList;

    public AdapterPokemon(List<Pokemon> pokemonList) { this.pokemonList = pokemonList; }

    @NonNull
    @Override
    public AdapterPokemon.vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pokemons, null, false);
        return new vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPokemon.vh holder, int position) {
        holder.txtName.setText(pokemonList.get(position).getName());
        holder.txtUrl.setText(pokemonList.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class vh extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtUrl;
        public vh(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tvName);
            txtUrl = itemView.findViewById(R.id.tvUrl);
        }
    }
}

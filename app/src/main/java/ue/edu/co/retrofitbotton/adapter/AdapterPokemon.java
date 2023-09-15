package ue.edu.co.retrofitbotton.adapter;


import android.app.Service;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ue.edu.co.retrofitbotton.R;
import ue.edu.co.retrofitbotton.model.Pokemon;
import ue.edu.co.retrofitbotton.model.Results;
import ue.edu.co.retrofitbotton.model.remote.ClientRetrofit;

public class AdapterPokemon extends RecyclerView.Adapter<AdapterPokemon.vh> implements View.OnClickListener {

    List<Pokemon> pokemonList;
    private View.OnClickListener listener;

    public AdapterPokemon(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

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

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.listener = onClickListener;
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


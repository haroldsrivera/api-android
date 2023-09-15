package ue.edu.co.retrofitbotton.view;




import static ue.edu.co.retrofitbotton.model.api.ValuesApi.BASE_URL;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ue.edu.co.retrofitbotton.R;
import ue.edu.co.retrofitbotton.adapter.AdapterPokemon;
import ue.edu.co.retrofitbotton.model.Pokemon;
import ue.edu.co.retrofitbotton.model.Results;
import ue.edu.co.retrofitbotton.model.api.Service;
import ue.edu.co.retrofitbotton.model.remote.ClientRetrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrincipalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrincipalFragment extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String RES = "Error de Response";

    // TODO: Rename and change types of parameters
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ShimmerFrameLayout shimmerFrameLayout;

    public PrincipalFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PrincipalFragment newInstance() {
        PrincipalFragment fragment = new PrincipalFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_principal, container, false);
        recyclerView = view.findViewById(R.id.rcView);
        shimmerFrameLayout = view.findViewById(R.id.shimmer1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getPokemons();
        return view;

    }

    private void stopShimmer(){
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.hideShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void getPokemons(){
        retrofit = ClientRetrofit.getClient(BASE_URL);
        Service service = retrofit.create(Service.class);
        Call<Results> call = service.getPokemons();
        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                if(response.isSuccessful()){
                    Results results = response.body();
                    ArrayList<Pokemon> list = results.getResults();
                    AdapterPokemon aPokemon = new AdapterPokemon(list);
                    stopShimmer();
                    aPokemon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Pokemon poke;
                            poke = list.get(recyclerView.getChildAdapterPosition(view));
                            Snackbar.make(getView(),""+poke.getName(),Snackbar.LENGTH_LONG).show();
                        }
                    });

                    recyclerView.setAdapter(aPokemon);
                }else{
                    Toast.makeText(getContext(), "Error: "+response.message(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                Log.i(RES, "Error of onResponse: "+t.getMessage());
            }
        });
    }
}


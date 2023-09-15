package ue.edu.co.retrofitbotton.model.api;

import retrofit2.Call;
import retrofit2.http.GET;
import ue.edu.co.retrofitbotton.model.Results;

public interface Service {
    @GET("pokemon")

    Call<Results> getPokemons();
}

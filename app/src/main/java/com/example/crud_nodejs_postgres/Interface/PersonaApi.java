package com.example.crud_nodejs_postgres.Interface;

import com.example.crud_nodejs_postgres.Models.PersonaModel;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PersonaApi {

    @GET("node/getpersona")
    public Call<List<PersonaModel>> getAllData();

    @GET("node/{id}")
    public Call<PersonaModel> getData(@Path("id")  int id);

    @POST("node/addpersona")
    public Call<PersonaModel> createRegistro(@Body PersonaModel persona);

    @PUT("node/updatepersona/{id}")
    public Call<PersonaModel>updatePersona(@Path("id") int id,@Body PersonaModel persona);

    @DELETE("node/{id}")
    public Call<PersonaModel>deletePersona(@Path("id") int id);

}

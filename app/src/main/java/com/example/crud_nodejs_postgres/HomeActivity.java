package com.example.crud_nodejs_postgres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.crud_nodejs_postgres.Adaptador.PersonaAdapter;
import com.example.crud_nodejs_postgres.Interface.PersonaApi;
import com.example.crud_nodejs_postgres.Models.PersonaModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    //VARIABLES
    RecyclerView lista_persona_recycler;
    ArrayList<PersonaModel> listaArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        lista_persona_recycler = findViewById(R.id.lista_recycler);
        lista_persona_recycler.setLayoutManager(new LinearLayoutManager(HomeActivity.this));

        //HACEMOS LA PETICION GET A LA BASE DE DATOS
        Retrofit retrofitInstance = new Retrofit.Builder().baseUrl("http://10.0.2.2:9000/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        PersonaApi personaapi =retrofitInstance.create(PersonaApi.class);

        Call<List<PersonaModel>> personaArreglo = personaapi.getAllData();

        //listaArray = new ArrayList<>();
        personaArreglo.enqueue(new Callback<List<PersonaModel>>() {
            @Override
            public void onResponse(Call<List<PersonaModel>> call, Response<List<PersonaModel>>response) {

                if (response.isSuccessful()) {
                    System.out.println("CORRECTO !");
                    System.out.println(response.body().toString());

                }else {
                    System.out.println("ERROR Garrafal !!");
                }
                //Toast.makeText(HomeActivity.this, " correcta peticion !", Toast.LENGTH_LONG).show();

                PersonaAdapter personaadapter = new PersonaAdapter(response.body());
                lista_persona_recycler.setAdapter(personaadapter);

            }
            @Override
            public void onFailure(Call<List<PersonaModel>> call, Throwable t) {
                System.out.println("ERROR !!" + t);
                Toast.makeText(HomeActivity.this, " ERROR !!!", Toast.LENGTH_LONG).show();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.homeprincipal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.homeprincipal:
                nuevoregistros();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void nuevoregistros(){
        //Para pasar de una vista a otra.

        Intent intent = new Intent(HomeActivity.this, AgregarActivity.class);
        startActivity(intent);
    }
}
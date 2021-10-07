package com.example.crud_nodejs_postgres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud_nodejs_postgres.Interface.PersonaApi;
import com.example.crud_nodejs_postgres.Models.PersonaModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModificarActivity extends AppCompatActivity {

    //VARIABLES
    EditText txtnombre,txtapellido,txtemail;
    Button btnactualizar;
    FloatingActionButton btn_eliminar;
    PersonaModel personamodel = new PersonaModel();
    int id=0;
    List<PersonaModel> nuevaLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        txtnombre = findViewById(R.id.txtnew_nombre);
        txtapellido = findViewById(R.id.txtnew_apellido);
        txtemail = findViewById(R.id.txtnew_email);
        btnactualizar = findViewById(R.id.btn_modificar);
        btn_eliminar = findViewById(R.id.floating_delete);

        Bundle extraParam =getIntent().getExtras();

        if (savedInstanceState == null) {
            System.out.println(" EXTRA EXTRA");
            System.out.println(savedInstanceState);

            //El extraParam viene a ser el ID enviado en el Adapter


            if (extraParam == null) {
                id=Integer.parseInt(null);
            }else{
                //La llave que especificamos en el Adapter para el extra es "id"
                id = extraParam.getInt("id");
                System.out.println( id + "PRIMER ELSE");

            }
        }else{
            id = (int) savedInstanceState.getSerializable("id");
            System.out.println(id + "SEGUNDO ELSE");
        }



        ///////////////////OBTENIENDO UN REGISTRO - RETROFIT

        Retrofit retrofitInstance = new Retrofit.Builder().baseUrl("http://10.0.2.2:9000/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        PersonaApi personaapi =retrofitInstance.create(PersonaApi.class);
        Call<PersonaModel> personaArreglo = personaapi.getData(id);

        personaArreglo.enqueue(new Callback<PersonaModel>() {

            @Override
            public void onResponse(Call<PersonaModel> call, Response<PersonaModel>response) {

                if (response.isSuccessful()) {
                    System.out.println("CORRECTO !");
                    System.out.println(response.body());

                }else {
                    System.out.println(response);
                    System.out.println("ERROR - x");
                }
                personamodel = response.body();
                System.out.println(personamodel + "DENTRO DEL METODO ENQUEQUE");
                txtnombre.setText(personamodel.getNombre());
                txtapellido.setText(personamodel.getApellido());
                txtemail.setText(personamodel.getEmail());
            }

            @Override
            public void onFailure(Call<PersonaModel> call, Throwable t) {
                System.out.println("ERROR #5252" + t);
            }

        });

        /////////// ACTUALIZAR REGISTRO - RETROFIT

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ////////////// MODIFICANDO REGISTRO - RETROFIT

                Retrofit retrofitInstance = new Retrofit.Builder().baseUrl("http://10.0.2.2:9000/")
                        .addConverterFactory(GsonConverterFactory.create()).build();

                PersonaApi personaapi =retrofitInstance.create(PersonaApi.class);
                System.out.println("ONCLICK" + personamodel + " id: " +  id);

                personamodel.setNombre(txtnombre.getText().toString());
                personamodel.setApellido(txtapellido.getText().toString());
                personamodel.setEmail(txtemail.getText().toString());


                Call<PersonaModel> personaArreglo = personaapi.updatePersona(id,personamodel);

                personaArreglo.enqueue(new Callback<PersonaModel>() {
                    @Override
                    public void onResponse(Call<PersonaModel> call, Response<PersonaModel> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ModificarActivity.this,"REGISTRO ACTUALIZADO !", Toast.LENGTH_LONG).show();
                            System.out.println(response.body() + "ESTO ES EL NUEVO REGISTRO");
                            Intent intent = new Intent(ModificarActivity.this,HomeActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(ModificarActivity.this,"ERROR AL ACTUALIZAR !", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PersonaModel> call, Throwable t) {
                        System.out.println("ERROR #Update555" + t);
                    }
                });
            }
        });


        //////////// ELIMINAR RESGISTRO - RETROFIT

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofitInstance = new Retrofit.Builder().baseUrl("http://10.0.2.2:9000/")
                        .addConverterFactory(GsonConverterFactory.create()).build();

                PersonaApi personaapi =retrofitInstance.create(PersonaApi.class);

                Call<PersonaModel> personaArreglo = personaapi.deletePersona(id);

                personaArreglo.enqueue(new Callback<PersonaModel>() {
                    @Override
                    public void onResponse(Call<PersonaModel> call, Response<PersonaModel> response) {

                        System.out.println(response + "RESPONSE **");
                        Toast.makeText(ModificarActivity.this,"REGISTRO ELIMINADO !", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ModificarActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<PersonaModel> call, Throwable t) {
                        System.out.println("ERROR ##222" + t);
                    }
                });
            }
        });



    }


    /*private PersonaModel traerUnRegistro(int ide){

        //HACEMOS LA PETICION GET A LA BASE DE DATOS
        Retrofit retrofitInstance = new Retrofit.Builder().baseUrl("http://10.0.2.2:9000/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        PersonaApi personaapi =retrofitInstance.create(PersonaApi.class);

        System.out.println("EL ID RECIBIDO ES: " + ide);

        Call<PersonaModel> personaArreglo = personaapi.getData(ide);


        personaArreglo.enqueue(new Callback<PersonaModel>() {

            @Override
            public void onResponse(Call<PersonaModel> call, Response<PersonaModel>response) {

                if (response.isSuccessful()) {
                    System.out.println("CORRECTO !");
                    System.out.println(response.body());

                }else {
                    System.out.println(response);
                    System.out.println("ERROR - x");
                }
                personamodel = response.body();
                System.out.println(personamodel + "DENTRO DEL METODO ENQUEQUE");
            }

            @Override
            public void onFailure(Call<PersonaModel> call, Throwable t) {
                System.out.println("ERROR #5252" + t);
            }

        });

        System.out.println(personamodel + "FUERA DEL METODO ENQUEQUE");

       return personamodel;

    }*/
}
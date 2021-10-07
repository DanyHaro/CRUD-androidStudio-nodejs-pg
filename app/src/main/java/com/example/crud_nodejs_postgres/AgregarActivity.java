package com.example.crud_nodejs_postgres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crud_nodejs_postgres.Interface.PersonaApi;
import com.example.crud_nodejs_postgres.Models.PersonaModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarActivity extends AppCompatActivity {

    EditText txtnombre, txtapellido, txtemail;
    Button btnguardar;
    Spinner prof_spinner;

    PersonaModel personamodel = new PersonaModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        txtnombre = findViewById(R.id.txtnew_nombre);
        txtapellido = findViewById(R.id.txtnew_apellido);
        txtemail = findViewById(R.id.txtnew_email);
        prof_spinner = findViewById(R.id.spiner_profesion);
        btnguardar = findViewById(R.id.btn_modificar);

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtnombre.getText().toString().isEmpty() || txtapellido.getText().toString().isEmpty() || txtemail.getText().toString().isEmpty()) {
                    Toast.makeText(AgregarActivity.this, "COMPLETE TODOS LOS CAMPOS !", Toast.LENGTH_LONG).show();
                }else{
                    personamodel.setNombre(txtnombre.getText().toString());
                    personamodel.setApellido(txtapellido.getText().toString());
                    personamodel.setEmail(txtemail.getText().toString());

                    guardarDatos();
                }
            }
        });
    }


    private void guardarDatos(){

        Retrofit retrofitInstance = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PersonaApi personaapi = retrofitInstance.create(PersonaApi.class);

        Call<PersonaModel> registro = personaapi.createRegistro(personamodel);

        registro.enqueue(new Callback<PersonaModel>() {
            @Override
            public void onResponse(Call<PersonaModel> call, Response<PersonaModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AgregarActivity.this,"REGISTRO GUARDADO !",Toast.LENGTH_LONG).show();
                    clearInputs();
                    Intent intent = new Intent(AgregarActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else {
                    System.out.println("ERROR !!");
                }
            }

            @Override
            public void onFailure(Call<PersonaModel> call, Throwable t) {
                System.out.println(t + "ERROR ##333 !");
            }
        });
    }

    private void clearInputs(){
        txtnombre.setText("");
        txtapellido.setText("");
        txtemail.setText("");

    }

}
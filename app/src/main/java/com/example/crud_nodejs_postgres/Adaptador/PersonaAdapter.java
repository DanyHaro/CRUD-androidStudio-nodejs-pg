package com.example.crud_nodejs_postgres.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud_nodejs_postgres.Models.PersonaModel;
import com.example.crud_nodejs_postgres.ModificarActivity;
import com.example.crud_nodejs_postgres.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder> {

    List<PersonaModel> listPersonas;

    public PersonaAdapter(List<PersonaModel> listContact){
        this.listPersonas = listContact;
        System.out.println(listPersonas + "En el Contructor Adapter");
    }


    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_personas,null,false);
        return new PersonaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        holder.nombre.setText(listPersonas.get(position).getNombre());
        holder.apellido.setText(listPersonas.get(position).getApellido());
        holder.email.setText(listPersonas.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return listPersonas.size();
    }

    public class PersonaViewHolder extends RecyclerView.ViewHolder {
        TextView nombre,apellido,email;

        public PersonaViewHolder(@NonNull View itemView) {
            super(itemView);

            System.out.println(listPersonas + "DENTRO DEL ULTIMO METODO !!!");
            //Se le asigna cada cada dato a un textview especificado en lista_personas.xml
            nombre = itemView.findViewById(R.id.recy_nombre);
            apellido = itemView.findViewById(R.id.recy_apellido);
            email = itemView.findViewById(R.id.recy_email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //MOSTRANDO DATOS DE UN SOLO REGISTRO AL HACER CLICK EN ELLO.
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ModificarActivity.class);

                    //PARA MOSTRAR LOS DATOS CORRESPONDIENTE A UN REGISTRO, LE ENVIAMOS EL "ID"
                    intent.putExtra("id", listPersonas.get(getAdapterPosition()).getIdpersona());
                    context.startActivity(intent);

                }
            });

        }
    }
}

package com.example.kaymo.resolveai;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ComentarioAdapter extends RecyclerView.Adapter {
    private List<Comentario> listaComentario;
    private List<Comentario> comentarios;
    private Context context;
    private DatabaseReference databaseReference;
    private Comentario todosComentarios;

    public ComentarioAdapter(Context context, List<Comentario> listaComentario) {
        this.listaComentario = listaComentario;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View elementoPrincipalXML = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentario, parent, false);
        return new ComentarioHolder(elementoPrincipalXML);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ComentarioHolder gaveta = (ComentarioHolder) holder;
        final Comentario daVez = this.listaComentario.get(position);

        comentarios = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("comentario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                comentarios.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    todosComentarios = postSnapshot.getValue(Comentario.class);
                    comentarios.add(todosComentarios);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d("TAG", "veridcomentario: "+daVez.getId());
        gaveta.exibeComentario(daVez);
    }

    @Override
    public int getItemCount() {
        return this.listaComentario.size();
    }
}

package br.com.franzim.ceep.ui.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.franzim.ceep.R;
import br.com.franzim.ceep.model.Nota;

public class ListaNotasAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Nota> notas;

    public ListaNotasAdapter(Context context, List<Nota> notas) {
        this.context = context;
        this.notas = notas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_nota, viewGroup, false);
        return new NotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Nota nota = this.notas.get(position);

        TextView tvTituloNota = viewHolder.itemView.findViewById(R.id.item_nota_titulo);
        tvTituloNota.setText(nota.getTitulo());

        TextView tvDescricaoNota = viewHolder.itemView.findViewById(R.id.item_nota_descricao);
        tvDescricaoNota.setText(nota.getDescricao());
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    class NotaViewHolder extends RecyclerView.ViewHolder {

        public NotaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

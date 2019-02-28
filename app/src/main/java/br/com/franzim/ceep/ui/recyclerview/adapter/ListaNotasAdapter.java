package br.com.franzim.ceep.ui.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.franzim.ceep.R;
import br.com.franzim.ceep.model.Nota;

public class ListaNotasAdapter extends RecyclerView.Adapter<ListaNotasAdapter.NotaViewHolder> {

    private final Context context;
    private final List<Nota> notas;

    private static int quantidadeViewCriadas;
    private static int quantidadeBindView;

    public ListaNotasAdapter(Context context, List<Nota> notas) {
        this.context = context;
        this.notas = notas;
    }

    @NonNull
    @Override
    public ListaNotasAdapter.NotaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        Log.i("recyclerView adapter", "onCreateViewHolder qtd: " + ++quantidadeViewCriadas);
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_nota, viewGroup, false);
        return new NotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaNotasAdapter.NotaViewHolder viewHolder, int position) {

        Log.i("recyclerView adapter", "onBindViewHolder: posicao " + position + " qtd: " + ++quantidadeBindView);

        Nota nota = this.notas.get(position);
        viewHolder.setVincula(nota);
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    class NotaViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTituloNota;
        private final TextView tvDescricaoNota;

        public NotaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTituloNota = itemView.findViewById(R.id.item_nota_titulo);
            tvDescricaoNota = itemView.findViewById(R.id.item_nota_descricao);
        }

        public void setVincula(Nota nota) {
            tvTituloNota.setText(nota.getTitulo());
            tvDescricaoNota.setText(nota.getDescricao());
        }
    }

    public void addNota(Nota nota) {
        this.notas.add(nota);
        notifyDataSetChanged();
    }
}

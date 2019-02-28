package br.com.franzim.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.franzim.ceep.R;
import br.com.franzim.ceep.dao.NotaDAO;
import br.com.franzim.ceep.model.Nota;
import br.com.franzim.ceep.ui.recyclerview.adapter.ListaNotasAdapter;


public class ListaNotasActivity extends AppCompatActivity {


    private static final NotaDAO notaDAO;

    static {
        notaDAO = new NotaDAO();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        populaNotas(notaDAO);

        TextView tvInsereNota = findViewById(R.id.lista_notas_insere_nota);
        tvInsereNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaNotasActivity.this, FormularioNotaActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        setRecyclerView(notaDAO);
    }

    private void populaNotas(NotaDAO notaDAO) {
        notaDAO.insere(new Nota("Primeira Nota", "Descrição pequena"));
        notaDAO.insere(new Nota("Segunda Nota", "Descrição grande pra cacete, bem maior que a primeira"));
    }

    private void setRecyclerView(NotaDAO notaDAO) {
        RecyclerView rvNotas = findViewById(R.id.rv_notas);

        rvNotas.setAdapter(new ListaNotasAdapter(this, notaDAO.todos()));

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        rvNotas.setLayoutManager(layoutManager);
    }




}

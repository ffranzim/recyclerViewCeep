package br.com.franzim.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.franzim.ceep.R;
import br.com.franzim.ceep.dao.NotaDAO;
import br.com.franzim.ceep.model.Nota;
import br.com.franzim.ceep.ui.recyclerview.adapter.ListaNotasAdapter;


public class ListaNotasActivity extends AppCompatActivity {


    private static final NotaDAO notaDAO;

    private ListaNotasAdapter adapter;

    static {
        notaDAO = new NotaDAO();
    }

    private List<Nota> todosAsNotas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        populaNotas();
        todosAsNotas = notaDAO.todos();
        setRecyclerView();
        setClickAddNota();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void populaNotas() {
        notaDAO.insere(new Nota("Primeira Nota", "Descrição pequena"));
        notaDAO.insere(new Nota("Segunda Nota", "Descrição grande pra cacete, bem maior que a primeira"));

    }

    private void setRecyclerView() {
        RecyclerView rvNotas = findViewById(R.id.rv_notas);
        adapter = new ListaNotasAdapter(this, todosAsNotas);
        rvNotas.setAdapter(adapter);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        rvNotas.setLayoutManager(layoutManager);
    }

    private void setClickAddNota() {
        TextView tvInsereNota = findViewById(R.id.lista_notas_insere_nota);
        tvInsereNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaNotasActivity.this, FormularioNotaActivity.class);
                startActivityForResult(intent, 999);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 999 && resultCode == 998 && data.hasExtra("nota")) {
            Nota nota = (Nota) data.getSerializableExtra("nota");
            notaDAO.insere(nota);
            adapter.addNota(nota);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

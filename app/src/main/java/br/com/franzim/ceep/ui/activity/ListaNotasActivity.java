package br.com.franzim.ceep.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import br.com.franzim.ceep.R;
import br.com.franzim.ceep.dao.NotaDAO;
import br.com.franzim.ceep.model.Nota;
import br.com.franzim.ceep.ui.recyclerview.adapter.ListaNotasAdapter;


public class ListaNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        RecyclerView rvNotas = findViewById(R.id.rv_notas);

        NotaDAO notaDAO = new NotaDAO();

        for (int i = 1;  i < 10000; i++) {
            notaDAO.insere(new Nota("Primeira Nota"  + i, "Primeira descrição"  + i));
        }




        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvNotas.setAdapter(new ListaNotasAdapter(this, notaDAO.todos()));
        rvNotas.setLayoutManager(layoutManager);


    }
}

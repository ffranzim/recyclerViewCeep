package br.com.franzim.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.franzim.ceep.R;
import br.com.franzim.ceep.dao.NotaDAO;
import br.com.franzim.ceep.model.Nota;
import br.com.franzim.ceep.ui.recyclerview.adapter.ListaNotasAdapter;
import br.com.franzim.ceep.ui.recyclerview.adapter.listener.OnItemClickListener;

import static br.com.franzim.ceep.ui.activity.ConstantsNota.CHAVE_NOTA;
import static br.com.franzim.ceep.ui.activity.ConstantsNota.POSICAO_INVALIDA;
import static br.com.franzim.ceep.ui.activity.ConstantsNota.POSICAO_NOTA;
import static br.com.franzim.ceep.ui.activity.ConstantsNota.REQUEST_CODE_INSERE_NOTA;
import static br.com.franzim.ceep.ui.activity.ConstantsNota.REQUEST_CODE_UPDATE_NOTA;


public class ListaNotasActivity extends AppCompatActivity {

    private static final NotaDAO notaDAO;
    private ListaNotasAdapter adapter;

    static {
        notaDAO = new NotaDAO();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        for (int i = 1; i <= 10; i++)
            notaDAO.insere(new Nota("Título " + i, "Descrição " + i));

        setRecyclerView();
        setClickAddNota();
    }

    private void setRecyclerView() {
        RecyclerView rvNotas = findViewById(R.id.rv_notas);
        adapter = new ListaNotasAdapter(this, notaDAO.todos());
        rvNotas.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Nota nota, int posicao) {
                Intent intent = new Intent(ListaNotasActivity.this, FormularioNotaActivity.class);
                intent.putExtra(CHAVE_NOTA, nota);
                intent.putExtra(POSICAO_NOTA, posicao);
                startActivityForResult(intent, REQUEST_CODE_UPDATE_NOTA);
            }
        });
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        rvNotas.setLayoutManager(layoutManager);
    }

    private void setClickAddNota() {
        TextView tvInsereNota = findViewById(R.id.lista_notas_insere_nota);
        tvInsereNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaNotasActivity.this, FormularioNotaActivity.class);
                startActivityForResult(intent, REQUEST_CODE_INSERE_NOTA);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_INSERE_NOTA && data.hasExtra(CHAVE_NOTA)) {
                Nota nota = (Nota) data.getSerializableExtra(CHAVE_NOTA);
                notaDAO.insere(nota);
                adapter.addNota(nota);
            } else if (requestCode == REQUEST_CODE_UPDATE_NOTA && data.hasExtra(CHAVE_NOTA) && data.hasExtra(POSICAO_NOTA)) {
                int posicao = data.getIntExtra(POSICAO_NOTA, POSICAO_INVALIDA);
                if (posicao > POSICAO_INVALIDA) {
                    Nota nota = (Nota) data.getSerializableExtra(CHAVE_NOTA);
                    notaDAO.altera(posicao, nota);
                    adapter.alteraNota(posicao, nota);
                } else {
                    Toast.makeText(this, "Ocorreu um problema ao alterar a nota!", Toast.LENGTH_SHORT).show();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

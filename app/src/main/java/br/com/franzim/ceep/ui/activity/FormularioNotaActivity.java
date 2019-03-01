package br.com.franzim.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.franzim.ceep.R;
import br.com.franzim.ceep.model.Nota;

import static br.com.franzim.ceep.ui.activity.ConstantsNota.CHAVE_NOTA;
import static br.com.franzim.ceep.ui.activity.ConstantsNota.POSICAO_INVALIDA;
import static br.com.franzim.ceep.ui.activity.ConstantsNota.POSICAO_NOTA;

public class FormularioNotaActivity extends AppCompatActivity {

    private static EditText edNotaTitulo;
    private static EditText edNotaDescricao;
    private int posicaoNota = POSICAO_INVALIDA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);

        bindCamposForm();
        getValuesIntent();

    }

    private void getValuesIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_NOTA) && intent.hasExtra(POSICAO_NOTA)) {
            setCamposForm(intent);
            posicaoNota = intent.getIntExtra(POSICAO_NOTA, POSICAO_INVALIDA);
        }
    }

    private void setCamposForm(Intent intent) {
        Nota nota = (Nota) intent.getSerializableExtra(CHAVE_NOTA);
        edNotaTitulo.setText(nota.getTitulo());
        edNotaDescricao.setText(nota.getDescricao());
    }


    private void bindCamposForm() {
        edNotaTitulo = findViewById(R.id.formulario_nota_titulo);
        edNotaDescricao = findViewById(R.id.formulario_nota_descricao);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_nota_salva, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_formulario_ic_nota_salva) {
            Nota nota = extraiNotaForm();
            setReturnIntent(RESULT_OK, nota);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private Nota extraiNotaForm() {
        return new Nota(edNotaTitulo.getText().toString(), edNotaDescricao.getText().toString());
    }

    private void setReturnIntent(int resultCode, Nota nota) {
        Intent resultInsert = new Intent();
        resultInsert.putExtra(CHAVE_NOTA, nota);
        resultInsert.putExtra(POSICAO_NOTA, posicaoNota);
        setResult(resultCode, resultInsert);
    }
}

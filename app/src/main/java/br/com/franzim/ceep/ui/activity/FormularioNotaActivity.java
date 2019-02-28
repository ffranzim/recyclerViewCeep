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
import static br.com.franzim.ceep.ui.activity.ConstantsNota.RESULT_CODE_NOTA_CRIADA;

public class FormularioNotaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_nota_salva, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_formulario_ic_nota_salva) {
            Nota nota = extraiNotaForm();
            setReturnIntent(nota);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private Nota extraiNotaForm() {
        EditText edNotaTitulo = findViewById(R.id.formulario_nota_titulo);
        EditText edNotaDescricao = findViewById(R.id.formulario_nota_descricao);

        return new Nota(edNotaTitulo.getText().toString(), edNotaDescricao.getText().toString());
    }

    private void setReturnIntent(Nota nota) {
        Intent resultInsert = new Intent();
        resultInsert.putExtra(CHAVE_NOTA, nota);
        setResult(RESULT_CODE_NOTA_CRIADA, resultInsert);
    }
}

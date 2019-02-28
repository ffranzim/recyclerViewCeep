package br.com.franzim.ceep.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.franzim.ceep.R;
import br.com.franzim.ceep.dao.NotaDAO;
import br.com.franzim.ceep.model.Nota;

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
            EditText edNotaTitulo = findViewById(R.id.formulario_nota_titulo);
            EditText edNotaDescricao = findViewById(R.id.formulario_nota_descricao);

            Nota nota = new Nota(edNotaTitulo.getText().toString(), edNotaDescricao.getText().toString());


            setReturnIntent(nota);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setReturnIntent(Nota nota) {
        Intent resultInsert = new Intent();
        resultInsert.putExtra("nota", nota);
        setResult(998, resultInsert);
    }
}

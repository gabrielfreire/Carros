package com.example.carros.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.carros.R;
import com.example.carros.domain.Carro;
import com.example.carros.fragment.CarroFragment;

public class CarroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);

        // Configura a Toolbar como a action bar
        setUpToolbar();

        // Atualiza o carro no fragment
        CarroFragment cf = (CarroFragment) getSupportFragmentManager()
                .findFragmentById(R.id.CarroFragment);

        Carro c = (Carro) getIntent().getSerializableExtra("carro");
        cf.setCarro(c);

        // Título da toolbar e botão up navigation
        getSupportActionBar().setTitle(c.nome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_carro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

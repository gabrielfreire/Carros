package com.example.carros.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.example.carros.R;
import com.example.carros.adapter.NavDrawerMenuAdapter;
import com.example.carros.adapter.NavDrawerMenuItem;

import java.util.List;

import livroandroid.lib.fragment.NavigationDrawerFragment;


public class MainActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavDrawerMenuAdapter listAdapter;
    private NavigationDrawerFragment mNavDrawerFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();


        // Nav Drawer
        mNavDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
                                                                .findFragmentById(R.id.nav_drawer_fragment);

        // Configura o drawer layout
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mNavDrawerFragment.setUp(drawerLayout);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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





    //
    // MÉTODOS DA INTERFACE "NavDrawerCallbacks" !!
    //


    // Deve retornar a view e o identificador do ListView
    @Override
    public NavigationDrawerFragment.NavDrawerListView getNavDrawerView(NavigationDrawerFragment navigationDrawerFragment, LayoutInflater layoutInflater, ViewGroup container) {

        View view = layoutInflater.inflate(R.layout.nav_drawer_listview, container, false);
        return new NavigationDrawerFragment.NavDrawerListView(view, R.id.listView);
    }



    // Este método deve retornar o adapter que vai preencher o ListView
    @Override
    public ListAdapter getNavDrawerListAdapter(NavigationDrawerFragment navigationDrawerFragment) {

        List<NavDrawerMenuItem> list = NavDrawerMenuItem.getList();

        // Deixa o primeiro item selecionado
        list.get(0).selected = true;
        this.listAdapter = new NavDrawerMenuAdapter(this, list);
        return listAdapter;
    }



    // Método chamado ao selecionar um item do ListView
    @Override
    public void onNavDrawerItemSelected(NavigationDrawerFragment navigationDrawerFragment, int position) {

        List<NavDrawerMenuItem> list = NavDrawerMenuItem.getList();
        NavDrawerMenuItem selectedItem = list.get(position);

        // Seleciona a linha
        this.listAdapter.setSelected(position, true);
        toast("Clicou no item: "+getString(selectedItem.title));
    }

}

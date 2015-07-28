package com.example.carros.adapter;

import com.example.carros.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel on 27/07/2015.
 */
public class NavDrawerMenuItem {


    // Título: R.string.xxx
    public int title;

    // Figura: R.drawable.xxx
    public int img;

    // Para colocar um fundo cinza quando a linha está selecionada
    public boolean selected;


    public NavDrawerMenuItem(int title, int img) {

        this.title = title;
        this.img = img;
    }

    // Cria lista com os itens de menu
    public static List<NavDrawerMenuItem> getList() {

        List<NavDrawerMenuItem> list = new ArrayList<NavDrawerMenuItem>();

        list.add(new NavDrawerMenuItem(R.string.carros, R.drawable.ic_drawer_carro));
        list.add(new NavDrawerMenuItem(R.string.site_livro, R.drawable.ic_drawer_site_livro));
        list.add(new NavDrawerMenuItem(R.string.configuracoes, R.drawable.ic_drawer_settings));

        return list;
    }
}

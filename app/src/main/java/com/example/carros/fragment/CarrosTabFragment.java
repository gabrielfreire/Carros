package com.example.carros.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carros.R;
import com.example.carros.adapter.TabsAdapter;

import livroandroid.lib.utils.Prefs;


/**
 * Created by Gabriel on 02/08/2015.
 */
public class CarrosTabFragment extends BaseFragment
                implements TabLayout.OnTabSelectedListener {

    private ViewPager mViewPager;
    private TabLayout tabLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_carros_tab, container, false);

        // ViewPager
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(2); // Manter sempre 2 tabs a mais em memória
        mViewPager.setAdapter(new TabsAdapter(getContext(), getChildFragmentManager()));


        // Tabs
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        int cor = getContext().getResources().getColor(R.color.white);

        // Cor branca no texto (o fundo azul foi definido no layout)
        tabLayout.setTabTextColors(cor, cor);


        // Adiciona as tabs
        tabLayout.addTab(tabLayout.newTab().setText(R.string.classico));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.esportivo));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.luxo));


        // Listener para tratar eventos de clique na tab
        tabLayout.setOnTabSelectedListener(this);


        // Se mudar a ViewPager atualiza a tab selecionada
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        // Ao criar a view, mostra a última tab selecionada
        //int tabIdx = Prefs.getInteger(getContext(), "tabIdx");
        //mViewPager.setCurrentItem(tabIdx);

        return view;
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        // Se mudar a tab, atualiza o ViewPager
        mViewPager.setCurrentItem(tab.getPosition());

        // Salva o índice da página/tab selecionada
        //Prefs.setInteger(getContext(), "tabIdx", mViewPager.getCurrentItem());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

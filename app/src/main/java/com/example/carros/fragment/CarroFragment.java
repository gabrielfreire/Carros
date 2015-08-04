package com.example.carros.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.carros.R;
import com.example.carros.domain.Carro;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarroFragment extends BaseFragment {

    private Carro carro;

    public CarroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carro, container, false);
    }


    // Método público chamado pela activity para atualizar os dados do carro
    public void setCarro(Carro carro){

        if (carro != null) {
            this.carro = carro;
            setTextString(R.id.tDesc, carro.desc);

            final ImageView imgView = (ImageView) getView().findViewById(R.id.imgCarro);
            //toast("Carro: "+carro.nome+" - "+carro.urlFoto);
            //Picasso.with(getContext()).load(carro.urlFoto).fit().into(imgView);
        }
    }

}

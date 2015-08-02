package com.example.carros.domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel on 30/07/2015.
 */
public class CarroService {

    public static List<Carro> getCarros(Context contexto, String tipo) {

        List<Carro> carros = new ArrayList<Carro>();

        for (int i = 0; i<20; i++){

            Carro c = new Carro();
            c.nome = "Carro "+tipo+": "+i; // Nome dinâmico conforme o tipo
            c.desc = "Desc "+i;
            c.urlFoto = "http://www.livroandroid.com.br/livro/carros/esportivos/Ferrari_FF.png";

            carros.add(c);
        }

        return carros;
    }
}

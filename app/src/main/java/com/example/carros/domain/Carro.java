package com.example.carros.domain;

import java.io.Serializable;

/**
 * Created by Gabriel on 30/07/2015.
 */
public class Carro implements Serializable {

    public long id;
    public String tipo;
    public String nome;
    public String desc;
    public String urlInfo;
    public String urlFoto;
    public String urlVideo;
    public String latitude;
    public String longitude;


    @Override
    public String toString() {
        return "Carro{" +
                "nome='" + nome + '\'' +
                '}';
    }
}

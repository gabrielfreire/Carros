package com.example.carros.domain;

import android.content.Context;
import android.util.Log;

import com.example.carros.R;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import livroandroid.lib.utils.FileUtils;
import livroandroid.lib.utils.XMLUtils;


/**
 * Created by Gabriel on 30/07/2015.
 */
public class CarroService {


    private static final boolean LOG_ON = false;
    private static final String TAG = "CarroService";





    public static List<Carro> getCarros(Context contexto, String tipo) {



        try{
            String xml = readFile(contexto, tipo);
            List<Carro> carros = parseXML(contexto, xml);

            return carros;

        } catch (Exception e) {


            // TODO explicar exception
            Log.e(TAG,"Erro ao ler os carros: "+e.getMessage(), e);

            return null;
        }

/*
        List<Carro> carros = new ArrayList<Carro>();

        for (int i = 0; i<20; i++){

            Carro c = new Carro();
            c.nome = "Carro "+tipo+": "+i; // Nome dinâmico conforme o tipo
            c.desc = "Desc "+i;
            c.urlFoto = "http://www.livroandroid.com.br/livro/carros/esportivos/Ferrari_FF.png";

            carros.add(c);
        }
        return carros;
        */
    }



    // Faz a leitura do arquivo que está na pasta /res/raw
    private static String readFile(Context context, String tipo) throws IOException {
        if ("cassicos".equals(tipo)) {
            return FileUtils.readRawFileString(context,R.raw.carros_classicos, "UTF-8");
        }
        else if ("esportivos".equals(tipo)) {
            return FileUtils.readRawFileString(context, R.raw.carros_esportivos, "UTF-8");
        }
        return FileUtils.readRawFileString(context,R.raw.carros_luxo, "UTF-8");
    }


    /*
    Faz o parser do XML e cria a lista de carros!!

    Recebe o tipo do carro desejado (esportivo,luxo ou clássicos), o arquivo xml correto é lido na pasta /res/raw.
    O retorno é uma String no formato XML, então é feito o parse e logo depois é criada a lista de carros.
     */
    private static List<Carro> parseXML(Context contexto, String xml) {

        List<Carro> carros = new ArrayList<Carro>();
        Element root = XMLUtils.getRoot(xml, "UTF-8");

        // Lê todas as tags <carro>
        List<Node> nodeCarros = XMLUtils.getChildren(root, "carro");

        // Insere cada carro na lista
        for (Node node : nodeCarros) {
            Carro c = new Carro();

            // Lê as informações de cada carro
            c.nome = XMLUtils.getText(node,"nome");
            c.desc = XMLUtils.getText(node,"desc");
            c.urlFoto = XMLUtils.getText(node,"url_foto");
            c.urlInfo = XMLUtils.getText(node,"url_info");
            c.urlVideo = XMLUtils.getText(node,"url_video");
            c.latitude = XMLUtils.getText(node,"latitude");
            c.longitude = XMLUtils.getText(node,"longitude");

            if (LOG_ON) {
                Log.d(TAG, "Carro "+c.nome+" > "+c.urlFoto);
            }
            carros.add(c);
        }

        if (LOG_ON) {
            Log.d(TAG,carros.size()+" encontrados.");
        }
        return carros;
    }

}

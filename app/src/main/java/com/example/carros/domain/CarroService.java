package com.example.carros.domain;

import android.content.Context;
import android.util.Log;

import com.example.carros.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import livroandroid.lib.utils.FileUtils;


/**
 * Created by Gabriel on 30/07/2015.
 */
public class CarroService {


    private static final boolean LOG_ON = false;
    private static final String TAG = "CarroService";
    private static final String URL = "http://www.livroandroid.com.br/livro/carros/carros_{tipo}.json";



    /*
    Recebe o tipo de carro desejado, a url é montada para buscar os carros no servidor.

    Uma vez que temos a URL do web service, basta uma linha de código para fazer a requisição HTTP por GET e obter o resultado
    utilizando HttpHelper.doGet(url).
     */
    public static List<Carro> getCarros(Context contexto, String tipo) throws IOException {

        List<Carro> carros = getCarrosFromBanco(contexto,tipo);

        if (carros != null && carros.size() > 0) {
            // Retorna os carros encontrados no banco
            return carros;
        }
        // Se não encontrar, busca no web service
        carros = getCarrosFromWebService(contexto, tipo);

        return carros;

        /*
        Buscar web service com json

        String url = URL.replace("{tipo}", tipo);
        // Faz a requisição HTTP no servidor e retorna a string com o conteúdo.
        String json = HttpHelper.doGet(url);

        List<Carro> carros = parseJSON(contexto, json);

        return carros;
*/


/*
        try{
            String json = readFile(contexto, tipo);
            //List<Carro> carros = parseXML(contexto, xml);
            List<Carro> carros = parseJSON(contexto, json);

            return carros;

        } catch (Exception e) {
            Log.e(TAG,"Erro ao ler os carros: "+e.getMessage(), e);

            return null;
        }
        */

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

    public static List<Carro> getCarrosFromBanco(Context contexto, String tipo) throws IOException{
        CarroDB db = new CarroDB(contexto);

        try {
            List<Carro> carros = db.findAllByTipo(tipo);

            Log.d(TAG, "Retornando "+carros.size()+" carros ["+tipo+"] do banco.");
            return carros;
        } finally {
            db.close();
        }
    }


    public static List<Carro> getCarrosFromWebService(Context contexto, String tipo) throws IOException {

        String url = URL.replace("{tipo}",tipo);
        Log.d(TAG, "URL: "+url);

        CarroDB db = new CarroDB(contexto);

        try {
            List<Carro> carros = db.findAllByTipo(tipo);

            Log.d(TAG, "Retornando "+carros.size()+" carros ["+tipo+"] do banco.");
            return carros;
        } finally {
            db.close();
        }
    }


    // Salva os carros no banco de dados
    private static void salvarCarros(Context contexto, String tipo, List<Carro> carros) throws IOException {
        CarroDB db = new CarroDB(contexto);

        try {
            // Deleta os carros antigos pelo tipo para limpar o banco
            db.deleteCarrosByTipo(tipo);

            // Salva todos os carros
            for (Carro c : carros) {
                c.tipo = tipo;
                Log.d(TAG, "Salvando o carro " + c.nome);
                db.save(c);
            }
        } finally {
            db.close();
        }
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


    private static List<Carro> parseJSON(Context contexto, String json) throws IOException {

        List<Carro> carros = new ArrayList<Carro>();

        //System.out.println("json: "+json);

        try {
            JSONObject root = new JSONObject(json);
            JSONObject obj = root.getJSONObject("carros");
            JSONArray jsonCarros = obj.getJSONArray("carro");

            // Insere cada carro na lista
            for (int i = 0; i < jsonCarros.length(); i++) {
                JSONObject jsonCarro = jsonCarros.getJSONObject(i);
                Carro c = new Carro();

                // Lê as informações de cada carro
                c.nome = jsonCarro.optString("nome");
                c.desc = jsonCarro.optString("desc");
                c.urlFoto = jsonCarro.optString("url_foto");
                c.urlInfo = jsonCarro.optString("url_info");
                c.urlVideo = jsonCarro.optString("url_video");
                c.latitude = jsonCarro.optString("latitude");
                c.longitude = jsonCarro.optString("longitude");

                if (LOG_ON) {
                    Log.d(TAG, "Carro " + c.nome + " > "+c.urlFoto);
                }
                carros.add(c);
            }

        } catch (JSONException e) {
            throw new IOException(e.getMessage(), e);
        }
        return carros;
    }




    /*
    Faz o parser do XML e cria a lista de carros!!

    Recebe o tipo do carro desejado (esportivo,luxo ou clássicos), o arquivo xml correto é lido na pasta /res/raw.
    O retorno é uma String no formato XML, então é feito o parse e logo depois é criada a lista de carros.
     */

    /*
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
*/
}

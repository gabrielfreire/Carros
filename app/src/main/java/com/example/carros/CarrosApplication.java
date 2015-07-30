package com.example.carros;

import android.app.Application;
import android.util.Log;


/**
 * Classe "Singleton", utilizada para armazenar informações de forma global no aplicativo.
 *
 * Obs: Esta classe faz parte do ciclo de vida da aplicação
 * e é criada junto aos processos, isso evita a criação de variáveis estáticas
 * fora do escopo, por exemplo.
 */
public class CarrosApplication extends Application {

    private static final String TAG = "CarrosApplication";
    private static CarrosApplication instance = null;



    public static CarrosApplication getInstance(){
        return instance; // Singleton
    }


    @Override
    public void onCreate() {
        Log.d(TAG, "CarrosApplication.onCreate()");
        // Salva a instância para termos acesso como Singleton
        instance = this;
    }


    @Override
    public void onTerminate(){
        super.onTerminate();
        Log.d(TAG, "CarrosApplication.onTerminate()");
    }
}

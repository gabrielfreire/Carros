package com.example.carros;

import android.app.Application;
import android.util.Log;

/**
 * Created by Gabriel on 27/07/2015.
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

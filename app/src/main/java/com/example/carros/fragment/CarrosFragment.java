package com.example.carros.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carros.R;
import com.example.carros.activity.CarroActivity;
import com.example.carros.adapter.CarroAdapter;
import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarrosFragment extends BaseFragment {

    protected RecyclerView recyclerView;
    private List<Carro> carros;
    private LinearLayoutManager mLayoutManager;

    private String tipo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.tipo = getArguments().getString("tipo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_carros, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        return view;
    }


    /*
    Iniciar a logica da tela
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskCarros();
    }


    /*
    Criar e preencher a lista de carros
     */
    private void taskCarros() {
        // Busca os carros: dispara a Task
        startTask("carros", new GetCarrosTask());

        // Busca os carros
        //this.carros = CarroService.getCarros(getContext(), tipo);

        // Atualiza a lista
        //recyclerView.setAdapter(new CarroAdapter(getContext(), carros, onClickCarro()));
    }

    // Task para buscar os carros
    private class GetCarrosTask implements TaskListener<List<Carro>> {


        @Override
        public List<Carro> execute() throws Exception {
            try {
                // Busca os carros em background (Thread)
                return CarroService.getCarros(getContext(), tipo);

            } catch (IOException e) {
                Log.e("livroandroid", e.getMessage(), e);
                return null;
            }
        }

        // Atualiza a interface
        @Override
        public void updateView(List<Carro> carros) {
            if (carros != null) {
                // Salva a lista de carros no atributo da classe
                CarrosFragment.this.carros = carros;

                // Atualiza a view na UI Thread
                recyclerView.setAdapter(new CarroAdapter(getContext(), carros, onClickCarro()));
            }
        }

        @Override
        public void onError(Exception e) {
            // Qualquer exceção lançada no método execute vai cair aqui.
            alert("Ocorreu algum erro ao buscar os dados");
        }

        @Override
        public void onCancelled(String s) {

        }
    }


    private CarroAdapter.CarroOnClickListener onClickCarro(){
        return new CarroAdapter.CarroOnClickListener() {

            @Override
            public void onClickCarro(View view, int idx) {
                Carro c = carros.get(idx);

                Intent intent = new Intent(getContext(), CarroActivity.class);
                intent.putExtra("carro", c);
                startActivity(intent);

                //Toast.makeText(getContext(), "Carro: " + c.nome, Toast.LENGTH_SHORT).show();
            }
        };
    }


}

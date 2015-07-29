package com.example.carros.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.carros.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SiteGoogleFragment extends BaseFragment {


    private static final String URL_SOBRE = "http://www.livroandroid.com.br/sobre.htm";
    private WebView webview;
    private ProgressBar progress;



    public SiteGoogleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_site_google, container, false);

        webview = (WebView) view.findViewById(R.id.webview);
        progress = (ProgressBar) view.findViewById(R.id.progress);

        //setWebVi
        return view;
    }

    private void setWebviewClient(WebView webview) {
        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView webview, String url, Bitmap favicon) {
                super.onPageStarted(webview, url, favicon);
                // Liga o progress
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView webview, String url) {
                // Desliga o progress
                progress.setVisibility(View.INVISIBLE);
            }
        });
    }

}

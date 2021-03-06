package com.example.carros.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.carros.R;

import livroandroid.lib.utils.AndroidUtils;


/**
 * Created by Gabriel on 30/07/2015.
 */
public class AboutDialog extends DialogFragment {

    // M�todo utilit�rio para mostrar o dialog
    public static void showAbout(FragmentManager fm) {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag("dialog_about");

        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        new AboutDialog().show(ft,"dialog_about");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Cria o HTML com o texto about
        SpannableStringBuilder aboutBody = new SpannableStringBuilder();
        String versionName = AndroidUtils.getVersionName(getActivity()); // app/build.gradle

        aboutBody.append(Html.fromHtml(getString(R.string.about_dialog_text, versionName)));

        // Infla o layout
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        TextView view = (TextView) inflater.inflate(R.layout.dialog_about, null);

        view.setMovementMethod(new LinkMovementMethod());

        // Cria o dialog customizado
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.about_dialog_title)
                .setView(view)
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        })
                        .create();
    }
}

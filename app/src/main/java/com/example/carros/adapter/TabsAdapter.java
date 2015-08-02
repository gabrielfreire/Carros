package com.example.carros.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.carros.R;
import com.example.carros.fragment.CarrosFragment;

/**
 * Created by Gabriel on 02/08/2015.
 */
public class TabsAdapter extends FragmentPagerAdapter {

    private Context context;


    public TabsAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();

        switch (position) {
            case 0:
                args.putString("tipo", "clássico");
                break;

            case 1:
                args.putString("tipo", "esportivo");
                break;

            case 2:
                args.putString("tipo", "luxo");
                break;
        }

        Fragment f = new CarrosFragment();
        f.setArguments(args);

        return f;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence charSequence = null;

        switch (position) {
            case 0:
                charSequence = context.getString(R.string.classico);
                break;

            case 1:
                charSequence = context.getString(R.string.esportivo);
                break;

            case 2:
                charSequence = context.getString(R.string.luxo);
                break;
        }

        return charSequence;
    }

}

package com.example.carros.adapter;

import android.content.Context;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carros.R;

import java.util.List;

/**
 * Created by Gabriel on 27/07/2015.
 */
public class NavDrawerMenuAdapter extends BaseAdapter {

    protected static final String TAG = "livroandroid";
    private final List<NavDrawerMenuItem> list;
    private final Context context;
    private LayoutInflater inflater;


    public NavDrawerMenuAdapter(Context context, List<NavDrawerMenuItem> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater)LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return (list != null) ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return (list != null) ? list.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder = null;

        if (view == null) {

            // Cria o ViewHolder
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.adapter_nav_drawer, parent, false);
            view.setTag(holder);

            holder.text = (TextView)view.findViewById(R.id.text);
            holder.img = (ImageView)view.findViewById(R.id.img);
        }
        else {
            // Reaproveitar o ViewHolder
            holder = (ViewHolder)view.getTag();
        }
        // Atualiza a view
        NavDrawerMenuItem item = list.get(position);
        holder.text.setText(item.title);
        holder.img.setImageResource(item.img);

        if (item.selected) {
            view.setBackgroundResource(R.drawable.seletor_nav_drawer_selected);
            holder.text.setTextColor(context.getResources().getColor(R.color.primary));
        } else {
            view.setBackgroundResource(R.drawable.seletor_nav_drawer);
            holder.text.setTextColor(context.getResources().getColor(R.color.black));
        }
        return view;
    }



    public void setSelected(int position, boolean selected) {
        clearSelect();

        list.get(position).selected = selected;
        notifyDataSetChanged();
    }

    public void clearSelect() {
        if (list != null) {
            for (NavDrawerMenuItem item : list) {
                item.selected = false;
            }
            notifyDataSetChanged();
        }
    }

    static class ViewHolder {
        TextView text;
        ImageView img;
    }
}

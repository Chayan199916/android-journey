package com.example.onetonteaching.customlistview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String names[];
    private final Integer demoImages[];

    public MyListAdapter(Activity context, String[] names, Integer[] demoImages) {
        super(context, R.layout.image_list, names);
        this.context = context;
        this.names = names;
        this.demoImages = demoImages;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.image_list, null, true);
        TextView textTitle = (TextView) rowView.findViewById(R.id.textView1);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView1);
        textTitle.setText(names[position]);
        imageView.setImageResource(demoImages[position]);
        return rowView;
    }
}

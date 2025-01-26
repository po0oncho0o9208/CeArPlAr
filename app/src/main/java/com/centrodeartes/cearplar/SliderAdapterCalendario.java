package com.centrodeartes.cearplar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Calendar;

public class SliderAdapterCalendario extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    public int[] listaimagenes = {R.mipmap.enero, R.mipmap.febrero, R.mipmap.marzo, R.mipmap.abril, R.mipmap.mayo, R.mipmap.junio, R.mipmap.julio,
            R.mipmap.agosto, R.mipmap.septiembre, R.mipmap.octubre, R.mipmap.noviembre, R.mipmap.diciembre};


    Calendar c = Calendar.getInstance();
    int mes = c.get(Calendar.MONTH);

    public int[] lista = new int[12 - mes];


    @Override
    public int getCount() {
        return lista.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_slider_adapter_calendario, container, false);
        ImageView imagenv = view.findViewById(R.id.imageviewcal);
        for (int i = 0; i < (12 - mes); i++) {
            lista[i] = listaimagenes[i + mes];
        }
        imagenv.setBackground(context.getResources().getDrawable(lista[position]));
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    public SliderAdapterCalendario(Context context) {
        this.context = context;

    }

}

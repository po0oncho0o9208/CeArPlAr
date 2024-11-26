package com.centrodeartes.cearplar;

import android.content.Context;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class SliderAdapter extends PagerAdapter {

    boolean isfull;
    Context context;
    ArrayList<Integer> arrayList;
    LayoutInflater layoutInflater;
    LinearLayout layout;
    Button btn;
    ViewPager viewPagerfull;


    public SliderAdapter(Context context, ArrayList<Integer> arrayList, Button btn, ViewPager viewPagerfull, LinearLayout layout) {
        this.context = context;
        this.arrayList = arrayList;
        this.layout = layout;
        this.btn = btn;
        this.viewPagerfull = viewPagerfull;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.viewpager, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(arrayList.get(position));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isfull) {
                    Animation animation = AnimationUtils.loadAnimation(context, R.anim.galeryanim1);
                    viewPagerfull.startAnimation(animation);
                    viewPagerfull.setVisibility(View.VISIBLE);
                    layout.startAnimation(animation);
                    layout.setVisibility(View.VISIBLE);
                    btn.startAnimation(animation);
                    btn.setVisibility(View.VISIBLE);
                    viewPagerfull.setCurrentItem(position);
                    isfull = true;
                }
            }
        });
        container.addView(view);
        return view;
    }




    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

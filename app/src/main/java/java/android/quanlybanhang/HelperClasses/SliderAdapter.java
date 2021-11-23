package java.android.quanlybanhang.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import java.android.quanlybanhang.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    int images[] = {
        R.drawable.slide1_thuchi,
        R.drawable.slide2,
        R.drawable.slide1_thuchi,
        R.drawable.slide_time
    };

    int headings[] = {
        R.string.app_name,
        R.string.app_name,
        R.string.app_name,
        R.string.app_name
    };

    int descriptions[] = {
        R.string.text123,
        R.string.text123,
        R.string.text123,
        R.string.text123
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout, container, false);


        //Hooks
        ImageView imageView = view.findViewById(R.id.slides_img);
        TextView heading = view.findViewById(R.id.slides_heading);
        TextView desc = view.findViewById(R.id.slide_desc);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        desc.setText(descriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout)object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}

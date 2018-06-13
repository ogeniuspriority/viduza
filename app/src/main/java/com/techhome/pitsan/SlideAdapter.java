package com.techhome.pitsan;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SlideAdapter extends PagerAdapter {
    public String[] titles = {
            "Crowdsourcing",
            "Single pit emptying",
            "Truck owners"
    };
    //---------------
    public static volatile int currPos_tr = 0;
    public int[] backgrounds = {
            Color.rgb(55, 55, 55),
            Color.rgb(239, 85, 85),
            Color.rgb(110, 49, 89)
    };
    public String[] btnTexts = {
            "Get started",
            "Get started",
            "Work with us"
    };
    Context context;
    LayoutInflater layoutInflater;
    public static volatile int theIn_posit = 0;

    public SlideAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    public String[] texts = {
            "Crowd sourcing Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam erat leo, dapibus vel elementum non, malesuada at leo. Aliquam ut ligula vel quam imperdiet varius eget et neque. Etiam ut maximus tortor. In sed magna vitae ex cursus scelerisque. Duis commodo urna quis elit tincidunt pellentesque. Cras vel odio eget velit rutrum aliquam. Morbi venenatis varius rutrum. Mauris pulvinar consequat sollicitudin. Vestibulum venenatis a felis nec rutrum. Donec feugiat scelerisque commodo. Praesent sem nisi, consectetur id erat non, venenatis aliquam mauris.\n",
            "Single pit Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam erat leo, dapibus vel elementum non, malesuada at leo. Aliquam ut ligula vel quam imperdiet varius eget et neque. Etiam ut maximus tortor. In sed magna vitae ex cursus scelerisque. Duis commodo urna quis elit tincidunt pellentesque. Cras vel odio eget velit rutrum aliquam. Morbi venenatis varius rutrum. Mauris pulvinar consequat sollicitudin. Vestibulum venenatis a felis nec rutrum. Donec feugiat scelerisque commodo. Praesent sem nisi, consectetur id erat non, venenatis aliquam mauris.\n",
            "Truck Owners Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam erat leo, dapibus vel elementum non, malesuada at leo. Aliquam ut ligula vel quam imperdiet varius eget et neque. Etiam ut maximus tortor. In sed magna vitae ex cursus scelerisque. Duis commodo urna quis elit tincidunt pellentesque. Cras vel odio eget velit rutrum aliquam. Morbi venenatis varius rutrum. Mauris pulvinar consequat sollicitudin. Vestibulum venenatis a felis nec rutrum. Donec feugiat scelerisque commodo. Praesent sem nisi, consectetur id erat non, venenatis aliquam mauris.\n"
    };

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);
        LinearLayout linearLayout = view.findViewById(R.id.slidelayout);
        LinearLayout linearLayout2 = view.findViewById(R.id.linearLayout2);
        TextView textView2 = view.findViewById(R.id.textView2);
        TextView textView3 = view.findViewById(R.id.textView3);
        Button button = view.findViewById(R.id.button2);

        linearLayout2.setBackgroundColor(backgrounds[position]);
        textView2.setText(titles[position]);
        textView3.setText(texts[position]);
        button.setText(btnTexts[position]);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RegisterActivity.class);
                intent.putExtra("whichPosition", position);
                intent.putExtra("whicddhPosition", btnTexts[position]);
                currPos_tr = position;
                // Check if we're running on Android 5.0 or higher
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Apply activity transition

                    context.startActivity(intent,
                            ActivityOptions.makeSceneTransitionAnimation((MainActivity) context).toBundle());
                } else {
                    // Swap without transition
                    context.startActivity(intent);
                }

            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }
}

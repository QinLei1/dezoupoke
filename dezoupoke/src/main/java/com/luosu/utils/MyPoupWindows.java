package com.luosu.utils;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.luosu.dezoupoke.R;

/**
 * Created by User on 2017/3/2.
 */

public class MyPoupWindows {
    private PopupWindow window;
    private Activity context;
    private TextView tv;

    public MyPoupWindows(Activity context) {
        this.context = context;

    }

    public void showWindows(View viewlocation, PopupWindow.OnDismissListener listener) {
        View view = View.inflate(context, R.layout.number_window, null);
        tv = (TextView) view.findViewById(R.id.tv_number);
        window = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setFocusable(false);
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ffffff")));
        window.setOutsideTouchable(false);
        window.showAtLocation(viewlocation, Gravity.CENTER, 0, 200);
        window.setOnDismissListener(listener);


    }

    public void setText(String text) {
        if (window!=null) {
            if (Integer.parseInt(text) <= 0) {
                dismissWindows();
            }
            tv.setText(text);
        }

    }

    public void dismissWindows() {
        if (window != null && window.isShowing()) {
            window.dismiss();
            window = null;
        }
    }

    public boolean windowIsshowing() {
        if (window != null) {
            return window.isShowing();
        } else {
            return false;
        }

    }

}

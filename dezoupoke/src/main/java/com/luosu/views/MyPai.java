package com.luosu.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.luosu.dezoupoke.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017/3/2.
 */

public class MyPai extends FrameLayout {
    private  Context context;
    private ImageView pai1,pai2,pai3,pai4,pai5;



    public MyPai(Context context) {
        this(context,null);
    }

    public MyPai(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyPai(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        View view= View.inflate(context, R.layout.mypai,null);
        this.context= context;
        pai1=(ImageView) view.findViewById(R.id.pai1);
        pai2=(ImageView) view.findViewById(R.id.pai2);
        pai3=(ImageView) view.findViewById(R.id.pai3);
        pai4=(ImageView) view.findViewById(R.id.pai4);
        pai5=(ImageView) view.findViewById(R.id.pai5);
        this.addView(view);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     *
     * @param list 图片id集合
     */
    public  void setImags(ArrayList<Integer> list){
        pai1.setImageDrawable(getResources().getDrawable(list.get(0)));
        pai2.setImageDrawable(getResources().getDrawable(list.get(1)));
        pai3.setImageDrawable(getResources().getDrawable(list.get(2)));
        pai4.setImageDrawable(getResources().getDrawable(list.get(3)));
        pai5.setImageDrawable(getResources().getDrawable(list.get(4)));
    }
}

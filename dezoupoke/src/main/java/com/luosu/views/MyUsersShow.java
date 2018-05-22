package com.luosu.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luosu.dezoupoke.R;

/**
 * Created by User on 2017/4/10.
 * 用于展示用户信息，入id，名称，钱币数量
 */

public class MyUsersShow extends LinearLayout {
    private boolean isvertical;
    private TextView tv_grade, tv_username, tv_gold_num;
    private ImageView img_header;
    private int userid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public MyUsersShow(Context context) {
        this(context, null);
    }

    public MyUsersShow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyUsersShow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context, attrs);
        initViews(context);
    }

    private void initParams(Context context, AttributeSet attrs) {


        TypedArray typearray = context.obtainStyledAttributes(attrs, R.styleable.myuserbuju);
        if (typearray != null) {
            isvertical = typearray.getBoolean(R.styleable.myuserbuju_isvertical, false);
            typearray.recycle();
        }
    }

    private void initViews(Context context) {
        View view;
        if (isvertical) {
            //纵向
            view = View.inflate(context, R.layout.my_user_show, null);


        } else {
            //横向
            view = View.inflate(context, R.layout.my_user_show_landspace, null);

        }
        tv_grade = (TextView) view.findViewById(R.id.tv_grade);
        tv_username = (TextView) view.findViewById(R.id.tv_username);
        tv_gold_num = (TextView) view.findViewById(R.id.tv_gold_num);
        img_header = (ImageView) view.findViewById(R.id.img_header);


        this.addView(view);

    }

    /**
     *
     * @param grade  等级
     * @param username 用户名
     * @param gold  金币数量
     */
    public void setMessage(String grade, String username, String gold) {
        tv_grade.setText(grade);
        tv_username.setText(username);
        tv_gold_num.setText(gold);
    }
}

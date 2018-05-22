package com.luosu.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.luosu.adapter.MyGridleAdapter;
import com.luosu.dezoupoke.R;
import com.luosu.utils.PaiUtils;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends Activity {
    private GridView gride_all;
    private MyGridleAdapter adapter;
    /**
     * 所以的牌
     */
    private ArrayList<Integer> list;
    /**
     * 公共牌
     */
    private ArrayList<Integer> listpai;
    private int index;
    private ImageView player1, player1_1, player1_2, player2, player2_1, player2_2, player3, player3_1, player3_2, player4, player4_1, player4_2,
            player5, player5_1, player5_2, player6, player6_1, player6_2, player7, player7_1, player7_2;
    /**
     * 开牌
     */
    private Button btn_open;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initViews();
        initData();
        adapter = new MyGridleAdapter(this, listpai);
        gride_all.setAdapter(adapter);

    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 1; i < 53; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        listpai = new ArrayList<>();
        listpai.add(PaiUtils.getdrawabelidfornumber(list.get(0), this));
        listpai.add(PaiUtils.getdrawabelidfornumber(list.get(1), this));
        listpai.add(PaiUtils.getdrawabelidfornumber(list.get(2), this));

    }

    private void initViews() {
        gride_all = (GridView) findViewById(R.id.gride_all);
        player1 = (ImageView) findViewById(R.id.player1);
        player2 = (ImageView) findViewById(R.id.player2);
        player3 = (ImageView) findViewById(R.id.player3);
        player4 = (ImageView) findViewById(R.id.player4);
        player5 = (ImageView) findViewById(R.id.player5);
        player6 = (ImageView) findViewById(R.id.player6);
        player7 = (ImageView) findViewById(R.id.player7);
        player1_1 = (ImageView) findViewById(R.id.player1_1);
        player1_2 = (ImageView) findViewById(R.id.player1_2);
        player2_1 = (ImageView) findViewById(R.id.player2_1);
        player2_2 = (ImageView) findViewById(R.id.player2_2);
        player3_1 = (ImageView) findViewById(R.id.player3_1);
        player3_2 = (ImageView) findViewById(R.id.player3_2);
        player4_1 = (ImageView) findViewById(R.id.player4_1);
        player4_2 = (ImageView) findViewById(R.id.player4_2);
        player5_1 = (ImageView) findViewById(R.id.player5_1);
        player5_2 = (ImageView) findViewById(R.id.player5_2);
        player6_1 = (ImageView) findViewById(R.id.player6_1);
        player6_2 = (ImageView) findViewById(R.id.player6_2);
        player7_1 = (ImageView) findViewById(R.id.player7_1);
        player7_2 = (ImageView) findViewById(R.id.player7_2);
        btn_open = (Button) findViewById(R.id.btn_open);
        registerListener();
    }

    private void registerListener() {
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAllPai();
            }
        });
    }

    private void fapai() {


    }

    /**
     * 打开所有的牌
     */
    private void openAllPai() {
        for (int i = 1; i < 53; i++) {
            Log.i("tag","i:---"+list.get(i-1));
        }
        player1_1.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(list.get(5), this)));
        player1_2.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(list.get(6), this)));
        player2_1.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(list.get(7), this)));
        player2_2.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(list.get(8), this)));
        player3_1.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(list.get(9), this)));
        player3_2.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(list.get(10), this)));
        player4_1.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(list.get(11), this)));
        player4_2.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(list.get(12), this)));
        player5_1.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(list.get(13), this)));
        player5_2.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(list.get(14), this)));
        player6_1.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(list.get(15), this)));
        player6_2.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(list.get(16), this)));
        player7_1.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(list.get(17), this)));
        player7_2.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(list.get(18), this)));


        if (listpai.size()<5){
            listpai.add(PaiUtils.getdrawabelidfornumber(list.get(3), this));
            listpai.add(PaiUtils.getdrawabelidfornumber(list.get(4), this));
        }
        adapter.notifyDataSetChanged();

    }
}

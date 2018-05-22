package com.luosu.activitys;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.luosu.dezoupoke.R;
import com.luosu.entity.BaseBean;
import com.luosu.entity.Pai;
import com.luosu.entity.PaiData;
import com.luosu.entity.Uploadgold;
import com.luosu.entity.UserGoldData;
import com.luosu.entity.loadjuMessage;
import com.luosu.listener.NettyHnalerListener;
import com.luosu.netty.NettyClient;
import com.luosu.utils.JavaBaseUtils;
import com.luosu.utils.MyHttpUtils;
import com.luosu.utils.MyPoupWindows;
import com.luosu.utils.PaiUtils;
import com.luosu.utils.PrefUtils;
import com.luosu.views.MyPai;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class NiuNiuActivity extends Activity implements View.OnClickListener {
    //显示的小牛抖动的动画容器控件
    private ImageView img_xin, img_qiuqiu, img_zaizai;
    //显示是什么牌，牛一，牛二
    private ImageView img_player1, img_player2, img_player3;
    //3副牌的显示自定义控件
    private MyPai mypai_player1, mypai_player2, mypai_player3;
    //显示窗口定位
    private LinearLayout linear1;
    //我的handler，处理handler内存泄漏
    private MyHandler handler;
    //到计时的总秒数
    private int num = 30;
    //显示倒计时用的
    MyPoupWindows windows;
    // 所有的牌，3副手牌显示
    private ArrayList<Integer> list1, list2, list3;
    //重新开始游戏，和开始德州扑克,加钱
    private Button btn_startgame, btn, btn_add_gold, btn_goto_socket;
    //小牛舞动动画
    Animation xinanim;
    //显示下注的总金币数，和个人下注数
    private TextView tv_xinxin_all, tv_xinxin_mine, tv_qiuqiu_all, tv_qiuqiu_mine, tv_zaizai_mine, tv_zaizai_all;
    //显示我的金币数
    private TextView tv_gold_mine;
    private LinearLayout linear_xin, linear_qiu, linear_zai;
    //当前下注的金币
    private int xinxin_gold = 0, qiuqiu_gold = 0, zaizai_gold = 0;
    //处理轻量级数据的
    PrefUtils prefutils;
    private int user_gold;
    //每次下注的钱
    private static final int NUM_XIAZHU = 10;
    //用于打印log
    private static final String LOG_TAG = "NiuNiuActivity";
    //所有用户数据
    ArrayList<Pai> pais;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niu_niu);
        handler = new MyHandler(this);
        initData();
        initViews();
        xinanim = AnimationUtils.loadAnimation(this, R.anim.xinxin_anim);
        xinanim.setRepeatMode(Animation.REVERSE);
        startGame();
        xinanim.cancel();

    }

    /**
     * 游戏开始
     */
    public void startGame() {
        //开始游戏时需要在后台初始化
        MyHttpUtils bejin = new MyHttpUtils();
        String url = "http://192.168.3.66:8030/MyTomCatTest/Myserver";
        StringBuilder sb = new StringBuilder(url);
        sb.append("?act=startgame");
        sb.append("&msg=" + JavaBaseUtils.putGold(Integer.parseInt(prefutils.getString(PrefUtils.USERID)), 0, 0, 0));
        bejin.httpget(sb.toString(), new MyHttpUtils.ResultListener() {
            @Override
            public void onResult(String result) {

            }
        });

        selectgold();


        img_player1.setVisibility(View.GONE);
        img_player2.setVisibility(View.GONE);
        img_player3.setVisibility(View.GONE);

        tv_xinxin_all.setVisibility(View.GONE);
        tv_xinxin_mine.setVisibility(View.GONE);
        tv_qiuqiu_all.setVisibility(View.GONE);
        tv_qiuqiu_mine.setVisibility(View.GONE);
        tv_zaizai_all.setVisibility(View.GONE);
        tv_zaizai_mine.setVisibility(View.GONE);

        xinxin_gold = 0;
        qiuqiu_gold = 0;
        zaizai_gold = 0;


        num = 30;
        if (null != windows) {
            windows.dismissWindows();
        }

        handler.removeMessages(12);
        list1.clear();
        list2.clear();
        list3.clear();
//        Collections.shuffle(list);
        img_xin.setAnimation(xinanim);
        img_qiuqiu.setAnimation(xinanim);
        img_zaizai.setAnimation(xinanim);
        xinanim.start();

        MyHttpUtils utils = new MyHttpUtils();

        StringBuilder sbnew = new StringBuilder(url);
        sbnew.append("?act=fapai");
        sbnew.append("&paizuoNum=77");
        utils.httpget(sbnew.toString(), new MyHttpUtils.ResultListener() {
            @Override
            public void onResult(String result) {
                Log.d(LOG_TAG, "发牌数据：" + result);
                PaiData data = JSON.parseObject(result, PaiData.class);


                pais = data.getData();

                list1.add(PaiUtils.getdrawabelidfornumber(pais.get(0).getList().get(0), NiuNiuActivity.this));
                list2.add(PaiUtils.getdrawabelidfornumber(pais.get(1).getList().get(0), NiuNiuActivity.this));
                list3.add(PaiUtils.getdrawabelidfornumber(pais.get(2).getList().get(0), NiuNiuActivity.this));
                for (int i = 0; i < 4; i++) {
                    list1.add(R.drawable.paibackground_niuniu);
                    list2.add(R.drawable.paibackground_niuniu);
                    list3.add(R.drawable.paibackground_niuniu);
                }
                mypai_player1.setImags(list1);
                mypai_player2.setImags(list2);
                mypai_player3.setImags(list3);
                linear1.post(new Runnable() {
                    @Override
                    public void run() {
                        windows = new MyPoupWindows(NiuNiuActivity.this);
                        windows.showWindows(linear1, new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                            }
                        });
                        handler.sendEmptyMessageDelayed(12, 1000);
                    }
                });

            }
        });


    }

    private void initData() {
//        list = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
//        for (int i = 1; i < 53; i++) {
//            list.add(i);
//        }
        selectgold();

    }

    private void selectgold() {
        prefutils = new PrefUtils(this, PrefUtils.user_messages);


        String url = "http://192.168.3.66:8030/MyTomCatTest/Myserver";
        StringBuilder sb = new StringBuilder(url);
        sb.append("?act=selectgold");
        sb.append("&userid=" + prefutils.getString(PrefUtils.USERID));
        MyHttpUtils utils = new MyHttpUtils();
        utils.httpget(sb.toString(), new MyHttpUtils.ResultListener() {
            @Override
            public void onResult(String result) {
                try {
                    JSONObject jo = new JSONObject(result);
                    if (jo.getInt("result") == 1) {
                        UserGoldData data = JSON.parseObject(result, UserGoldData.class);
                        user_gold = data.getGold().getGold();
                        prefutils.setint(PrefUtils.GOLD_NUM, user_gold);
                        tv_gold_mine.setText("我的金币：" + user_gold);
                    } else {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    private void initViews() {
        img_xin = (ImageView) findViewById(R.id.img_xinxin);
        img_player1 = (ImageView) findViewById(R.id.img_player1);
        img_player2 = (ImageView) findViewById(R.id.img_player2);
        img_player3 = (ImageView) findViewById(R.id.img_player3);
        img_qiuqiu = (ImageView) findViewById(R.id.img_qiuqiu);
        img_zaizai = (ImageView) findViewById(R.id.img_zaizai);
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        mypai_player1 = (MyPai) findViewById(R.id.mypai_player1);
        mypai_player2 = (MyPai) findViewById(R.id.mypai_player2);
        mypai_player3 = (MyPai) findViewById(R.id.mypai_player3);
        btn_startgame = (Button) findViewById(R.id.btn_startgame);
        btn = (Button) findViewById(R.id.btn);
        btn_add_gold = (Button) findViewById(R.id.btn_add_gold);
        btn_goto_socket = (Button) findViewById(R.id.btn_goto_socket);

        tv_xinxin_all = (TextView) findViewById(R.id.tv_xinxin_all);
        tv_xinxin_mine = (TextView) findViewById(R.id.tv_xinxin_mine);
        tv_qiuqiu_all = (TextView) findViewById(R.id.tv_qiuqiu_all);
        tv_qiuqiu_mine = (TextView) findViewById(R.id.tv_qiuqiu_mine);
        tv_zaizai_all = (TextView) findViewById(R.id.tv_zaizai_all);
        tv_zaizai_mine = (TextView) findViewById(R.id.tv_zaizai_mine);

        linear_xin = (LinearLayout) findViewById(R.id.linear_xinxin);
        linear_qiu = (LinearLayout) findViewById(R.id.linear_qiuqiu);
        linear_zai = (LinearLayout) findViewById(R.id.linear_zaizai);


        tv_gold_mine = (TextView) findViewById(R.id.tv_gold_mine);

        registerListener();

    }

    /**
     * 对handler得处理
     */
    private void messageHandlerTod() {
        num--;
        windows.setText(num + "");
        handler.sendEmptyMessageDelayed(12, 1000);
        //快结束时
        if (num == 1) {
            //开始游戏时需要在后台初始化
            MyHttpUtils stop = new MyHttpUtils();
            String url = "http://192.168.3.66:8030/MyTomCatTest/Myserver";
            StringBuilder sb = new StringBuilder(url);
            sb.append("?act=stopgame");
            sb.append("&paizuoNum=77");

            stop.httpget(sb.toString(), new MyHttpUtils.ResultListener() {
                @Override
                public void onResult(String result) {

                }
            });
        }
        if (num <= 0) {

            list1.clear();
            list2.clear();
            list3.clear();
            list1.add(PaiUtils.getdrawabelidfornumber(pais.get(0).getList().get(0), NiuNiuActivity.this));
            list1.add(PaiUtils.getdrawabelidfornumber(pais.get(0).getList().get(1), NiuNiuActivity.this));
            list1.add(PaiUtils.getdrawabelidfornumber(pais.get(0).getList().get(2), NiuNiuActivity.this));
            list1.add(PaiUtils.getdrawabelidfornumber(pais.get(0).getList().get(3), NiuNiuActivity.this));
            list1.add(PaiUtils.getdrawabelidfornumber(pais.get(0).getList().get(4), NiuNiuActivity.this));
            list2.add(PaiUtils.getdrawabelidfornumber(pais.get(1).getList().get(0), NiuNiuActivity.this));
            list2.add(PaiUtils.getdrawabelidfornumber(pais.get(1).getList().get(1), NiuNiuActivity.this));
            list2.add(PaiUtils.getdrawabelidfornumber(pais.get(1).getList().get(2), NiuNiuActivity.this));
            list2.add(PaiUtils.getdrawabelidfornumber(pais.get(1).getList().get(3), NiuNiuActivity.this));
            list2.add(PaiUtils.getdrawabelidfornumber(pais.get(1).getList().get(4), NiuNiuActivity.this));
            list3.add(PaiUtils.getdrawabelidfornumber(pais.get(2).getList().get(0), NiuNiuActivity.this));
            list3.add(PaiUtils.getdrawabelidfornumber(pais.get(2).getList().get(1), NiuNiuActivity.this));
            list3.add(PaiUtils.getdrawabelidfornumber(pais.get(2).getList().get(2), NiuNiuActivity.this));
            list3.add(PaiUtils.getdrawabelidfornumber(pais.get(2).getList().get(3), NiuNiuActivity.this));
            list3.add(PaiUtils.getdrawabelidfornumber(pais.get(2).getList().get(4), NiuNiuActivity.this));
            mypai_player1.setImags(list1);
            mypai_player2.setImags(list2);
            mypai_player3.setImags(list3);
            img_xin.clearAnimation();
            img_qiuqiu.clearAnimation();
            img_zaizai.clearAnimation();
            handler.removeMessages(12);
            //判定牌是否有牛
            int mypai_player1_niu = PaiUtils.paiisNiuJi(pais.get(0).getList());
            int mypai_player2_niu = PaiUtils.paiisNiuJi(pais.get(1).getList());
            int mypai_player3_niu = PaiUtils.paiisNiuJi(pais.get(2).getList());
            img_player1.setVisibility(View.VISIBLE);
            img_player2.setVisibility(View.VISIBLE);
            img_player3.setVisibility(View.VISIBLE);
            img_player1.setImageDrawable(ContextCompat.getDrawable(this, PaiUtils.getDrawableResIDByName(this, "ico_bull_n" + mypai_player1_niu)));
            img_player2.setImageDrawable(ContextCompat.getDrawable(this, PaiUtils.getDrawableResIDByName(this, "ico_bull_n" + mypai_player2_niu)));
            img_player3.setImageDrawable(ContextCompat.getDrawable(this, PaiUtils.getDrawableResIDByName(this, "ico_bull_n" + mypai_player3_niu)));
            if (mypai_player1_niu >= mypai_player2_niu && mypai_player1_niu >= mypai_player3_niu) {
                user_gold = user_gold + xinxin_gold * 3;
                prefutils.setint(PrefUtils.GOLD_NUM, user_gold);
                tv_gold_mine.setText("我的金币：" + user_gold);
            }
            if (mypai_player2_niu > mypai_player1_niu && mypai_player2_niu >= mypai_player3_niu) {
                user_gold = user_gold + qiuqiu_gold * 3;
                prefutils.setint(PrefUtils.GOLD_NUM, user_gold);
                tv_gold_mine.setText("我的金币：" + user_gold);
            }
            if (mypai_player3_niu > mypai_player2_niu && mypai_player3_niu > mypai_player1_niu) {
                user_gold = user_gold + zaizai_gold * 3;
                prefutils.setint(PrefUtils.GOLD_NUM, user_gold);
                tv_gold_mine.setText("我的金币：" + user_gold);
            }
            String url = "http://192.168.3.66:8030/MyTomCatTest/Myserver";
            StringBuilder sb = new StringBuilder(url);
            sb.append("?act=updatagold");
            sb.append("&userid=" + prefutils.getString(PrefUtils.USERID));
            sb.append("&gold=" + user_gold);
            MyHttpUtils utils = new MyHttpUtils();
            utils.httpget(sb.toString(), new MyHttpUtils.ResultListener() {
                @Override
                public void onResult(String result) {
                    BaseBean bean = JSON.parseObject(result, BaseBean.class);

                }
            });
        }
//        if (num <= 3) {
//            linear_xin.setClickable(false);
//
//            linear_qiu.setClickable(false);
//            linear_zai.setClickable(false);
//
//        }
    }


    private void registerListener() {
        btn_startgame.setOnClickListener(this);
        btn.setOnClickListener(this);

        linear_xin.setOnClickListener(this);
        linear_qiu.setOnClickListener(this);
        linear_zai.setOnClickListener(this);
        btn_add_gold.setOnClickListener(this);
        btn_goto_socket.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_startgame:
                Log.i("tag", "num:" + num);
                if (!windows.windowIsshowing()) {
                    Log.i("tag", "重新开始");
                    startGame();

                } else {
                    Toast.makeText(NiuNiuActivity.this
                            , "不可停止", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.linear_xinxin:
                if (user_gold >= NUM_XIAZHU) {
                    if (qiuqiu_gold > 2 && zaizai_gold > 2) {
                        Toast.makeText(NiuNiuActivity.this
                                , "最多下两个注", Toast.LENGTH_LONG).show();
                    } else {
                        if (num > 3) {
                            xinxin_gold += NUM_XIAZHU;
                            MyHttpUtils utils = new MyHttpUtils();
                            String url = "http://192.168.3.66:8030/MyTomCatTest/Myserver";
                            StringBuilder sb = new StringBuilder(url);
                            sb.append("?act=uploinggold");
                            sb.append("&msg=" + JavaBaseUtils.putGold(Integer.parseInt(prefutils.getString(PrefUtils.USERID)), NUM_XIAZHU, 0, 0));
                            utils.httpget(sb.toString(), new MyHttpUtils.ResultListener() {
                                @Override
                                public void onResult(String result) {

                                }
                            });
                            disposeclick(tv_xinxin_all, tv_xinxin_mine, xinxin_gold);
                        } else {
                            Toast.makeText(NiuNiuActivity.this
                                    , "开局最后3秒不可下注", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(NiuNiuActivity.this
                            , "你没钱,请充值", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.linear_qiuqiu:
                if (user_gold >= NUM_XIAZHU) {
                    if (xinxin_gold > 2 && zaizai_gold > 2) {
                        Toast.makeText(NiuNiuActivity.this
                                , "最多下两个注", Toast.LENGTH_LONG).show();
                    } else {
                        if (num > 3) {
                            qiuqiu_gold += NUM_XIAZHU;
                            MyHttpUtils utils = new MyHttpUtils();
                            String url = "http://192.168.3.66:8030/MyTomCatTest/Myserver";
                            StringBuilder sb = new StringBuilder(url);
                            sb.append("?act=uploinggold");
                            sb.append("&msg=" + JavaBaseUtils.putGold(Integer.parseInt(prefutils.getString(PrefUtils.USERID)), 0, NUM_XIAZHU, 0));
                            utils.httpget(sb.toString(), new MyHttpUtils.ResultListener() {
                                @Override
                                public void onResult(String result) {

                                }
                            });

                            disposeclick(tv_qiuqiu_all, tv_qiuqiu_mine, qiuqiu_gold);
                        } else {
                            Toast.makeText(NiuNiuActivity.this
                                    , "开局最后3秒不可下注", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(NiuNiuActivity.this
                            , "你没钱,请充值", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.linear_zaizai:
                if (user_gold >= NUM_XIAZHU) {
                    if (qiuqiu_gold > 2 && xinxin_gold > 2) {
                        Toast.makeText(NiuNiuActivity.this
                                , "最多下两个注", Toast.LENGTH_LONG).show();
                    } else {
                        if (num > 3) {
                            zaizai_gold += NUM_XIAZHU;
                            MyHttpUtils utils = new MyHttpUtils();
                            String url = "http://192.168.3.66:8030/MyTomCatTest/Myserver";
                            StringBuilder sb = new StringBuilder(url);
                            sb.append("?act=uploinggold");
                            sb.append("&msg=" + JavaBaseUtils.putGold(Integer.parseInt(prefutils.getString(PrefUtils.USERID)), 0, 0, NUM_XIAZHU));
                            utils.httpget(sb.toString(), new MyHttpUtils.ResultListener() {
                                @Override
                                public void onResult(String result) {

                                }
                            });
                            disposeclick(tv_zaizai_all, tv_zaizai_mine, zaizai_gold);

                        } else {
                            Toast.makeText(NiuNiuActivity.this
                                    , "开局最后3秒不可下注", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(NiuNiuActivity.this
                            , "你没钱,请充值", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn:
                startActivity(new Intent(NiuNiuActivity.this, GameActivity.class));
                break;
            case R.id.btn_add_gold:
                user_gold += 100;
                prefutils.setint(PrefUtils.GOLD_NUM, user_gold);
                tv_gold_mine.setText("我的金币：" + user_gold);
                break;
            case R.id.btn_goto_socket:
                for (int i = 0; i < 1; i++) {
                    final NettyClient client = new NettyClient(NiuNiuActivity.this, new NettyHnalerListener() {
                        @Override
                        public void channelReadListener(String msg) {
                            Log.d("tag", "监听的消息为:" + msg);

                            final Uploadgold gold = JSON.parseObject(msg, Uploadgold.class);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    chuliAllGold(gold.getUser1gold(), gold.getUser2gold(), gold.getUser3gold());
                                }
                            });

//                            Toast.makeText(NiuNiuActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
                    client.connect(NettyClient.PORT, NettyClient.IP, i + 1);
                    handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                loadjuMessage message = new loadjuMessage();
                                                message.setUserid(new PrefUtils(NiuNiuActivity.this, PrefUtils.user_messages).getString(PrefUtils.USERID));
                                                message.setZuoNum("77");
                                                client.sendMessage(JSON.toJSONString(message));

                                            }
                                        }, 5000

                    );

                }
                break;
        }


    }

    private void disposeclick(TextView tv_all, TextView tv_mine, int goldNumber) {
        tv_all.setVisibility(View.VISIBLE);
//        tv_all.setText(goldNumber + "");
        tv_mine.setVisibility(View.VISIBLE);
        tv_mine.setText(goldNumber + "");
        user_gold -= NUM_XIAZHU;
        prefutils.setint(PrefUtils.GOLD_NUM, user_gold);
        tv_gold_mine.setText("我的金币：" + user_gold);

    }

    public void chuliAllGold(int xingold, int qiugold, int zaigold) {
        tv_xinxin_all.setVisibility(View.VISIBLE);
        tv_qiuqiu_all.setVisibility(View.VISIBLE);
        tv_zaizai_all.setVisibility(View.VISIBLE);
        tv_xinxin_all.setText(xingold + "");
        tv_qiuqiu_all.setText(qiugold + "");
        tv_zaizai_all.setText(zaigold + "");

    }

    private static class MyHandler extends Handler {
        private final WeakReference<NiuNiuActivity> mActivity;

        private MyHandler(NiuNiuActivity activity) {
            mActivity = new WeakReference<NiuNiuActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {

            if (mActivity.get() == null) {
                return;
            }
            mActivity.get().messageHandlerTod();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        windows.dismissWindows();
//        this.dismissDialog();
    }
}


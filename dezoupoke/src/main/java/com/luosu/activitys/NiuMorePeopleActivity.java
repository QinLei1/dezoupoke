package com.luosu.activitys;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.luosu.dezoupoke.R;
import com.luosu.entity.AllPeopleMessage;
import com.luosu.entity.AllpreparePeople;
import com.luosu.entity.BejinMessage;
import com.luosu.entity.OnePeoplePai;
import com.luosu.entity.UserGoldandname;
import com.luosu.listener.NettyHnalerListener;
import com.luosu.netty.NettyClient;
import com.luosu.utils.PaiUtils;
import com.luosu.utils.PrefUtils;
import com.luosu.views.MyPai;
import com.luosu.views.MyUsersShow;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


public class NiuMorePeopleActivity extends Activity implements View.OnClickListener {
    //自己的牌，5张牌
    private ImageView img1, img2, img3, img4, img5;
    //用于显示牌几
    private ImageView img1_niuji, img2_niuji, img3_niuji, img4_niuji, img_niuji_shelf;
    //判定上面是否相加显示
    private LinearLayout linear_numbers_sum;

    // 用于展示个人信息，
    private MyUsersShow myshelf, player1message, player2message, player3message, player4message;
    //其他人的牌集合
    private MyPai pais1, pais2, pais3, pais4;
    //有牛还是没有牛,准备开始,离开此桌
    private Button btn_have_niu, btn_nohave_niu, begin, lizuo;
    //用于控制显示有牛还是没有牛的两个按钮的隐藏和显示;显示自己的5张牌
    private LinearLayout linear_niu_noniu, linear_player_myshelf_pais;
    private TextView daojishi;
    //用于显示加减的数字
    private TextView tv_num1, tv_num2, tv_num3, tv_num4;
    //个人的牌集合,把牌变成1到10内的数字
    ArrayList<Integer> myPailist, mypailist1to10;
    //个人的牌集合
    ArrayList<Integer> userChosePais;
    //用于数据连接
    private NettyClient client;
    private Handler handler = new Handler();
    MyListener listener;

    private ImageView img_zhuangjia;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;


    private static final int MOVE_FLAG = 20;


    private static final String LOG_TAG = "NiuMorePeopleActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niu_more_people);
        initViews();
        listener = new MyListener();
        userChosePais = new ArrayList<>();
        client = new NettyClient(this, listener);
        client.connect(NettyClient.PORT, NettyClient.IP, 1);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //准备
                Log.d("tag", "用户落座");
                BejinMessage msg = new BejinMessage();
                msg.setAct("luozuo");
                PrefUtils utils = new PrefUtils(NiuMorePeopleActivity.this, PrefUtils.user_messages);
                msg.setUserid(utils.getString(PrefUtils.USERID));
                msg.setZuoNum("1");
                client.sendMessage(JSON.toJSONString(msg));
            }
        }, 2000);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initViews() {
        img1 = (ImageView) findViewById(R.id.img_pai1);
        img2 = (ImageView) findViewById(R.id.img_pai2);
        img3 = (ImageView) findViewById(R.id.img_pai3);
        img4 = (ImageView) findViewById(R.id.img_pai4);
        img5 = (ImageView) findViewById(R.id.img_pai5);


        img_zhuangjia = (ImageView) findViewById(R.id.img_zhuangjia);


        img1_niuji = (ImageView) findViewById(R.id.img_player1);
        img2_niuji = (ImageView) findViewById(R.id.img_player2);
        img3_niuji = (ImageView) findViewById(R.id.img_player3);
        img4_niuji = (ImageView) findViewById(R.id.img_player4);
        img_niuji_shelf = (ImageView) findViewById(R.id.img_niuji_shelf);


        myshelf = (MyUsersShow) findViewById(R.id.player_myshelf);
        player1message = (MyUsersShow) findViewById(R.id.player1);
        player2message = (MyUsersShow) findViewById(R.id.player2);
        player3message = (MyUsersShow) findViewById(R.id.player3);
        player4message = (MyUsersShow) findViewById(R.id.player4);

        pais1 = (MyPai) findViewById(R.id.mypai_player1);
        pais2 = (MyPai) findViewById(R.id.mypai_player2);
        pais3 = (MyPai) findViewById(R.id.mypai_player3);
        pais4 = (MyPai) findViewById(R.id.mypai_player4);

        btn_have_niu = (Button) findViewById(R.id.btn_niu);
        btn_nohave_niu = (Button) findViewById(R.id.btn_noniu);
        begin = (Button) findViewById(R.id.begin);
        lizuo = (Button) findViewById(R.id.lizuo);


        linear_niu_noniu = (LinearLayout) findViewById(R.id.linear_btns);
        linear_player_myshelf_pais = (LinearLayout) findViewById(R.id.linear_player_myshelf_pais);
        linear_numbers_sum = (LinearLayout) findViewById(R.id.linear_numbers_sum);

        tv_num1 = (TextView) findViewById(R.id.tv_num1);
        tv_num2 = (TextView) findViewById(R.id.tv_num2);
        tv_num3 = (TextView) findViewById(R.id.tv_num3);
        tv_num4 = (TextView) findViewById(R.id.tv_num4);


        daojishi = (TextView) findViewById(R.id.daojishi);
        registerListener();

    }

    private void registerListener() {
        begin.setOnClickListener(this);
        btn_have_niu.setOnClickListener(this);
        btn_nohave_niu.setOnClickListener(this);
        lizuo.setOnClickListener(this);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.begin:
                //准备
                Log.d("tag", "开始被点击");
                BejinMessage msg = new BejinMessage();
                msg.setAct("prepare");
                PrefUtils utils = new PrefUtils(this, PrefUtils.user_messages);
                msg.setUserid(utils.getString(PrefUtils.USERID));
                msg.setZuoNum("1");
                client.sendMessage(JSON.toJSONString(msg));


                break;
            case R.id.btn_niu:
                int sum = 0;
                if (userChosePais != null) {

                    for (int i = 0; i < userChosePais.size(); i++) {
                        sum += userChosePais.get(i);

                    }
                    if (sum % 10 == 0) {
                        //代表有牛，可以将数据发给服务器端

                        ishaveNIuOrNot(true);
                        //防止用户重复点击
                        btn_have_niu.setClickable(false);
                        btn_have_niu.setFocusable(false);
                        btn_nohave_niu.setClickable(false);
                        btn_nohave_niu.setFocusable(false);

                        img_niuji_shelf.setImageDrawable(ContextCompat.getDrawable(this, PaiUtils.getDrawableResIDByName(this, "ico_bull_n" + PaiUtils.paiisNiuJi(myPailist))));
                        img_niuji_shelf.setVisibility(View.VISIBLE);


                    } else {
                        Toast.makeText(NiuMorePeopleActivity.this, "选取的牌不符合规则", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(NiuMorePeopleActivity.this, "还未选牌", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_noniu:
                //用户选择无牛，所以就算用户有牛也算作无牛,将数据发给服务端

                ishaveNIuOrNot(false);
//防止用户重复点击
                btn_have_niu.setClickable(false);
                btn_have_niu.setFocusable(false);
                btn_nohave_niu.setClickable(false);
                btn_nohave_niu.setFocusable(false);
                img_niuji_shelf.setVisibility(View.VISIBLE);

                break;
            //离桌的时候调用一下
            case R.id.lizuo:

                lizuo();

                break;
            case R.id.img_pai1:
                String tag1 = (String) img1.getTag();

                //tag如果为0代表在下面，没有动过，1代表上移过了
                if (tag1.equals("0")) {
                    if (userChosePais.size() < 3) {
                        tag1 = "1";
                        img1.setTag(tag1);
                        img1.scrollBy(0, MOVE_FLAG);
                        userChosePais.add(mypailist1to10.get(0));

                    }

                } else {
                    tag1 = "0";
                    img1.setTag(tag1);
                    img1.scrollBy(0, 0 - MOVE_FLAG);
                    userChosePais.remove(new Integer(mypailist1to10.get(0)));
                }
                flushUserChoseNumbers();
                break;
            case R.id.img_pai2:

                String tag2 = (String) img2.getTag();
                //tag如果为0代表在下面，没有动过，1代表上移过了
                if (tag2.equals("0")) {
                    if (userChosePais.size() < 3) {
                        tag2 = "1";
                        img2.setTag(tag2);
                        img2.scrollBy(0, MOVE_FLAG);
                        userChosePais.add(mypailist1to10.get(1));
                    }
                } else {
                    tag2 = "0";
                    img2.setTag(tag2);
                    img2.scrollBy(0, 0 - MOVE_FLAG);
                    userChosePais.remove(new Integer(mypailist1to10.get(1)));
                }
                flushUserChoseNumbers();
                break;
            case R.id.img_pai3:

                String tag3 = (String) img3.getTag();
                //tag如果为0代表在下面，没有动过，1代表上移过了
                if (tag3.equals("0")) {
                    if (userChosePais.size() < 3) {
                        tag3 = "1";
                        img3.setTag(tag3);
                        img3.scrollBy(0, MOVE_FLAG);
                        userChosePais.add(mypailist1to10.get(2));
                    }
                } else {
                    tag3 = "0";
                    img3.setTag(tag3);
                    img3.scrollBy(0, 0 - MOVE_FLAG);
                    userChosePais.remove(new Integer(mypailist1to10.get(2)));
                }
                flushUserChoseNumbers();
                break;
            case R.id.img_pai4:

                String tag4 = (String) img4.getTag();
                //tag如果为0代表在下面，没有动过，1代表上移过了
                if (tag4.equals("0")) {
                    if (userChosePais.size() < 3) {
                        tag4 = "1";
                        img4.setTag(tag4);
                        img4.scrollBy(0, MOVE_FLAG);
                        userChosePais.add(mypailist1to10.get(3));
                    }
                } else {
                    tag4 = "0";
                    img4.setTag(tag4);
                    img4.scrollBy(0, 0 - MOVE_FLAG);
                    userChosePais.remove(new Integer(mypailist1to10.get(3)));
                }
                flushUserChoseNumbers();
                break;
            case R.id.img_pai5:

                String tag5 = (String) img5.getTag();
                //tag如果为0代表在下面，没有动过，1代表上移过了
                if (tag5.equals("0")) {
                    if (userChosePais.size() < 3) {
                        tag5 = "1";
                        img5.setTag(tag5);
                        img5.scrollBy(0, MOVE_FLAG);
                        userChosePais.add(mypailist1to10.get(4));
                    }
                } else {
                    tag5 = "0";
                    img5.setTag(tag5);
                    img5.scrollBy(0, 0 - MOVE_FLAG);
                    userChosePais.remove(new Integer(mypailist1to10.get(4)));
                }
                flushUserChoseNumbers();
                break;
            default:
                break;
        }

    }

    public void ishaveNIuOrNot(boolean haveorNot) {
        if (haveorNot) {
            BejinMessage msg = new BejinMessage();
            msg.setAct("haveornotniu");
            PrefUtils utils = new PrefUtils(NiuMorePeopleActivity.this, PrefUtils.user_messages);
            msg.setUserid(utils.getString(PrefUtils.USERID));
            msg.setZuoNum("1");
            msg.setResever1("have");
            Log.d("tag", "msg信息：" + msg.toString());
            client.sendMessage(JSON.toJSONString(msg));
        } else {
            BejinMessage msg = new BejinMessage();
            msg.setAct("haveornotniu");
            PrefUtils utils = new PrefUtils(NiuMorePeopleActivity.this, PrefUtils.user_messages);
            msg.setUserid(utils.getString(PrefUtils.USERID));
            msg.setZuoNum("1");
            msg.setResever1("no");
            client.sendMessage(JSON.toJSONString(msg));
        }
    }


    /**
     * 用于刷新用户在选择牌后显示在上面
     */
    private void flushUserChoseNumbers() {
        switch (userChosePais.size()) {
            case 0:
                tv_num1.setText("");
                tv_num2.setText("");
                tv_num3.setText("");
                tv_num4.setText("");
                break;
            case 1:
                tv_num1.setText(userChosePais.get(0) + "");
                tv_num2.setText("");
                tv_num3.setText("");

                tv_num4.setText(userChosePais.get(0) + "");
                break;
            case 2:
                tv_num1.setText(userChosePais.get(0) + "");
                tv_num2.setText(userChosePais.get(1) + "");
                tv_num3.setText("");
                tv_num4.setText((userChosePais.get(0) + userChosePais.get(1)) + "");
                break;
            case 3:
                tv_num1.setText(userChosePais.get(0) + "");
                tv_num2.setText(userChosePais.get(1) + "");
                tv_num3.setText(userChosePais.get(2) + "");
                tv_num4.setText((userChosePais.get(0) + userChosePais.get(1) + userChosePais.get(2)) + "");

                break;
            default:
                break;
        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("NiuMorePeople Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        AppIndex.AppIndexApi.start(client2, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client2, getIndexApiAction());
        client2.disconnect();
    }


    private class MyListener implements NettyHnalerListener {

        /**
         * 用于消息处理
         *
         * @param msg
         */
        @Override
        public void channelReadListener(final String msg) {
            JSONObject jso = JSON.parseObject(msg + "");
            Set<Map.Entry<String, Object>> map = jso.entrySet();
            String act = null;
            for (Map.Entry<String, Object> entry : map) {
                if (entry.getKey().equals("act")) {
                    act = (String) entry.getValue();
                }
            }
            switch (act) {
                case "onpeoplepai":
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            onpeoplepai(msg + "");
                        }
                    });

                    break;
                case "daojishi":
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            daojishi(msg + "");
                        }
                    });

                    break;
                case "allpeoplemessage":
                    //关闭倒计时
                    daojishi.setText("开牌时间");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    allpeoplemessage(msg + "");
                                }
                            });
                        }
                    }, 2000);


                    break;
                case "luozuo":
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            luozuo(msg + "");
                        }
                    });
                    break;
                case "sendgoldtoclient":
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            sendgoldtoclient(msg + "");
                        }
                    });
                    break;
                case "zhuangjia":
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            zhuangjia(msg + "");
                        }
                    });
                    break;
                default:
                    break;
            }


        }

    }


    /* 有新用户落座的时候
    *
            * @param s
    */
    private void sendgoldtoclient(String s) {
        AllpreparePeople allpeople = JSON.parseObject(s, AllpreparePeople.class);
        //所有list的集合
        ArrayList<UserGoldandname> list = allpeople.getList();
        PrefUtils pref = new PrefUtils(this, PrefUtils.user_messages);
//        myshelf.setVisibility(View.VISIBLE);
//        myshelf.setMessage("lv.1", pref.getString(PrefUtils.USERID) + "", pref.getint(PrefUtils.GOLD_NUM) + "");
//        myshelf.setUserid(Integer.parseInt(pref.getString(PrefUtils.USERID)));
//
//        list.remove(new Integer(Integer.parseInt(pref.getString(PrefUtils.USERID))));
        for (int i = 0; i < list.size(); i++) {
            switch (i) {
                case 0:
                    if (list.get(i).getUserid() == Integer.parseInt(pref.getString(PrefUtils.USERID))) {
                        myshelf.setVisibility(View.VISIBLE);
                        myshelf.setMessage(list.get(i).getUsername() + "", list.get(i).getUserid() + "", list.get(i).getGold() + "");
                        myshelf.setUserid(list.get(i).getUserid());
                    } else {
                        player1message.setVisibility(View.VISIBLE);
                        player1message.setMessage(list.get(i).getUsername() + "", list.get(i).getUserid() + "", list.get(i).getGold() + "");
                        player1message.setUserid(list.get(i).getUserid());
                    }
                    break;
                case 1:
                    if (list.get(i).getUserid() == Integer.parseInt(pref.getString(PrefUtils.USERID))) {
                        myshelf.setVisibility(View.VISIBLE);
                        myshelf.setMessage(list.get(i).getUsername() + "", list.get(i).getUserid() + "", list.get(i).getGold() + "");
                        myshelf.setUserid(list.get(i).getUserid());
                    } else {
                        player2message.setVisibility(View.VISIBLE);
                        player2message.setMessage(list.get(i).getUsername() + "", list.get(i).getUserid() + "", list.get(i).getGold() + "");
                        player2message.setUserid(list.get(i).getUserid());
                    }
                    break;
                case 2:
                    if (list.get(i).getUserid() == Integer.parseInt(pref.getString(PrefUtils.USERID))) {
                        myshelf.setVisibility(View.VISIBLE);
                        myshelf.setMessage(list.get(i).getUsername() + "", list.get(i).getUserid() + "", list.get(i).getGold() + "");
                        myshelf.setUserid(list.get(i).getUserid());
                    } else {
                        player3message.setVisibility(View.VISIBLE);
                        player3message.setMessage(list.get(i).getUsername() + "", list.get(i).getUserid() + "", list.get(i).getGold() + "");
                        player3message.setUserid(list.get(i).getUserid());
                    }
                    break;
                case 3:
                    if (list.get(i).getUserid() == Integer.parseInt(pref.getString(PrefUtils.USERID))) {
                        myshelf.setVisibility(View.VISIBLE);
                        myshelf.setMessage(list.get(i).getUsername() + "", list.get(i).getUserid() + "", list.get(i).getGold() + "");
                        myshelf.setUserid(list.get(i).getUserid());
                    } else {
                        player4message.setVisibility(View.VISIBLE);
                        player4message.setMessage(list.get(i).getUsername() + "", list.get(i).getUserid() + "", list.get(i).getGold() + "");
                        player4message.setUserid(list.get(i).getUserid());


                    }
                    break;
                default:
                    break;

            }
        }

        ArrayList<Integer> paiids1 = new ArrayList<>();
        paiids1.add(PaiUtils.getDrawableResIDByName(NiuMorePeopleActivity.this, "paibackground_niuniu"));
        paiids1.add(PaiUtils.getDrawableResIDByName(NiuMorePeopleActivity.this, "paibackground_niuniu"));
        paiids1.add(PaiUtils.getDrawableResIDByName(NiuMorePeopleActivity.this, "paibackground_niuniu"));
        paiids1.add(PaiUtils.getDrawableResIDByName(NiuMorePeopleActivity.this, "paibackground_niuniu"));
        paiids1.add(PaiUtils.getDrawableResIDByName(NiuMorePeopleActivity.this, "paibackground_niuniu"));

        pais1.setImags(paiids1);
        pais2.setImags(paiids1);
        pais3.setImags(paiids1);
        pais4.setImags(paiids1);


    }

    /**
     * 服务端选出的庄家
     *
     * @param s
     */
    private void zhuangjia(String s) {
        img_zhuangjia.setVisibility(View.VISIBLE);
        BejinMessage msg = JSON.parseObject(s, BejinMessage.class);
        //这个userid为庄家的id
        int userid = Integer.parseInt(msg.getUserid());
        Toast.makeText(this, "庄家为：" + userid, Toast.LENGTH_SHORT).show();


        //此处可以加个选庄的动画


        //获得控件需要移动到的位置

        int[] imglocations = new int[2];
        int[] loactions = new int[2];
        img_zhuangjia.getLocationOnScreen(imglocations);
        int bejinX = imglocations[0];
        int bejinY = imglocations[1];


        if (userid == myshelf.getUserid()) {

            myshelf.getLocationOnScreen(loactions);
        } else if (userid == player1message.getUserid()) {

            player1message.getLocationOnScreen(loactions);
        } else if (userid == player2message.getUserid()) {

            player2message.getLocationOnScreen(loactions);
        } else if (userid == player3message.getUserid()) {

            player3message.getLocationOnScreen(loactions);
        } else if (userid == player4message.getUserid()) {

            player4message.getLocationOnScreen(loactions);
        }
        int finishX = loactions[0];
        int finishY = loactions[1];


        AnimatorSet animset = new AnimatorSet();

        Log.i(LOG_TAG, "起始位置：-----" + bejinX + "--------" + bejinY);
        Log.i(LOG_TAG, "结束位置：-----" + finishX + "--------" + finishY);


        ObjectAnimator animatorX = ObjectAnimator.ofFloat(img_zhuangjia, "translationX", 0, finishX - bejinX);


        ObjectAnimator animatorY = ObjectAnimator.ofFloat(img_zhuangjia, "translationY", 0, finishY, -bejinY);
        animset.play(animatorX).with(animatorY);
        animset.setDuration(4000);
        animset.start();


    }


    /**
     * 用户离桌,用户在点击退出的时候调用
     */
    private void lizuo() {
        Log.d("tag", "用户离座");
        BejinMessage msg = new BejinMessage();
        msg.setAct("lizuo");
        PrefUtils utils = new PrefUtils(NiuMorePeopleActivity.this, PrefUtils.user_messages);
        msg.setUserid(utils.getString(PrefUtils.USERID));
        msg.setZuoNum("1");
        client.sendMessage(JSON.toJSONString(msg));

        NiuMorePeopleActivity.this.finish();
    }


    /**
     * 有新用户落座的时候
     *
     * @param s
     */
    private void luozuo(String s) {
        AllpreparePeople allpeople = JSON.parseObject(s, AllpreparePeople.class);
        //所有list的集合
        ArrayList<UserGoldandname> list = allpeople.getList();
        PrefUtils pref = new PrefUtils(this, PrefUtils.user_messages);
        myshelf.setVisibility(View.VISIBLE);
        myshelf.setMessage("lv.1", pref.getString(PrefUtils.USERID) + "", pref.getint(PrefUtils.GOLD_NUM) + "");
        myshelf.setUserid(Integer.parseInt(pref.getString(PrefUtils.USERID)));

        list.remove(new Integer(Integer.parseInt(pref.getString(PrefUtils.USERID))));
        for (int i = 0; i < list.size(); i++) {
            switch (i) {
                case 0:
                    if (list.get(i).getUserid() == Integer.parseInt(pref.getString(PrefUtils.USERID))) {
                        myshelf.setVisibility(View.VISIBLE);
                        myshelf.setMessage(list.get(i).getUsername() + "", list.get(i).getUserid() + "", list.get(i).getGold() + "");
                        myshelf.setUserid(list.get(i).getUserid());
                    } else {
                        player1message.setVisibility(View.VISIBLE);
                        player1message.setMessage(list.get(i).getUsername() + "", list.get(i).getUserid() + "", list.get(i).getGold() + "");
                        player1message.setUserid(list.get(i).getUserid());
                    }
                    break;
                case 1:
                    if (list.get(i).getUserid() == Integer.parseInt(pref.getString(PrefUtils.USERID))) {
                        myshelf.setVisibility(View.VISIBLE);
                        myshelf.setMessage(list.get(i).getUsername() + "", list.get(i).getUserid() + "", list.get(i).getGold() + "");
                        myshelf.setUserid(list.get(i).getUserid());
                    } else {
                        player2message.setVisibility(View.VISIBLE);
                        player2message.setMessage(list.get(i).getUsername() + "", list.get(i).getUserid() + "", list.get(i).getGold() + "");
                        player2message.setUserid(list.get(i).getUserid());
                    }
                    break;
                case 2:
                    if (list.get(i).getUserid() == Integer.parseInt(pref.getString(PrefUtils.USERID))) {
                        myshelf.setVisibility(View.VISIBLE);
                        myshelf.setMessage(list.get(i).getUsername() + "", list.get(i).getUserid() + "", list.get(i).getGold() + "");
                        myshelf.setUserid(list.get(i).getUserid());
                    } else {
                        player3message.setVisibility(View.VISIBLE);
                        player3message.setMessage(list.get(i).getUsername() + "", list.get(i).getUserid() + "", list.get(i).getGold() + "");
                        player3message.setUserid(list.get(i).getUserid());
                    }
                    break;
                case 3:
                    if (list.get(i).getUserid() == Integer.parseInt(pref.getString(PrefUtils.USERID))) {
                        myshelf.setVisibility(View.VISIBLE);
                        myshelf.setMessage(list.get(i).getUsername() + "", list.get(i).getUserid() + "", list.get(i).getGold() + "");
                        myshelf.setUserid(list.get(i).getUserid());
                    } else {
                        player4message.setVisibility(View.VISIBLE);
                        player4message.setMessage(list.get(i).getUsername() + "", list.get(i).getUserid() + "", list.get(i).getGold() + "");
                        player4message.setUserid(list.get(i).getUserid());


                    }
                    break;
                default:
                    break;

            }
        }
    }


    /**
     * 发送了所有的用户数据
     *
     * @param s
     */
    private void allpeoplemessage(String s) {
        //关闭倒计时
        daojishi.setText("");
        AllPeopleMessage msg = JSON.parseObject(s, AllPeopleMessage.class);
        ArrayList<OnePeoplePai> list = msg.getList();
        for (OnePeoplePai people : list) {
            if (people.getUserid() == myshelf.getUserid()) {
                img_niuji_shelf.setImageDrawable(ContextCompat.getDrawable(this, PaiUtils.getDrawableResIDByName(this, "ico_bull_n" + people.getNiuji())));
                img_niuji_shelf.setVisibility(View.VISIBLE);

            } else if (people.getUserid() == player1message.getUserid()) {
                ArrayList<Integer> paiids1 = new ArrayList<>();
                paiids1.add(PaiUtils.getdrawabelidfornumber(people.getList().get(0), NiuMorePeopleActivity.this));
                paiids1.add(PaiUtils.getdrawabelidfornumber(people.getList().get(1), NiuMorePeopleActivity.this));
                paiids1.add(PaiUtils.getdrawabelidfornumber(people.getList().get(2), NiuMorePeopleActivity.this));
                paiids1.add(PaiUtils.getdrawabelidfornumber(people.getList().get(3), NiuMorePeopleActivity.this));
                paiids1.add(PaiUtils.getdrawabelidfornumber(people.getList().get(4), NiuMorePeopleActivity.this));
                pais1.setImags(paiids1);
                img1_niuji.setImageDrawable(ContextCompat.getDrawable(this, PaiUtils.getDrawableResIDByName(this, "ico_bull_n" + people.getNiuji())));
                img1_niuji.setVisibility(View.VISIBLE);

            } else if (people.getUserid() == player2message.getUserid()) {
                ArrayList<Integer> paiids = new ArrayList<>();
                paiids.add(PaiUtils.getdrawabelidfornumber(people.getList().get(0), NiuMorePeopleActivity.this));
                paiids.add(PaiUtils.getdrawabelidfornumber(people.getList().get(1), NiuMorePeopleActivity.this));
                paiids.add(PaiUtils.getdrawabelidfornumber(people.getList().get(2), NiuMorePeopleActivity.this));
                paiids.add(PaiUtils.getdrawabelidfornumber(people.getList().get(3), NiuMorePeopleActivity.this));
                paiids.add(PaiUtils.getdrawabelidfornumber(people.getList().get(4), NiuMorePeopleActivity.this));
                pais2.setImags(paiids);
                img2_niuji.setImageDrawable(ContextCompat.getDrawable(this, PaiUtils.getDrawableResIDByName(this, "ico_bull_n" + people.getNiuji())));
                img2_niuji.setVisibility(View.VISIBLE);
            } else if (people.getUserid() == player3message.getUserid()) {
                ArrayList<Integer> paiids = new ArrayList<>();
                paiids.add(PaiUtils.getdrawabelidfornumber(people.getList().get(0), NiuMorePeopleActivity.this));
                paiids.add(PaiUtils.getdrawabelidfornumber(people.getList().get(1), NiuMorePeopleActivity.this));
                paiids.add(PaiUtils.getdrawabelidfornumber(people.getList().get(2), NiuMorePeopleActivity.this));
                paiids.add(PaiUtils.getdrawabelidfornumber(people.getList().get(3), NiuMorePeopleActivity.this));
                paiids.add(PaiUtils.getdrawabelidfornumber(people.getList().get(4), NiuMorePeopleActivity.this));
                pais3.setImags(paiids);
                img3_niuji.setImageDrawable(ContextCompat.getDrawable(this, PaiUtils.getDrawableResIDByName(this, "ico_bull_n" + people.getNiuji())));
                img3_niuji.setVisibility(View.VISIBLE);
            } else if (people.getUserid() == player4message.getUserid()) {
                ArrayList<Integer> paiids = new ArrayList<>();
                paiids.add(PaiUtils.getdrawabelidfornumber(people.getList().get(0), NiuMorePeopleActivity.this));
                paiids.add(PaiUtils.getdrawabelidfornumber(people.getList().get(1), NiuMorePeopleActivity.this));
                paiids.add(PaiUtils.getdrawabelidfornumber(people.getList().get(2), NiuMorePeopleActivity.this));
                paiids.add(PaiUtils.getdrawabelidfornumber(people.getList().get(3), NiuMorePeopleActivity.this));
                paiids.add(PaiUtils.getdrawabelidfornumber(people.getList().get(4), NiuMorePeopleActivity.this));
                pais4.setImags(paiids);
                img4_niuji.setImageDrawable(ContextCompat.getDrawable(this, PaiUtils.getDrawableResIDByName(this, "ico_bull_n" + people.getNiuji())));
                img4_niuji.setVisibility(View.VISIBLE);
            }

        }


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                linear_player_myshelf_pais.setVisibility(View.INVISIBLE);
                begin.setVisibility(View.VISIBLE);
                linear_niu_noniu.setVisibility(View.INVISIBLE);
                pais1.setVisibility(View.INVISIBLE);
                pais2.setVisibility(View.INVISIBLE);
                pais3.setVisibility(View.INVISIBLE);
                pais4.setVisibility(View.INVISIBLE);
                img1_niuji.setVisibility(View.INVISIBLE);
                img2_niuji.setVisibility(View.INVISIBLE);
                img3_niuji.setVisibility(View.INVISIBLE);
                img4_niuji.setVisibility(View.INVISIBLE);
                img_niuji_shelf.setVisibility(View.INVISIBLE);
                linear_numbers_sum.setVisibility(View.INVISIBLE);
                tv_num1.setText("");
                tv_num2.setText("");
                tv_num3.setText("");
                tv_num4.setText("");

                img_niuji_shelf.setVisibility(View.INVISIBLE);

                String tag = "0";
                String tag1 = (String) img1.getTag();
                if (tag1.equals("1")) {
                    img1.setTag(tag);
                    img1.scrollBy(0, 0 - MOVE_FLAG);
                }

                String tag2 = (String) img2.getTag();
                if (tag2.equals("1")) {
                    img2.setTag(tag);
                    img2.scrollBy(0, 0 - MOVE_FLAG);
                }
                String tag3 = (String) img3.getTag();
                if (tag3.equals("1")) {
                    img3.setTag(tag);
                    img3.scrollBy(0, 0 - MOVE_FLAG);
                }
                String tag4 = (String) img4.getTag();
                if (tag4.equals("1")) {
                    img4.setTag(tag);
                    img4.scrollBy(0, 0 - MOVE_FLAG);
                }

                String tag5 = (String) img5.getTag();
                if (tag5.equals("1")) {
                    img5.setTag(tag);
                    img5.scrollBy(0, 0 - MOVE_FLAG);
                }

                //结束，给服务器一个提示

                BejinMessage msg = new BejinMessage();
                msg.setAct("onegameover");
                PrefUtils utils = new PrefUtils(NiuMorePeopleActivity.this, PrefUtils.user_messages);
                msg.setUserid(utils.getString(PrefUtils.USERID));
                msg.setZuoNum("1");

                Log.d("tag", "msg信息：" + msg.toString());
                client.sendMessage(JSON.toJSONString(msg));

            }
        }, 5000);

        int[] imglocations = new int[2];
        img_zhuangjia.getLocationOnScreen(imglocations);

        int bejinx = imglocations[0];
        int bejiny = imglocations[1];


        WindowManager wm= NiuMorePeopleActivity.this.getWindowManager();
        int width= wm.getDefaultDisplay().getWidth()/2;
        int height= wm.getDefaultDisplay().getHeight()/2;


        img_zhuangjia.scrollBy(width-bejinx,height-bejiny);

    }

    /**
     * 处理倒计时
     *
     * @param s
     */
    private void daojishi(String s) {
        BejinMessage msg = JSON.parseObject(s, BejinMessage.class);
        int time = Integer.parseInt(msg.getResever1());
        Log.d("tag", "倒计时时间：" + time);

        if (time > 0) {
            daojishi.setText(time + "");
        }


    }
    //处理用户发来的数据

    private void onpeoplepai(String s) {
        //发牌后的页面变化
        linear_player_myshelf_pais.setVisibility(View.VISIBLE);
        linear_numbers_sum.setVisibility(View.VISIBLE);

        if (player1message.getVisibility() == View.VISIBLE) {
            pais1.setVisibility(View.VISIBLE);
        }
        if (player2message.getVisibility() == View.VISIBLE) {
            pais2.setVisibility(View.VISIBLE);
        }
        if (player3message.getVisibility() == View.VISIBLE) {
            pais3.setVisibility(View.VISIBLE);
        }
        if (player4message.getVisibility() == View.VISIBLE) {
            pais4.setVisibility(View.VISIBLE);
        }


        Log.d("tag", "数据:" + s);
        Toast.makeText(NiuMorePeopleActivity.this, "数据为:" + s, Toast.LENGTH_LONG).show();
        OnePeoplePai pai = JSON.parseObject(s, OnePeoplePai.class);
        PrefUtils utils = new PrefUtils(this, PrefUtils.user_messages);
        int userid = Integer.parseInt(utils.getString(PrefUtils.USERID));

        myPailist = pai.getList();
        mypailist1to10 = new ArrayList<>();
        for (Integer num : myPailist) {
            int onetoten = PaiUtils.get1to10(num);
            if (onetoten > 10) {
                onetoten = 10;
            }
            mypailist1to10.add(onetoten);
        }


        img1.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(myPailist.get(0), NiuMorePeopleActivity.this)));
        img2.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(myPailist.get(1), NiuMorePeopleActivity.this)));
        img3.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(myPailist.get(2), NiuMorePeopleActivity.this)));
        img4.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(myPailist.get(3), NiuMorePeopleActivity.this)));
        img5.setImageDrawable(getResources().getDrawable(PaiUtils.getdrawabelidfornumber(myPailist.get(4), NiuMorePeopleActivity.this)));
        Log.d("tag", "数据已经传入了");
        begin.setVisibility(View.INVISIBLE);
        linear_niu_noniu.setVisibility(View.VISIBLE);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}

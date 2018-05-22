package com.luosu.utils;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by User on 2017/3/10.
 */

public class DeZhouPuKeUtils {

    public static String getPaiMin(Context context, int number) {

        int i = number / 13;
        int j = number % 13;
        String drawablename = "";
        switch (i) {
            case 0:

                if (j == 0) {
                    break;
                } else if (j == 1) {
                    drawablename = "xin" + 14;
                } else {
                    drawablename = "xin" + j;
                }
                break;
            case 1:
                if (j == 0) {
                    drawablename = "xin" + 13;
                } else if (j == 1) {
                    drawablename = "fang" + 14;
                } else {
                    drawablename = "fang" + j;
                }

                break;
            case 2:
                if (j == 0) {
                    drawablename = "fang" + 13;
                } else if (j == 1) {
                    drawablename = "mei" + 14;
                } else {
                    drawablename = "mei" + j;
                }

                break;
            case 3:
                if (j == 0) {
                    drawablename = "mei" + 13;
                } else if (j == 1) {
                    drawablename = "tao" + 14;
                } else {
                    drawablename = "tao" + j;
                }
                break;

            case 4:
                drawablename = "tao" + 13;
                break;
        }

        return drawablename;
    }

    /**
     * @param number 得到不分花色的数字
     * @return
     */
    public static int getPaiHao(int number) {

        int i = number / 13;
        int j = number % 13;
        int num = 0;
        switch (i) {
            case 0:

                if (j == 0) {
                    break;
                } else if (j == 1) {
                    num = 14;
                } else {
                    num = j;
                }
                break;
            case 1:
                if (j == 0) {
                    num = 13;
                } else if (j == 1) {
                    num = 14;
                } else {
                    num = j;
                }

                break;
            case 2:
                if (j == 0) {
                    num = 13;
                } else if (j == 1) {
                    num = 14;
                } else {
                    num = j;
                }

                break;
            case 3:
                if (j == 0) {
                    num = 13;
                } else if (j == 1) {
                    num = 14;
                } else {
                    num = j;
                }
                break;

            case 4:
                num = 13;
                break;
        }

        return num;
    }

    /**
     * 牌的类型
     */
    public static void getPaiGenre(Context context, ArrayList<Integer> list) {
        ArrayList<String> pailistForHuase = new ArrayList<>();
        ArrayList<Integer> pailist = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 4; j++) {
                for (int x = j + 1; x < 5; x++) {
                    for (int y = x + 1; y < 6; y++) {
                        for (int z = y + 1; z < 7; z++) {
//存储排名用于判定
                            pailist.add(getPaiHao(list.get(i)));
                            pailist.add(getPaiHao(list.get(j)));
                            pailist.add(getPaiHao(list.get(x)));
                            pailist.add(getPaiHao(list.get(y)));
                            pailist.add(getPaiHao(list.get(z)));
                            //存储带花色的排名，用于判定同花情况
                            pailistForHuase.add(getPaiMin(context, list.get(i)));
                            pailistForHuase.add(getPaiMin(context, list.get(j)));
                            pailistForHuase.add(getPaiMin(context, list.get(x)));
                            pailistForHuase.add(getPaiMin(context, list.get(y)));
                            pailistForHuase.add(getPaiMin(context, list.get(z)));

                            switch (getFlag(pailist)) {
                                case 6:
//                                    金刚,4条

                                    break;
                                case 4:
                                    //葫芦,三条加一对
                                    break;
                                case 3:
                                    //三条
                                    break;
                                case 2:
                                    //两对
                                    break;
                                case 1:
                                    //一对
                                    break;
                                case 0:
                                    //高牌

                                    break;
                            }


                        }
                    }
                }
            }
        }

    }

    public static int getFlag(ArrayList<Integer> list) {
        int flag = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (list.get(i) == list.get(j)) {
                    flag++;
                }

            }
        }

        return flag;
    }


}

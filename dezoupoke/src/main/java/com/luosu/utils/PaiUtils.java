package com.luosu.utils;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by User on 2017/2/28.
 */

public class PaiUtils {
    /**
     * 通过1-52的数字获得drawableid
     *
     * @param number
     * @param context
     * @return
     */
    public static int getdrawabelidfornumber(int number, Context context) {

        int i = number / 13;
        int j = number % 13;
        String drawablename = "";
        switch (i) {
            case 0:

                if (j == 0) {
                    break;
                } else {
                    drawablename = "xin" + j;
                }
                break;
            case 1:
                if (j == 0) {
                    drawablename = "xin" + 13;
                } else {
                    drawablename = "fang" + j;
                }

                break;
            case 2:
                if (j == 0) {
                    drawablename = "fang" + 13;
                } else {
                    drawablename = "mei" + j;
                }

                break;
            case 3:
                if (j == 0) {
                    drawablename = "mei" + 13;
                } else {
                    drawablename = "tao" + j;
                }
                break;

            case 4:
                drawablename = "tao" + 13;
                break;
        }

        return getDrawableResIDByName(context, drawablename);
    }

    /**
     *
     * 得到不分花色的数字
     * @param number  是1-52里面的数字
     * @return
     */
    public static int get1to10(int number) {

        int i = number / 13;
        int j = number % 13;
        int num = 0;
        switch (i) {
            case 0:

                if (j == 0) {
                    break;
                } else {
                    num = j;
                }
                break;
            case 1:
                if (j == 0) {
                    num = 13;
                } else {
                    num = j;
                }

                break;
            case 2:
                if (j == 0) {
                    num = 13;
                } else {
                    num = j;
                }

                break;
            case 3:
                if (j == 0) {
                    num = 13;
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

    public static int getDrawableResIDByName(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    /**
     * 获得这副牌是牛几
     * @param list  手牌的集合，数字为1-52的集合中的数字
     * @return  牛几，为0-10的数字，0为无牛，10为牛牛
     */
    public static int paiisNiuJi(ArrayList<Integer> list) {
        //获得了一到10的数字集合
        ArrayList<Integer> paiNumber = new ArrayList<>();
        //牛几
        int number = 0;
        int pai = 0;
        // 剩下牌的和
        int sum = 0;
        for (Integer num : list) {
            pai = get1to10(num);
            if (pai > 10) {
                pai = 10;
            }
            paiNumber.add(pai);
        }
        Log.d("tag", paiNumber.get(0) + "---" + paiNumber.get(1) + "---" + paiNumber.get(2) + "---" + paiNumber.get(3) + "---" + paiNumber.get(4));
        for (int x = 0; x < 3; x++) {
            for (int y = x + 1; y < 4; y++) {
                for (int z = y + 1; z < 5; z++) {
//判定是否有牛
                    if ((paiNumber.get(x) + paiNumber.get(y) + paiNumber.get(z)) % 10 == 0) {
                        Log.d("tag", paiNumber.get(x) + "---" + paiNumber.get(y) + "---" + paiNumber.get(z));
                        //有牛的处理
                        for (int j = 0; j < paiNumber.size(); j++) {
                            if (j != x && j != y && j != z) {
                                sum += paiNumber.get(j);
                            }
                        }
                        Log.d("tag", "sum:" + sum);
                        //判定是否是牛10
                        if (sum % 10 == 0) {
                            number = 10;
                            return 10;
                        } else {
                            number = Math.max(number, sum % 10);
                        }
                        sum = 0;

                    } else {


                    }

                }
            }
        }
        return number;

    }

    public static ArrayList<Integer> qiege(ArrayList<Integer> list, int begin, int last) {
        ArrayList<Integer> sonList = new ArrayList<>();
        for (int i = begin; i < last; i++) {
            sonList.add(list.get(i));
        }
        return sonList;
    }

    public void combine(int[] a, int n) {

        if (null == a || a.length == 0 || n <= 0 || n > a.length)
            return;

        int[] b = new int[n];//辅助空间，保存待输出组合数
        getCombination(a, n, 0, b, 0);
    }

    private void getCombination(int[] a, int n, int begin, int[] b, int index) {

        if (n == 0) {//如果够n个数了，输出b数组
            for (int i = 0; i < index; i++) {
                System.out.print(b[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = begin; i < a.length; i++) {

            b[index] = a[i];
            getCombination(a, n - 1, i + 1, b, index + 1);
        }

    }
}

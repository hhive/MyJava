import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main
 */
public class Main {

    private int test = 0;

    public Main() {
        System.out.println(test);
    }

    /**
     * @param s The object of print
     */
    public static void myPrint(String s) {
        System.out.println(s);
    }

    /**
     *
     * @param arg  parameter for main
     */
    public static void main(String[] arg) {
        new GaiFeng().gaiFeng2();
    }
}
/**
 *
 */
class GaiFeng {
    /**
     *
     */
    public void gaiFeng() {
        int ave = 0;
        int index = 0;
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int num = Integer.parseInt(input);
        while (num > 0) {
            int count = 0;
            input = sc.nextLine();
            String[] inputs = input.split(" ");
            int totalNum = Integer.parseInt(inputs[0]);
            int requestAve = Integer.parseInt(inputs[1]);
            int[] scores = new int[totalNum];
            input = sc.nextLine();
            inputs = input.split(" ");
            for (int i = 0; i < totalNum; i++) {
                scores[i] = Integer.parseInt(inputs[i]);
            }
            Arrays.sort(scores);
            for (int x : scores) {
                System.out.println(x);
            }
//            for (int i = 0;i < totalNum; i++){
//                int max = 0;
//                for (int j = 1; j< totalNum - i; j++) {
//                    if (scores[j] > scores[max]) {
//                        max = j;
//                    }
//                }
//                if (scores[max] != scores[totalNum - 1 - i]) {
//                    int tmp = scores[max];
//                    scores[max] =  scores[totalNum - 1 - i];
//                    scores[totalNum - 1 - i] = tmp;
//                }
//            }
            while (true) {
                int totalScore = 0;
                for (int x : scores) {
                    totalScore += x;
                }
                ave = totalScore / totalNum;
                System.out.println(ave);
                if (ave < requestAve) {
                    scores[index++] = 100;
                    count++;
                } else {
                    break;
                }
            }
            System.out.println(count);
            num--;
        }
    }

    /**
     *from dalao
     */
    public void gaiFeng2() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        System.out.println(t);
        ArrayList<Integer> resList = new ArrayList<>();

        for (int i = 0; i < t; i++) {
            int count = 0;
            int n = in.nextInt();
            System.out.println(n);
            int X = in.nextInt();
            System.out.println(X);
            ArrayList<Integer> list = new ArrayList<>();

            for (int k = 0; k < n; k++) {
                list.add(in.nextInt());
            }
            Collections.sort(list);

            // 都成立的情况下
            if (averageOfList(list) >= X) {
                resList.add(count);
                continue;
            }

            // 不成立：开始尝试调整每个list
            for (int c = 0; c < list.size(); c++) {
                list.set(c, 100);
                count++;
                if (averageOfList(list) >= X) {
                    resList.add(count);
                    break;
                }
            }
        }
        if (resList.size() == 0) {
            return;
        }

        for (int i = 0; i < resList.size(); i++) {
            System.out.println(resList.get(i));
        }
    }

    /**
     *
     * @param list
     * @return
     */
    private static double averageOfList(ArrayList<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum / (double) list.size();
    }
}
/**
 *
 */
class QuJian {
    private int n,k,t;
    private int chongfu = 0;
    private int count = 0;
    /**
     *
     */
    public void myQuJian(){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] ss = s.split(" ");
        n = Integer.parseInt(ss[0]);
        k = Integer.parseInt(ss[1]);
        t = Integer.parseInt(ss[2]);
        int[] num = new int[n];
        s = sc.nextLine();
        ss = s.split(" ");
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(ss[i]);
        }
        for (int i = 0; i < n - k + 1; i++) {
            for (int j = i; j <= k - 1 + i; j++) {
                int tem = num[j];
                for (int z = j + 1; z <= k - 1 + i; z++) {
                    if (tem == num[z]) {
                        chongfu++;
                    }
                }
                if (chongfu >= t)
                    count++;
            }
        }
        System.out.println(count);
    }
}
/**
 * java 的链表 ；
 */
class BianLiWuXiangTu {
    /**
     *
     */
    public void my() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = br.readLine();
            int num = Integer.parseInt(s);
            String[] ss1 = new String[(num - 1) * 2];
            while (num > 1) {
                s = br.readLine();
                ss1 = s.split(" ");
                num--;
            }
            for (String x : ss1) {
                System.out.println(x);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

/**
 * cross
 */
class Cross {
    /**
     *
     */
    public void cross() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
//            Scanner sc = new Scanner(System.in);
//            String s = sc.nextLine();
//            String[] ss = s.split(" ");
            String s1 = br.readLine();
            String[] ss1 = s1.split(" ");
//            for (String x : ss) {
//                System.out.println(x);
//            }
            for (String x : ss1) {
                System.out.println(x);
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
/**
 * match child String
 */
class MatchString {
    /**
     *
     */
    public void matchString() {
        int count = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String p = br.readLine();
            Matcher m = Pattern.compile("a").matcher(p);
            Matcher m1 = Pattern.compile("aa").matcher(p);
            Matcher m2 = Pattern.compile("aaa").matcher(p);
            Matcher m3 = Pattern.compile("b").matcher(p);
            Matcher m4 = Pattern.compile("bb").matcher(p);
            if (m.find()) count++;
            if (m.find()) count++;
            if (m.find()) count++;
            if (m.find()) count++;
            if (m.find()) count++;
            System.out.println(count);
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}

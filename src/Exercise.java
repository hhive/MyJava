import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class Exercise {
    /**
     * @param arg
     */
    public static void main(String[] arg) {
        new Max().test();
//        new StringCatcher().print();
    }
}
/**
 *有 n 个学生站成一排，每个学生有一个能力值，
 * 牛牛想从这 n 个学生中按照顺序选取 k 名学生，
 * 要求相邻两个学生的位置编号的差不超过 d，使得这 k 个学生的能力值的乘积最大，你能返回最大的乘积吗？
 */
class Max {
    ArrayList<Integer> student = new ArrayList<>();
    public void test() {
        student.add(1);
        student.add(2);
        System.out.println(student.get(2));
        int i = Collections.binarySearch(student,1);
        Math.
    }
}
/**
 * 从第一个元素开始，按照元素的大小移动，
 * 正数后移，负数前移，0不移，看是否能越界，能true
 */
class YueJie {
    /**
     *
     */
    public void test() {
        System.out.println("请输入数组的大小：");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = sc.nextInt();
        }
        try {
            for (int i = 0; i < size; ) {
                i += a[i];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("true");
        }
    }
}

/**
 * A='youzan',B='zanyou',切割A为两部分并换位置再连接成B
 */
class StringCatcher {
    /**
     *
     */
    public void print() {
        int count = 0;
        boolean flag = false;
        String c = "aa";
//        StringBuilder b = new StringBuilder("zanyou");
        String b = "zanyou";
        StringBuilder a = new StringBuilder("youzan");
        while (!flag && count < a.length()) {
            char temp = a.charAt(0);
            a.deleteCharAt(0);
            a.append(temp);
            flag = a.toString().equals(b);
            count++;
        }
        System.out.println(flag);
        System.out.println(a);
        System.out.println(count);
    }
}

/**
 *
 */
class Kills {
    int i;
    List<Integer> kills = new ArrayList<>();
    List<Integer> store = new ArrayList<>();

    /**
     *
     */
    public void kills() {

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        for (int i = 0; i < num; i++) {
            kills.add(sc.nextInt());
        }
        System.out.println(kills);
        int count = 0;
        for (int i = 0; ; i = 0) {
            while (kills.size() > i + 1) {
                if (kills.get(i) > kills.get(i + 1)) {
                    store.add(kills.get(i + 1));
                    i++;
                } else {
                    i++;
                }

            }
            i = 0;
            if (store.size() == 0) {
                break;
            }
            while (store.size() > i) {
                //remove(int position) and remove(object object)
                kills.remove(store.get(i));
                i++;
            }
            store.clear();
            count++;
        }
        System.out.println(count);
    }

}

/**
 *
 */
class GaiFeng {
    /**
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
     * from dalao
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
}

/**
 *
 */
class QuJian {
    private int n, k, t;
    private int chongfu = 0;
    private int count = 0;

    /**
     *
     */
    public void myQuJian() {
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
        } catch (Exception e) {
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
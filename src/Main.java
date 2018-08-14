
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

import static java.lang.Thread.sleep;

public class Main {
    private int i = 0;

    public Main() {
        System.out.println(i);
    }

    public void my_print() {
        System.out.println("Test");
    }

    public static void main(String[] arg){

        new splitNumAndLetter().mySplit();

//        new MyArray().myprint();

//         Object[] a= {1,2,3,5,4,8,6,7};
//        perm b = new perm(a,1,8);
//        System.out.println( a[0]);
//        Statictest a = new Statictest(String.valueOf("1946"));
//        System.out.println(a.isBornBoomer());
//        System.out.println(String.valueOf("1946"));
    }
}


class splitNumAndLetter{
    String zifu = "ds123d4s4dqwAw57vv58gsTg578q93JNH21dsd2445";
    int[] num = new int[100];
    char[] letter = new char[100];
    int k = 0;
    int j = 0;
   public void mySplit(){
       for(int i = 0; i < zifu.length(); i++) {
           if (zifu.charAt(i) >= 'A' && zifu.charAt(i) <= 'z') {
               letter[k++] =zifu.charAt(i);
           }else{
               num[j++] = zifu.charAt(i)-48;
           }
       }
       for (int i = 0; i < k; i++){
           System.out.print(letter[i]);
       }
       System.out.println();
       for (int i = 0; i < j; i++){
           System.out.print(num[i]);
       }
//       System.out.println(Arrays.toString(letter));
//       System.out.println(Arrays.toString(num));
   }
}

//Array
class MyArray{
    int[] c = {1,2,3};
    int[][] d = {{1,2,3,4,5},
            {1,33,8,9,7}};
    String[][] str1 = new String[][]{new String[3], new String[]{"Hello"}};
    String[][] str2 = {new String[3],new String[]{"Hello"}};

    public void myprint(){
        String[] strings1 = {"Hello"};
        System.out.println(d[1][3]);
        int[] arr1 = {3,-4,25,16,30,18};
        //排序,利用了cpu并行提高性能
        Arrays.parallelSort(arr1);
        System.out.println(Arrays.toString(arr1));
        System.out.println(arr1[1] == 3);
        int[] arr2 = {3,-4,25,16,30,18};
        Arrays.parallelPrefix(arr2, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left*right;
            }
        });
        System.out.println(Arrays.toString(arr2));
        int[] arr3 = new int[5];
        Arrays.parallelSetAll(arr3, new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                return operand * 5;
            }
        });
        System.out.println(Arrays.toString(arr3));
    }

}

//排列问题
class perm{
    private int a;
    public void  perm(Object[] list,int k,int m) {

        if (k == m) {
            for (int i = 0; i <= m; i++)
                System.out.println(list[i]);
            System.out.println();
        }else {
            for (int i = k;i <= m;i++)
            {
                swap(list,k,i);
                perm(list,k+1,m);
                swap(list,k,i);
            }
        }

    }

    public void swap(Object[] list,int k,int i){
        Object temp = 0;
        temp = list[k];
        list[k] = list[i];
        list[i] = temp;

    }


}

//static
class StaticTest {
    private String birthString;
    private static String startString, endString;

    static {

        startString = String.valueOf("1946");
        endString = String.valueOf("1964");
    }

    public StaticTest(String birthString) {
        this.birthString = birthString;
    }

    boolean isBornBoomer() {
        return birthString.compareTo(startString) >= 0 && birthString.compareTo(endString) < 0;
    }
}
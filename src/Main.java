import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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


        // Object[] a= {1,2,3,5,4,8,6,7};
//        perm b = new perm(a,1,8);
//        System.out.println( a[0]);
//        test2 a = new test2(String.valueOf("1946"));
//        System.out.println(a.isBornBoomer());
//        System.out.println(String.valueOf("1946"));
    }
}




//排列问题
class perm{

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
class test2 {
    private String birthString;
    private static String startString, endString;

    static {

        startString = String.valueOf("1946");
        endString = String.valueOf("1964");
    }

    public test2(String birthString) {
        this.birthString = birthString;
    }

    boolean isBornBoomer() {
        return birthString.compareTo(startString) >= 0 && birthString.compareTo(endString) < 0;
    }
}
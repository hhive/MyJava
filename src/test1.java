

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import javax.swing.*;
import java.util.*;

/**
 * Main
 */
public class test1 {

    private int test = 0;

    public test1() {
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

//        new MyArray().myprint();
//        ParentClass a = new ChildClass(4, 5, 6);
//        ChildClass b = new ChildClass(1, 2, 3);
//        ((ChildClass) a).childFunction(); //true
//        a.childFunction(); //error
//        b.parentFunction(); //true
//         Object[] a= {1,2,3,5,4,8,6,7};
//        perm b = new perm(a,1,8);
//        System.out.println( a[0]);
//        Statictest a = new Statictest(String.valueOf("1946"));
//        System.out.println(a.isBornBoomer());
        //System.out.println(String.valueOf("1946"));
    }
}


/**
 *The test of super
 */
class ParentClass {
    private int i = 0;
    private int j = 0;

    /**
     * null constructor
     */
//    ParentClass(){

//    }
    /**
     *
     * @param i test
     * @param j test
     */
    ParentClass(int i, int j) {
        this.i = i;
        this.j = j;
    }
    /**
     * test
     */
    public void parentFunction() {
        System.out.println(1);
    }
}

/**
 *
 */
class  ChildClass extends ParentClass {
    private int k;
    ChildClass(int i, int j, int k) {
        //If the constructor of parent class has parameters(no empty constructor),
        //the child class must be explicitly called with "super"
        super(i, j);
        this.k = k;
    }

    /**
     * test
     */
    public void childFunction() {
        System.out.println(2);
    }
}




/**
 * //排列问题
 */
class Perm {
    private int a;

    /**
     *
     * @param list test
     * @param k test
     * @param m test
     */
    public void  perm1(Object[] list, int k, int m) {
        if (k == m) {
            for (int i = 0; i <= m; i++) {
                System.out.println(list[i]);
            }
            System.out.println();
        } else {
            for (int i = k; i <= m; i++) {
                swap(list, k, i);
                perm1(list, k + 1, m);
                swap(list, k, i);
            }
        }

    }

    /**
     *
     * @param list test
     * @param k test
     * @param i test
     */
    public void swap(Object[] list, int k, int i) {
        Object temp;
        temp = list[k];
        list[k] = list[i];
        list[i] = temp;

    }


}

/**
 * static
 */
class StaticTest {
    private String birthString;
    private static String startString, endString;

    static {

        startString = String.valueOf("1946");
        endString = String.valueOf("1964");
    }

    /**
     * @param birthString test
     */
    StaticTest(String birthString) {
        this.birthString = birthString;
    }

    /**
     * @return test
     */
    boolean isBornBoomer() {
        return birthString.compareTo(startString) >= 0 && birthString.compareTo(endString) < 0;
    }
}

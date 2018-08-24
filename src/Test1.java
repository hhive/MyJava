

import java.io.*;
import javax.swing.*;
import java.util.*;

/**
 * Main
 */
public class Test1 {

    private int test = 0;

    public Test1() {
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

        System.out.println(new String("xyz") == "xyz");
        System.out.println(new String("xyz") == new String("xyz"));
        System.out.println(new String("xyz") + "xyz");
        System.out.println(new String("xyz") + new String("xyz"));

//        new TransientTest().printForTransientWithStatic();

//        try{
//            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("flyPig.txt")));
//            oos.writeObject(new TestForFinal());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        new TestForFinal();
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
 * Use transient to not serialize a variable,
 * Note that when reading,the order of reading data must be
 * consistent with the order in which the data is stored
 * @author Alexia
 */
class TransientTest {

    /**
     *
     */
    public void printForTransientWithStatic() {

        User user = new User();
        user.setId("01");
        user.setUsername("Alexia");
        user.setPasswd("123456");
        user.setSex("male");
        user.setIdentity("student");

        System.out.println("read before Serializable: ");
        System.out.println("id:" + user.getId());
        System.out.println("username: " + user.getUsername());
        System.err.println("password: " + user.getPasswd());
        System.out.println("sex:" + user.getSex());
        System.out.println("identity:" + user.getIdentity());

        try {
            ObjectOutputStream os = new ObjectOutputStream(
                    new FileOutputStream("D:\\JavaProject\\MyJava\\Miscellaneous\\user.txt"));
            //Write the object of User to file
            os.writeObject(user);
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            //Change the value of username before Deserialize
            User.username = "wang";

            ObjectInputStream is = new ObjectInputStream(new FileInputStream(
                    "D:\\JavaProject\\MyJava\\Miscellaneous\\user.txt"));
            //Read the data of User from Stream
            user = (User) is.readObject();
            is.close();

            System.out.println("\nread after Serializable: ");
            System.out.println("id:" + user.getId());
            System.out.println("username: " + user.getUsername());
            System.err.println("password: " + user.getPasswd());
            System.err.println("sex: " + user.getSex());
            System.out.println("identity:" + user.getIdentity());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

/**
 *  test for Serializable
 */
class User implements Serializable {
    private static final long serialVersionUID = 8294180014912103005L;

    private String id;
    public static String username;
    private transient String password;
    private String sex;
    private String identity;

    /**
     *@return test
     */
    public String getId() {
        return id;
    }
    /**
     * @param id test
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     *
     * @return test
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username test
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return test
     */
    public String getPasswd() {
        return password;
    }

    /**
     *
     * @param password test
     */
    public void setPasswd(String password) {
        this.password = password;
    }

    /**
     *
     * @return test
     */
    public String getSex() {
        return sex;
    }
    /**
     * @param sex test
     */
    public  void setSex(String sex) {
        this.sex = sex;
    }
    /**
     * @return test
     */
    public String getIdentity() {
        return identity;
    }
    /**
     * @param identity test
     */
    public void setIdentity(String identity) {
        this.identity = identity;
    }

}
/**
 * test for final
 */
class TestForFinal {
    private int test = 0;
    /**
     *
     */
    TestForFinal() {
        String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        System.out.println(c);
        String e = d + 2;
        System.out.println(e);
        //Because of FINAL,in the place where b is used,b will be directly replaced with its value
        System.out.println(a == c);
        System.out.println(a == e);

        final String f = getHello();
        String g = f + 2;
        System.out.println(a == f);

        //After the reference variable is modified by FINAL,
        // it can no longer point to other object(change),
        // but the content of the object it points to is variable
        final MyClassForFinal h = new MyClassForFinal();
        System.out.println(++h.i);
        //The difference between STATIC and FINAL,STATIC variable can change
        MyClassForFinal myClass1 = new MyClassForFinal();
        MyClassForFinal myClass2 = new MyClassForFinal();
        System.out.println(myClass1.k);
        System.out.println(myClass1.j);
        System.out.println(myClass2.k);
        System.out.println(myClass2.j);
        final StringBuilder builder = new StringBuilder("hello");
        builder.append("world");
        System.out.println(builder);
        //builder = new StringBuilder("good");
    }

    /**
     *
     * @return test
     */
    public static String getHello() {

       return "hello";
    }


}
/**
 *
 */
class MyClassForFinal {
    public static double k = Math.random();
    final double j = Math.random();
    int i = 0;
}
///**
// * test for split and char
// */
//class SplitAndChar{
//    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    /**
//     *
//     */
//    SplitAndChar(){
//        try{
//            String in = br.readLine();
//            char[] Sp = in.split(",");
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//}
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


/**
 * The algorithm of chess with robot
 * https://www.cnblogs.com/DevLegal/p/8831894.html
 */
//public interface IRobot {
//    static final Random rand = new Random();
//
//    /**
//     * There we provide a default implementation to simulate robot's behavior
//     *
//     * @return a {@code robot.Pair} which contains a valid (x,y) position
//     */
//    default ChessWithRobot.Pair getDeterminedPos() {
//        return new ChessWithRobot.Pair(rand.nextInt(15) + 1, rand.nextInt(15) + 1);
//    }
//
//    /**
//     * This method is used to retrieve game board such that robot can determine its (x,y) position
//     * @param gameBoard the 2-dimension array to represent the game board
//     */
//    void retrieveGameBoard(int[][] gameBoard);
//}
///**
// * The algorithm of chess with robot
// */
//class Pair {
//    public int x;
//    public int y;
//
//    Pair() {}
//    Pair(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//}
///**
// * The algorithm of chess with robot
// */
//class StupidRobot implements IRobot {
//    private static final int BOARD_SIZE = 15;
//    private static final int ROLE_OPPONENT = 1;
//    private static final int ROLE_ROBOT = 2;
//    private static final int ROLE_NON = 0;
//    private static final int ORIENTATION_LR = 0;
//    private static final int ORIENTATION_UD = 1;
//    private static final int ORIENTATION_LT_RD = 2;
//    private static final int ORIENTATION_RT_LD = 3;
//
//    private int[][] boardRef = null;
//
//
//    /**
//     * There we provide a default implementation to simulate robot's behavior
//     *
//     * @return a {@code robot.Pair} which contains a valid (x,y) position
//     */
//    @Override
//    public Pair getDeterminedPos() {
//        int[][] situationRobot  = new int[boardRef.length][boardRef[0].length];
//        int[][] situationOpponent  = new int[boardRef.length][boardRef[0].length];
//
//        int maxRobotScore = 0;
//        Pair maxRobotPoint = new Pair();
//
//        int maxOpponentScore = 0;
//        Pair maxOpponentPoint = new Pair();
//        for(int i=0;i<BOARD_SIZE;i++){
//            for(int k=0;k<BOARD_SIZE;k++){
//                if(boardRef[i][k]!=ROLE_NON){
//                    situationOpponent[i][k]=situationRobot[i][k]=0;
//                }else{
//                    boardRef[i][k] = ROLE_OPPONENT;
//                    situationOpponent[i][k] = evaluateScore(ROLE_OPPONENT,i,k);
//                    boardRef[i][k]=ROLE_NON;
//                    if(situationOpponent[i][k]>maxOpponentScore){
//                        maxOpponentScore = situationOpponent[i][k];
//                        maxOpponentPoint.x = i;
//                        maxOpponentPoint.y = k;
//                    }
//
//                    boardRef[i][k]=ROLE_ROBOT;
//                    situationRobot[i][k]=evaluateScore(ROLE_ROBOT,i,k);
//                    boardRef[i][k]=ROLE_NON;
//                    if(situationRobot[i][k]>maxRobotScore){
//                        maxRobotScore = situationRobot[i][k];
//                        maxRobotPoint.x = i;
//                        maxRobotPoint.y = k;
//                    }
//
//                }
//            }
//        }
//        if(maxRobotScore > maxOpponentScore || maxRobotScore==Integer.MAX_VALUE){
//            return maxRobotPoint;
//        }else{
//            return maxOpponentPoint;
//        }
//    }
//
//    /**
//     * This method is used to retrieve game board such that robot can determine its (x,y) position
//     *
//     * @param gameBoard the 2-dimension array to represent the game board
//     */
//    @Override
//    public void retrieveGameBoard(int[][] gameBoard) {
//        boardRef = gameBoard;
//    }
//
//
//    /**
//     * The policy of evaluating was referred to https://www.cnblogs.com/maxuewei2/p/4825520.html
//     * @param role the role of current player
//     * @param x position x
//     * @param y position y
//     * @param orientation orientation of determining line
//     * @return
//     */
//    private int patternRecognition(int role, int x,int y,int orientation){
//        StringBuilder sb = new StringBuilder();
//        if(orientation==ORIENTATION_LR){
//            int leftBound = (x - 4)>=0?x-4:0;
//            int rightBound = (x +4)<BOARD_SIZE?x+4:BOARD_SIZE-1;
//
//            for(int i=leftBound;i<=rightBound;i++){
//                sb.append(boardRef[i][y]);
//            }
//        }else if(orientation == ORIENTATION_UD){
//            int bottomBound = (y+4)<BOARD_SIZE?y+4:BOARD_SIZE-1;
//            int topBound = (y-4)>=0?y-4:0;
//
//            for(int i=topBound;i<=bottomBound;i++){
//                sb.append(boardRef[x][i]);
//            }
//        }else if(orientation== ORIENTATION_LT_RD){
//            int leftBound = 0,rightBound = 0,bottomBound = 0,topBound = 0;
//
//            for(int i=1;i<=4;i++){
//                leftBound = x-i;
//                topBound = y-i;
//                if(leftBound<0||topBound<0){
//                    leftBound++;
//                    topBound++;
//                    break;
//                }
//            }
//            for(int k=1;k<=4;k++){
//                rightBound = x+k;
//                bottomBound = y+k;
//                if(rightBound>BOARD_SIZE||bottomBound>BOARD_SIZE){
//                    rightBound--;
//                    bottomBound--;
//                    break;
//                }
//            }
//            for(int i=topBound,k=leftBound;i<=bottomBound && k<=rightBound;i++,k++){
//                sb.append(boardRef[k][i]);
//            }
//        }else if(orientation== ORIENTATION_RT_LD){
//            int leftBound = 0,rightBound = 0,bottomBound = 0,topBound = 0;
//
//            for(int i=1;i<=4;i++){
//                rightBound = x+i;
//                topBound = y-i;
//                if(rightBound>BOARD_SIZE||topBound<0){
//                    rightBound--;
//                    topBound++;
//                    break;
//                }
//            }
//            for(int k=1;k<=4;k++){
//                leftBound = x-k;
//                bottomBound = y+k;
//                if(leftBound<0||bottomBound>BOARD_SIZE){
//                    leftBound++;
//                    bottomBound--;
//                    break;
//                }
//            }
//
//            for(int i=topBound,k=rightBound;i<=bottomBound && k>=leftBound;i++,k--){
//                sb.append(boardRef[k][i]);
//            }
//        }
//        String str = sb.toString();
//        if(str.contains(role == ROLE_ROBOT ? "22222" : "11111")){
//            return Integer.MAX_VALUE;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "022220" : "011110")){
//            return 300000;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "22202" : "11101") ||
//                str.contains(role == ROLE_ROBOT ? "20222" : "10111")){
//            return 3000;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "0022200" : "0011100")){
//            return 3000;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "22022" : "11011")){
//            return 2600;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "22220" : "11110")||
//                str.contains(role == ROLE_ROBOT ? "02222" : "01111")){
//            return 2500;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "020220" : "010110")||
//                str.contains(role == ROLE_ROBOT ? "022020" : "011010")){
//            return 800;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "00022000" : "00011000")){
//            return 650;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "20022" : "10011")||
//                str.contains(role == ROLE_ROBOT ? "22002" : "11001")){
//            return 600;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "20202" : "10101")){
//            return 550;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "22200" : "11100")||
//                str.contains(role == ROLE_ROBOT ? "00222" : "00111")){
//            return 500;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "0020200" : "0010100")){
//            return 250;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "020020" : "010010")){
//            return 200;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "22000" : "11000")||
//                str.contains(role == ROLE_ROBOT ? "00022" : "00011")){
//            return 150;
//        }
//        return 0;
//    }
//
//    private int evaluateScore(int role,int x, int y){
//        int a = patternRecognition(role,x,y,ORIENTATION_RT_LD);
//        int b = patternRecognition(role,x,y,ORIENTATION_LT_RD);
//        int c = patternRecognition(role,x,y,ORIENTATION_UD);
//        int d = patternRecognition(role,x,y,ORIENTATION_LR);
//        return Math.max(Math.max(Math.max(a,b),c),d);
//    }
//}

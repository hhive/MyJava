
import javax.swing.*;
import java.util.*;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class Main {
    private int i = 0;

    public Main() {
        System.out.println(i);
    }

    public void my_print() {
        System.out.println("Test");
    }

    public static void main(String[] arg){

//        LambdaQs lambdaQs = new LambdaQs();
//        lambdaQs.PrintForLam(lambdaQs);

        MyCollection myCollection = new MyCollection();
        myCollection.PrintForCollection();
        myCollection.PrintForStream();
        //myCollection.PrintForIterator();

//        new splitNumAndLetter().mySplit();

//        new MyArray().myprint();

//         Object[] a= {1,2,3,5,4,8,6,7};
//        perm b = new perm(a,1,8);
//        System.out.println( a[0]);
//        Statictest a = new Statictest(String.valueOf("1946"));
//        System.out.println(a.isBornBoomer());
//        System.out.println(String.valueOf("1946"));
    }
}

//Set is basically the same as Collection,
// but Set dose not allow duplicate elements
class MySet{

}

class LambdaQs{
    interface Eatable{
        void taste();
    }
    interface Flyable{
        void fly(String weather);
    }
    interface Addable{
        int add(int a, int b);
    }

    @FunctionalInterface
    interface Converter{
        Integer convert(String from);
    }

    @FunctionalInterface
    interface MyTest{
        String test(String a , int b , int c );
    }

    @FunctionalInterface
    interface YourTest{
        JFrame win(String title);
    }

    public void eat(Eatable e){
        //输出对象e
        System.out.println(e);
        e.taste();
    }
    public void drive(Flyable f){
        System.out.println("我开：" + f);
        f.fly("晴天");
    }
    public void toAdd(Addable add){
        System.out.println("5+3:" + add.add(5,3));
    }

    public void PrintForLam(LambdaQs lq){

        lq.eat(()->System.out.println("苹果味道好！"));
        lq.drive(weather -> {
            System.out.println("今天天气是：" + weather);
            System.out.println("直升机飞行平稳");
        });
        lq.toAdd((a, b)->a + b);
        //使用函数式接口对Lambda表达式进行强制类型转换
        Object obj = (Runnable)()->{
            for (int i = 0; i < 5; i++){
                System.out.println();
            }
        };
    }
    public void PrintforLamRefer(){
        Converter converter = from -> Integer.valueOf(from);
        Converter converter1 = Integer::valueOf;
        Integer val = converter.convert("99");
        //方法引用替代Lamdba表达式：引用类方法
        //函数式接口中被实现方法的全部参数传给该类方法作为参数
        System.out.println(val);

        Converter converter2 = from -> "fkit.org".indexOf(from);
        Converter converter3 = "fkit.org"::indexOf;
        //indexOf 方法返回一个整数值，指出 String 对象内子字符串的开始位置。如果没有找到子字符串，则返回-1。
        Integer value = converter3.convert("it");
        System.out.println(value);

        YourTest yt = (String a) -> new JFrame(a);
        YourTest yt1 = JFrame::new;
        JFrame jf = yt.win("我的窗口");
        System.out.println(jf);

        MyTest mt = (a,b,c)->a.substring(b,c);
        MyTest mt1 = String::substring;
        String str = mt.test("Java I Love you" , 2 , 9);
        System.out.println(str);
    }
}

class MyCollection{
    Collection c = new ArrayList();
    Collection books = new HashSet();

    public void PrintForCollection(){
        c.add("孙悟空");
        c.add(6);
        System.out.println("c的元素数：" + c.size());
        c.remove(6);
        System.out.println("c中是否包含“孙悟空”字符串：" + c.contains("孙悟空"));
        c.add("Java");
        System.out.println("c集合的元素：" + c);

        books.add("Java");
        books.add("Android");
        System.out.println("c集合是否完全包含books集合？" + c.containsAll(books));
        //用c集合减去books集合里的元素
        c.removeAll(books);
        System.out.println("c中的元素：" + c);
        c.clear();
        System.out.println("c中的元素：" + c);
        //控制books集合中只剩下c集合里也包含的元素
        books.retainAll(c);
        System.out.println("books集合的元素：" + books);
        books.add("q");
        books.add("a");
        books.add("w");
        for (Object obj : books){
            System.out.println(obj);
        }
    }
    public void PrintForIterator(){
        books.add("q");
        books.add("a");
        books.add("w");
        //使用Lambda表达式，调用forEach()方法遍历集合
        books.forEach(obj->System.out.println("迭代集合元素：" + obj));
        Iterator it = books.iterator();
        System.out.println(it.hasNext());
        while ( it.hasNext()){
            //it.next()返回的是Object类型
            String book = (String)it.next();
            System.out.println("book:" + book);
            //使用Iterator迭代过程中，不可修改集合元素，类似books.remove(book)
            if (book.equals("a")){
                it.remove();
            }
            book = "b";
        }
        System.out.println(books);
    }
    public void PrintForStream(){
        //stream()将集合转换成Stream
        System.out.println(books.stream()
        .filter(ele->((String)ele).contains("Java"))
        .count());
        System.out.println(books.stream()
        .filter(ele->((String)ele).length() > 10)
                .count());
        //mapToInt()获取原有的Stream对应的IntStream
        books.stream().mapToInt(ele -> ((String)ele).length())
                .forEach(System.out::println);
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
        //Arrays.parallelPrefix(arr2, (left,right)->left * right);
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
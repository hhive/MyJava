
public class Main {
    int i = 0;
    public Main() {
        System.out.println(i);
    }
    public void print(){
        System.out.println("Test");
    }

    public static void main(String[] arg) {
        //main是静态的，为什么可以在这里面new不是静态的类（包括自身的类）？因为构造函数（构造器）是静态的吗？
        //和new a = new Main();有什么去区别？
        //new Main();
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (20 == i) {
                //共享k
                MyThread2 a = new MyThread2();
                new Thread(a, "新线程1").start();
                new Thread(a, "新线程2").start();
            }

//        for (int i = 0; i<100 ;i++){
//            System.out.println(Thread.currentThread().getName() + " " + i);
//            if(20 == i){
                  //不共享k
//                new MyThread1().start();
//                new MyThread1().start();
//            }
//        }
//        production a = new production();
//        new Thread(a).start();
            // Object[] a= {1,2,3,5,4,8,6,7};
//        perm b = new perm(a,1,8);
//        System.out.println( a[0]);
//        test2 a = new test2(String.valueOf("1946"));
//        System.out.println(a.isBornBoomer());
//        System.out.println(String.valueOf("1946"));
        }
    }
}

//多线程,implements Runnable
class MyThread2 implements Runnable{
    private int k;
    public void run(){
        for (;k<100;k++){
            System.out.println(Thread.currentThread().getName()
            + " " + k);
        }
    }
}

//多线程，extends Tread
class MyThread1 extends Thread{
    private int k;
    public void run(){
        try{
            for (; k < 100; k++){
                System.out.println(getName() + " " + k);
                //有sleep()就要try catch
                sleep(10);
             }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}


class production implements Runnable{
    int MAX_PRODUCT = 100;
    int MIN_PRODUCT = 0;
    volatile int product = 0;

    public void run(){
        while (true){
            product();
            consume();
        }
    }
    public synchronized void product(){
        if(this.product >= MAX_PRODUCT){
            try {
                wait();
                System.out.println("产品已满");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return;
        }
        this.product++;
        System.out.println("生产了"+this.product+"个产品");
        notifyAll();
    }

    public synchronized void consume(){
        if (this.product <= MIN_PRODUCT){
            try {
                wait();
                System.out.println("缺货");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return;
        }
        System.out.println("消费了"+this.product+"个产品");
        this.product--;
        notifyAll();
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
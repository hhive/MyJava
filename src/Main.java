import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

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
//    	//多线程Callable，FutureTask
//    	FutureTask<Integer> task = new FutureTask<Integer>((Callable<Integer>)()->{
//    	    //call()
//    		int i = 0;
//    		for (; i < 100; i++)
//              System.out.println(Thread.currentThread().getName() + "的循环变量i的值：" + i);
//    		return i;
//    	});
//    	for(int i = 0; i<100 ; i++){
//    	    System.out.println(Thread.currentThread().getName() + "的循环变量i的值："+ i);
//            if (20 == i) {
//            	  new Thread(task,"有返回值的线程").start();
//	    	}
//	    }
//	    try {
//            System.out.println("子线程的返回值：" + task.get());
//        }catch (Exception e) {
//            e.printStackTrace();
//        }

//        for (int i = 0; i < 100; i++) {
//            System.out.println(Thread.currentThread().getName() + " " + i);
//            if (20 == i) {
//                try {
//                    //共享k
//                    MyThread2 a = new MyThread2();
////                  new Thread(a, "新线程1").start();
////                  new Thread(a, "新线程2").start();
//                    Thread t1 = new Thread(a, "新线程1");
//                    Thread t2 = new Thread(a, "新线程2");
//                    t2.start();
//                    t1.start();
//                    t1.join();
//                    t2.sleep(1);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//        }

        for (int i = 0; i<100 ;i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
            if(20 == i){
                try{
                    //不共享k
                   // new MyThread1("低级").start();
                    MyThread1 t1 =  new MyThread1("高级");
                    MyThread1 t2 =  new MyThread1("低级");
                    t1.setPriority(Thread.MAX_PRIORITY);
                    t1.start();
                    t2.setPriority(Thread.MIN_PRIORITY);
                    t2.start();
                   // t1.join();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }

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


//多线程，Callable,future


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
    public MyThread1(String name){
        super(name);
    }
    public void run(){
        try{
            for (; k < 100; k++){
                System.out.println(getName() + " " + k);
                //有sleep()抛出InterruptedException
                sleep(10);
                if (20 == k){
                    Thread.yield();
                }
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
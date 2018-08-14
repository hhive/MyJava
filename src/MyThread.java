import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class MyThread {
    private int i = 0;

    public MyThread() {
        System.out.println(i);
    }

    public void my_print() {
        System.out.println("Test");
    }

    public static void main(String[] arg){

//        Park park = new Park();
//        ParkThread1 parkThread1 = new ParkThread1(park);
//        new Thread(parkThread1).start();
//        new Thread(parkThread1).start();
//        new Thread(parkThread1).start();
//        new Thread(parkThread1).start();
//        new Thread(parkThread1).start();
//        new Thread(parkThread1).start();
//        new Thread(parkThread1).start();
//        new ParkThread1(park).start();
//        new ParkThread1(park).start();
//        new ParkThread1(park).start();
//        new ParkThread1(park).start();
//        NumAndLetter a = new NumAndLetter();
//        new NALThread1(a).start();
//        new NALThread2(a).start();

//        //线程池
//            throws Exception{
//        int[] arr = new int[100];
//        Random rand = new Random();
//        int total = 0;
//        for (int i =0 , len = arr.length; i < len ; i++){
//             int tmp = rand.nextInt(20);
//             total += (arr[i] = tmp);
//        }
//        System.out.println(total);

//        //创建一个通用池
//        ForkJoinPool pool = ForkJoinPool.commonPool();
//        Future<Integer> future = pool.submit(new CalTask(arr,0,arr.length) );
//        System.out.println(future.get());
//        pool.shutdown();

//          ForkJoinPool pool = new ForkJoinPool();
//          pool.submit(new PrintTask(0, 300));
//          pool.awaitTermination(2, TimeUnit.SECONDS);
//          pool.shutdown();
//            throws Exception {
//        //创建一个固定线程数为6的线程池
//        ExecutorService pool = Executors.newFixedThreadPool(6);
//        //使用lambda表达式创建Runable对象
//        Runnable target = () -> {
//            for (int i = 0; i < 100; i++) {
//                System.out.println(Thread.currentThread().getName() + "的i值为：" + i);
//            }
//        };
//        //向池提交两个线程
//        pool.submit(target);
//        pool.submit(target);
//        //关闭线程池
//        pool.shutdown();

//         Production production = new Production();
//         new production_Thread1("生产者",production).start();
//         new production_Thread2("消费者1",production).start();
//         new production_Thread2("消费者2",production).start();
//         new production_Thread2("消费者3",production).start();

//        Account acct = new Account("1234567",1000);
//        new DrawThread("甲",acct,800).start();
//        new DrawThread("乙",acct,800).start();

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

        //Runnable
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
        //继承Thread
//        for (int i = 0; i<100 ;i++){
//            System.out.println(Thread.currentThread().getName() + " " + i);
//            if(20 == i){
//                try{
//                    //不共享k
//                   // new MyThread1("低级").start();
//                    MyThread1 t1 =  new MyThread1("高级");
//                    MyThread1 t2 =  new MyThread1("低级");
//                    t1.setPriority(Thread.MAX_PRIORITY);
//                    t1.start();
//                    t2.setPriority(Thread.MIN_PRIORITY);
//                    t2.start();
//                   // t1.join();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
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

class ParkThread1 implements Runnable{
    private Park park;

    public ParkThread1(Park park){
        this.park = park;
    }

    public void run(){
        for (int i = 0; i < 100; i++)
            park.parking();
    }
}
class Park{
    //    private boolean[] sites = new boolean[3];
    //true可以停车，false不可以
    private boolean[] sites = {true,true,true};
    private int num = 0;

    public synchronized void parking(){
        //for循环放这里跟放ParkThread1的run里面不一样
        boolean flag = false;
        int k = 0;
        int i = 0;
        try {
            for (; i < 3; i++){
                if(true == sites[i]){
                    k = i;
                    System.out.println(Thread.currentThread().getName() + "停进了" + (i+1) + "号位");
                    num++;
                    sites[k] = false;
                    flag = true;
                    break;
                }
            }
            notify();
            wait();
            if(false == flag){
                System.out.println(Thread.currentThread().getName() + "无法进入，车位已满，请稍后");
                notify();
                wait();
            }else {
                sleep(new Random().nextInt(10000) + 1000);
                System.out.println(Thread.currentThread().getName() + "离开了" + (i + 1) + "号位");
                num--;
                sites[k] = true;
                notifyAll();
                wait();
                sleep(new Random().nextInt(10000) + 1000);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}


class NALThread1 extends Thread{
    NumAndLetter numAndLetter;

    public NALThread1(NumAndLetter numAndLetter){
        this.numAndLetter = numAndLetter;
    }
    public void run(){
        while (true){
            if(!numAndLetter.num())
                break;
        }
    }
}
class NALThread2 extends Thread{
    private NumAndLetter numAndLetter;

    public NALThread2(NumAndLetter numAndLetter){
        this.numAndLetter = numAndLetter;
    }
    public void run(){
        while (true){
            if(!numAndLetter.letter())
                break;
        }
    }
}
class NumAndLetter{
    private int i = 1;
    private int num = i + 2;
    private int k = 65;
    public synchronized boolean num(){
        try {
            if (i <= 52){
                System.out.print(i);
                System.out.print(i+1);
                i = num;
                num = i + 2;
                notifyAll();
                wait();
            }else
                return false;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return true;
    }
    public synchronized boolean letter(){
        try {
            if (k<91){
                System.out.print((char)(k++));
                notifyAll();
                wait();
            }else
                return false;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return true;
    }

}


//继承RecursiveTask<Integer>来实现“可分解”的任务（有返回值）
class CalTask extends RecursiveTask<Integer>{
    //每个小任务最多打印50个
    private static final int THRESHOLD = 20;
    private int arr[];
    private int start;
    private int end;
    public CalTask(int[] arr, int start, int end){
        this.arr = arr;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute(){
        int sum = 0;
        if(end - start < THRESHOLD){
            for (int i = start ; i < end ;i++) {
                sum += arr[i];
            }
            return sum;
        }else {
            int middle = (start + end) / 2;
            CalTask left = new CalTask(arr,start, middle);
            CalTask right = new CalTask(arr, middle, end);
            left.fork();
            right.fork();
            return left.join()+right.join();
        }
    }
}

//继承RecursiveAction来实现“可分解”的任务（无返回值）
class PrintTask extends RecursiveAction{
    //每个小任务最多打印50个
    private static final int THRESHOLD = 50;
    private int start;
    private int end;
    public PrintTask(int start, int end){
        this.start = start;
        this.end = end;
    }
    @Override
    protected void compute(){
        if(end - start < THRESHOLD){
            for (int i = start ; i < end ;i++){
                System.out.println(Thread.currentThread().getName() + "的i值：" + i);
            }
        }else {
            int middle = (start + end) / 2;
            PrintTask left = new PrintTask(start, middle);
            PrintTask right = new PrintTask(middle, end);
            left.fork();
            right.fork();
        }
    }
}


//传统线程通信+condition
class production_Thread1 extends Thread{
    private Production production;

    public production_Thread1(String name,Production production){
        super(name);
        this.production = production;
    }

    public void run(){
        for (int i = 0; i<100 ;i++)
            production.product();
    }
}
class production_Thread2 extends Thread{
    private Production production;

    public production_Thread2(String name,Production production){
        super(name);
        this.production = production;
    }

    public void run(){
        for (int i = 0; i<100 ;i++)
            production.consume();
    }
}
class Production {

//    private final Lock lock = new ReentrantLock();
//    private final Condition condition = lock.newCondition();

    int MAX_PRODUCT = 100;
    int MIN_PRODUCT = 0;
    volatile int product = 0;

    public synchronized void product() {
        //lock和condition
        //lock.lock();
        try {
            if (this.product >= MAX_PRODUCT) {
                //condition.await();
                wait();
                System.out.println("产品已满");
                return;
            }
            this.product++;
            System.out.println(Thread.currentThread().getName() + "生产了" + this.product + "个产品");
            notifyAll();
            //condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        finally {
//            lock.unlock();
//        }
    }

    public synchronized void consume() {
        //lock.lock();
        try {
            if (this.product <= MIN_PRODUCT) {
                // condition.await();
                System.out.println("缺货");
                wait();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "消费了第" + this.product + "个产品");
            this.product--;
            notifyAll();
            //condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
//        }finally {
//            lock.unlock();
//        }
        }
    }
}


//synchronized(obj)
class Account{
    private String accountNo;
    private double balance;
    private double drawAmount;
    public Account(){ }

    public Account(String accountNo,double balance){
        this.accountNo = accountNo;
        this.balance  = balance;
    }

    public void setAccountNo(String accountNo){
        this.accountNo = accountNo;
    }

    public String getAccountNo(){
        return accountNo;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public synchronized void draw(double drawAmount){
        if(balance >= drawAmount){
            System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票：" + drawAmount);
            try {
                sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            balance -= drawAmount;
            System.out.println("\t余额为：" + balance);
        }
        else {
            System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足！");
        }
    }
}
class DrawThread extends Thread{
    private Account account;
    //取钱数
    private double drawAmount;
    public DrawThread(String name,Account account,double drawAmount){
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    public void run(){
        account.draw(drawAmount);
        //同步代码块
//        synchronized (account){
//            if(account.getBalance() >= drawAmount){
//                System.out.println(getName()+"取钱成功！吐出钞票：" + drawAmount);
//                try {
//                    Thread.sleep(1);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//                account.setBalance(account.getBalance() - drawAmount);
//                System.out.println("\t余额为：" + account.getBalance());
//            }
//            else {
//                System.out.println(getName() + "取钱失败！余额不足！");
//            }
        // }
    }
}


//多线程,implements Runnable
class MyRunable2 implements Runnable{
    private int k;
    public void run(){
        for (;k<100;k++){
            System.out.println(Thread.currentThread().getName()
                    + " " + k);
        }
    }
}

//多线程，extends Thread
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
                    //转入就绪状态，给同级或者更高等级线程机会
                    Thread.yield();
                }
            }

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}

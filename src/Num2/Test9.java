package Num2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test9 {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.firstThread();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.secondThread();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        task.getCounter();
    }
}
class Task{
    private int counter;
    private Lock lock = new ReentrantLock();


    private void increment(){
        for (int i = 0; i<100000;i++){
            counter++;
        }
    }

    public void firstThread(){
        lock.lock();
        increment();
        lock.unlock();
    }

    public void secondThread(){
        lock.lock();
        increment();
        lock.unlock();
    }

    public void getCounter() {
        System.out.println( counter);
    }
}

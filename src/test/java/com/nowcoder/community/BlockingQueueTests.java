package com.nowcoder.community;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTests {

    public static void main(String[] args) {

        BlockingQueue queue = new ArrayBlockingQueue(10); //最多存储10个数
        // 一个生产者生产数据，3个消费者消费数据
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }
}

// 生产者线程
class Producer implements Runnable {

    // 阻塞队列
    private BlockingQueue<Integer> queue;

    // 有参构造器
    public Producer(BlockingQueue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            for (int i = 0; i < 100; i++) {
                Thread.sleep(20);
                queue.put(i);       // 生产的数据
                System.out.println(Thread.currentThread().getName() + "生产: " + queue.size());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

// 消费者线程
class Consumer implements Runnable{

    private BlockingQueue<Integer> queue;
    public Consumer(BlockingQueue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            while(true){
                // 随机休眠0-1000ms
                Thread.sleep(new Random().nextInt(1000));
                queue.take();
                System.out.println(Thread.currentThread().getName() + "消费: " + queue.size());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
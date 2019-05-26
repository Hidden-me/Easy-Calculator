package task;

import task.util.SuperContainer;

public class Main {
    private static boolean producerFinished = false, consumerFinished = false;
    public static void producerFinish(){
        producerFinished = true;
    }
    public static void consumerFinish(){
        consumerFinished = true;
    }
    public static boolean isReadyToTerminate(){
        return producerFinished && consumerFinished;
    }
    public static void main(String[] args){
        int threshold = 5;
        long timeout = 300;
        SuperContainer<Integer> container = new SuperContainer<>(threshold, timeout);
        Producer producer = new Producer(container, threshold);
        Consumer consumer = new Consumer(container, threshold);
        new Thread(producer, "producer").start();
        new Thread(consumer, "consumer").start();
    }
}

package task;

import task.util.SuperContainer;

public class Main {
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

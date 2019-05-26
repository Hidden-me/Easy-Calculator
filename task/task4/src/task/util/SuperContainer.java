package task.util;

import task.Main;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SuperContainer<T> implements Runnable {
    class Item{
        T value;
        long createMillis;
        Item(T ele){
            value = ele;
            createMillis = System.currentTimeMillis();
        }
    }
    private long timeout;
    private int threshold;
    private Deque<Item> dq;
    private boolean isStack(){
        return dq.size() > threshold;
    }
    private boolean isTimeout(Item item){
        return System.currentTimeMillis() - item.createMillis > timeout;
    }
    public SuperContainer(int threshold, long timeout){
        this.timeout = timeout;
        this.threshold = threshold;
        dq = new LinkedList<>();
        new Thread(this, "container").start();
    }
    public synchronized int size(){
        return dq.size();
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public synchronized void push(T element){
        dq.addLast(new Item(element));
    }
    public synchronized T pop(){
        Item item;
        if(isEmpty()){
            return null;
        }
        if(isStack()){
            item = dq.removeLast();
        }else{
            item = dq.removeFirst();
        }
        return item.value;
    }
    public synchronized void checkTimeout(){
        List<Item> list = new LinkedList<>();
        for(Item item : dq){
            if(isTimeout(item)){
                list.add(item);
            }else{
                break;
            }
        }
        for(Item item : list){
            dq.remove(item);
            System.out.println("timeout: " + item.value);
        }
    }
    public void run(){
        while(true){
            checkTimeout();
            try{
                Thread.sleep(10);
            }catch(Exception e){
                e.printStackTrace();
            }
            if(Main.isReadyToTerminate()){
                break;
            }
        }
    }
}

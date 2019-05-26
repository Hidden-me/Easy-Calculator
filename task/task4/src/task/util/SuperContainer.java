package task.util;

import java.util.Deque;
import java.util.LinkedList;

public class SuperContainer<T> {
    class Item{
        T value;
        long createMillis;
    }
    private long timeout;
    private int threshold;
    private Deque<T> dq;
    private boolean isStack(){
        return dq.size() > threshold;
    }
    public SuperContainer(int threshold, long timeout){
        this.timeout = timeout;
        this.threshold = threshold;
        dq = new LinkedList<>();
    }
    public synchronized void push(T element){
        dq.addLast(element);
    }
    public synchronized T pop(){
        if(isStack()){
            return dq.removeLast();
        }else{
            return dq.removeFirst();
        }
    }
    public void checkTimeout(){

    }
}

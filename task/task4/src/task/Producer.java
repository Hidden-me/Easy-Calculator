package task;

import task.util.SuperContainer;

public class Producer implements Runnable {
    private int threshold;
    private SuperContainer container;
    public Producer(SuperContainer container, int threshold){
        this.container = container;
        this.threshold = threshold;
    }
    public void run(){
        for(int i = 0; i < threshold * 4; i++){
            System.out.println("push: " + i);
            container.push(i);
            try{
                Thread.sleep(100);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

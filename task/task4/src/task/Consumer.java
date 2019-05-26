package task;

import task.util.SuperContainer;

public class Consumer implements Runnable {
    private int threshold;
    private SuperContainer container;
    public Consumer(SuperContainer container, int threshold){
        this.container = container;
        this.threshold = threshold;
    }
    public void run(){
        for(int i = 0; i < threshold * 4; i++){
            try{
                Thread.sleep(200);
            }catch(Exception e){
                e.printStackTrace();
            }
            Integer item = (Integer) container.pop();
            if(item != null){
                System.out.println("pop: " + item);
            }
        }
        Main.consumerFinish();
    }
}

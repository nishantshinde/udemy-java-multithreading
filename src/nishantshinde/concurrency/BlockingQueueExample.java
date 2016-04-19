package nishantshinde.concurrency;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {

	private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
	private final int MAX = 10;
	
	public void producer() {
		Random source = new Random();
		Random random = new Random();
		int count = 0;
		while(count<MAX) {
			try {
				Thread.sleep(100);
				if(random.nextInt(5)==0) { // Once every 0.5 second on average
					count++;
					int produce = source.nextInt(100);
					queue.put(produce);
					System.out.println("Produced "+produce+ " ; Queue : " + queue.toString()); 
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void consumer() {
		Random random = new Random();
		int count = 0;
		while(count<MAX) {
			try {
				Thread.sleep(100);
				if(random.nextInt(10)==0) { // Once every second on average
					count++;
					int consume = queue.take();
					System.out.println("Consumed "+consume+ " ; Queue : " + queue.toString()); 
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	public void doWork() {
		
		final BlockingQueueExample object = this;
		Thread t1 =new Thread(new Runnable(){

			@Override
			public void run() {
				object.producer();
			}
			
		});

		Thread t2 =new Thread(new Runnable(){

			@Override
			public void run() {
				object.consumer();
			}
			
		});

		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		BlockingQueueExample example = new BlockingQueueExample();
		example.doWork();
	}
	
}

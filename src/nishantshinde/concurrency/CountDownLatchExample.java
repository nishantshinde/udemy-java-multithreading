package nishantshinde.concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
	public void doWork() {
		
		final CountDownLatch latch = new CountDownLatch(2);
		
		Thread t1 =new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					System.out.println("Thread 1 is waiting for the latch ..." + latch.getCount());
					latch.await();
					System.out.println("Thread 1 resumes.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 =new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					Thread.sleep(2*1000);
					System.out.println("Thread 2 is counting down on the latch ..." + latch.getCount());
					latch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});

		Thread t3 =new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					Thread.sleep(3*1000);
					System.out.println("Thread 3 is counting down on the latch ..." + latch.getCount());
					latch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		CountDownLatchExample example = new CountDownLatchExample();
		example.doWork();
	}
}

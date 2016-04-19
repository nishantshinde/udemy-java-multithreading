package nishantshinde.concurrency.waitnotify;

import java.util.Scanner;

public class Processor {

	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Producer thread is waiting ... ");
			wait();
			System.out.println("Producer now resumed.");
		}
	}

	public void consume() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2*1000);
		synchronized (this) {
			System.out.println("Consumer thread waiting for return key ... ");
			scanner.nextLine();
			System.out.println("Consumer thread accepted the return key & notifying.");
			notify();
		}
	}

}

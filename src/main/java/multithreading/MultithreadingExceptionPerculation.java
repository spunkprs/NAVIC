package multithreading;

import java.util.concurrent.CompletableFuture;

public class MultithreadingExceptionPerculation {
	
	public static void main(String ar[]) {
		try {
			System.out.println("In the main thread !!");
			CompletableFuture completableFuture = CompletableFuture.runAsync(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("Going to throw Exception !!");
					//throw new RuntimeException("Exception raised inside thread 1");
				}
			});
			//completableFuture.complete("AAA");
			Object object = completableFuture.get();
			System.out.println("Is task running in parallel done !! " + completableFuture.isDone());
		} catch(Exception e) {
			System.out.print("Some exception occured !!");
			e.printStackTrace();
		}
	}

}

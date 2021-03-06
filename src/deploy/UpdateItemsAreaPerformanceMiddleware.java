package deploy;

import naming.NamingInvoker;
import performance.UpdateItemsAreaClient;
import server.SmartHomeServer;

public class UpdateItemsAreaPerformanceMiddleware {
	public static void main(String[] args) {

		Thread namingThread = new Thread() {
			public void run() {
				NamingInvoker.main(args);
			}
		};

		namingThread.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread serverThread = new Thread() {
			public void run() {
				try {
					SmartHomeServer.main(args);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		serverThread.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread clientThread = new Thread() {
			public void run() {
				try {
					UpdateItemsAreaClient.main(args);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		clientThread.start();
	}
}

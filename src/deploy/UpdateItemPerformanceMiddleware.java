package deploy;

import naming.NamingInvoker;
import performance.UpdateItemClient;
import server.SmartHomeServer;

public class UpdateItemPerformanceMiddleware {
	public static void main(String[] args) {

		Thread namingThread = new Thread() {
			public void run() {
				NamingInvoker.main(args);
			}
		};

		namingThread.start();

		try {
			Thread.sleep(2000);
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
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread clientThread = new Thread() {
			public void run() {
				try {
					UpdateItemClient.main(args);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		clientThread.start();
	}
}

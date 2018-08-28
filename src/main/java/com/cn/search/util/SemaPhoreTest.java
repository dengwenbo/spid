package com.cn.search.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/**
 * 多线程----信号量
 * @author dengwenbo
 *
 */
public class SemaPhoreTest {
   public static void main(String[] args) {
	   SemaPhoreTest test = new  SemaPhoreTest();
	   test.execute();
}
   
   private void execute() {
	   //定义窗口个数
	   final Semaphore s =new Semaphore(2);
	   ExecutorService threadPool = Executors.newCachedThreadPool();
	   for(int i=0;i<20;i++) {
		   threadPool.execute(new MyTask(s,i+1));
	   }
	   threadPool.shutdown();
   }
	class MyTask implements Runnable {
		private Semaphore s;
		
		private int user;
		
		public MyTask(Semaphore s,int user) {
			this.s=s;
			this.user=user;
		}
		@Override
		public void run() {
			//获取信号量许可
			try {
				s.acquire();
				System.out.println("用户["+user+"]准备买票........");
			     Thread.sleep((long)10000);
			     System.out.println("用户["+user+"]买完票准备离开........");
			     Thread.sleep((long)10000);
			     System.out.println("用户["+user+"]离开窗口........");
			     s.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}

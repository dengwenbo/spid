package com.cn.search.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程屏障(练习)
 * @author dengwenbo
 *
 */
public class CycllicBarrierDemo {
	
	
	private ExecutorService threadPool = Executors.newCachedThreadPool();
     
	
	final CyclicBarrier cb = new CyclicBarrier(4,new Runnable() {

		@Override
		public void run() {
			System.out.println("人到齐了，开始拍照");
			
		}
		
	});
	
	
	class Employee implements Runnable{
		
		private int employee;
		
		
		
		public Employee(int employee) {
			this.employee=employee;
			
		}
		@Override
		public void run() {
			System.out.println("雇员["+employee+"]到达预定的集合地点");
			try {
				cb.await();
		    final int user =employee;
				if(user==1) {
					System.out.println("拍照完毕，可以开始烧烤了");
				}
				Thread.sleep(1000l);
				System.out.println("雇员["+user+"]离开聚会地点准备回家");
				Thread.sleep(1000l);
				cb.await();
				System.out.println("大家安全到家，聚会圆满结束！");
				} catch (InterruptedException | BrokenBarrierException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		
		
	}
	
	public void count() {
		 for(int i=0;i<4;i++) {
			threadPool.execute(new Employee(i+1));
		 }
	}
	 public static void main(String[] args) throws InterruptedException {
		 CycllicBarrierDemo demo = new CycllicBarrierDemo();
		 demo.count();
		Thread.sleep(10000l);
		demo.threadPool.shutdown();
		
	}
}

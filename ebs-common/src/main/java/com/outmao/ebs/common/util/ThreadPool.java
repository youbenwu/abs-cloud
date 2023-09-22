package com.outmao.ebs.common.util;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class ThreadPool {

	private static ThreadPool pool = new ThreadPool();

	private final Object THREADPOOL_LOCK = new Object();
	
	private Vector<Runnable> threadPool = new Vector<Runnable>();

	private Vector<RunnableWrapper> threads = new Vector<RunnableWrapper>();

	private int threadCount = 0;
	private int maxThreadCount = 20;
	private int availableThreads = 0;

	private int actionTimeout = 20 * 1000;
	
	private boolean done = false;

	private boolean stop = false;
	
	private Timer timer;
	
	public ThreadPool() {

	}

	public static ThreadPool getThreadPool() {
		return pool;
	}

	public static void pushToThreadPool(Runnable r) {
		pool.push(r);
	}

	public void close() {
		synchronized (THREADPOOL_LOCK) {
			done = true;
			threadPool.clear();
		}
		if(timer!=null){
			timer.cancel();
			timer=null;
		}
	}

	public void stop() {
		synchronized (THREADPOOL_LOCK) {
			stop = true;
		}
	}

	public void start() {
		synchronized (THREADPOOL_LOCK) {
			if (done || stop) {
				done = false;
				stop = false;
				THREADPOOL_LOCK.notify();
			}
		}
		if(timer==null){
			timer=new Timer();
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					for (int i = threads.size() - 1; i >= 0; i--) {
						threads.get(i).tryDestoryIfTimeout();
					}
				}
			}, actionTimeout, actionTimeout);
		}
	}

	public boolean isStarted() {
		return !done;
	}
	
	public boolean isStoped() {
		return stop;
	}

	public int getMaxThreadCount() {
		return maxThreadCount;
	}

	public void setMaxThreadCount(int maxThreadCount) {
		this.maxThreadCount = maxThreadCount;
	}

	public int getTimeout() {
		return actionTimeout;
	}

	public void setTimeout(int actionTimeout) {
		this.actionTimeout = actionTimeout;
	}

	public void push(Runnable r) {
		if (availableThreads == 0 && threadCount < maxThreadCount) {
			Thread poolThread = new RunnableWrapper();
			poolThread.start();
		}
		synchronized (THREADPOOL_LOCK) {
			threadPool.addElement(r);
			THREADPOOL_LOCK.notify();
		}
	}

	public void remove(Runnable r) {
		synchronized (THREADPOOL_LOCK) {
			threadPool.remove(r);
		}
	}

	final class RunnableWrapper extends Thread {

		public RunnableWrapper() {
			setUncaughtExceptionHandler(exceptionHadler);
			threadCount++;
			threads.add(this);
		}

		long actionStartTimes = 0;

		UncaughtExceptionHandler exceptionHadler = new UncaughtExceptionHandler() {

			public void uncaughtException(Thread t, Throwable e) {
				threadCount--;
				threads.remove(t);
			}

		};

		void tryDestoryIfTimeout() {
			if (actionStartTimes > 0
					&& (System.currentTimeMillis() - actionStartTimes) > actionTimeout) {
				actionStartTimes = 0;
				interrupt();
			}
		}

		public void run() {
			while (!done) {
				Runnable r = null;
				synchronized (THREADPOOL_LOCK) {
					if (!stop && threadPool.size() > 0) {
						r = (Runnable) threadPool.elementAt(0);
						threadPool.removeElementAt(0);
					} else {
						try {
							availableThreads++;
							THREADPOOL_LOCK.wait();
							availableThreads--;
						} catch (InterruptedException ex) {
							availableThreads--;
						}
					}
				}
				if (r != null) {
					actionStartTimes = System.currentTimeMillis();
					r.run();
					actionStartTimes = 0;
				} 
			}
			threadCount--;
			threads.remove(this);
		}
	}

}

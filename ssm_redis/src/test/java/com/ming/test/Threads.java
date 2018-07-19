package com.ming.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Threads extends Thread{
    private static final int corePoolSize=10;
    private static final int maximumPoolSize=2;
    private static final long  keepAliveTime=1000;
    private static final TimeUnit unit=TimeUnit.MILLISECONDS;
    private static final BlockingQueue<Runnable> workQueue=new LinkedBlockingQueue<Runnable>(5);
    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(maximumPoolSize,corePoolSize,keepAliveTime,unit,workQueue);
    public void run(){

    }

}

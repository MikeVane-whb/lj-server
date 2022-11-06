package top.mikevane.ljserver.util;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPoolManager;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author mikevane
 * @Date 19:06
 * @Version 1.0
 */
public final class ThreadPoolUtil extends ThreadPoolExecutor {

    private static volatile ThreadPoolUtil sInstance;

    public ThreadPoolUtil() {
        super(8, 200,
                30L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>());
    }

    public static ThreadPoolUtil getInstance() {
        if(sInstance == null) {
            synchronized (ThreadPoolManager.class) {
                if(sInstance == null) {
                    sInstance = new ThreadPoolUtil();
                }
            }
        }
        return sInstance;
    }
}

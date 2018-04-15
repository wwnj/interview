package multiThread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by wb on 2018/4/13.
 */
public class CacheDemo {

    private Map<String,Object> cache = new HashMap<>();

    public static void main(String[] args) {

    }


    private ReadWriteLock lock = new ReentrantReadWriteLock();
    public Object getData(String key){
        lock.readLock().lock();
        Object value = null;
        try {
            value = cache.get(key);
            if (value == null) {
                lock.readLock().unlock();
                lock.writeLock().lock();
                try{
                    value = "aaaa";//实际是去queryDB();
                }finally {
                    lock.writeLock().unlock();
                }
                lock.readLock().lock();
            }
        }finally{
            lock.readLock().unlock();
        }
        return value;
    }
}

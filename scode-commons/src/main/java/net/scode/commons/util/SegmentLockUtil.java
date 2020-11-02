package net.scode.commons.util;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class SegmentLockUtil {
    private static int segments = 3000;
    private final static HashMap<Integer, ReentrantLock> lockMap = new HashMap<>();

    static {
        for (int i = 0; i < segments; i++) {
            lockMap.put(i, new ReentrantLock(true));
        }
    }

    public static void lock(String key) {
        ReentrantLock lock = lockMap.get((key.hashCode() & Integer.MAX_VALUE) % segments);
        lock.lock();
    }

    public static void unlock(String key) {
        ReentrantLock lock = lockMap.get((key.hashCode() & Integer.MAX_VALUE) % segments);
        lock.unlock();
    }
}

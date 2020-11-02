package net.scode.commons.hutool;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.thread.ThreadUtil;
import org.junit.Test;

/**
 * 缓存测试
 *
 * @author tanghuang 2020年02月24日
 */
public class CacheTest {

    /**
     * 测试定时缓存
     */
    @Test
    public void testTimedCache() {
        // 创建缓存，默认10毫秒过期
        TimedCache<String, String> timedCache = CacheUtil.newTimedCache(10);
        timedCache.put("key1", "value1", 1);//1毫秒过期
        timedCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 15); //15毫秒过期
        timedCache.put("key3", "value3");//默认过期(10毫秒)

        // 启动定时任务，每8毫秒秒检查一次过期
        timedCache.schedulePrune(8);
        // 等待10毫秒
        ThreadUtil.sleep(10);

        // 10毫秒后，由于value2设置了15毫秒过期，因此只有value2被保留下来
        String value1 = timedCache.get("key1");
        String value2 = timedCache.get("key2");
        System.out.println("value1:" + value1);
        System.out.println("value2:" + value2);

        // 10毫秒后，由于设置了默认过期，key3只被保留10毫秒，因此为null
        String value3 = timedCache.get("key3");
        System.out.println("value3:" + value3);

        //取消定时清理
        timedCache.cancelPruneSchedule();
    }

    /**
     * 测试最少使用率策略缓存
     */
    @Test
    public void testLFUCache() {
        Cache<String, String> lfuCache = CacheUtil.newLFUCache(3);
        lfuCache.put("key1", "value1", DateUnit.SECOND.getMillis() * 3);
        // 使用次数+1
        lfuCache.get("key1");
        lfuCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 3);
        lfuCache.put("key3", "value3", DateUnit.SECOND.getMillis() * 3);
        lfuCache.put("key4", "value4", DateUnit.SECOND.getMillis() * 3);

        // 由于缓存容量只有3，当加入第四个元素的时候，根据LRU规则，最少使用的将被移除（2,3被移除）
        String value1 = lfuCache.get("key1");
        String value2 = lfuCache.get("key2");
        String value3 = lfuCache.get("key3");
        System.out.println("value1:" + value1);
        System.out.println("value2:" + value2);
        System.out.println("value3:" + value3);
    }

}

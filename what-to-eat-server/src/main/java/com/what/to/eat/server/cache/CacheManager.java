package com.what.to.eat.server.cache;

public interface CacheManager {
    /**
     * 从缓存中删除key对应的数据。
     *
     * @param key 待删除数据对应的key
     */
    void deleteFromCache(String key);

    /**
     * 把数据存放在缓存中，同时设置缓存数据的过期时间。
     *
     * @param key 此数据对应的key
     * @param exp 过期时间，单位：秒
     * @param obj 要存放在缓存中的数据
     */
    void setToCache(String key, int exp, Object obj);

    /**
     * 根据key从缓存中获取数据。
     *
     * @param key 缓存数据对应的key
     * @return 缓存数据，如果无法获取或数据已过期则返回null
     */
    Object getFromCache(String key);

    /**
     * 判断一个key是否存在
     */
    boolean hasKey(String key);

    /**
     * 设置一个key
     *
     * @param expireSeconds 过期时间，秒
     */
    void set(String key, Object value, int expireSeconds);


    /**
     * 设置一个 key 的过期时间
     *
     * @param seconds 过期时间秒数
     */
    void expire(String key, long seconds);

}

package com.example.uiplog.utils;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Redis工具类
 *
 * @author eric
 */
@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**  过期时长为1小时，单位：秒 */
    public final static long HOUR_ONE_EXPIRE = 60 * 60 * 1L;
    /**  过期时长为1天，单位：秒 */
    public final static long DAY_ONE_EXPIRE = 24 * 60 * 60 * 1L;
    /**  不设置过期时长 */
    public final static long NOT_EXPIRE = -1L;

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private static final Long RELEASE_SUCCESS = 1L;

    public void set(String key, Object value, long expire){
        redisTemplate.opsForValue().set(key, value);
        if(expire != NOT_EXPIRE){
            expire(key, expire);
        }
    }

    public void set(String key, Object value){
        set(key, value, NOT_EXPIRE);
    }

    public Object get(String key, long expire) {
        Object value = redisTemplate.opsForValue().get(key);
        if(expire != NOT_EXPIRE){
            expire(key, expire);
        }
        return value;
    }

    public Object get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public String getString(String key) {
        Object o = get(key, NOT_EXPIRE);
        return o==null?null:o.toString();
    }
    public Boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    public void removeSet(String key, Object value){
        redisTemplate.opsForSet().remove(key, value);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    public Map<String, Object> hGetAll(String key){
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(key);
    }

    public void hMSet(String key, Map<String, Object> map){
        hMSet(key, map, NOT_EXPIRE);
    }

    public void hMSet(String key, Map<String, Object> map, long expire){
        redisTemplate.opsForHash().putAll(key, map);

        if(expire != NOT_EXPIRE){
            expire(key, expire);
        }
    }

    public void leftPushSet(String key, Object value, long expire){
        redisTemplate.opsForSet().add(key, value);

        if(expire != NOT_EXPIRE){
            expire(key, expire);
        }
    }

    public void hSet(String key, String field, Object value) {
        hSet(key, field, value, NOT_EXPIRE);
    }

    public void hSet(String key, String field, Object value, long expire) {
        redisTemplate.opsForHash().put(key, field, value);

        if(expire != NOT_EXPIRE){
            expire(key, expire);
        }
    }

    public void expire(String key, long expire){
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    public void hDel(String key, Object... fields){
        redisTemplate.opsForHash().delete(key, fields);
    }

    public List<String> getListString(String searchKey){
        List<Object> list = redisTemplate.opsForList().range(searchKey, 0, -1);
        return list==null?null: JSON.parseArray(list.toString(), String.class);
    }

    public Set<String> getSetString(String searchKey){
        Set<Object> list = redisTemplate.opsForSet().members(searchKey);
        return list==null?null: list.stream().map(String::valueOf).collect(Collectors.toSet());
    }


    public long getListLength(String searchKey){
        return redisTemplate.opsForList().size(searchKey);
    }

    /**********左(上)进右(下)出*********/
    public void leftPush(String key, Object value){
        leftPush(key, value, NOT_EXPIRE);
    }
    public void leftPush(String key, Object value, long expire){
        redisTemplate.opsForList().leftPush(key, value);

        if(expire != NOT_EXPIRE){
            expire(key, expire);
        }
    }
    public Object rightPop(String key){
        return redisTemplate.opsForList().rightPop(key);
    }

    /**********右(下)进左(上)出*********/
    public void rightPush(String key, Object value){
        rightPush(key, value, NOT_EXPIRE);
    }
    public void rightPush(String key, Object value, long expire){
        redisTemplate.opsForList().rightPush(key, value);

        if(expire != NOT_EXPIRE){
            expire(key, expire);
        }
    }
    public Object leftPop(String key){
        return redisTemplate.opsForList().leftPop(key);
    }

    public List<Object> range(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 获取当前路径下，所有key
     *
     * @param pattern 路径（可以是模糊路径）
     * @author tjy
     **/
    public Set<String> getScanKeys(String pattern) {
        return redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> tmpKeys = new HashSet<>();
            ScanOptions options;
            options = ScanOptions.scanOptions().match(pattern).count(5000).build();
            // 迭代一直查找，直到找到redis中所有满足条件的key为止(cursor变为0为止)
            Cursor<byte[]> cursor = connection.scan(options);
            while (cursor.hasNext()) {
                tmpKeys.add(new String(cursor.next()));
            }
            return tmpKeys;
        });
    }


}

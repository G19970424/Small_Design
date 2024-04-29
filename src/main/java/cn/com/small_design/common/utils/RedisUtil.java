package cn.com.small_design.common.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author gejj
 * @create 2024年03月26日 11:05
 * @version 1.0
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;


    private static final String CACHE_KEY_PRE = "";
    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     * @return 缓存的对象
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value);
        return operation;
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return 缓存的对象
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value, Integer timeout, TimeUnit timeUnit) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value, timeout, timeUnit);
        return operation;
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public void deleteObject(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     * @return
     */
    public <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {
            for (Map.Entry<String, T> entry : dataMap.entrySet()) {
                hashOperations.put(key,  CACHE_KEY_PRE+ entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(String key) {
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        Map<String, T> map = redisTemplate.opsForHash().entries(key);
        return map;
    }

    /**
     * 获取存储在哈希表中指定字段的值
     *
     * @param key
     * @param field
     * @return
     */
    public String getCacheMapValueString(String key, String field) {
        if (!hasKey(key)) {
            return null;
        }
        String preField = CACHE_KEY_PRE + field;
        Object obj = redisTemplate.opsForHash().get(key, preField);
        if (obj != null) {
            return obj.toString();
        }
        return "";
    }

    public Object getCacheMapValueObj(String key, String field) {
        if (!hasKey(key)) {
            return null;
        }
        String preField = CACHE_KEY_PRE + field;
        Object obj = redisTemplate.opsForHash().get(key, preField);
        return obj;
    }


    /**
     * 获取存储在哈希表中指定字段的值
     *
     * @param key
     * @param field
     * @return
     */
    public <T> T getCacheMapValueJson(String key, String field, Class<T> clazz) {
        if (!hasKey(key)) {
            return null;
        }
        String preField = CACHE_KEY_PRE + field;
        Object obj = redisTemplate.opsForHash().get(key, preField);
        if (obj != null) {
            return JSON.parseObject(obj.toString(), clazz);
        }
        return null;
    }

    public Map<String, String> getCacheMapScan(String key, String field) {
        String preField = CACHE_KEY_PRE + field;
        Map<String, String> map = new HashMap<>();
        Cursor<Map.Entry<Object,Object>> scan = redisTemplate.opsForHash().scan(key, ScanOptions.scanOptions().match(preField + "*").build());
        while(scan.hasNext()) {
            Map.Entry<Object,Object> entry = scan.next();
            map.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return map;
    }

    /**
     * 模糊查找
     * @param key
     * @param field
     * @return
     */
    public String getCacheMapScanOne(String key, String field) {
        String preField = CACHE_KEY_PRE + field;
        Cursor<Map.Entry<Object,Object>> scan = redisTemplate.opsForHash().scan(key, ScanOptions.scanOptions().match(preField + "*").build());
        while(scan.hasNext()) {
            Map.Entry<Object,Object> entry = scan.next();
            return entry.getValue().toString();
        }
        return "";
    }

    public <T> List<T> getCacheMapValueScan(String key, String field, Class<T> clazz) {
        String preField = CACHE_KEY_PRE + field;
        List<T> dataList = new ArrayList<T>();
        Cursor<Map.Entry<Object,Object>> scan = redisTemplate.opsForHash().scan(key, ScanOptions.scanOptions().match(preField + "*").build());
        while(scan.hasNext()) {
            Map.Entry<Object,Object> entry = scan.next();
            dataList.add(JSON.parseObject(entry.getValue().toString(), clazz));
        }
        return dataList;
    }
    /**
     * 删除HASH中指定key
     *
     * @param key
     * @param field
     */
    public void deleteMapKey(String key, String field) {
        String preField = CACHE_KEY_PRE + field;
        redisTemplate.opsForHash().delete(key, preField);
    }

    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获得key的数据类型
     *
     * @param key Redis键
     * @return 数据类型
     */
    public DataType keyType(String key) {
        return redisTemplate.type(key);
    }

    /**
     * 根据条件分页查询Redis键集合
     *
     * @param keyPattern 查询条件
     * @param pageNum    页码
     * @param pageSize   页大小
     * @return 键集合
     * @throws IOException
     */
    public List<String> getPageKeys(String keyPattern, int pageNum, int pageSize) {
        List<String> keys = new ArrayList<>();
        try {
            // 设置分页查询的起始位置
            int startIndex = (pageNum - 1) * pageSize;
            // 使用 SCAN 命令进行分页遍历，并加入 MATCH 选项进行键的匹配
            Cursor<byte[]> cursor = (Cursor<byte[]>) redisTemplate.execute((RedisCallback<Cursor<byte[]>>) connection -> {
                ScanOptions scanOptions = null;
                if (keyPattern != null && !keyPattern.trim().isEmpty()) {
                    scanOptions = ScanOptions.scanOptions().count(pageSize).match(keyPattern).build();
                } else {
                    scanOptions = ScanOptions.scanOptions().count(pageSize).build();
                }
                return connection.scan(scanOptions);
            });
            // 获取指定页码的键值
            int currentIndex = 0;
            while (cursor.hasNext()) {
                byte[] keyBytes = cursor.next();
                String key = new String(keyBytes);

                if (currentIndex >= startIndex) {
                    keys.add(key);
                }
                currentIndex++;
                if (keys.size() == pageSize) {
                    break;  // 达到指定页码的大小，提前结束遍历
                }
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return keys;
    }

    /**
     * 获取键总数
     *
     * @param keyPattern 查询条件
     * @return 键总数
     */
    public long getKeyCount(String keyPattern) {
        ScanOptions scanOptions = null;
        if (keyPattern != null && !keyPattern.trim().isEmpty()) {
            scanOptions = ScanOptions.scanOptions().count(Integer.MAX_VALUE).match(keyPattern).build();
        } else {
            scanOptions = ScanOptions.scanOptions().count(Integer.MAX_VALUE).build();
        }
        ScanOptions finalScanOptions = scanOptions;
        Long count = (Long) redisTemplate.execute((RedisCallback<Long>) connection -> {
            long totalCount = 0;
            try {
                Cursor<byte[]> cursor = connection.scan(finalScanOptions);
                while (cursor.hasNext()) {
                    cursor.next();
                    totalCount++;
                }
                cursor.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return totalCount;
        });
        return count != null ? count : 0;
    }

    /**
     * 根据条件分页查询Redis hash键值集合
     *
     * @param hashKey      hash键
     * @param fieldPattern hash值中的键
     * @param pageNumber   页码
     * @param pageSize     页大小
     * @return hash键值集合
     */
    public List<Map.Entry<String, String>> getPageHashEntries(String hashKey, String fieldPattern, int pageNumber, int pageSize) {
        List<Map.Entry<String, String>> hashEntries = new ArrayList<>();
        try {
            // 设置分页查询的起始位置
            int startIndex = (pageNumber - 1) * pageSize;
            // 使用 HSCAN 命令进行分页遍历，并加入 MATCH 选项进行字段的匹配
            Cursor<Map.Entry<byte[], byte[]>> cursor = (Cursor<Map.Entry<byte[], byte[]>>) redisTemplate.execute((RedisCallback<Cursor<Map.Entry<byte[], byte[]>>>) connection -> {
                ScanOptions scanOptions = null;
                if (fieldPattern != null && !fieldPattern.trim().isEmpty()) {
                    scanOptions = ScanOptions.scanOptions().count(pageSize).match(fieldPattern).build();
                } else {
                    scanOptions = ScanOptions.scanOptions().count(pageSize).build();
                }
                return connection.hScan(hashKey.getBytes(), scanOptions);
            });
            // 获取指定页码的Hash字段
            int currentIndex = 0;
            while (cursor.hasNext()) {
                Map.Entry<byte[], byte[]> entry = cursor.next();
                String field = new String(entry.getKey());
                String value = new String(entry.getValue());
                if (currentIndex >= startIndex) {
                    Map.Entry<String, String> hashEntry = new HashMap.SimpleEntry<>(field, value);
                    hashEntries.add(hashEntry);
                }
                currentIndex++;
                if (hashEntries.size() == pageSize) {
                    break;  // 达到指定页码的大小，提前结束遍历
                }
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return hashEntries;
    }

    /**
     * 根据条件查询Redis hash键值总数
     *
     * @param hashKey      hash键
     * @param fieldPattern hash值中的键
     * @return 总数量
     */
    public long getHashCount(String hashKey, String fieldPattern) {
        ScanOptions scanOptions = null;
        if (fieldPattern != null && !fieldPattern.trim().isEmpty()) {
            scanOptions = ScanOptions.scanOptions().count(Integer.MAX_VALUE).match(fieldPattern).build();
        } else {
            scanOptions = ScanOptions.scanOptions().count(Integer.MAX_VALUE).build();
        }
        ScanOptions finalScanOptions = scanOptions;
        Long count = (Long) redisTemplate.execute((RedisCallback<Long>) connection -> {
            long totalCount = 0;
            Cursor<Map.Entry<byte[], byte[]>> cursor = connection.hScan(hashKey.getBytes(), finalScanOptions);
            while (cursor.hasNext()) {
                cursor.next();
                totalCount++;
            }
            try {
                cursor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return totalCount;
        });
        return count != null ? count : 0;
    }
}

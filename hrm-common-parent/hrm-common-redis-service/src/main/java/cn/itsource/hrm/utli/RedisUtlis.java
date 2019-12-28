package cn.itsource.hrm.utli;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
@Scope("singleton")
public class RedisUtlis {
    private String host = "127.0.0.1";
    private Integer port = 6379;
    private String password = "2459423peach";
    private Integer maxIdle = 1;
    private Integer maxTotal = 11;
    private Long maxWaitMillis = 10*1000L;
    private boolean testOnBorrow = true;
    JedisPool jedisPool = null;

    public RedisUtlis() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        poolConfig.setTestOnBorrow(testOnBorrow);
        jedisPool = new JedisPool(poolConfig,host,port,maxWaitMillis.intValue());
    }

    public Jedis getSource(){
        return jedisPool.getResource();
    }

    public void closeSource(Jedis jedis) {
        if (jedis != null)jedis.close();
    }

    public void set(String key, String value) {
        Jedis jedis = getSource();
        jedis.set(key, value);
        closeSource(jedis);
    }

    public void set(byte[] key, byte[] value) {
        Jedis jedis = getSource();
        jedis.set(key, value);
        closeSource(jedis);
    }

    public byte[] get(byte[] key) {
        Jedis jedis = getSource();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSource(jedis);
        }return null;
    }

    public String get(String key) {
        Jedis jedis = getSource();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSource(jedis);
        }
        return null;
    }
}

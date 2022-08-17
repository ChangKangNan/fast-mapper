package cn.ft.ckn.fastmapper.util.lock;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import io.netty.util.concurrent.FastThreadLocal;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author ckn
 * @date 2022/8/17
 */
public class BaseLock {
    /**
     * redis连接复用
     */
    private static FastThreadLocal<StringRedisTemplate> rcf = new FastThreadLocal<>();
    /**
     * 前缀
     */
    private static final String KEY_PRE = "redis_lock_" + StrUtil.DOT;
    /**
     * 锁key
     */
    protected final String lockKey;

    /**
     * 确保对同一个对象进行加锁
     */
    protected static final Interner<Object> inter = Interners.newWeakInterner();
    /**
     * 锁是否被占用
     */
    protected Boolean isLock = Boolean.FALSE;

    protected StringRedisTemplate redisTemplate;

    protected String threadId;

    public BaseLock(String lockKey) {
        this.lockKey = KEY_PRE + lockKey;
        init();
    }


    public void unLock() {
        if (isLock && StrUtil.equals(threadId, redisTemplate.opsForValue().get(lockKey))) {
            redisTemplate.delete(lockKey);
        }
    }

   private void init() {
        redisTemplate = rcf.get();
        if (redisTemplate == null) {
            redisTemplate = new StringRedisTemplate(SpringUtil.getBean(RedisConnectionFactory.class));
            rcf.set(redisTemplate);
        }
    }
}

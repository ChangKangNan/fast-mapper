package cn.ft.ckn.fastmapper.util.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author ckn
 * @date 2022/8/17
 */
public class StatusLock extends BaseLock {

    public StatusLock(String lockKey) {
        super(lockKey);
    }

    public Boolean lock(long expireTime) {
        Boolean ifAbsent = redisTemplate.opsForValue().setIfAbsent(lockKey, threadId,expireTime, TimeUnit.MILLISECONDS);
        if(Boolean.TRUE.equals(ifAbsent)){isLock=Boolean.TRUE;}
        return ifAbsent;
    }

    public Boolean lock(){
        return lock(10000L);
    }
}

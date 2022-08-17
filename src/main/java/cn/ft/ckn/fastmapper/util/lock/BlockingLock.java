package cn.ft.ckn.fastmapper.util.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author ckn
 * @date 2022/8/17
 */
public class BlockingLock extends BaseLock {

    public BlockingLock(String lockKey) {
        super(lockKey);
    }

    public Boolean lock(long lockTime, long cycle) {
        synchronized (inter.intern(lockKey)) {
            long currentTimeMillis = System.currentTimeMillis();
            long expireTimeMillis=currentTimeMillis+lockTime;
            while(Boolean.FALSE.equals(redisTemplate.opsForValue()
                    .setIfAbsent(lockKey, threadId, 2*lockTime, TimeUnit.MILLISECONDS))){
                try {
                    if(currentTimeMillis>=expireTimeMillis){
                        return false;
                    }
                    Thread.sleep(cycle);
                    currentTimeMillis+=currentTimeMillis+cycle;
                }catch (Exception e){
                    return false;
                }
            }
            isLock=Boolean.TRUE;
        }
        return true;
    }
    public Boolean lock(){
        return lock(10000L,10L);
    }

}

package cn.ft.ckn.fastmapper.util.lock;

/**
 * @author ckn
 * @date 2022/8/17
 */
public class FastLock {

    public static BlockingLock createBlockingLock(String lock) {
        return new BlockingLock(lock);
    }

    public static StatusLock createStatusLock(String lock) {
        return new StatusLock(lock);
    }
}

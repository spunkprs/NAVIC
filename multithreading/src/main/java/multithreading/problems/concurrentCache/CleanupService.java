package multithreading.problems.concurrentCache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CleanupService {

    private CacheService cacheService;

    private ExecutorService executorService;

    public CleanupService(CacheService cacheService) {
        this.cacheService = cacheService;
        this.executorService = Executors.newFixedThreadPool(cacheService.getSegments().length);
    }

    public void cleanupSegments() {
        for (int i = 0; i < cacheService.getSegments().length; i++) {
            Cache segments[] = cacheService.getSegments();
            int index = i;
            executorService.submit(() -> {
                Cache segment = segments[index];
                while (true) {
                    try {
                        ListNode listNode = (ListNode) segment.getDelayQueue().poll(); //Not a blocking call unlike take(), it returns null if none of the node has expired object reference otherwise
                        if (listNode != null) {
                            segment.getReentrantLock().lock(); //acquiring lock on the segment only when some node has expired
                            segment.removeNode(listNode);
                        }
                    } catch (Exception e) {
                      e.printStackTrace();
                    } finally {
                        segment.getReentrantLock().unlock();
                    }
                }
            });
        }
    }
}

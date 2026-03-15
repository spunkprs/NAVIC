package multithreading.problems.concurrentCache;

/**
 To tackle concern of throughput I have wrapped it around CacheService and make every Cache node behave like a segment instead, this will up to a great extent will handle
 concern of throughput, key can be used to compute hash which will be used to distribute traffic across different cache nodes, hence contention will be limited to specific
 Cache node
 * */

public class CacheService<K, V> {

    private Cache<K, V>[] segments;
    private int maxCacheSize;
    private int segmentLength;

    public CacheService(int maxCacheSize, int segmentLength) {
        this.maxCacheSize = maxCacheSize;
        this.segmentLength = segmentLength;
        this.segments = new Cache[segmentLength];
    }
}

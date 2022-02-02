package com.jpoverclock.dungeon.regions;

import java.util.*;

public class RegionPartitioner {

    private static final float AREA_THRESHOLD = 0.20f;
    private static final float EDGE_JITTER = 0.10f;

    private final Random random;

    public RegionPartitioner(Random random) {
        this.random = random;
    }

    public Collection<Region> partition(Region region) {

        // Initial parameters
        var originalArea = region.area();

        var leafs = new ArrayList<Region>();
        var toVisit = new Stack<Region>();
        toVisit.push(region);

        while (!toVisit.isEmpty()) {
            var candidate = toVisit.pop();

            if (criteriaMet(candidate, originalArea)) {
                leafs.add(candidate);
            } else {
                for (var child : split(candidate)) {
                    toVisit.push(child);
                }
            }
        }

        return leafs;
    }

    private boolean criteriaMet(Region region, int originalArea) {
        return region.area() <= originalArea * AREA_THRESHOLD;
    }

    private Collection<Region> split(Region region) {
        // Decide on negative or positive jitter
        int signal = random.nextBoolean() ? -1 : 1;

        // Split on longest edge
        if (region.getWidth() >= region.getHeight()) {
            int jitter = (int) (region.getWidth() * EDGE_JITTER);
            var splitPoint = (region.getWidth() >> 1) + (signal * jitter);

            return region.bisectVertically(splitPoint);
        } else {
            int jitter = (int) (region.getHeight() * EDGE_JITTER);
            var splitPoint = (region.getHeight() >> 1) + (signal * jitter);

            return region.bisectHorizontally(splitPoint);
        }
    }
}

package ru.tinkoff.landscape.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KuhnAlgorithm {
    private final Map<Long, List<Long>> graph;
    private final Map<Long, Long> matching;
    private final Map<Long, Boolean> used;

    public KuhnAlgorithm(Map<Long, List<Long>> graph) {
        this.graph = graph;
        this.matching = new HashMap<>();
        this.used = new HashMap<>();
    }

    public boolean tryKuhn(Long v) {
        if (used.getOrDefault(v, false)){
            return false;
        }
        used.put(v, true);
        for (Long to : graph.get(v)) {
            if (matching.get(to) == null || tryKuhn(matching.get(to))) {
                matching.put(to, v);
                return true;
            }
        }
        return false;
    }

    public void findMatching() {
        for (Long v : graph.keySet()) {
            used.clear();
            tryKuhn(v);
        }
    }

    public Map<Long, Long> getMatching() {
        if (matching.isEmpty()) {
            findMatching();
        }
        return matching;
    }
}

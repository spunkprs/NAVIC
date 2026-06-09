package microsoft.dfs;

import java.util.*;

public class KillProcess {

    public static void main(String ar[]) {
        KillProcess unit = new KillProcess();

        List<Integer> pid = Arrays.asList(1, 3, 10, 5, 4, 2);
        List<Integer> ppid = Arrays.asList(3, 0, 5, 3, 10, 10);


        List<Integer> result = unit.killProcess(pid, ppid, 5);

        System.out.print(result);

    }

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> mapping = prepareReleventMapping(pid, ppid);
        List<Integer> result = new ArrayList<>();
        if (mapping.isEmpty()) {
            result.add(pid.get(0));
        } else {
            processToFetchKilledNodes(kill, mapping, result);
        }
        return result;
    }

    private void processToFetchKilledNodes(int kill, Map<Integer, List<Integer>> mapping, List<Integer> result) {
        process(kill, mapping, result);
    }

    private void process(int parentNode, Map<Integer, List<Integer>> mapping, List<Integer> result) {
        result.add(parentNode);
        if (mapping.containsKey(parentNode)) {
            for (Integer childNode : mapping.get(parentNode)) {
                process(childNode, mapping, result);
            }
        }
    }

    private Map<Integer, List<Integer>> prepareReleventMapping(List<Integer> pid, List<Integer> ppid) {
        Map<Integer, List<Integer>> mapping = new HashMap<>();
        for (int i = 0; i < ppid.size(); i++) {
            if (ppid.get(i) != 0) {
                if (mapping.containsKey(ppid.get(i))) {
                    List<Integer> children = mapping.get(ppid.get(i));
                    children.add(pid.get(i));
                } else {
                    List<Integer> children = new ArrayList<>();
                    children.add(pid.get(i));
                    mapping.put(ppid.get(i), children);
                }
            }
        }
        return mapping;
    }

}

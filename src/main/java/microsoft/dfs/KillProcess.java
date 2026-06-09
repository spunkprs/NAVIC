package microsoft.dfs;

import java.util.*;

/**
Problem : 582
Link : https://leetcode.com/problems/kill-process/description/?envType=problem-list-v2&envId=depth-first-search

You have n processes forming a rooted tree structure. You are given two integer arrays pid and ppid,
where pid[i] is the ID of the ith process and ppid[i] is the ID of the ith process's parent process.

Each process has only one parent process but may have multiple children processes. Only one process has ppid[i] = 0,
which means this process has no parent process (the root of the tree).

When a process is killed, all of its children processes will also be killed.

Given an integer kill representing the ID of a process you want to kill, return a list of the IDs of the
processes that will be killed. You may return the answer in any order.


Constraints:-

a.) n == pid.length
b.) n == ppid.length
c.) 1 <= n <= 5 * 10^4
d.) 1 <= pid[i] <= 5 * 10^4
e.) 0 <= ppid[i] <= 5 * 10^4
f.) Only one process has no parent.
g.) All the values of pid are unique.
h.) kill is guaranteed to be in pid.

Time Complexity = O(N), where N being number of nodes in the tree
Space Complexity = O(N)

 * */

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

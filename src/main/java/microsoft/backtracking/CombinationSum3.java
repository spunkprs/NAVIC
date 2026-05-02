package microsoft.backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3 {

    private List<List<Integer>> result = new ArrayList<>();

    public static void main(String ar[]) {
        CombinationSum3 unit = new CombinationSum3();

        int k = 3;
        int n = 9;

        unit.combinationSum3(k, n);

        unit.printResult();
    }

    private void printResult() {
        for (int i = 0; i < result.size(); i++) {
            result.get(i).forEach(x -> {
                System.out.print(x);
                System.out.print(" ");
            });
            System.out.println();
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        processToPrepareCombinationSum(k, n);
        return result;
    }

    private void processToPrepareCombinationSum(int maxDepth, int maxSum) {
        StringBuilder sb = null;
        for (int i = 1; i <= 9; i++) {
            sb = new StringBuilder(String.valueOf(i));
            process(i, i, maxSum, 1, maxDepth, sb);
        }
    }

    private void process(int num, int sumTillNow, int maxSum, int depthTillNow, int maxDepth, StringBuilder sb) {
        if (depthTillNow < maxDepth) {
            if (sumTillNow < maxSum) {
                for (int i = num + 1; i <= 9; i++) {
                    sb.append(i);
                    process(i, sumTillNow + i, maxSum, depthTillNow + 1, maxDepth, sb);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        } else if (depthTillNow == maxDepth) {
            if (sumTillNow == maxSum) {
                prepareResult(sb);
            }
        }
    }

    private void prepareResult(StringBuilder sb) {
        List<Integer> intermittentResult = new ArrayList<>();
        for (char ch : sb.toString().toCharArray()) {
            intermittentResult.add(Integer.parseInt(String.valueOf(ch)));
        }
        result.add(intermittentResult);
    }
}

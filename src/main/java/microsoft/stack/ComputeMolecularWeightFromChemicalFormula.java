package microsoft.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ComputeMolecularWeightFromChemicalFormula {

    public static void main(String ar[]) {
        ComputeMolecularWeightFromChemicalFormula unit = new ComputeMolecularWeightFromChemicalFormula();
        String formula = "HO2(CH4)2(CO(HO)3)5";
        System.out.println("Computed molecular weight for formula " + formula + " is " + unit.computeMolecularWeight(formula));
    }

    public int computeMolecularWeight(String formula) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('C', 12);
        map.put('H', 1);
        map.put('O', 8);

        char arr[] = formula.toCharArray();
        int result = 0;

        Stack<String> stack =  processToComputeWeight(arr, map);

        while (!stack.isEmpty()) {
            result += Integer.parseInt(stack.peek());
            stack.pop();
        }
        return result;
    }

    private Stack<String> processToComputeWeight(char[] arr, Map<Character, Integer> map) {
        Stack<String> stack = new Stack<>();

        int index = 0;

        while (index < arr.length) {
            if (arr[index] == '(') {
                stack.push("(");
            } else if (Character.isDigit(arr[index])) {
                int num = Integer.parseInt(stack.peek());
                stack.pop();
                int intermittentResult = Integer.parseInt(String.valueOf(arr[index])) * num;
                stack.push(String.valueOf(intermittentResult));
            } else if (Character.isAlphabetic(arr[index])) {
                stack.push(String.valueOf(map.get(arr[index])));
            } else if (arr[index] == ')') {
                int intermittentResult = 0;
                while (stack.peek() != "(") {
                    intermittentResult += Integer.parseInt(stack.peek());
                    stack.pop();
                }
                stack.pop();
                stack.push(String.valueOf(intermittentResult));
            }
            index++;
        }

        return stack;
    }
}

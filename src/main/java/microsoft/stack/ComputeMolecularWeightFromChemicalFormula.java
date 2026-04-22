package microsoft.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
You are provided with a mapping of chemical element symbols to their atomic weights: {'C': 12, 'H': 1, 'O': 8}.
Your task is to implement a function that takes a chemical formula string and calculates its total molecular weight.

The chemical formula string can include:

Uppercase letters for element symbols (e.g., 'C', 'H', 'O').
Numbers immediately following an element symbol to denote its count (e.g., 'H2' means two Hydrogen atoms).
Parentheses for grouping (e.g., '(CH4)' represents a methane molecule).
Numbers immediately following a closing parenthesis to denote a multiplier for the entire group (e.g., '(CH4)2' means two methane molecules).
Your solution should correctly parse the nested structure and multipliers to compute the final weight.

Constraints:-
a.) formula will only contain uppercase English letters, digits, and parentheses.
b.) atomicWeights will contain valid mappings for all elements in formula.
c.) formula length will be between 1 and 1000.
d.) Calculated weight will fit within a standard integer type.


 * */

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

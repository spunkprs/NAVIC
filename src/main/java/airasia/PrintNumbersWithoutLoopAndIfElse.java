package airasia;

public class PrintNumbersWithoutLoopAndIfElse {

    public static void main(String ar[]) {
    printNumbers(10);
    }

    public static boolean printNumbers(int num) {
        return num > 0 &&
                (System.out.printf("%d %s%n", num, (num % 2 == 0 ? "EVEN" : "ODD")) != null) &&
                printNumbers(num - 1);
    }


    /**
     Approach 2 (Cleaner)

     Use recursion and array indexing instead of if.

     public class OddEven {

     static String[] type = {"Even", "Odd"};

     static void print(int n) {
     try {
     System.out.println(type[n % 2] + " " + n);
     print(n + 1);
     } catch (StackOverflowError e) {
     }
     }
     }

     Not ideal either.
     * */
}

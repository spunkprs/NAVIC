package airasia;

public class ImplementAndForNArgumentsWIthoutUsingAndOperator {

    public static void main(String ar[]) {

        boolean values[] = {true, true, false, true};

        System.out.println("Result from approach one is " + andOne(values));
        System.out.println("Result from approach two is " + andTwo(values));
        System.out.println("Result from approach three is " + andThree(values));
    }

    //Approach 1 [Use logical AND]
    static boolean andOne(boolean... values) {
        boolean ans = true;
        for(boolean value : values)
            ans = ans && value;

        return ans;
    }

    //Approach 2 [Without Using logical AND]
    static boolean andTwo(boolean... values) {
        for(boolean b : values) {
            if(!b)
                return false;
        }
        return true;
    }

    //Approach 2 [Without Using logical AND, making use of bitwise & operator instead]
    static boolean andThree(boolean... values) {
        boolean result = true;
        for (boolean value : values) {
            result &= value;
        }
        return result;
    }


    /**
     *
     The key difference is:

     result &= value;

     is equivalent to:

     result = result & value;

     whereas

     result = result && value;

     uses the logical AND (&&) operator.

     Difference
     &= / &
     Always evaluates both operands.
     Performs a bitwise AND for integers, and a logical AND for booleans.
     Updates result in place.

     &&
     Short-circuits: if result is already false, value is not evaluated.
     Commonly used in if conditions.

     * */


}

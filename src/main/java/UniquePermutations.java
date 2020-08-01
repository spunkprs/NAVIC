import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniquePermutations {

    private List<List<Integer>> result = new ArrayList<List<Integer>>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        processUniquePermutations(nums);
        return result;
    }

    private void processUniquePermutations(int[] nums) {
        Set<Integer> setOne = new HashSet<Integer>();
        Set<String> setTwo = new HashSet<String>();
        List<Integer> list = new ArrayList<Integer>();
        process(nums, setOne, setTwo, list, 1, "");
    }

    private void process(int[] nums, Set<Integer> setOne, Set<String> setTwo, List<Integer> list, int depth, String tillNow) {
        if (list.size() != nums.length) {
            for (int i = 0; i < nums.length; i++) {
                if (!setOne.contains(i)) {
                    tillNow += String.valueOf(nums[i]);
                    if (!setTwo.contains(tillNow)) {
                        setOne.add(i);
                        setTwo.add(tillNow.toString());
                        list.add(nums[i]);
                        process(nums, setOne, setTwo, list, depth + 1, tillNow);
                        setOne.remove(i);
                        list.remove(list.size() - 1);
                        String val = String.valueOf(nums[i]);
                        tillNow = tillNow.substring(0, tillNow.length() - val.length());
                    } else {
                        String val = String.valueOf(nums[i]);
                        tillNow = tillNow.substring(0, tillNow.length() - val.length());
                    }
                }
            }
        } else {
            result.add(prepareAnotherList(list));
        }

    }

    private List<Integer> prepareAnotherList(List<Integer> list) {
        List<Integer> newList = new ArrayList<Integer>(list);
        return newList;
    }
}

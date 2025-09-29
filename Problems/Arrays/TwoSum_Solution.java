package Arrays;

import java.util.HashMap;
import java.util.Map;

public class TwoSum_Solution {
  public int[] twoSum(int[] nums, int target) {

Map<Integer, Integer> prevMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (prevMap.containsKey(complement)) {
            return new int[] { prevMap.get(complement), i };
        }
        prevMap.put(nums[i], i);
    }
    throw new IllegalArgumentException("No two sum solution");
}
} 
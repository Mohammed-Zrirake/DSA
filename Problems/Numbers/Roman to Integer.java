package Numbers;
import java.util.Map;

class Solution {
    public int romanToInt(String s) {
     Map<Character, Integer> map = Map.of(
            'I', 1, 'V', 5, 'X', 10,
            'L', 50, 'C', 100, 'D', 500, 'M', 1000
        );

int result = 0;
for (int i = 0; i < s.length(); i++) {
    int value = map.get(s.charAt(i));
    if (i + 1 < s.length()) {
        int nextValue = map.get(s.charAt(i + 1));
        if (value < nextValue) {
            result -= value; 
        } else {
            result += value; 
        }
    } else {
        result += value; 
    }
    }
    return result;
    }
    }
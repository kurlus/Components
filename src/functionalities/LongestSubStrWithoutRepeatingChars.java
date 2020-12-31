package functionalities;

import java.util.Map;
import java.util.HashMap;

public class LongestSubStrWithoutRepeatingChars {

    //optimized window solution
    public int maxLngthSubStrWithoutRepChars(String charString){
        int ans  = 0;
        int lngthCharString = charString.length();
        Map<Character, Integer> indexMap = new HashMap<>();

        for (int j=0, i=0; j<lngthCharString; j++){
            if (indexMap.containsKey(charString.charAt(j)))
                i = Math.max(indexMap.get(charString.charAt(j)), i);
            ans = Math.max(ans, j-i+1);
            indexMap.put(charString.charAt(j), j+1);
        }
        return ans;
    }

}

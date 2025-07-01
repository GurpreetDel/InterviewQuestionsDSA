package com.boot;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    public static void main(String[] args) {
        int[] arr=findTwoSum();
        String str= Arrays.toString(arr);
        String str1= String.valueOf(arr).toString();
        System.out.println(str1);
        System.out.println(str);
        String str2= String.join(",", Arrays.stream(arr)
                .mapToObj(String::valueOf)
                .toArray(String[]::new));
        System.out.println(str2);

    }
    public static int[] findTwoSum() {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] arr={1,2,3,4,5};
        int target=6;
        for (int i=0;i<arr.length;i++)
        {
            int compliment=target-arr[i];
            if (map.containsKey(compliment))
            {
                return new int[]{map.get(compliment),i};

            }

            else
            {
                map.put(arr[i],i);
            }
        }
        return new int[]{};
    }
}

package com.boot;

public class isValidSubsequence
{
    public static void main(String[] args)
    {
        String array="abcda";
        String sequence="acd";
        boolean result=isValidSubsequenceMethod(array,sequence);
        System.out.println(result);

    }

    private static boolean isValidSubsequenceMethod(String array, String sequence)
    {
        int arrIdx=0;
        int seqIdx=0;

        while (arrIdx<array.length() && seqIdx<sequence.length())
        {
            if(array.charAt(arrIdx)==sequence.charAt(seqIdx))
            {
                seqIdx++;
            }
            else
            {
                arrIdx++;
            }


        }

        return seqIdx==sequence.length();
    }
}

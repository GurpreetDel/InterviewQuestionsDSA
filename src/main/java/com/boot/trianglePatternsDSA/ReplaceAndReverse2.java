package com.boot.trianglePatternsDSA;

public class ReplaceAndReverse
{
    public static void main(String[] args) {
        String str="abcdD";
        char oldChar='D';
        char newChar='X';
        String finalResult=reverseandReplace(str ,oldChar, newChar);
        System.out.println(finalResult);
    }

    public static String reverseandReplace(String str,char oldChar,char newChar)
    {
        char[] charArray = str.toCharArray();

        for(int i=0;i<charArray.length;i++)
        {
            if(charArray[i]==oldChar)
            {
                charArray[i]=newChar;
            }
        }

        int left = 0;
        int right = charArray.length-1;

        while(left<right)
        {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }

        return new String(charArray);


    }
}
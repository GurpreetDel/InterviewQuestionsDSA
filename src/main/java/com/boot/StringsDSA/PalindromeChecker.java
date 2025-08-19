// O(n^2) time / O(n) space
public class PalindromeChecker {

    public static boolean isPalindrome(String string) {
        String reversedString = "";
        for (int i = string.length() - 1; i >= 0; i--) {
            reversedString += string.charAt(i);
        }
        return string.equals(reversedString);
    }

    // Test method
    public static void main(String[] args) {
        // Test cases
        System.out.println(isPalindrome("racecar")); // true
        System.out.println(isPalindrome("hello"));   // false
        System.out.println(isPalindrome("madam"));   // true
        System.out.println(isPalindrome(""));        // true
        System.out.println(isPalindrome("a"));       // true
    }
}
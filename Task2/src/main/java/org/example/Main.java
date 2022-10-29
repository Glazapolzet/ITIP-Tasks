package org.example;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Q1:");
        System.out.println(repeat("mice", 5));
        System.out.println(repeat("hello", 3));
        System.out.println(repeat("stop", 1));
        System.out.println();

        System.out.println("Q2:");
        System.out.println(differenceMaxMin(new int[] {10, 4, 1, 4, -10, -50, 32, 21}));
        System.out.println(differenceMaxMin(new int[] {44, 32, 86, 19}));
        System.out.println();

        System.out.println("Q3:");
        System.out.println(isAvgWhole(new int[] {1, 3}));
        System.out.println(isAvgWhole(new int[] {1, 2, 3, 4}));
        System.out.println(isAvgWhole(new int[] {1, 5, 6}));
        System.out.println(isAvgWhole(new int[] {1, 1, 1}));
        System.out.println(isAvgWhole(new int[] {9, 2, 2, 5}));
        System.out.println();

        System.out.println("Q4:");
        System.out.println(Arrays.toString(cumulativeSum(new int[] {1, 2, 3})));
        System.out.println(Arrays.toString(cumulativeSum(new int[] {1, -2, 3})));
        System.out.println(Arrays.toString(cumulativeSum(new int[] {3, 3, -2, 408, 3, 3})));
        System.out.println();

        System.out.println("Q5:");
        System.out.println(getDecimalPlaces("43.20"));
        System.out.println(getDecimalPlaces("400"));
        System.out.println(getDecimalPlaces("3.1"));
        System.out.println();

        System.out.println("Q6:");
        System.out.println(Fibonacci(3));
        System.out.println(Fibonacci(7));
        System.out.println(Fibonacci(12));
        System.out.println();

        System.out.println("Q7:");
        System.out.println(isValid("59001"));
        System.out.println(isValid("853a7"));
        System.out.println(isValid("732 32"));
        System.out.println(isValid("393939"));
        System.out.println();

        System.out.println("Q8:");
        System.out.println(isStrangePair("ratio", "orator"));
        System.out.println(isStrangePair("sparkling", "groups"));
        System.out.println(isStrangePair("bush", "hubris"));
        System.out.println(isStrangePair("", ""));
        System.out.println();

        System.out.println("Q9:");
        System.out.println(isPrefix("automation", "auto-"));
        System.out.println(isSuffix("arachnophobia", "-phobia"));
        System.out.println(isPrefix("retrospect", "sub-"));
        System.out.println(isSuffix("vocation", "-logy"));
        System.out.println();

        System.out.println("Q10:");
        System.out.println(boxSeq(0));
        System.out.println(boxSeq(1));
        System.out.println(boxSeq(2));
    }

    public static StringBuilder repeat(String word, int num) {
        StringBuilder newWord = new StringBuilder();
        for(int i = 0; i < word.length(); i++) {
            newWord.append(String.valueOf(word.charAt(i)).repeat(num));
        }
        return newWord;
    }

    public static int differenceMaxMin(int[] numbers) {
        Arrays.sort(numbers);
        return numbers[numbers.length-1] - numbers[0];
    }

    public static boolean isAvgWhole(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum % nums.length == 0;
    }

    public static int[] cumulativeSum(int[] nums) {
        int[] cumulativeNums = new int[nums.length];
        int sum = 0;

        for (int num = 0; num < nums.length; num++) {
            if(cumulativeNums[0] != nums[0]) {
                sum += nums[0];
                cumulativeNums[0] = nums[0];
            }
            else {
                sum += nums[num];
                cumulativeNums[num] = sum;
            }
        }
        return cumulativeNums;
    }

    public static int getDecimalPlaces(String num) {
        if (num.indexOf('.') == -1)
            return 0;
        return num.substring(num.indexOf('.')+1).split("").length;
    }

    public static int Fibonacci(int num) {
        if (num == 0 || num == 1) {
            return 1;
        }
        return Fibonacci(num-2) + Fibonacci(num-1);
    }

    public static boolean isValid(String postcode) {
        return postcode.matches("\\d{1,5}");
    }

    public static boolean isStrangePair(String firstStr, String secondStr) {
        return  firstStr.length() == 0 && secondStr.length() == 0 || firstStr.split("")[0].equals(secondStr.split("")[secondStr.length() - 1]) && secondStr.split("")[0].equals(firstStr.split("")[firstStr.length() - 1]);
    }

    public static boolean isPrefix(String str, String prefix) {
        return str.startsWith(String.join("", prefix.split("-")));
    }

    public static boolean isSuffix(String str, String suffix) {
        return str.endsWith(String.join("", suffix.split("-")));
    }

    public static int boxSeq(int steps) {
        if(steps % 2 == 0)
            return steps;
        return steps+2;
    }
}
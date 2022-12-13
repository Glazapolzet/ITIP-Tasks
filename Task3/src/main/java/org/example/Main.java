package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Q1");
        System.out.println(solutions(1, 0, -1));
        System.out.println(solutions(1, 0, 0));
        System.out.println(solutions(1, 0, 1));
        System.out.println();

        System.out.println("Q2");
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(findZip("all zip files are compressed"));
        System.out.println();

        System.out.println("Q3");
        System.out.println(checkPerfect(6));
        System.out.println(checkPerfect(28));
        System.out.println(checkPerfect(496));
        System.out.println(checkPerfect(12));
        System.out.println(checkPerfect(97));
        System.out.println();

        System.out.println("Q4");
        System.out.println(flipEndChars("Cat, dog, and mouse."));
        System.out.println(flipEndChars("ada"));
        System.out.println(flipEndChars("Ada"));
        System.out.println(flipEndChars("z"));
        System.out.println();

        System.out.println("Q5");
        System.out.println(isValidHexCode("#CD5C5C"));
        System.out.println(isValidHexCode("#EAECEE"));
        System.out.println(isValidHexCode("#eaecee"));
        System.out.println(isValidHexCode("#CD5C58C"));
        System.out.println(isValidHexCode("#CD5C5Z"));
        System.out.println(isValidHexCode("#CD5C&C"));
        System.out.println(isValidHexCode("CD5C5C"));
        System.out.println();

        System.out.println("Q6");
        System.out.println(same(new int[] {1, 3, 4, 4, 4}, new int[] {2, 5, 7}));
        System.out.println(same(new int[] {9, 8, 7, 6}, new int[] {4, 4, 3, 1}));
        System.out.println(same(new int[] {2}, new int[] {3, 3, 3, 3, 3}));
        System.out.println();

        System.out.println("Q7");
        System.out.println(isKaprekar(3));
        System.out.println(isKaprekar(5));
        System.out.println(isKaprekar(297));
        System.out.println();

        System.out.println("Q8");
        System.out.println(longestZero("01100001011000"));
        System.out.println(longestZero("100100100"));
        System.out.println(longestZero("11111"));
        System.out.println();

        System.out.println("Q9");
        System.out.println(nextPrime(12));
        System.out.println(nextPrime(24));
        System.out.println(nextPrime(11));
        System.out.println();

        System.out.println("Q10");
        System.out.println(rightTriangle(3, 4, 5));
        System.out.println(rightTriangle(145, 105, 100));
        System.out.println(rightTriangle(70, 130, 110));
        System.out.println();
    }

    public static int solutions(int a, int b, int c) {
        double D = Math.pow(b, 2) - 4*a*c;
        if (D < 0)
            return 0;
        else if (D == 0)
            return 1;
        else
            return 2;
    }

    public static int findZip(String str) {
        if(str.contains("zip") && str.indexOf("zip") != str.lastIndexOf("zip")) {
            return str.indexOf("zip", str.indexOf("zip") + 1);
        }
        return -1;
    }

    public static boolean checkPerfect(int num) {
        int[] coefArr = new int[num];
        int coefSum = 0;
        int index = 0;

        for (int coef = 1; coef < num; coef++) {
            if (num % coef == 0) {
                coefArr[index] = coef;
                index++;
            }
        }
        for (int coef : coefArr) {
            coefSum += coef;
        }
        return coefSum == num;
    }

    public static String flipEndChars(String str) {
        if (str.length() < 2) {
            return "Incompatible.";
        }
        else if (str.charAt(0) == str.charAt(str.length()-1)) {
            return "Two's a pair.";
        }
        else {
            return str.charAt(str.length()-1) + str.substring(1, str.length()-1) + str.charAt(0);
        }
    }

    public static boolean isValidHexCode(String hex) {
        return hex.matches("#[0-9a-fA-F]{6}");
    }

    public static boolean same(int[] arr1, int[] arr2) {
        return Arrays.stream(arr1).distinct().toArray().length == Arrays.stream(arr2).distinct().toArray().length;
    }

    public static boolean isKaprekar(int num) {
        String numSquaredStr = String.valueOf(num * num);
        if (numSquaredStr.length() == 1) {
            return num*num == num;
        }
        return Integer.parseInt(numSquaredStr.substring(0, numSquaredStr.length()/2)) +
                Integer.parseInt(numSquaredStr.substring(numSquaredStr.length()/2)) == num;
    }

    public static String longestZero(String bin) {
        return bin.contains("0") ? Arrays.stream(bin.split("1"))
                .reduce((prev, curr) -> prev.length() <= curr.length() ? curr : prev)
                .get() : "";

    }

    public static int nextPrime(int num) {
        boolean isPrime;

        while (true) {
            isPrime = true;
            for(int i = 2; i < num; ++i) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (!isPrime) {
                num++;
            }
            else {
                return num;
            }
        }
    }

    public static boolean rightTriangle(int x, int y, int z) {
        int[] sides = new int[] {x, y, z};
        int hyp = Arrays.stream(sides).max().getAsInt();
        int firstSide = 0;
        int secondSide = 0;

        for (int side : sides) {
            if (side == hyp) {
                continue;
            }

            if (firstSide == 0) {
                firstSide = side;
            }
            else {
                secondSide = side;
            }
        }

        return Math.pow(hyp, 2) == Math.pow(firstSide, 2) + Math.pow(secondSide, 2) &&
                Arrays.stream(sides).min().getAsInt() > 0;
    }
}
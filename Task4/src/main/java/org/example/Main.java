package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println(makeEssay(10,7, "hello my name is Bessie and this is my essay"));
        System.out.println();

        System.out.println(split("((()))(())()()(()())"));
        System.out.println(split("((()))"));
        System.out.println(split("((()))(())()()(()())"));
        System.out.println(split("((())())(()(()()))"));
        System.out.println();

        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toSnakeCase("helloEdabit"));
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("getColor"));
        System.out.println();

        System.out.println(overTime(new double[]{9, 17, 30, 1.5}));
        System.out.println(overTime(new double[]{16, 18, 30, 1.8}));
        System.out.println(overTime(new double[]{13.25, 15, 30, 1.5}));
        System.out.println();

        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(BMI("55 kilos", "1.65 meters"));
        System.out.println(BMI("154 pounds", "2 meters"));
        System.out.println();

        System.out.println(bugger(39));
        System.out.println(bugger(999));
        System.out.println(bugger(4));
        System.out.println();

        System.out.println(toStarShorthand("abbccc"));
        System.out.println(toStarShorthand("77777geff"));
        System.out.println(toStarShorthand("abc"));
        System.out.println(toStarShorthand(""));
        System.out.println();

        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM."));
        System.out.println(doesRhyme("You are off to the races", "a splendid day."));
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));
        System.out.println();

        System.out.println(trouble(451999277, 41177722899L));
        System.out.println(trouble(1222345, 12345));
        System.out.println(trouble(666789, 12345667));
        System.out.println(trouble(33789, 12345337));
        System.out.println();

        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", '$'));
        System.out.println(countUniqueBooks("ZZABCDEF", 'Z'));
        System.out.println();
    }

    public static String makeEssay(int n, int k, String s) {
        String[] words = s.split(" ");
        StringBuilder essay = new StringBuilder();
        int i = 0;
        int spaceLasted;

        while (i < n) {
            essay.append(words[i]);
            spaceLasted = k - words[i].length();
            i += 1;
            while (i < n && words[i].length() <= spaceLasted) {
                essay.append(" ").append(words[i]);
                spaceLasted -= words[i].length();
                i += 1;
            }
            if (i < n) {
                essay.append("\n");
            } else break;
        }

        return essay.toString();
    }

    public static ArrayList<String> split(String s) {
        String[] bracketsList = s.split("");
        ArrayList<String> clastersArray = new ArrayList<>();
        String bracketsLasted = s;
        int openBr = 0;
        int closeBr = 0;
        for (String br : bracketsList) {
            if (Objects.equals(br, "(")) {
                openBr++;
            } else closeBr++;
            if (openBr == closeBr) {
               clastersArray.add(bracketsLasted.substring(0, openBr+closeBr));
               bracketsLasted = bracketsLasted.substring(openBr+closeBr);
               openBr = 0;
               closeBr = 0;
            }
        }
        return clastersArray;
    }

    public static String toCamelCase(String s) {
        String[] substrings = s.split("_");
        char lowerCaseChar;
        for (int i = 1; i < substrings.length; i++) {
            lowerCaseChar = substrings[i].charAt(0);
            substrings[i] = substrings[i].replace(lowerCaseChar, Character.toUpperCase(lowerCaseChar));
        }
        return String.join("", substrings);
    }

    public static String toSnakeCase(String s) {
        String[] upperCaseChars = String.join("", s.split("[a-z]*")).split("");
        String res = s;
        for (String chr : upperCaseChars) {
            res = res.replace(chr, "_" + chr.toLowerCase());
        }
        return res;
    }

    public static String overTime(double[] data) {
            double startTime = data[0];
            double endTime = data[1];
            double pay = data[2];
            double overPay = data[3];

            double normalTimeStart = 9.0;
            double normalTimeEnd = 17.0;

            return "$" + (Math.max(Math.min(endTime, normalTimeEnd) - Math.max(startTime, normalTimeStart), 0)*pay +
                    (Math.max(endTime, normalTimeEnd) - Math.max(startTime, normalTimeEnd))*pay*overPay+
                    (Math.min(endTime, normalTimeStart) - Math.min(startTime, normalTimeStart))*pay*overPay
            );
    }

    public static String BMI(String weight, String height) {
        double kilos = Double.parseDouble(String.join("", weight.split("[a-z]*")));
        double meters = Double.parseDouble(String.join("", height.split("[a-z]*")));

        kilos = weight.contains("pounds") ? kilos/2.205 : kilos;
        meters = height.contains("inches") ? meters/39.37 : meters;

        double bmi = kilos / (meters*meters);

        return bmi < 18.5
                ? String.format("%.1f",bmi) + " Underweight"
                : bmi >= 25
                ? String.format("%.1f",bmi) + " Overweight"
                : String.format("%.1f",bmi) + " Normal weight";
    }

    public static int bugger(int n) {
        String num = String.valueOf(n);
        int iter = 0;
        while(num.length() > 1) {
            num = Arrays.stream(num.split(""))
                    .reduce((prev, curr) -> String.valueOf(Integer.parseInt(prev)*Integer.parseInt(curr)))
                    .get();
            iter++;
        }
        return iter;
    }

    public static String toStarShorthand (String s) {
        String[] chars = s.split("");
        StringBuilder res = new StringBuilder(chars[0]);
        String prevChr = chars[0];
        int r = 0;
        for (int i = 1; i<chars.length; i++) {
            if (Objects.equals(prevChr, chars[i])) {
                r++;
                if (i == chars.length-1) {
                    res.append("*").append(r + 1);
                }
            }
            else {
                if (r != 0) {
                    res.append("*").append(r + 1);
                    r = 0;
                }
                prevChr = chars[i];
                res.append(prevChr);
            }
        }
        return res.toString();
    }

    public static boolean doesRhyme(String s1, String s2) {
        String[] firstVowels = String.join("", s1.substring(s1.lastIndexOf(" ")+1).toLowerCase().split("[^aiyueo]"))
                .split("");
        String[] secondVowels = String.join("", s2.substring(s2.lastIndexOf(" ")+1).toLowerCase().split("[^aiyueo]"))
                .split("");

        ArrayList<String> vowelsToCompare = new ArrayList<>();

        for (String vowel : firstVowels) {
            if (!vowelsToCompare.contains(vowel)) {
                vowelsToCompare.add(vowel);
            }
        }

        for (String vowel : secondVowels) {
            if (!vowelsToCompare.contains(vowel)) {
                return false;
            }
        }
        return true;
    }
    public static boolean trouble(long num1, long num2) {
        String[] str1 = String.valueOf(num1).split("");
        String[] str2 = String.valueOf(num2).split("");
        String repElement1 = "";
        String repElement2 = "";

        String rep;

        for(int i = 1; i < str1.length; i++) {
            rep = str1[i-1];
            if (Objects.equals(rep, str1[i])) {
                repElement1 += rep;
                if (repElement1.length() == 1) {
                    repElement1 += rep;
                }
                continue;
            }
            if (repElement1.matches("^(?<f>[0-9]){3}(?!\\k<f>)")) {
                break;
            } else {
                repElement1 = "";
            }
        }

        for(int i = 1; i < str2.length; i++) {
            rep = str2[i-1];
            if (Objects.equals(rep, str2[i])) {
                repElement2 += rep;
                if (repElement2.length() == 1) {
                    repElement2 += rep;
                }
                continue;
            }
            if (repElement1.contains(repElement2) && repElement2.matches("^(?<f>[0-9]){2}(?!\\k<f>)")) {
                break;
            } else {
                repElement2 = "";
            }
        }

        return repElement1.contains(repElement2) && repElement2.matches("^(?<f>[0-9]){2}(?!\\k<f>)");
    }

    public static int countUniqueBooks(String s, char bookBorder){;
        HashSet<Character> uniqueBooks = new HashSet<>();
        boolean startAdding = false;
        for (char book : s.toCharArray()) {
            if (book == bookBorder) {
                startAdding = !startAdding;
                continue;
            }
            if (startAdding) {
                uniqueBooks.add(book);
            }
        }
        return uniqueBooks.size();
    }
}
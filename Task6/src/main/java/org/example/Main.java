package org.example;

import java.util.*;

import java.util.Stack;
import java.lang.Character;

public class Main {
    public static void main(String[] args) {
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));
        System.out.println();

        System.out.println(translateWord("flag"));
        System.out.println(translateWord("Apple"));
        System.out.println(translateWord("button"));
        System.out.println(translateWord(""));

        System.out.println(translateSentence("I like to eat, honey?? waffles!?"));
        System.out.println(translateSentence("Do you think it is going to rain today?"));
        System.out.println();

        System.out.println(validColor("rgb(0,0,0)"));
        System.out.println(validColor("rgb(0,,0)"));
        System.out.println(validColor("rgb(255,256,255)"));
        System.out.println(validColor("rgba(0,0,0,0.123456789)"));
        System.out.println();

        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
        System.out.println(stripUrlParams("https://edabit.com"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{"b"}));
        System.out.println(stripUrlParams("https://edabit.com", new String[]{"b"}));
        System.out.println();

        System.out.println(getHashTags("How the Avocado Became the Fruit of the Global Trade?"));
        System.out.println(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year"));
        System.out.println(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit"));
        System.out.println(getHashTags("Visualizing Science Science"));
        System.out.println();

        System.out.println(ulam(4));
        System.out.println(ulam(9));
        System.out.println(ulam(206));
        System.out.println();

        System.out.println(longestNonrepeatingSubstring("abcabcbb"));
        System.out.println(longestNonrepeatingSubstring("aaaaaa"));
        System.out.println(longestNonrepeatingSubstring("abcde"));
        System.out.println(longestNonrepeatingSubstring("abcda"));
        System.out.println();

        System.out.println(convertToRoman(2));
        System.out.println(convertToRoman(12));
        System.out.println(convertToRoman(16));
        System.out.println();

//        System.out.println(formula("6 * 4 = 24"));
//        System.out.println(formula("18 / 17 = 2"));
//        System.out.println(formula("16 * 10 = 160 = 14 + 120"));
//        System.out.println();

        System.out.println(palindromeDescendant(11211230));
        System.out.println(palindromeDescendant(13001120));
        System.out.println(palindromeDescendant(23336014));
        System.out.println(palindromeDescendant(11));
    }

    public static int bell(int n) {
        int[][] bell = new int[n + 1][n + 1];
        bell[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            bell[i][0] = bell[i - 1][i - 1];
            for (int j = 1; j <= i; j++)
                bell[i][j] = bell[i - 1][j - 1] + bell[i][j - 1];
        }
        return bell[n][0];
    }

    public static String translateWord(String word) {
        if (word.length() == 0) {
            return word;
        }

        String firstLetter = word.substring(0, 1);

        if (firstLetter.matches("(?i)[^aiyueo]")) {
            String firstVowel = String.join("", word.split("(?i)[^aiyueo]")).split("")[0];
            if (firstLetter.matches("^[A-Z]")) {
                return firstVowel.toUpperCase() +
                        word.substring(word.indexOf(firstVowel) + 1) +
                        word.substring(0, word.indexOf(firstVowel)).toLowerCase() + "ay";
            }
            return word.substring(word.indexOf(firstVowel)) + word.substring(0, word.indexOf(firstVowel)) + "ay";
        }
        return word + "yay";
    }

    public static String translateSentence(String s) {
        String[] sentence = s.split(" ");

        List<String> sentenceWords = new ArrayList<>(Arrays.asList(s.split("\\s|\\.|,|\\?|!")));
        sentenceWords.removeIf(String::isEmpty);

        for (int i = 0; i < sentenceWords.size(); i++) {
            sentence[i] = sentence[i].replace(sentenceWords.get(i), translateWord(sentenceWords.get(i)));
        }

        return String.join(" ", sentence);
    }

    public static boolean validColor(String rgb) {
        return rgb.matches("rgba?\\((0|([1-9]\\d?|2[0-5][0-5]|1\\d{1,2})),(0|([1-9]\\d?|2[0-5][0-5]|1\\d{1,2})),(0|([1-9]\\d?|2[0-5][0-5]|1\\d{1,2}))(,((0.\\d*)|1))?\\)");
    }

    public static String stripUrlParams(String url) {
        boolean paramsNotStated = String.join("", url.split("[^?]")).isEmpty();
        if (paramsNotStated) {
            return url;
        }

        StringBuilder result = new StringBuilder(url.substring(0, url.indexOf("?") + 1));
        HashMap<String, String> unicParams = new HashMap<>();
        String[] params = String.join("", url.substring(url.indexOf("?") + 1).split("[=&0-9]")).split("");
        String[] values = String.join("", url.substring(url.indexOf("?") + 1).split("[=&a-z]")).split("");

        for (int i = 0; i < params.length; i++) {
            unicParams.put(params[i], values[i]);
        }

        for (String key : unicParams.keySet()) {
            result.append(key).append("=").append(unicParams.get(key));
        }

        return result.toString();
    }

    public static String stripUrlParams(String url, String[] paramsToStrip) {
        boolean paramsNotStated = String.join("", url.split("[^?]")).isEmpty();
        if (paramsNotStated) {
            return url;
        }

        StringBuilder result = new StringBuilder(url.substring(0, url.indexOf("?") + 1));
        HashMap<String, String> unicParams = new HashMap<>();
        String[] params = String.join("", url.substring(url.indexOf("?") + 1).split("[=&0-9]")).split("");
        String[] values = String.join("", url.substring(url.indexOf("?") + 1).split("[=&a-z]")).split("");

        for (int i = 0; i < params.length; i++) {
            if (String.join("", paramsToStrip).contains(params[i])) {
                continue;
            }
            unicParams.put(params[i], values[i]);
        }

        for (String key : unicParams.keySet()) {
            result.append(key).append("=").append(unicParams.get(key));
        }

        return result.toString();
    }

    public static String getHashTags(String title) {
        String[] titleWords = String.join(" ", title.split("(?i)[^a-z]")).split("\\s+");
        ArrayList<String> hashTags = new ArrayList<>();

        //если размер заголовка меньше или равен 3
        if (titleWords.length <= 3) {
            String longestWord;
            while (hashTags.size() < titleWords.length) {
                longestWord = "";
                //TODO:
                for (String titleWord : titleWords) {
                    if (hashTags.contains("#" + titleWord.toLowerCase())) {
                        continue;
                    }
                    longestWord = longestWord.length() <= titleWord.length() ? titleWord : longestWord;
                }
                hashTags.add("#" + longestWord.toLowerCase());
            }
            return hashTags.toString();
        }

        HashMap<Integer, String> sortedWordsWithLength = new HashMap<>();

        for (int i = titleWords.length - 1; i >= 0; i--) {
            sortedWordsWithLength.put(titleWords[i].length(), titleWords[i]);
        }

        Object[] sortedWords = sortedWordsWithLength.values().toArray();

        int i = sortedWords.length - 1;
        int wordsNeeded = 3;
        while (wordsNeeded > 0 && i >= 0) {
            hashTags.add("#" + ((String) sortedWords[i]).toLowerCase());
            i--;
            wordsNeeded--;
        }

        return hashTags.toString();
    }

    public static int ulam(int n) {
        int MAX = 3000;
        ArrayList<Integer> ulamNumbers = new ArrayList<>();

        ulamNumbers.add(1);
        ulamNumbers.add(2);

        if (n == 1) {
            return ulamNumbers.get(0);
        } else if (n == 2) {
            return ulamNumbers.get(1);
        }

        //генерируем последовательность чисел улама вплоть до нужного нам числа
        for (int i = 3; i < MAX; i++) {

            if(n == ulamNumbers.size()) {
                return ulamNumbers.get(n-1);
            }

            int count = 0;

            for (int j = 0; j < ulamNumbers.size() - 1; j++) {
                for (int k = j + 1; k < ulamNumbers.size(); k++) {
                    if (ulamNumbers.get(j) + ulamNumbers.get(k) == i) {
                        count++;
                    }
                    if (count > 1)
                        break;
                }
                if (count > 1)
                    break;
            }

            if (count == 1) {
                ulamNumbers.add(i);
            }
        }
        return 0;
    }

    public static String longestNonrepeatingSubstring(String s) {
        ArrayList<String> elements = new ArrayList<>(List.of(s.split("")));
        LinkedHashSet<String> longestNonrepeatingElements = new LinkedHashSet<>();
        LinkedHashSet<String> currentNonrepeatingElements = new LinkedHashSet<>();

        if (elements.size() <= 1) {
            return elements.toString();
        }

        while (elements.size() > 1) {
            for(String el : elements) {
                if(currentNonrepeatingElements.contains(el)) {
                    s = s.substring(s.indexOf(el)+1);
                    elements.clear();
                    elements.addAll(List.of(s.split("")));
                    if(currentNonrepeatingElements.size() > longestNonrepeatingElements.size()) {
                        longestNonrepeatingElements.clear();
                        longestNonrepeatingElements.addAll(currentNonrepeatingElements);
                    }
                    currentNonrepeatingElements.clear();
                    break;
                }
                currentNonrepeatingElements.add(el);
            }
        }
        return longestNonrepeatingElements.toString();
    }

    public static String convertToRoman(int num) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return thousands[num / 1000] + hundreds[(num % 1000) / 100] + tens[(num % 100) / 10] + units[num % 10];
    }

    //TODO: польская запись ?
    public static String formula(String f) {
        String fo = String.join("", f.split("\s=.*"));
        System.out.println(fo);
//        \s?\d+\s?
//        String signsWithoutForbiddenPatterns = String.join("", signs.split("[/+=\\-*]{2,}|^[/+=\\-*]|=[^=]="));
//        return infixToRpn(fo);
        return "";
    }

//    public static boolean letterOrDigit(char c) {
//    // boolean check
//        if (Character.isLetterOrDigit(c))
//            return true;
//        else
//            return false;
//    }
//
//    // Operator having higher precedence
//    // value will be returned
//    public static int getPrecedence(char ch) {
//        if (ch == '+' || ch == '-')
//            return 1;
//        else if (ch == '*' || ch == '/')
//            return 2;
//        else if (ch == '^')
//            return 3;
//        else
//            return -1;
//    }
//
//    // Operator has Left --> Right associativity
//    public static boolean hasLeftAssociativity(char ch) {
//        if (ch == '+' || ch == '-' || ch == '/' || ch == '*') {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    // Method converts  given infixto postfix expression
//    // to illustrate shunting yard algorithm
//    public static String infixToRpn(String expression) {
//        // Initialising an empty String
//        // (for output) and an empty stack
//        Stack<Character> stack = new Stack<>();
//
//        // Initially empty string taken
//        String output = new String("");
//
//        // Iterating over tokens using inbuilt
//        // .length() function
//        for (int i = 0; i < expression.length(); ++i) {
//            // Finding character at 'i'th index
//            char c = expression.charAt(i);
//
//            // If the scanned Token is an
//            // operand, add it to output
//            if (letterOrDigit(c))
//                output += c;
//
//            // If the scanned Token is an '('
//            // push it to the stack
//            else if (c == '(')
//                stack.push(c);
//
//            // If the scanned Token is an ')' pop and append
//            // it to output from the stack until an '(' is
//            // encountered
//            else if (c == ')') {
//                while (!stack.isEmpty() && stack.peek() != '(') {
//                    output += stack.pop();
//                }
//                stack.pop();
//            }
//
//            // If an operator is encountered then taken the
//            // further action based on the precedence of the
//            // operator
//
//            else {
//                while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek()) && hasLeftAssociativity(c)) {
//                    // peek() inbuilt stack function to
//                    // fetch the top element(token)
//                    output += stack.pop();
//                }
//                stack.push(c);
//            }
//        }
//
//        // pop all the remaining operators from
//        // the stack and append them to output
//        while (!stack.isEmpty()) {
//            if (stack.peek() == '(') {
//                return "This expression is invalid";
//            }
//            output += stack.pop();
//        }
//        return output;
//    }

    public static boolean palindromeDescendant(long num) {
        String s = String.valueOf(num);

        while (s.length() > 1) {
            boolean checkState = isPalindrome(s);
            if(checkState) {
                return true;
            }
            s = makeSumOfPairs(s);
        }

        return false;
    }

    public static String reverseString(String str) {
        StringBuilder reversedStr = new StringBuilder();

        for(int i = str.length() - 1; i >= 0; --i) {
            reversedStr.append(str.charAt(i));
        }

        return reversedStr.toString();
    }

    public static boolean isPalindrome(String s) {
        return s.equals(reverseString(s));
    }

    public static String makeSumOfPairs(String num) {
        String[] digits = num.split("");
        ArrayList<String> descendantNum = new ArrayList<>();

        for(int i = 0; i < digits.length-1; i+=2){
            descendantNum.add(String.valueOf(Integer.parseInt(digits[i]) + Integer.parseInt(digits[i+1])));
        }

        return String.join("", descendantNum);
    }
}


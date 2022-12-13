package org.example;

import java.util.*;
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

public class Main {
    public static void main(String[] args) {
        System.out.println(encrypt("Hello"));
        System.out.println(decrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println(encrypt("Sunshine"));
        System.out.println();

        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));
        System.out.println();

        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(canComplete("butlz", "beautiful"));
        System.out.println(canComplete("tulb", "beautiful"));
        System.out.println(canComplete("bbutl", "beautiful"));
        System.out.println();

        System.out.println(sumDigProd(16, 28));
        System.out.println(sumDigProd(0));
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6));
        System.out.println();

        System.out.println(sameVowelGroup(new String[]{"toe", "ocelot", "maniac"}));
        System.out.println(sameVowelGroup(new String[]{"many", "carriage", "emit", "apricot", "animal"}));
        System.out.println(sameVowelGroup(new String[]{"hoops", "chuff", "bot", "bottom"}));
        System.out.println();

        System.out.println(validateCard(1234567890123456L));
        System.out.println(validateCard(1234567890123452L));
        System.out.println();

        System.out.println(numToRus(0));
        System.out.println(numToRus(18));
        System.out.println(numToRus(59));
        System.out.println(numToRus(104));
        System.out.println(numToRus(126));
        System.out.println(numToRus(909));
        System.out.println(numToRus(999));

        System.out.println(numToEng(0));
        System.out.println(numToEng(18));
        System.out.println(numToEng(59));
        System.out.println(numToEng(104));
        System.out.println(numToEng(126));
        System.out.println(numToEng(909));
        System.out.println(numToEng(999));
        System.out.println();

        System.out.println(getSha256Hash("password123"));
        System.out.println(getSha256Hash("Fluffy@home"));
        System.out.println(getSha256Hash("Hey dude!"));
        System.out.println();

        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        System.out.println(correctTitle("I DIDN'T WATCH THE-GAME-OF-THRONES."));
        System.out.println();

        System.out.println(hexLattice(1));
        System.out.println(hexLattice(7));
        System.out.println(hexLattice(19));
        System.out.println(hexLattice(61));
    }

    public static String encrypt(String message) {
        char[] chars = message.toCharArray();
        int[] encryptedChars = new int[chars.length];
        encryptedChars[0] = chars[0];
        for (int i = 1; i < chars.length; i++) {
            encryptedChars[i] = chars[i] - chars[i-1];
        }
        return Arrays.toString(encryptedChars);
    }

    public static String decrypt(int[] encryptedChars) {
        char[] chars = new char[encryptedChars.length];
        chars[0] = (char) encryptedChars[0];
        for (int i = 1; i < encryptedChars.length; i++) {
            chars[i] = (char) (chars[i-1] + encryptedChars[i]);
        }
        return String.valueOf(chars);
    }

    public static String[][] getBoard() {
        ArrayList<Character> cols = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'));
        ArrayList<Integer> rows = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

        int boardSize = 8;
        String[][] board = new String[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            Character col = cols.get(i);
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = col + String.valueOf(rows.get(j));
            }
        }
        return board;
    }

    public static int getRowPosition(String item) {
        String[][] board = getBoard();
        for (int i = 0; i < board.length; i++) {
            if (Arrays.asList(board[i]).contains(item)) {
                return i;
            }
        }
        return 0;
    }

    public static int getColPosition(String item) {
        String[][] board = getBoard();
        String[] row = board[getRowPosition(item)];
        for (int i = 0; i < row.length; i++) {
            if (row[i].equals(item)) {
                return i;
            }
        }
        return 0;
    }

    public static boolean pawn(int startPosX, int startPosY, int posX, int posY){
        return startPosX == posX && startPosY + 1 == posY;
    }
    public static boolean king (int startPosX, int startPosY, int posX, int posY){
        return Math.abs(startPosX - posX) <= 1 &&  Math.abs(startPosY - posY) <= 1;
    }
    public static boolean rook(int startPosX, int startPosY, int posX, int posY){
        return startPosX == posX || startPosY == posY;
    }
    public static boolean bishop(int startPosX, int startPosY, int posX, int posY){
        return Math.abs(startPosX - posX) ==  Math.abs(startPosY - posY);
    }
    public static boolean queen(int startPosX, int startPosY, int posX, int posY){
        return Math.abs(startPosX - posX) ==  Math.abs(startPosY - posY) ||
                startPosX == posX || startPosY == posY;
    }
    public static boolean knight (int startPosX, int startPosY, int posX, int posY){
        return Math.abs(startPosX - posX) == 1 && Math.abs(startPosY - posY) == 2 ||
                Math.abs(startPosX - posX) == 2 && Math.abs(startPosY - posY) == 1;
    }

    public static boolean canMove(String figureName, String start, String end) {
        return switch (figureName) {
            case "Pawn" -> pawn(
                    getColPosition(start), getRowPosition(start),
                    getColPosition(end), getRowPosition(end)
            );
            case "Rook" -> rook(
                    getColPosition(start), getRowPosition(start),
                    getColPosition(end), getRowPosition(end)
            );
            case "King" -> king(
                    getColPosition(start), getRowPosition(start),
                    getColPosition(end), getRowPosition(end)
            );
            case "Bishop" -> bishop(
                    getColPosition(start), getRowPosition(start),
                    getColPosition(end), getRowPosition(end)
            );
            case "Knight" -> knight(
                    getColPosition(start), getRowPosition(start),
                    getColPosition(end), getRowPosition(end)
            );
            case "Queen" -> queen(
                    getColPosition(start), getRowPosition(start),
                    getColPosition(end), getRowPosition(end)
            );
            default -> false;
        };
    }

    public static boolean canComplete(String borders, String word) {
        ArrayList<String> bordersGroup = new ArrayList<>(Arrays.asList(borders.split("")));
        String[] charsGroup = word.split("");
        boolean isCompleted = false;

        for (String chr : charsGroup) {
            if (!bordersGroup.isEmpty() && Objects.equals(bordersGroup.get(0), chr)) {
                bordersGroup.remove(0);
                isCompleted = true;
            } else {
                isCompleted = false;
            }
        }

        return isCompleted && bordersGroup.isEmpty();
    }

    public static int sumDigProd(int ...nums) {
        String defaultDigit = String.valueOf(Arrays.stream(nums).reduce(0, Integer::sum));
        while (defaultDigit.length() > 1) {
            defaultDigit = Arrays.stream(defaultDigit.split(""))
                    .reduce((prev, curr) -> String.valueOf(Integer.parseInt(prev)*Integer.parseInt(curr)))
                    .get();
        }
        return Integer.parseInt(defaultDigit);
    }

    public static String sameVowelGroup(String[] words) {
        String[] firstWordVowels = String.join("", words[0].toLowerCase().split("[^aiyueo]")).split("");
        String[] otherVowels;

        boolean hasSameVowels = false;

        ArrayList<String> vowelsToCompare = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        for (String vowel : firstWordVowels) {
            if (!vowelsToCompare.contains(vowel)) {
                vowelsToCompare.add(vowel);
            }
        }

        for (String word : words) {
            otherVowels = String.join("", word.toLowerCase().split("[^aiyueo]")).split("");
            for (String vowel : otherVowels) {
                if (!vowelsToCompare.contains(vowel)) {
                    hasSameVowels = false;
                    break;
                }
                hasSameVowels = true;
            }
            if (hasSameVowels) {
                result.add(word);
            }
        }

        return Arrays.toString(result.toArray());
    }

    public static boolean validateCard(long cardNumber) {
        ArrayList<String> digits = new ArrayList<>(List.of(String.valueOf(cardNumber).split("")));
        ArrayList<String> reversedDigits = new ArrayList<>();

        int checkDigit = Integer.parseInt(digits.remove(digits.size()-1));

        String doubledDigit;
        int sum = 0;
        String[] sumDigits;

        for (int i = digits.size()-1; i >= 0; i--) {
            reversedDigits.add(digits.get(i));
        }

        for (int i = 0; i < reversedDigits.size(); i++) {
            if (i % 2 != 0) {
                doubledDigit = String.valueOf(Integer.parseInt(reversedDigits.get(i)) * 2);
                if (doubledDigit.length() > 1) {
                    doubledDigit = Arrays.stream(doubledDigit.split(""))
                            .reduce((prev, curr) -> String.valueOf(Integer.parseInt(prev)+Integer.parseInt(curr))).get();
                }
                reversedDigits.set(i, doubledDigit);
            }
            sum += Integer.parseInt(reversedDigits.get(i));
        }

        sumDigits = String.valueOf(sum).split("");

        return 10 - Integer.parseInt(sumDigits[sumDigits.length-1]) == checkDigit;
    }

    public static String numToEng(int num) {
        int unit = num % 10;
        int ten = num % 100;
        int tenDigit = num % 100 / 10;
        int deca = num / 100;

        Map<Integer, String> unitsNames = Map.of(
                0, "zero",
                1, "one",
                2, "two",
                3, "three",
                4, "four",
                5, "five",
                6, "six",
                7, "seven",
                8, "eight",
                9, "nine"
        );
        Map<Integer, String> tensNames = Map.of(
                10, "ten",
                11, "eleven",
                12, "twelve",
                13, "thirteen",
                14, "fourteen",
                15, "fifteen",
                16, "sixteen",
                17, "seventeen",
                18, "eighteen",
                19, "nineteen"
        );
        Map<Integer, String> decaNames = Map.of(
                2, "twenty",
                3, "thirty",
                4, "forty",
                5, "fifty",
                6, "sixty",
                7, "seventy",
                8, "eighty",
                9, "ninety"
        );

        if(deca != 0) {
            if(ten < 20) {
                return unitsNames.get(deca) + " hundred " + (tensNames.containsKey(ten)
                        ? tensNames.get(ten)
                        : unitsNames.get(unit));
            } else {
                return unitsNames.get(deca) + " hundred " + decaNames.get(tenDigit) + " " + unitsNames.get(unit);
            }
        }
        else if(tenDigit != 0) {
            if(ten < 20) {
                return tensNames.get(ten);
            } else {
                return decaNames.get(tenDigit) + " " + unitsNames.get(unit);
            }
        } else {
            return unitsNames.get(unit);
        }
    }

    public static String numToRus(int num) {
        int unit = num % 10;
        int ten = num % 100;
        int tenDigit = num % 100 / 10;
        int deca = num / 100;

        Map<Integer, String> unitsNames = Map.of(
                0, "ноль",
                1, "один",
                2, "два",
                3, "три",
                4, "четыре",
                5, "пять",
                6, "шесть",
                7, "семь",
                8, "восемь",
                9, "девять"
        );
        Map<Integer, String> tensNames = Map.of(
                10, "десять",
                11, "одиннадцать",
                12, "двенадцать",
                13, "тринадцать",
                14, "четырнадцать",
                15, "пятнадцать",
                16, "шестнадцать",
                17, "семнадцать",
                18, "восемнадцать",
                19, "девятнадцать"
        );
        Map<Integer, String> decaNames = Map.of(
                2, "двадцать",
                3, "тридцать",
                4, "сорок",
                5, "пятьдесят",
                6, "шестьдесят",
                7, "семьдесят",
                8, "восемьдесят",
                9, "девяносто"
        );
        Map<Integer, String> hundredNames = Map.of(
                1, "сто",
                2, "двести",
                3, "триста",
                4, "четыреста",
                5, "пятьсот",
                6, "шестьсот",
                7, "семьсот",
                8, "восемьсот",
                9, "девятьсот"
        );

        if (deca != 0) {
            if (ten < 20) {
                return hundredNames.get(deca) + " " + (tensNames.containsKey(ten)
                        ? tensNames.get(ten)
                        : unitsNames.get(unit));
            } else {
                return hundredNames.get(deca) + " " + decaNames.get(tenDigit) + " " + unitsNames.get(unit);
            }
        } else if (tenDigit != 0) {
            if (ten < 20) {
                return tensNames.get(ten);
            } else {
                return decaNames.get(tenDigit) + " " + unitsNames.get(unit);
            }
        } else {
            return unitsNames.get(unit);
        }
    }

    public static HashCode getSha256Hash(String password) {

        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(password, Charsets.UTF_8);
        HashCode sha256 = hasher.hash();

        return sha256;
    }

    public static String correctTitle(String title) {
        String[] words = title.split(" ");
        String[] splittedWord;
        for (int i = 0; i < words.length; i++) {
            if(words[i].matches("(?i)^(in|and|the|of)$")) {
                words[i] = words[i].toLowerCase();
                continue;
            }
            if(words[i].contains("-")) {
                splittedWord = words[i].split("-");
                for(int j = 0; j < splittedWord.length; j++) {
                    splittedWord[j] = splittedWord[j].substring(0, 1).toUpperCase() + splittedWord[j].substring(1).toLowerCase();
                }
                words[i] = String.join("-", splittedWord);
                continue;
            }
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }
        return String.join(" ", words);
    }

    public static String hexLattice(int number) {
        int n = 1;
        int hexSize = 1;
        String hex = "";
        int firstLineWidth;
        int currLineWidth;

        while(3*n*(n-1) + 1 < number) {
            n++;
        }

        if(3*n*(n-1) + 1 != number) {
            return "Invalid";
        }

        if(n > 1) {
            for(int i = 1; i<n; i++) {
                hexSize = hexSize+2;
            }
        }

        firstLineWidth = hexSize - (hexSize-1)/2;
        currLineWidth = firstLineWidth-1;

        while(currLineWidth < hexSize) {
            currLineWidth++;
            hex += " ".repeat(hexSize-currLineWidth);
            hex += "o ".repeat(currLineWidth);
            hex += "\n";
        }

        while(currLineWidth > firstLineWidth) {
            currLineWidth--;
            hex += " ".repeat(hexSize-currLineWidth);
            hex += "o ".repeat(currLineWidth);
            hex += "\n";
        }

        return hex;
    }
}
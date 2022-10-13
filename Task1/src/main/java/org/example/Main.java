package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Q1");
        System.out.println(remainder(1, 3));
        System.out.println(remainder(3, 4));
        System.out.println(remainder(-9, 45));
        System.out.println(remainder(5, 5) + "\n");

        System.out.println("Q2");
        System.out.println(triArea(3, 2));
        System.out.println(triArea(7, 4));
        System.out.println(triArea(10, 10) + "\n");

        System.out.println("Q3");
        System.out.println(animals(2, 3, 5));
        System.out.println(animals(1, 2, 3));
        System.out.println(animals(5, 2, 8) + "\n");

        System.out.println("Q4");
        System.out.println(profitableGamble(0.2,50,9));
        System.out.println(profitableGamble(0.9,1,2));
        System.out.println(profitableGamble(0.9,3,2) + "\n");

        System.out.println("Q5");
        System.out.println(operation(24,15,9));
        System.out.println(operation(24,26,2));
        System.out.println(operation(15,11,11) + "\n");

        System.out.println("Q6");
        System.out.println(ctoa('A'));
        System.out.println(ctoa('m'));
        System.out.println(ctoa('['));
        System.out.println(ctoa('\\') + "\n");

        System.out.println("Q7");
        System.out.println(addUpTo(3));
        System.out.println(addUpTo(10));
        System.out.println(addUpTo(7) + "\n");

        System.out.println("Q8");
        System.out.println(nextEdge(8, 10));
        System.out.println(nextEdge(5, 7));
        System.out.println(nextEdge(9, 2) + "\n");

        System.out.println("Q9");
        int[] a = {1, 5, 9};
        System.out.println(sumOfCubes(a));
        int[] b = {3, 4, 5};
        System.out.println(sumOfCubes(b));
        int[] c = {2};
        System.out.println(sumOfCubes(c));
        int[] d = {};
        System.out.println(sumOfCubes(d) + "\n");

        System.out.println("Q10");
        System.out.println(abcmath(42,5,10));
        System.out.println(abcmath(5,2,1));
        System.out.println(abcmath(1,2,3));
    }

    public static int remainder(int num1, int num2) {
        return num1 % num2;
    }

    public static int triArea(int a, int h) {
        return (a * h)/2;
    }

    public static int animals(int chickens, int cows, int pigs) {
        return chickens*2 + cows*4 + pigs*4;
    }

    public static boolean profitableGamble(double prob, int prize, int pay) {
        return prob * prize > pay;
    }

    public static String operation(int N, int a, int b) {
        if (a-b == N) {
            return "subtracted";
        } else if (a+b == N) {
            return "added";

        } else
            return "none";
    }

    public static int ctoa(char c) {
        return c;
    }

    public static int addUpTo(int numBound) {
        int addResult = 0;
        for(int i = 1; i<=numBound; i++) {
            addResult += i;
        }
        return addResult;
    }

    public static int nextEdge(int a, int b) {
        return a + b - 1;
    }

    public static int sumOfCubes(int[] numbers) {
        int cubeSum = 0;
        if(numbers.length == 0)
            return 0;
        for(int i = 0; i <= numbers.length - 1; i++) {
            cubeSum += (int) Math.pow(numbers[i], 3);
        }
        return cubeSum;
    }

    public static boolean abcmath(int a, int b, int c) {
        for(int i = 1; i<=b; i++) {
            a += a;
        }
        return a%c == 0;
    }
}
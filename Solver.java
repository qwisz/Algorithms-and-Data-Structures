package seminar1;

import seminar1.collections.LinkedStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.DoubleSummaryStatistics;

/**
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = 101
 * ( 1 + ( 5 * ( 4 * 5 ) ) ) ( 1 + ( 5 * 20 ) ) = 101
 * ( 1 + 100 ) = 101
 *
 * Считаем, что операции деления на ноль отсутствуют
 */
public class Solver {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN   = '(';
    private static final char RIGHT_PAREN  = ')';
    private static final char PLUS         = '+';
    private static final char MINUS        = '-';
    private static final char TIMES        = '*';
    private static final char DIVISION     = '/';

    private static double evaluate(String[] values) {
        /* TODO: implement it */
        // Double.valueOf(values[i])
        LinkedStack<String> ls = new LinkedStack<>();
        int count = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i].charAt(0)  == RIGHT_PAREN) {
                double b = Double.parseDouble(ls.pop());
                String op = ls.pop();
                double a = Double.parseDouble(ls.pop());
                ls.push(calc(a, b, op));
            }
            else {
                if (!(values[i].charAt(0) == LEFT_PAREN))
                    ls.push(values[i]);
            }
        }
        return Double.parseDouble(ls.pop());
    }

    private static String calc(double a, double b, String op) {
        double result = 0;
        switch (op) {
            case "+": result = a + b;
                break;
            case "-": result = a - b;
                break;
            case "*": result = a * b;
                break;
            case "/": result = a / b;
                break;
            default:
                System.out.println("Error");
                break;
        }
        return Double.toString(result);
    }


    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(evaluate(sequence.split(" ")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

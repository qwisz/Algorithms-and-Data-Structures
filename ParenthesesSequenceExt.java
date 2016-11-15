package seminar1;

import seminar1.collections.LinkedStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. пустая строка — правильная скобочная последовательность;
 * 2. правильная скобочная последовательность,
 *      взятая в скобки одного типа — правильная скобочная последовательность;
 * 3. правильная скобочная последовательность,
 *      к которой приписана слева или справа правильная скобочная последовательность
 *      — тоже правильная скобочная последовательность.
 */
public class ParenthesesSequenceExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN     = '(';
    private static final char RIGHT_PAREN    = ')';
    private static final char LEFT_BRACE     = '{';
    private static final char RIGHT_BRACE    = '}';
    private static final char LEFT_BRACKET   = '[';
    private static final char RIGHT_BRACKET  = ']';

    // sequence = "()()" | "(({}[]))[[[" | "{}" | ...
    private static boolean isBalanced(String sequence) {
        /* TODO: implement it */
        LinkedStack<Character> ls = new LinkedStack<>();
        char temp;
        for (int i = 0; i < sequence.length(); i++) {
            if (sequence.charAt(i) == LEFT_BRACE || sequence.charAt(i) == LEFT_BRACKET || sequence.charAt(i) == LEFT_PAREN)
                ls.push(sequence.charAt(i));
            else {
                if (ls.size() == 0)
                    return false;
                temp = ls.pop();
                if ((sequence.charAt(i) == RIGHT_BRACE && temp != LEFT_BRACE)
                        || (sequence.charAt(i) == RIGHT_BRACKET && temp != LEFT_BRACKET)
                        || (sequence.charAt(i) == RIGHT_PAREN && temp != LEFT_PAREN))
                    return false;
            }
        }
        if (ls.size() != 0)
            return false;
        return true;
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(isBalanced(sequence) ? "YES" : "NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.github.r351574nc3.amex.assignment3;

/**
 * Solve quadratic equations in standard form.
 *
 */
public class App {
    public static void main(final String ... args) {
        if (args.length > 0) {
            final QuadraticFunction function = QuadraticFunctionFactory.getInstance().newFunction(args[0]);
            final Double[] solution = function.solve();
            System.out.println(String.format("Solution: (%s, %s)", solution[0], solution[1]));
        }
        System.exit(0);
    }
}

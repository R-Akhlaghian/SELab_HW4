package calculator;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int squareRootOfDivision(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return (int) Math.sqrt((double) a / b);
    }

}

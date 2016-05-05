import entities.PlainEquation;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        parser.parse();
        List<PlainEquation> equations = parser.getEquations();
        Counter counter = new Counter(equations);
        counter.count();
        System.out.println(Arrays.toString(counter.getPoint()));
        System.out.println(counter.getValue());
    }
}

import entities.PlainEquation;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by artvi on 05/05/2016.
 */
public class Counter {
    private static final int NUMBER_IN_GROUP = 4;

    private List<PlainEquation> equations;
    private double[] point;
    private double value;

    public Counter(List<PlainEquation> equations) {
        this.equations = equations;
    }

    public void count() {
        LinearObjectiveFunction function = new LinearObjectiveFunction(new double[] {20, NUMBER_IN_GROUP * 3, 50}, 0);
        Collection<LinearConstraint> constraints = new ArrayList<>();
        for (PlainEquation equation : equations) {
            constraints.add(new LinearConstraint(equation.getALine(), Relationship.GEQ, equation.getB()));
        }
        SimplexSolver simplexSolver = new SimplexSolver();
        PointValuePair solution = simplexSolver.optimize(
                new MaxIter(100),
                function,
                new LinearConstraintSet(constraints),
                GoalType.MAXIMIZE,
                new NonNegativeConstraint(true));
        point = solution.getPoint();
        value = solution.getValue();
    }

    public double[] getPoint() {
        return point;
    }

    public double getValue() {
        return value;
    }
}

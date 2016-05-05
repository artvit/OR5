package entities;

/**
 * Created by artvi on 04/05/2016.
 */
public class PlainEquation {
    private double a;
    private double b;
    private double c;
    private double d;

    public PlainEquation(Point p1, Point p2, Point p3) {
        this.a = p1.getY() * (p2.getZ() - p3.getZ())
                + p2.getY() * (p3.getZ() - p1.getZ())
                + p3.getY() * (p1.getZ() - p2.getZ());
        this.b = p1.getZ() * (p2.getX() - p3.getX())
                + p2.getZ() * (p3.getX() - p1.getX())
                + p3.getZ() * (p1.getX() - p2.getX());
        this.c = p1.getX() * (p2.getY() - p3.getY())
                + p2.getX() * (p3.getY() - p1.getY())
                + p3.getX() * (p1.getY() - p2.getY());
        this.d = -(p1.getX() * (p2.getY() * p3.getZ() - p3.getY() * p2.getZ())
                + p2.getX() * (p3.getY() * p1.getZ() - p1.getY() * p3.getZ())
                + p3.getX() * (p1.getY() * p2.getZ() - p2.getY() * p1.getZ()));
    }

    public double count(Point p) {
        return a * p.getX() + b * p.getY() + c * p.getZ() + d;
    }

    public int compare(Point p) {
        return (int) this.count(p);
    }

    public double[] getALine() {
        return new double[] {a, b, c};
    }

    public double getB() {
        return -d;
    }
}

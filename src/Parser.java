import entities.PlainEquation;
import entities.Point;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private List<PlainEquation> equations;

    public void parse() {
        Path path = Paths.get("p000001 (1).brs");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            int mode = 0;
            String line = null;
            List<Point> points = new ArrayList<>();
            equations = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (mode == 1) {
                    String[] strings = line.split(" ");
                    if (strings.length != 3) {
                        mode = 0;
                        continue;
                    }
                    points.add(new Point(strings[0], strings[1], strings[2]));
                } else if (mode == 2) {
                    String[] strings = line.split(" ");
                    if (strings.length != 3) {
                        mode = 0;
                        continue;
                    }
                    int i = Integer.parseInt(strings[0]);
                    int j = Integer.parseInt(strings[1]);
                    int k = Integer.parseInt(strings[2]);
                    if (points.get(i).validate() || points.get(j).validate() || points.get(k).validate()) {
                        equations.add(new PlainEquation(points.get(i), points.get(j), points.get(k)));
                    }
                } else if (line.contains("vertices")) {
                    mode = 1;
                } else if (line.contains("triangles")) {
                    mode = 2;
                }
            }
            System.out.println(points.size());
            System.out.println(equations.size());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    public List<PlainEquation> getEquations() {
        return equations;
    }
}

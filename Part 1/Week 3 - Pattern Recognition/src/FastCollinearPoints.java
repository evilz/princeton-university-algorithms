import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Vincent on 21/02/2016.
 */
public class FastCollinearPoints {

    private ArrayList<LineSegment> lineSegments = new ArrayList<>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        for (Point origine : points) {
            Arrays.sort(points, origine.slopeOrder());


        }

        lineSegments.add(new LineSegment(points[0], points[1]));

    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] array = lineSegments.toArray(new LineSegment[lineSegments.size()]);
        return array;
    }
}

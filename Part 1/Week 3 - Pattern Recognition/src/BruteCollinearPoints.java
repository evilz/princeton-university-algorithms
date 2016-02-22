import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Vincent on 21/02/2016.
 */
public class BruteCollinearPoints {

    private ArrayList<LineSegment> lineSegments = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        for (int p = 0; p < points.length - 3; p++) {
            Point pointP = points[p];

            for (int q = p + 1; q < points.length - 2; q++) {
                Point pointQ = points[q];

                for (int r = q + 1; r < points.length - 1; r++) {
                    Point pointR = points[r];

                    for (int s = r + 1; s < points.length; s++) {

                        Point pointS = points[s];
                        if (pointP == null) {
                            throw new NullPointerException();
                        }
                        if (pointQ == null) {
                            throw new NullPointerException();
                        }
                        if (pointR == null) {
                            throw new NullPointerException();
                        }
                        if (pointS == null) {
                            throw new NullPointerException();
                        }

                        if (pointP == pointQ || pointP == pointR || pointP == pointS) {
                            throw new IllegalArgumentException();
                        }

                        double slopePQ = pointP.slopeTo(pointQ);
                        double slopePR = pointP.slopeTo(pointR);
                        double slopePS = pointP.slopeTo(pointS);
                        if (slopePQ == slopePR && slopePQ == slopePS) {

                            Point[] pointInLine = new Point[4];
                            pointInLine[0] = pointP;
                            pointInLine[1] = pointQ;
                            pointInLine[2] = pointR;
                            pointInLine[3] = pointS;
                            Arrays.sort(pointInLine);

                            for (int i = 0; i < pointInLine.length - 1; i++) {
                                lineSegments.add(new LineSegment(pointInLine[i], pointInLine[i+1]));
                            }

                        }
                    }
                }
            }
        }
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

    public static void main(String[] args) {

        args = new String[]{"C:\\Users\\Vincent\\Documents\\GITHUB\\princeton-university-algorithms\\Part 1\\Week 3 - Pattern Recognition\\src\\input8.txt"};
        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }
}
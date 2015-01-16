/* COPYRIGHT (C) 2015 Puncak Tegap Sdn Bhd. All Rights Reserved. */

package com.test.robot;


/**
 * @author Domu Ansor Harahap
 */
public class Point {

    int x;
    int y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Point addPoint(final Point p) {
        final int newPointX = x + p.x;
        final int newPointY = y + p.y;

        return new Point(newPointX, newPointY);
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}

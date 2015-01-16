/* COPYRIGHT (C) 2015 Puncak Tegap Sdn Bhd. All Rights Reserved. */

package com.test.robot;


/**
 * @author Domu Ansor Harahap
 */
public class Robot {
    Point point;
    Direction d;

    public void placeMe(final Point p, final Direction d) {
        point = p;
        this.d = d;

        if (isOutOfRange()) {
            throw new RuntimeException("Can't place the robot to this point: " + point);
        }
    }

    public void turnLeft() {
        switch (d) {
        case NORTH:
            d = Direction.WEST;
            break;
        case WEST:
            d = Direction.SOUTH;
            break;
        case SOUTH:
            d = Direction.EAST;
            break;
        case EAST:
            d = Direction.NORTH;
            break;
        default:
            break;
        }
    }

    public void turnRight() {
        switch (d) {
        case NORTH:
            d = Direction.EAST;
            break;
        case WEST:
            d = Direction.NORTH;
            break;
        case SOUTH:
            d = Direction.WEST;
            break;
        case EAST:
            d = Direction.SOUTH;
            break;
        default:
            break;
        }
    }

    public void move() {
        final Point moveP = ToyRobot.steps.get(d);
        point = point.addPoint(moveP);

        if (isOutOfRange()) {
            throw new RuntimeException("Can't move the robot to this point: " + point);
        }
    }

    public boolean isOutOfRange() {
        if (point.x > TableTop.MAX_WIDTH || point.x < 0 || point.y > TableTop.MAX_HEIGHT || point.y < 0) {
            return true;
        }

        return false;
    }

    public String report() {
        return "[" + point.x + ", " + point.y + ", " + d + "]";
    }
}

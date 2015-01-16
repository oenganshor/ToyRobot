/* COPYRIGHT (C) 2015 Puncak Tegap Sdn Bhd. All Rights Reserved. */

package com.test.robot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Domu Ansor Harahap
 */
public class ToyRobot {
    public static Map<Direction, Point> steps = new HashMap<Direction, Point>();
    private static Map<String, Method> commands = new HashMap<String, Method>();

    static {
        steps.put(Direction.NORTH, new Point(0, 1));
        steps.put(Direction.EAST, new Point(1, 0));
        steps.put(Direction.WEST, new Point(-1, 0));
        steps.put(Direction.SOUTH, new Point(0, -1));

        try {
            commands.put("MOVE", ToyRobot.class.getDeclaredMethod("move"));
            commands.put("LEFT", ToyRobot.class.getDeclaredMethod("left"));
            commands.put("RIGHT", ToyRobot.class.getDeclaredMethod("right"));
            commands.put("REPORT", ToyRobot.class.getDeclaredMethod("report"));
        } catch (final SecurityException e) {
            e.printStackTrace();
        } catch (final NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    Robot robot = new Robot();

    public static void main(final String[] args) {
        final ToyRobot robotSimulator = new ToyRobot();
        final Scanner in = new Scanner(System.in);
        String command = null;
        while (true) {
            command = in.nextLine();
            if ("q".equalsIgnoreCase(command)) {
                return;
            }

            if (!commands.containsKey(command)) {
                if (!command.startsWith("PLACE")) {
                    System.out.println("The command is not valid, " + command);
                    continue;
                }
            }

            if (command != null && command.trim().toLowerCase().startsWith("place")) {
                final String[] splitCommands = command.split(" ");
                if (splitCommands != null && splitCommands.length == 2) {
                    final String[] params = splitCommands[1].split(",");
                    if (params != null && params.length == 3) {
                        final String xStr = params[0];
                        final String yStr = params[1];
                        final String face = params[2];

                        int x, y;
                        Direction d;
                        try {
                            x = Integer.valueOf(xStr);
                            y = Integer.valueOf(yStr);
                            d = Direction.faces.get(face);
                        } catch (final NumberFormatException nfe) {
                            System.out.println("The PLACE command params are not valid, " + command);
                            continue;
                        }

                        robotSimulator.placeXYF(x, y, d);
                    } else {
                        System.out.println("The PLACE command params are not valid, " + command);
                        continue;
                    }
                } else {
                    System.out.println("The PLACE command params are not valid, " + command);
                    continue;
                }
            } else {
                try {
                    commands.get(command).invoke(robotSimulator);
                } catch (final IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                } catch (final InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void placeXYF(final int x, final int y, final Direction d) {
        robot.placeMe(new Point(x, y), d);
    }

    public void move() {
        robot.move();
    }

    public void left() {
        robot.turnLeft();
    }

    public void right() {
        robot.turnRight();
    }

    public void report() {
        System.out.println(robot.report());
    }
}

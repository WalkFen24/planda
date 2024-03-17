package com.ia.planda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PointTracker {
    private static int points;
    private File file = new File("items.txt");
    private Scanner scan;
    private FileWriter fw = new FileWriter(file, true);

    public PointTracker() throws IOException {
        scan = new Scanner(file);
        scan.nextLine();
        scan.nextLine();
        points = Integer.parseInt(scan.nextLine());
    }

    public void printPoints() {
        System.out.println("Points: " + points);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int p) throws IOException {
        points = p;
        printPoints();

        scan = new Scanner(file);
        String str = scan.nextLine() + "\n" + scan.nextLine() + "\n";

        fw = new FileWriter(file);
        fw.write(str + points);
        fw.close();
    }

    public void incrPointsBy(int a) throws IOException {
        setPoints(points + a);
    }

    public void decrPointsBy(int s) throws IOException {
        setPoints(points - s);
    }
}

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        Scanner getMarks = new Scanner(System.in);
        System.out.println("Enter marks, type a to end");
        List<Float> marks = new ArrayList<Float>();
        while (true) {
            try {
                float mark = getMarks.nextFloat();
                marks.add(mark);
            } catch (InputMismatchException e) {
                break;
            }
        }
        getMarks.close();
        
        float ave = 0f;
        for (int i = 0; i < marks.size(); i++) {
            if (marks.get( i ) >= 90.0f) {
                ave += 4.00f;
            } else if (marks.get( i ) >= 85.0f) {
                ave += 3.90f;
            } else if (marks.get( i ) >= 80.0f) {
                ave += 3.70f;
            } else if (marks.get( i ) >= 77.0f) {
                ave += 3.30f;
            } else if (marks.get( i ) >= 7.30f) {
                ave += 3.00f;
            } else if (marks.get( i ) >= 70.0f) {
                ave += 2.70f;
            } else if (marks.get( i ) >= 67.0f) {
                ave += 2.30f;
            } else if (marks.get( i ) >= 63.0f) {
                ave += 2.00f;
            } else if (marks.get( i ) >= 60.0f) {
                ave += 1.70f;
            } else if (marks.get( i ) >= 56.0f) {
                ave += 1.30f;
            } 
        }
        ave /= marks.size();
        System.out.println("CGPA is " + ave);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}


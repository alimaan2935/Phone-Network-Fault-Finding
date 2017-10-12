package au.edu.uq.itee.comp3506.assn2.tests;

import java.time.LocalDateTime;

public class Tester {

    public static void main(String[] args) {
        AutoTester tester = new AutoTester();

        LocalDateTime start = LocalDateTime.parse("2017-09-03T18:23:33.821");
        LocalDateTime end = LocalDateTime.parse("2017-09-12T02:14:39.654");


        System.out.println(tester.called(1750268055L));
        System.out.println(tester.called(1750268055L, start, end));
        System.out.println(tester.callers(1750268055L));
        System.out.println(tester.callers(1750268055L, start, end));
        System.out.println(tester.findConnectionFault(1750268055L));
        System.out.println(tester.findConnectionFault(1750268055L, start, end));
        System.out.println(tester.findReceivingFault(1750268055L));
        System.out.println(tester.findReceivingFault(1750268055L, start, end));
        System.out.println(tester.maxConnections());
        System.out.println(tester.maxConnections(start, end));
        System.out.println(tester.minConnections());
        System.out.println(tester.minConnections(start, end));
    }
}

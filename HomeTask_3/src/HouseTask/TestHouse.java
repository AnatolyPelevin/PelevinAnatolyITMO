package HouseTask;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

public class TestHouse {


   public static void testHouse(){
        System.out.println("****Start test House.****");
        Hashtable<Humane, Boolean> rentersHashTable = new Hashtable<>();

        for (int i = 1; i<=10; i++) {
            Random rndForElem = new Random();
            int floor = 1+ rndForElem.nextInt(5);
            rentersHashTable.put(new Humane("Renter " + i, floor), false);
        }
        System.out.println("--Create renters--");
        getRentsListInfo(rentersHashTable);

        System.out.println("--Create buildings--");
        Building bld1 = new Building(2, 5, 3, "Street 1, h.1");
        System.out.println(bld1.toString());
        Building bld2 = new Building(3, 5, 6, "Street 1, h.2");
        System.out.println(bld2.toString());
        bld2.setAddress("Street 1, h.2.1");
        System.out.println(bld2.toString());


       for (int i = 0; i<5; i ++){
           System.out.println("--Iteration adding--"  + i + "--------------");
           bld2.getRentersInfo();
           try {
               bld2.addRenters(rentersHashTable);
           } catch (HouseTaskException e) {
               e.printStackTrace();
           }
           getRentsListInfo(rentersHashTable);
           bld2.getRentersInfo();
           System.out.println("--Iteration adding--"  + i + "--------------");
           System.out.println("");
       }
    }


    private static void getRentsListInfo(Hashtable<Humane, Boolean> rentersHashTable) {
        Enumeration renters = rentersHashTable.keys();
        while (renters.hasMoreElements()) {
            Humane retner = (Humane)renters.nextElement();
            retner.toString();
            System.out.println( retner.toString() + " Renter status: " + rentersHashTable.get(retner));
        }
    }
}

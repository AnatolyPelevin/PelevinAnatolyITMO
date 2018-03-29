package HouseTask;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Дом строется со следующими характеристиками:
 Что из низ задается через конструктор, а что потом - решайте сами
 1. какое-то количество подъездов
 2. какое-то количество этажей
 3. какое-то количество квартир на этаже
 4. адрес
 У дома должна быть возможность сообщить, сколько в нем квартир - метод
 У дома должна быть возможность показать адрес - метод
 У дома должна быть возможность сообщить, сколько людей в нем живет - метод

 Есть очередь людей (массив с объектами класса Human),
 каждый хочет заселиться в дом , но у каждого есть пожелание - этажность дома!

 Одновременно в дом можно заселить троих.
 house.add(); при вызове этого метода можно заселить только 3х людей
 */
public class Building {

    private int numEntrance;
    private int numFloor;
    private int numFlatsPerFloor;
    private String address;
    private Hashtable<Integer, Integer> flatsOnFloorWithNotFree;


    public Building (int numEntrance, int numFloor, int numFlatsPerFloor) {
        this.numEntrance = numEntrance;
        this.numFloor = numFloor;
        this.numFlatsPerFloor = numFlatsPerFloor;
        flatsOnFloorWithNotFree = new Hashtable<Integer, Integer>(numFloor*numFlatsPerFloor);
        for(int i = 1 ; i<= numFloor; i++) {
            flatsOnFloorWithNotFree.put(i,0);
        }
    }

    public Building (int mumEntrance, int numFloor, int numFlatsPerFloor, String address) {
        this( mumEntrance,  numFloor,  numFlatsPerFloor) ;
        this.address = address;
    }

    public void addRenters(Hashtable<Humane, Boolean> humaneList ) throws HouseTaskException {
      if(humaneList != null ) {
          final int lastIteration = (humaneList.size() < 3) ? humaneList.size(): 3 ;
          humaneList.entrySet().stream()
                  .filter(obj ->  !obj.getValue() && checkForRent(obj) )
                  .peek(humaneBooleanEntry -> {
                         addRenter(humaneBooleanEntry);
                          humaneList.put(humaneBooleanEntry.getKey(), true);
                  })
                  .limit(lastIteration)
                  .collect(Collectors.toList());
      } else {
          System.out.println("humaneList equals null");
          throw new HouseTaskException("No humans!", -1);
      }
    }

    private boolean checkForRent(Map.Entry humaneBooleanEntry) {

        Humane humane = (Humane)humaneBooleanEntry.getKey();
        int floor = humane.getWhatFloor();
        Integer notFreeFlatsCount =  flatsOnFloorWithNotFree.get(floor);
        if (notFreeFlatsCount !=null && notFreeFlatsCount < numFlatsPerFloor) {

            return true;
        } else {

            return false;
        }
    }

    private void addRenter(Map.Entry humaneBooleanEntry){
     //  if(checkForRent(humaneBooleanEntry)) {
           Humane humane = (Humane)humaneBooleanEntry.getKey();
           int floor = humane.getWhatFloor();
           Integer notFreeFlatsCount =  flatsOnFloorWithNotFree.get(floor);
           flatsOnFloorWithNotFree.put(floor, notFreeFlatsCount + 1);
        //}
    }

    public void getRentersInfo() {

        Enumeration floors = flatsOnFloorWithNotFree.keys();
        while (floors.hasMoreElements()) {
            Integer floorNum = (Integer)floors.nextElement();
            System.out.println( "Floor number  = " + floorNum + " Flats not free: " +
                    flatsOnFloorWithNotFree.get(floorNum));
        }
    }

    public int getNumEntrance() {
        return numEntrance;
    }

    public void setNumEntrance(int mumEntrance) {
        this.numEntrance = mumEntrance;
    }

    public int getNumFloor() {
        return numFloor;
    }

    public void setNumFloor(int numFloor) {
        this.numFloor = numFloor;
    }

    public int getNumFlatsPerFloor() {
        return numFlatsPerFloor;
    }

    public void setNumFlatsPerFloor(int numFlatsPerFloor) {
        this.numFlatsPerFloor = numFlatsPerFloor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

  @Override
    public String toString(){
        return new StringBuilder()
                .append("[House] - Adress:  ")
                .append(getAddress())
                .append(" Number of entrance: ")
                .append(numEntrance)
                .append(" Number of floors: ")
                .append(numFloor)
                .append(" Number of flats Per floor: ")
                .append(numFlatsPerFloor)
                .toString();

  }

}

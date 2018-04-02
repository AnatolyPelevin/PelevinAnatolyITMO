package School;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.stream.Collectors;

public class School {
    public static final int MAX_CLASSES = 4*10; //just for trainning
    private int schoolNum;
    private String schoolAddress;
    private Hashtable<Class, String> classList = new Hashtable<Class, String>(MAX_CLASSES);


    public School (int schoolNum, String schoolAddress){
        this.schoolNum  = schoolNum;
        this.schoolAddress = schoolAddress;
        }

    public void printClassInformation(){
        if(this.classList !=null){
            this.classList.entrySet()
                    .stream()
                    .peek(obj->System.out.println(obj.getKey().toString()))
                    .collect(Collectors.toList());
        } else {
            System.out.println("No classes at school");
        }
    }



    public void initClassList(ArrayList<Class> classListForAdd){
      if (classListForAdd != null) {
          classListForAdd.stream()
                  .peek(classListEntry -> {
                      addClass(classListEntry);
                  })
                  .collect(Collectors.toList());
      }
    }


    private int checkForExistsClassNum(Class classForSchool) {
       String classNum = classForSchool.getClassNum();
        return checkForExistsClassNum(classNum );
    }

    private int checkForExistsClassNum( String classNum ) {
        if (classNum == null && "".equals(classNum)) {
            System.out.println("Class doesn't exists!");
            return -1;
        } else if ( this.classList.containsValue(classNum)) {
            System.out.println("Class exists in this school!");
            return 0;
        }else  {
          //  System.out.println("Class doesn't exists in this school!");
            return 1;
        }
    }

    public void addClass(Class classForSchool){
       if(checkForExistsClassNum(classForSchool) ==1)
       {
        String classNum = classForSchool.getClassNum();
        this.classList.put(classForSchool, classNum);
       }
    }

    public void removeClass(Class classForSchool){
        if(checkForExistsClassNum(classForSchool) ==0)
        {
            String classNum = classForSchool.getClassNum();
            this.classList.remove(classForSchool, classNum);
            if ( !this.classList.containsValue(classNum)) {
                System.out.println("Class has been removed from the school!");}
        }
    }


    @Override
    public String toString() {
        return new StringBuilder()
                .append("[School] - Number:  ")
                .append(schoolNum)
                .append(" Address:  ")
                .append(schoolAddress)
                .toString();
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public int getSchoolNum() {
        return schoolNum;
    }

    public void setSchoolNum(int schoolNum) {
        this.schoolNum = schoolNum;
    }


}

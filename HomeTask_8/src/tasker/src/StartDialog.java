package tasker.src;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

public class StartDialog {
    private static Scanner sc = new Scanner(System.in);
    private static Hashtable<String, String> users = new Hashtable<>();
    private static boolean closeProgramm = false;
    private static HashMap<String, String> currentLog = new HashMap<>();

    public static void startDialog(){
        users.put("admin", "123");

        System.out.println("Tasker program:");
        System.out.println("   Enter name.");

        mainDialog();
        System.out.println(" Closing programme!");
    }

    private static void setLoginParam(String string, int param){
        String parName;
        if (param == 0) {
            setName(string);
        } else {
            parName = "Password";
        }
    }

    private static void setName(String name){
        if (name == null|| "".equals(name.trim())) {
            System.out.println("User name is empty. Enter User name.");
            return;
        } else {
            currentLog.put(name,"");
            System.out.println(" Enter pswd: ");
            return;
        }
    }



    private static void mainDialog(){
        do{
            String temp = sc.nextLine();
            switch (temp){
                case "close":
                    System.out.println(" Buy-buy!");
                    currentLog.clear();
                    closeProgramm = true;
                    break;
//                case "exit":
//                    System.out.println("   Enter name.");
//                    currentLog.clear();
//                    break;
                default:
                    setLoginParam(temp,0);
                    break;
            }

        } while (!closeProgramm);
    }

    private static void defaultDialog(String temp){

    }

}

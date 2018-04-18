package duplicate.src;

import java.util.Scanner;

public class StartDialog {
    public static void startDialog(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Scanner program:");
        System.out.println("   0 - duplicate numbers;");
        System.out.println("   1 - anagrams.");

        int modeNum=-1;
        do {
            System.out.println("Enter program mode: ");
            while (true)
                try {
                    modeNum = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Wrong mode. Please try again: ");
                }
        } while (modeNum<0 || modeNum>1);

        ModeStart ms;
        try {
            ms  = ModeFactory.createMode(modeNum);
            ms.perform();
        } catch (IllegalArgumentException e) {
            System.err.println( "(1): " + e.getMessage()  + " Wrong Mode!! ");
        }

    }
}

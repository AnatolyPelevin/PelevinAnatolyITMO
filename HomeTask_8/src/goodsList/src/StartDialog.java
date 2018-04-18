package goodsList.src;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class StartDialog {
    private static Scanner sc = new Scanner(System.in);
    private static Set<Client> clients = new HashSet<>();

    public static void startDialog(){
        boolean closeProgramm = false;
        System.out.println("Googs program:");
        System.out.println("   Enter clients and goods.");
        System.out.println("   Format: Client_name goods_name goods_amount");


        do{
            String temp = sc.nextLine();
            switch (temp){
                case "add data":
                    addDate();
                    System.out.println(" Data was added!");
                    break;
                case "show data":
                    showData();
                    break;
                case "info":
                    System.out.println(" Commands:");
                    System.out.println("   add goods: add goods and clients");
                    System.out.println("   show data: show goods and clients");
                    System.out.println("   close: close programme");
                    break;
                case "close":
                    System.out.println(" Buy-buy!");
                    closeProgramm = true;
                    break;
            }

        } while (!closeProgramm);
        System.out.println(" Buy-buy!");
    }


    private static void addDate(){
        String inputString;
        do {
            inputString = sc.nextLine();
            Object[] obj = getStrAsObjectArray(inputString);
            parseClient(obj);
        } while (inputString.isEmpty());
    }

    private static void showData(){
        clients.stream()
                .forEach(client-> {
                    System.out.println("Client name: " + client.getClientName());
                    client.getAllGoods()
                            .entrySet()
                            .stream()
                            .forEach(good->System.out.println("     Good name: "+ good.getKey() + "  Good amount: " + good.getValue()));
                });
    }

    private static Object[] getStrAsObjectArray(String strMain) {
        return (Arrays.stream(strMain
                .trim()
                .split("\\s"))
                .map(word -> word.replaceAll("[-.?!)(,:;'\"*]", "").trim())
                .filter(word -> word.length() > 0))
                .toArray();
    }

    private static void parseClient(Object[] obj) {
        if (obj == null) {
            System.out.println("Empty client!");
            return;
        }
        if(obj.length<3){
            System.out.println("Not enough parameters fo client!");
            return;
        }
        if (!isNumeric((String)obj[2])){
            System.out.println("Wrong goods amount!");
            return;
        }

        String goodName  = (String)obj[1];
        Float goodAmount  = (Float.parseFloat((String)obj[2]));

        Client client  = new Client((String)obj[0], goodName, goodAmount);

        if (!clients.contains(client)) {
            clients.add(client);
        } else {
           Client clientOld =  clients.stream().filter(item->item.equals(client)).findFirst().get();
            clientOld.addGoods(goodName,goodAmount);
        }
    }


    public static boolean isNumeric(String str)
    {
        try
        {
            float d = Float.parseFloat(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}

package goodsList.src;

import java.util.Hashtable;

public class Client {
    private String clientName;
    private Hashtable<String, Float> goods;



    public Client(String clientName) {
        this.clientName =clientName;
        goods = new Hashtable<>();
    }

    public Client(String clientName, String goodName, Float goodAmount) {
        this(clientName);
        addGoods(goodName, goodAmount);
    }

    public String getClientName() {
        return clientName;
    }

    public Hashtable<String, Float> getAllGoods() {
        return goods;
    }

    public void addGoods(String goodName, Float goodAmount){
        if (goodName == null || "".equals(goodName)) {
            System.out.println("Empty good name");
            return;
        }

        if (goodAmount == null || goodAmount == 0) {
            System.out.println("Empty good amount");
            return;
        }

        if (goods.containsKey(goodName)) {
            Float goodsAmountOld = goods.get(goodName);
            goodsAmountOld += goodAmount;
            goods.put(goodName, goodsAmountOld);
         } else {
            goods.put(goodName, goodAmount);
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + clientName.hashCode();
        return result;

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Client)) {
            return false;
        }

        Client client = (Client) obj;
        return client.clientName.equals(clientName);
    }

}

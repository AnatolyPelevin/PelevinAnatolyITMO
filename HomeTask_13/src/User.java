public class User {
    private static int userIDGenerate;
    private final int userID;
    private String userEmail;

    public User() {
        this.userID = ++userIDGenerate;
    }

    public User(String userEmail) {
       this();
       this.userEmail = userEmail;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}

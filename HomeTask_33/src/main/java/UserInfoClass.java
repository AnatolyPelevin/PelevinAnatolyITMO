public class UserInfoClass {

    private int userID;
    private String userName;
    private String userFirstName;


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }


    @Override
    public String toString() {
        return "UserInfoClass{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                '}';
    }
}

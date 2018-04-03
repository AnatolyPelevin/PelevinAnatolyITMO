package PersonalOrganizer.OrganizerItem;

public class PasswordItem implements OrganizerItemInterface {
    private String passwordItemTitle;
    private String passwordItemDescription;
    private String login;
    private String password;
    private static int count;



    public PasswordItem(){
        this.passwordItemTitle = "New Password" + count;
        count++;
    }

    public PasswordItem(String login, String password){
        this();
        this.login = login;
        this.password = password;
    }

    public PasswordItem(String passwordItemTitle, String login, String password){
        this.passwordItemTitle = passwordItemTitle;
        this.login = login;
        this.password = password;
    }

    public PasswordItem(String passwordItemTitle, String login, String password, String passwordItemDescription){
        this.passwordItemTitle = passwordItemTitle;
        this.login = login;
        this.password = password;
        this.passwordItemDescription = passwordItemDescription;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordItemDescription() {
        return passwordItemDescription;
    }

    public void setPasswordItemDescription(String passwordItemDescription) {
        this.passwordItemDescription = passwordItemDescription;
    }

    @Override
    public void setItemTitle(String itemTitle) {
      if (itemTitle ==null || "".equals(itemTitle)) {
          this.passwordItemTitle = "New Password" + count;
          count++;
      } else{
          this.passwordItemTitle =  itemTitle;
      }
    }

    @Override
    public String getItemTitle() {
        return passwordItemTitle;
    }
}

package PersonalOrganizer.OrganizerItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class ContactItem implements OrganizerItemInterface {
    private String FIO;
    private Map<String, String> phones;
    private Map<String, String> mails;
    private Date dayOfBirth;

    private static int count;


    public ContactItem (){
        this.FIO = "New Contact" + count;
        count++;
    }

    @Override
    public void setItemTitle(String FIO) {
        this.FIO = FIO;
    }

    @Override
    public String getItemTitle() {
        return FIO;
    }
}

package PersonalOrganizer;

import PersonalOrganizer.OrganizerItem.OrganizerItemInterface;

import java.util.ArrayList;
import java.util.List;

public class PersonalOrganizer {
    private List<OrganizerItemInterface> Items = new ArrayList<OrganizerItemInterface>();

    public List<OrganizerItemInterface> getAllItems () {
        return Items;
    }

    public void setItem(OrganizerItemInterface item){
        this.Items.add(item);
        System.out.println("Item has been added!");
    }

    public void removeItem(OrganizerItemInterface item){
        this.Items.remove(item);
        System.out.println("Item has been removed!");
    }

}

package PersonalOrganizer;

import PersonalOrganizer.OrganizerItem.NoteItem;
import PersonalOrganizer.OrganizerItem.OrganizerItemInterface;
import PersonalOrganizer.OrganizerItem.TaskItem;

import java.util.Date;
import java.util.List;

public class PerformPersonalOrganizer {
    public static void PerformPersonalOrganizer(){
        System.out.println("****Start test Personal Organizer.****");
        PersonalOrganizer po = new PersonalOrganizer();

        NoteItem note  = new NoteItem("Note 1 text", "Note 1");
        po.setItem(note);

        TaskItem task  = new TaskItem("To do Task 1", new Date());
        po.setItem(task);

        List<OrganizerItemInterface> listitems = po.getAllItems();
        listitems.stream().forEach(ob ->System.out.println(ob.getItemTitle()));

    }
}

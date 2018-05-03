package ListenerTask;

import java.util.ArrayList;
import java.util.List;

public class Sensor {
    private List<AlarmListener>  listeners = new ArrayList<>();
    private Integer temperature = 0;

    public void addListener (AlarmListener alarmListener){
        listeners.add(alarmListener);
        System.out.println("New listener has been added: " + alarmListener);
    }

    public void removeListener (AlarmListener alarmListener){
        listeners.remove(alarmListener);
        System.out.println("The listener has been removed: " + alarmListener);
    }

    public void changeTemperature(Integer temp){
        if (temp == null) {
            return;
        }
        if (temp > 0){
            System.out.println("Temperature increased: " + temp);
        } else {
            System.out.println("Temperature decreased: " + temp);
        }
        temperature=temp;
        notifyListeners(temperature);
    }


    private void notifyListeners(Integer temp){
        for (AlarmListener listener: listeners) {
            listener.changeTemperature(temp);
        }
    }
}

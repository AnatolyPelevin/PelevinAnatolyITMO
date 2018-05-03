package ListenerTask;

public class YellowAlarm implements AlarmListener{
    private Integer currentTemp = 0;
    @Override
    public void changeTemperature(Integer temp) {
        if (temp == null){
            return;
        }
        if (temp >= 300 && temp <600 && currentTemp <300) {
            System.out.println("YELLOW alarm! Temperature has increased. Current temperature = " + temp);
        }
        currentTemp = temp;
    }
}

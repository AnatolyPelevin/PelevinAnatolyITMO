package ListenerTask;

public class RedAlarm implements AlarmListener{
    private Integer currentTemp = 0;
    @Override
    public void changeTemperature(Integer temp) {
        if (temp==null){
            return;
        }
        if (temp >= 600 && currentTemp < 600) {
            System.out.println("RED alarm! Temperature has increased. Current temperature = " + temp);
        }
        currentTemp = temp;
    }
}

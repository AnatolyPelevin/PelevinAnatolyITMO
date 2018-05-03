package ListenerTask;

public class GreenAlarm implements AlarmListener{
    private Integer currentTemp = 0;
    @Override
    public void changeTemperature(Integer temp) {
        if (temp == null){
            return;
        }
        if (temp >= 100 && temp <300 && currentTemp < 100 ) {
            System.out.println("GREEN alarm! Temperature has increased. Current temperature = " + temp);
        }
        currentTemp = temp;
    }
}

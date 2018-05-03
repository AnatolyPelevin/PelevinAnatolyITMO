package ListenerTask;

public class AlarmProducer {
    public static AlarmListener produce(AlarmType alarmType){
        switch (alarmType){
            case GREEN:
                return new GreenAlarm();
            case YELLOW:
                return new YellowAlarm();
            case RED:
                return new RedAlarm();
            default:
                throw new IllegalArgumentException("No such alarm");

        }

    }
}

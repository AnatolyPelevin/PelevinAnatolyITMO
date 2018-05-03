package ListenerTask;

public class PerformListner {
    public static void performListner(){
        System.out.println("******Perform Listener Task******");
        Sensor sensor = new Sensor();
        sensor.addListener(AlarmProducer.produce(AlarmType.GREEN));
        sensor.addListener(AlarmProducer.produce(AlarmType.YELLOW));
        sensor.addListener(AlarmProducer.produce(AlarmType.RED));

        sensor.changeTemperature(50);
        sensor.changeTemperature(100);
        sensor.changeTemperature(150);
        sensor.changeTemperature(90);
        sensor.changeTemperature(140);
        sensor.changeTemperature(350);
        sensor.changeTemperature(650);
        sensor.changeTemperature(800);
        sensor.changeTemperature(550);
        sensor.changeTemperature(250);
        sensor.changeTemperature(350);
    }
}

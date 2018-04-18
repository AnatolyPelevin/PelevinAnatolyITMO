package duplicate.src;

public class ModeFactory {
    public static ModeStart createMode(int modeNum) throws IllegalArgumentException  {
        switch (modeNum){
            case 0:
              return new NumberMode();
            case 1:
                return new AnagramMode();
            default:
                throw new IllegalArgumentException();
        }
    }
}

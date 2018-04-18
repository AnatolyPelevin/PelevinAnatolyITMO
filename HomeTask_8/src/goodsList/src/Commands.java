package goodsList.src;

public enum Commands {
    ADD_GOODS("add goods"),
    SHOW_DATA("show data"),
    CLOSE("close");

    private final String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}

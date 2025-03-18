package Db;

public class DbIntegretyExcepetion extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public DbIntegretyExcepetion(String message) {
        super(message);
    }
}

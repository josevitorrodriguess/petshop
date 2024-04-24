package database;

public class DbExeption extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DbExeption(String message){
        super(message);
    }


}

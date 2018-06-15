import java.sql.Connection;

public abstract class DAOAbstract<T> implements DAOInterface<T> {
    private final Connection con;

    public Connection getCon() {
        return con;
    }


  public DAOAbstract(Connection con){
      this.con = con;
  }

}

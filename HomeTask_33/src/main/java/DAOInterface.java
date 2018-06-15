import java.sql.SQLException;

public interface DAOInterface<T> {
    public Integer save(T t) throws DAOException, SQLException;
    public Integer delete(T t);
    public T getById(Integer id);
}

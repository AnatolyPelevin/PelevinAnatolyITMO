import java.sql.Connection;

public class DAOFactory {
    public static DAOInterface createDAO (DAOType daoType, Connection con) throws DAOException {
        switch(daoType){
            case USER_INFO:
                return new UserInfoDAO(con);
            default:
                throw new DAOException("Type doesn't supported!");
        }
    }
}

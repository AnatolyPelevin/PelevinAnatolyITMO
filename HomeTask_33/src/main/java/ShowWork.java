import java.sql.Connection;
import java.sql.SQLException;

public class ShowWork {
    public static void main(String[] args) throws SQLException, DAOException {
        try (Connection con = DataSource.getConnection()) {

            UserInfoClass userInfoClass = new UserInfoClass();
            userInfoClass.setUserName("Ivan");
            userInfoClass.setUserFirstName("Ivanov");

            DAOInterface userDAO = DAOFactory.createDAO(DAOType.USER_INFO ,con);

            userDAO.save(userInfoClass);

            System.out.println(userInfoClass.toString());
        }
    }
}


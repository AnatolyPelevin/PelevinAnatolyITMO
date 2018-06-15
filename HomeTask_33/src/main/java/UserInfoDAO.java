import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoDAO extends DAOAbstract<UserInfoClass> {

    public UserInfoDAO(Connection con) {
        super(con);
    }

    @Override
    public Integer save(UserInfoClass userInfoClass) throws DAOException, SQLException {
        if (userInfoClass == null) {
            throw new DAOException("User  can't be null!");
        }

        Integer user_id = null;

        try(PreparedStatement preparedStatement = getCon().prepareStatement("SELECT user_info_add_update_delete(?,?,?,?)")){
            preparedStatement.setInt(1,userInfoClass.getUserID());
            preparedStatement.setString(2,userInfoClass.getUserName());
            preparedStatement.setString(3,userInfoClass.getUserFirstName());
            preparedStatement.setInt(4,0);

            try(ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()){
                    user_id = rs.getInt(1);
                    userInfoClass.setUserID(user_id);
                }
            }

        }
        return user_id;
    }

    @Override
    public Integer delete(UserInfoClass userInfoClass) {
        return 0;
    }

    @Override
    public UserInfoClass getById(Integer id) {
        return null;
    }


}

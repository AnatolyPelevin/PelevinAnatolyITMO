package datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static volatile DataSource dataSource;
    private ComboPooledDataSource cpds;

    private DataSource() throws PropertyVetoException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
            e.printStackTrace();
            return;
        }

        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("org.postgresql.Driver");
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        cpds.setUser("postgres");
        cpds.setPassword("1");
        cpds.setMinPoolSize(1);
        cpds.setMaxPoolSize(5);
        cpds.setMaxStatements(20);

    }

    public static DataSource instance() {
        if (dataSource == null) {
            synchronized (DataSource.class) {
                if (dataSource == null) {
                    try {
                        dataSource = new DataSource();
                    } catch (PropertyVetoException e) {
                        throw new IllegalStateException(e);
                    }
                }
            }
        }
        return dataSource;
    }

    public Connection connection() throws SQLException {
        return cpds.getConnection();
    }

    public static Connection getConnection() throws SQLException {
        return instance().connection();
    }
}

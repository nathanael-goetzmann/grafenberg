package grafenberg.app.modeles;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TypeOperationDAO {

    private Connection connection;

    public TypeOperationDAO() {
        this.connection = new ConnexionDAO();
    }

    public ResultSet getAllTypeOperationDAO() throws SQLException {
        
        String query = "SELECT * FROM type_operation"

        PreparedStatement statement = connection.preparedStatement(query);

        return statement.executeQuery();
    }

}
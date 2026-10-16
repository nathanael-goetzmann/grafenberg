package grafenberg.app.modeles;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriePersonnelDAO {

    private Connection connection;

    public CategoriePersonnelDAO() {
        this.connection = new ConnexionDAO();
    }

    public ResultSet getAllCategoriePersonnel() throws SQLException {

        String query = "SELECT * FROM categorie_personnel"

        PreparedStatement statement = connection.preparedStatement(query);

        return statement.executeQuery();
    }

    public ResultSet getOneCategoriePersonnel(categorie) throws SQLException {

        String query = "SELECT * FROM categorie_personnel WHERE cay_code = ?"

        PreparedStatement statement = connection.preparedStatement(query);

        return statement.executeQuery();
    }

}
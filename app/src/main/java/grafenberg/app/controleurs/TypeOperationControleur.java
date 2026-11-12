package grafenberg.app.controleurs;

import grafenberg.app.modeles.TypeOperationDAO;
import grafenberg.app.modeles.TypeOperation;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;

public final class TypeOperationControleur {
    
    private final TypeOperationDAO typeOperationDAO;
    private final BlocControleur blocControleur;
    
    public TypeOperationControleur() {
        typeOperationDAO = new TypeOperationDAO();
        blocControleur = new BlocControleur();
    }
    
    private TypeOperation createTypeOperation(ResultSet resultSetOpe) throws SQLException {
        var typeOperation = new TypeOperation();
                
        typeOperation.setId(resultSetOpe.getInt("top_id"));
        typeOperation.setNom(resultSetOpe.getString("top_nom"));
        typeOperation.setCharges(resultSetOpe.getDouble("top_charges"));

        var bloc = blocControleur.getOneBloc(resultSetOpe.getInt("top_bloc_id"));
        typeOperation.setBloc(bloc);
                
        return typeOperation;
    }
    
    public ArrayList<TypeOperation> getAllTypeOperation() throws SQLException {
        try (var resultSetOpe = typeOperationDAO.selectAllTypeOperation();) {
            var lesTypesOperations = new ArrayList<TypeOperation>();
            
            while (resultSetOpe.next()) {
                lesTypesOperations.add(createTypeOperation(resultSetOpe));
            }
            
            return lesTypesOperations;
        }
    }
    
    public TypeOperation getOneTypeOperation(int id) throws SQLException {
        try (var resultSetOpe = typeOperationDAO.selectOneTypeOperation(id);) {
            return resultSetOpe.next() ? createTypeOperation(resultSetOpe) : null;
        }
    }
}

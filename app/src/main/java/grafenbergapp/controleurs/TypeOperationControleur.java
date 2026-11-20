package grafenbergapp.controleurs;

import grafenbergapp.modeles.TypeOperationDAO;
import grafenbergapp.modeles.TypeOperation;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;

public final class TypeOperationControleur {
    
    private final TypeOperationDAO typeOperationDAO;
    private final BlocControleur blocControleur;
    
    public TypeOperationControleur() throws SQLException {
        typeOperationDAO = new TypeOperationDAO();
        blocControleur = new BlocControleur();
    }
    
    private TypeOperation createTypeOperation(ResultSet resultSetTypeOp) throws SQLException {
        var typeOperation = new TypeOperation();
                
        typeOperation.setId(resultSetTypeOp.getInt("top_id"));
        typeOperation.setNom(resultSetTypeOp.getString("top_nom"));
        typeOperation.setCharges(resultSetTypeOp.getDouble("top_charges"));

        var bloc = blocControleur.getOneBloc(resultSetTypeOp.getInt("top_bloc_id"));
        typeOperation.setBloc(bloc);
                
        return typeOperation;
    }
    
    public ArrayList<TypeOperation> getAllTypeOperation() throws SQLException {
        try (var resultSetTypeOp = typeOperationDAO.selectAllTypeOperation();) {
            var lesTypesOperations = new ArrayList<TypeOperation>();
            
            while (resultSetTypeOp.next()) {
                lesTypesOperations.add(createTypeOperation(resultSetTypeOp));
            }
            
            return lesTypesOperations;
        }
    }
    
    public TypeOperation getOneTypeOperation(int id) throws SQLException {
        try (var resultSetTypeOp = typeOperationDAO.selectOneTypeOperation(id);) {
            return resultSetTypeOp.next() ? createTypeOperation(resultSetTypeOp) : null;
        }
    }
}

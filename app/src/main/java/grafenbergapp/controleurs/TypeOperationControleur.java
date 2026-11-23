package grafenbergapp.controleurs;

import grafenbergapp.modeles.TypeOperationDAO;
import grafenbergapp.modeles.TypeOperation;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * 
 * @author Nathanaël Goetzmann
 * @version 1.0
 * @since 2026-11-13
 */
public final class TypeOperationControleur {
    
    /**  */
    private final TypeOperationDAO typeOperationDAO;
    /**  */
    private final BlocControleur blocControleur;
    
    /**
     * 
     * @throws SQLException 
     */
    public TypeOperationControleur() throws SQLException {
        typeOperationDAO = new TypeOperationDAO();
        blocControleur = new BlocControleur();
    }
    
    /**
     * 
     * @param resultSetTypeOp
     * @return
     * @throws SQLException 
     */
    private TypeOperation createTypeOperation(ResultSet resultSetTypeOp) throws SQLException {
        var typeOperation = new TypeOperation();
                
        typeOperation.setId(resultSetTypeOp.getInt("top_id"));
        typeOperation.setNom(resultSetTypeOp.getString("top_nom"));
        typeOperation.setCharges(resultSetTypeOp.getDouble("top_charges"));

        var bloc = blocControleur.getOneBloc(resultSetTypeOp.getInt("top_bloc_id"));
        typeOperation.setBloc(bloc);
                
        return typeOperation;
    }
    
    /**
     * 
     * @return
     * @throws SQLException 
     */
    public ArrayList<TypeOperation> getAllTypeOperation() throws SQLException {
        try (var resultSetTypeOp = typeOperationDAO.selectAllTypeOperation();) {
            var lesTypesOperations = new ArrayList<TypeOperation>();
            
            while (resultSetTypeOp.next()) {
                lesTypesOperations.add(createTypeOperation(resultSetTypeOp));
            }
            
            return lesTypesOperations;
        }
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws SQLException 
     */
    public TypeOperation getOneTypeOperation(int id) throws SQLException {
        try (var resultSetTypeOp = typeOperationDAO.selectOneTypeOperation(id);) {
            return resultSetTypeOp.next() ? createTypeOperation(resultSetTypeOp) : null;
        }
    }
}

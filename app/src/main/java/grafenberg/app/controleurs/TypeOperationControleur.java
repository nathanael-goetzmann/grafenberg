package grafenberg.app.controleurs;

import grafenberg.app.modeles.TypeOperationDAO;
import grafenberg.app.modeles.TypeOperation;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Nathanaël Goetzmann
 * @version v1.0
 * @since 2026-10-16
 */

public final class TypeOperationControleur {
    
    private final TypeOperationDAO typeOperationDAO;
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
     * @return
     * @throws SQLException 
     */
    public ArrayList<TypeOperation> getAllTypeOperation() throws SQLException {
        try (var resultSet = typeOperationDAO.selectAllTypeOperation()) {
            var lesTypes = new ArrayList<TypeOperation>();
            
            while (resultSet.next()) {
                TypeOperation type = new TypeOperation();
                
                type.setId(resultSet.getInt("top_num"));
                type.setCharges(resultSet.getDouble("top_charges"));
                
                var bloc = blocControleur.getUnBloc(resultSet.getInt("top_bloc_num"));
                type.setBloc(bloc);
                        
                lesTypes.add(type);
            }
            
            return lesTypes;
        }
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws SQLException 
     */
    public TypeOperation getUnTypeOperation(int id) throws SQLException {
        try (var resultSet = typeOperationDAO.selectUnTypeOperation(id)) {
            TypeOperation type = null;
            
            if (resultSet.next()) {
                type = new TypeOperation();
                
                type.setId(resultSet.getInt("top_num"));
                type.setCharges(resultSet.getDouble("top_charges"));
                
                var bloc = blocControleur.getUnBloc(resultSet.getInt("top_bloc_num"));
                type.setBloc(bloc);
            }
            
            return type;
        }
    }
}
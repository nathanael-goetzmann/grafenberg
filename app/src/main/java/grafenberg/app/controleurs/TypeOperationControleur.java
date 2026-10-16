package grafenberg.app.controleurs;

import grafenberg.app.modeles.TypeOperationDAO;
import grafenberg.app.modeles.TypeOperation;
import java.sql.SQLException;
import java.util.ArrayList;

public final class TypeOperationControleur {
    
    private final TypeOperationDAO typeOperationDAO;
    private final BlocControleur blocControleur;
    
    public TypeOperationControleur() throws SQLException {
        typeOperationDAO = new TypeOperationDAO();
        blocControleur = new BlocControleur();
    }
    
    public ArrayList<TypeOperation> getAllTypeOperation() throws SQLException {
        try (var resultSet = typeOperationDAO.selectAllTypeOperation()) {
            var lesTypes = new ArrayList<TypeOperation>();
            
            while (resultSet.next()) {
                var type = new TypeOperation();
                
                type.setId(resultSet.getInt("top_num"));
                type.setCharges(resultSet.getDouble("typeOperationDAO"));
                
                var bloc = blocControleur.getUnBloc(resultSet.getInt("top_bloc_num"));
                type.setBloc(bloc);
                        
                lesTypes.add(type);
            }
            
            return lesTypes;
        }
    }
    
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
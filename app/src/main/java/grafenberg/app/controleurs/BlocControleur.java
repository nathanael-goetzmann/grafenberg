package grafenberg.app.controleurs;

import grafenberg.app.modeles.BlocDAO;
import grafenberg.app.modeles.Bloc;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Nathanaël Goetzmann
 * @version v1.0
 * @since 2026-10-16
 */

public final class BlocControleur {
    
    private final BlocDAO blocDAO;
    
    /**
     * 
     * @throws SQLException 
     */
    public BlocControleur() throws SQLException {
        blocDAO = new BlocDAO();
    }
    
    /**
     * 
     * @return
     * @throws SQLException 
     */
    public ArrayList<Bloc> getAllBloc() throws SQLException {
        try (var resultSet = blocDAO.selectAllBloc()) {
            var lesBlocs = new ArrayList<Bloc>();
            
            while (resultSet.next()) {
                Bloc bloc = new Bloc();
                
                bloc.setId(resultSet.getInt("bloc_num"));
                bloc.setCharges(resultSet.getDouble("bloc_charges"));
                
                lesBlocs.add(bloc);
            }
            
            return lesBlocs;
        }
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws SQLException 
     */
    public Bloc getUnBloc(int id) throws SQLException {
        try (var resultSet = blocDAO.selectUnBloc(id)) {
            Bloc bloc = null;
            
            if (resultSet.next()) {
                bloc = new Bloc();
                
                bloc.setId(resultSet.getInt("bloc_num"));
                bloc.setCharges(resultSet.getDouble("bloc_charges"));
            }
            
            return bloc;
        }
    }
}
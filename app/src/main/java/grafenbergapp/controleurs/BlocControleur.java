package grafenbergapp.controleurs;

import grafenbergapp.modeles.BlocDAO;
import grafenbergapp.modeles.Bloc;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * La classe participe au traitement des demandes de l'utilisateur, en interrogeant un objet BlocDAO.<br>
 * Format des données.
 * @author Nathanaël Goetzmann
 * @version 1.0
 * @since 2026-11-13
 */
public final class BlocControleur {
    
    /**  */
    private final BlocDAO blocDAO;
    
    /**
     * 
     * @throws SQLException 
     */
    public BlocControleur() throws SQLException { blocDAO = new BlocDAO(); }
    
    /**
     * 
     * @param resultSetBloc
     * @return
     * @throws SQLException 
     */
    private Bloc createBloc(ResultSet resultSetBloc) throws SQLException {
        var bloc = new Bloc();
        
        bloc.setId(resultSetBloc.getInt("bloc_id"));
        bloc.setCharges(resultSetBloc.getDouble("bloc_charges"));
        
        return bloc;
    }
    
    /**
     * 
     * @return
     * @throws SQLException 
     */
    public ArrayList<Bloc> getAllBloc() throws SQLException {
        try (var resultSetBloc = blocDAO.selectAllBloc();) {
            var lesBlocs = new ArrayList<Bloc>();
            
            while (resultSetBloc.next()) {
                lesBlocs.add(createBloc(resultSetBloc));
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
    public Bloc getOneBloc(int id) throws SQLException {
        try (var resultSetBloc = blocDAO.selectOneBloc(id);) {
            return resultSetBloc.next() ? createBloc(resultSetBloc) : null;
        }
    }
}

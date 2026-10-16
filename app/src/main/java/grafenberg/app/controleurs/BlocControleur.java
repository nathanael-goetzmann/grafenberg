package grafenberg.app.controleurs;

import grafenberg.app.modeles.BlocDAO;
import grafenberg.app.modeles.Bloc;
import java.sql.SQLException;
import java.util.ArrayList;

public final class BlocControleur {
    
    private final BlocDAO blocDAO;
    
    public BlocControleur() throws SQLException {
        blocDAO = new BlocDAO();
    }
    
    public ArrayList<Bloc> getAllBloc() throws SQLException {
        try (var resultSet = blocDAO.selectAllBloc()) {
            var lesBlocs = new ArrayList<Bloc>();
            
            while (resultSet.next()) {
                var bloc = new Bloc();
                
                bloc.setId(resultSet.getInt("bloc_num"));
                bloc.setCharges(resultSet.getDouble("bloc_charges"));
                
                lesBlocs.add(bloc);
            }
            
            return lesBlocs;
        }
    }
    
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
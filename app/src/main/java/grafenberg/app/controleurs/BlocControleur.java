package grafenberg.app.controleurs;

import grafenberg.app.modeles.BlocDAO;
import grafenberg.app.modeles.Bloc;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;

public final class BlocControleur {
    
    private final BlocDAO blocDAO;
    
    public BlocControleur() { blocDAO = new BlocDAO(); }
    
    private Bloc createBloc(ResultSet resultSetBloc) throws SQLException {
        var bloc = new Bloc();
        
        bloc.setId(resultSetBloc.getInt("bloc_id"));
        bloc.setCharges(resultSetBloc.getDouble("bloc_charges"));
        
        return bloc;
    }
    
    public ArrayList<Bloc> getAllBloc() throws SQLException {
        try (var resultSetBloc = blocDAO.selectAllBloc();) {
            var lesBlocs = new ArrayList<Bloc>();
            
            while (resultSetBloc.next()) {
                lesBlocs.add(createBloc(resultSetBloc));
            }
            
            return lesBlocs;
        }
    }
    
    public Bloc getOneBloc(int id) throws SQLException {
        try (var resultSetBloc = blocDAO.selectOneBloc(id);) {
            return resultSetBloc.next() ? createBloc(resultSetBloc) : null;
        }
    }
}

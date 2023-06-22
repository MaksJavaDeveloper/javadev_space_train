package passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerDaoService {

    private PreparedStatement createSt;
    private PreparedStatement getByPassportSt;

    public PassengerDaoService(Connection connection) throws SQLException {

        createSt = connection.prepareStatement("INSERT INTO passenger (passport, name) VALUES (?, ?)");
        getByPassportSt = connection.prepareStatement("SELECT id, name FROM passenger WHERE passport = ?");
    }

    public void create(Passenger passenger) throws SQLException {
        createSt.setString(1, passenger.getPassport());
        createSt.setString(2, passenger.getName());

        createSt.executeUpdate();
    }

    public Passenger getByPassport(String passport) throws SQLException {
        getByPassportSt.setString(1, passport);

        try(ResultSet rs = getByPassportSt.executeQuery()) {
            if(!rs.next()) {
                return null;
            }
        Passenger result = new Passenger();
            result.setId(rs.getLong("id"));
            result.setPassport(rs.getString("passport"));
            result.setName(rs.getString("name"));

            return result;
        }
    }
}

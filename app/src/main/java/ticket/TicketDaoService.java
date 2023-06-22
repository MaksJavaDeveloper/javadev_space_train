package ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TicketDaoService {

    private PreparedStatement createSt;
    private PreparedStatement getMaxIdSt;
    private PreparedStatement getTicketCountToPlanetSt;

    private ExecutorService insertExecutor;
    public TicketDaoService(Connection connection) throws SQLException {

        insertExecutor = Executors.newSingleThreadExecutor();

        createSt = connection.prepareStatement
                ("INSERT INTO ticket (passenger_id, from_planet, to_planet) VALUES (?, ?, ?)") ;
        getMaxIdSt = connection.prepareStatement("SELECT max(id) AS maxId FROM ticket");

        getTicketCountToPlanetSt = connection.prepareStatement(
                "SELECT count(*) AS cnt FROM ticket WHERE to_planet = ?"
        );

    }

    public long create(Ticket ticket) throws SQLException, ExecutionException, InterruptedException {
        Future<Long> ft = insertExecutor.submit(() -> {
            //create
            createSt.setLong(1, ticket.getPassengerId());
            createSt.setString(2, ticket.getFrom().name());
            createSt.setString(3, ticket.getTo().name());
            createSt.executeUpdate();

            //find and return max ID
            try(ResultSet rs = getMaxIdSt.executeQuery()) {
                rs.next();

                return rs.getLong("maxId");
        }
        });

        return ft.get();
    }

    public long getTicketCountToPlanet(Planet planet) throws SQLException {
        getTicketCountToPlanetSt.setString(1, planet.name());

        try(ResultSet rs = getTicketCountToPlanetSt.executeQuery()) {
            rs.next();
            return rs.getLong("maxId");
        }
    }
}

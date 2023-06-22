package cli;

import ticket.Planet;
import ticket.TicketDaoService;

import java.sql.SQLException;

public class PlanetStatsState extends CliState {

    public PlanetStatsState(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void init() {
        System.out.println("Enter TO planet:");

        Planet planet = new PlanetChooser(fsm.getScanner()).ask();

        try {
            TicketDaoService ticketDaoService = new TicketDaoService(fsm.getConnectionProvider().createConnection());
            long ticketCountToPlanet = ticketDaoService.getTicketCountToPlanet(planet);
            System.out.println(planet + " found. Ticket count: " + ticketCountToPlanet);

            fsm.setState(new IdleState(fsm));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package cli;

public class IdleState extends CliState{

    public IdleState(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void unknownCommand(String cmd) {
        System.out.println("Unknown command: " + cmd);
    }

    @Override
    public void newTicketRequested() {
        fsm.setState(new AddTicketState(fsm));
    }

    @Override
    public void planetStatsRequested() {
        fsm.setState(new PlanetStatsState(fsm));
    }


}

package cli;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CliState {

    protected final CliFSM fsm;

    public void init() {

    }
    public void newTicketRequested() {

    }
    public void ticketOrdered() {

    }

    public void planetStatsRequested() {

    }

    public void planetStatsPrinted() {

    }

    public void unknownCommand(String cmd) {

    }
}

package ticket;

import lombok.Data;

@Data
public class Ticket {
    private long id;
    private long passengerId;
    private Planet from;
    private Planet to;


}

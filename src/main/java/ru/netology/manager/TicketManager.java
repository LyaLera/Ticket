package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

public class TicketManager {
    private TicketRepository repository;
    private Ticket[] tickets = new Ticket[0];

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void add(Ticket ticket) {
        repository.save(ticket);
    }

    public Ticket[] searchBy(String text, String text1) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.findAll(text, text1)) {
            if (matches(ticket, text, text1)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Ticket ticket, String search, String search1) {
        if (ticket.getFrom().contains(search) && ticket.getTo().contains(search1)) {
            return true;
        }
        return false;
    }

}

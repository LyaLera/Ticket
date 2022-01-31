package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;
import java.util.Comparator;

public class TicketManager {
    private TicketRepository repository;
    private Ticket[] tickets = new Ticket[0];

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void add(Ticket ticket) {
        repository.save(ticket);
    }

    public Ticket[] findAll(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] result = new Ticket[0];
        for(Ticket ticket : tickets)
        if (ticket.getFrom().contains(from) && ticket.getTo().contains(to)) {
            Ticket[] tmp = new Ticket[result.length + 1];
            System.arraycopy(result, 0, tmp, 0, result.length);
            tmp[tmp.length - 1] = ticket;
            result = tmp;

            Arrays.sort(result, comparator);
        }
        return result;
    }
}


package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    TicketRepository repository = new TicketRepository();
    TicketManager manager = new TicketManager(repository);

    Ticket first = new Ticket(1, 20000, "AAQ", "ALC", 2000);
    Ticket second = new Ticket(2, 15000, "AAQ", "ALC", 2000);
    Ticket third = new Ticket(3, 9000, "AAQ", "ALC", 1800);
    Ticket fourth = new Ticket(4, 12000, "AAQ", "ALC", 1900);
    Ticket fifth = new Ticket(5, 16000, "AAQ", "ALC", 2100);
    Ticket sixth = new Ticket(6, 16000, "AAQ", "ALC", 1900);
    Ticket seventh = new Ticket(7, 16000, "ALC", "AAQ", 1300);
    Ticket eighth = new Ticket(8, 16000, "ALC", "AAQ", 1300);
    Ticket ninth = new Ticket(9, 16000, "ALC", "AAQ", 1300);

    @Test
    public void shouldSortByTravelTimeIfMatchFromTo() {

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
        manager.add(seventh);
        manager.add(eighth);
        manager.add(ninth);

        Ticket[] actual = manager.findAll(first.getFrom(), first.getTo(), Comparator.comparing(Ticket::getTravelTime));
        Ticket[] expected = new Ticket[]{third, fourth, sixth, first, second, fifth};

        assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldNotSortByTravelTimeIfEmpty() {
        Ticket[] actual = manager.findAll(fourth.getFrom(), fourth.getTo(), Comparator.comparing(Ticket::getTravelTime));
        Ticket[] expected = new Ticket[]{};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByFromAndToIfOne() {

        manager.add(third);
        Ticket[] actual = manager.findAll(third.getFrom(), third.getTo(), Comparator.comparing(Ticket::getTravelTime));
        Ticket[] expected = new Ticket[]{third};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchIfNotExists() {

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
        manager.add(seventh);
        manager.add(eighth);
        manager.add(ninth);

        repository.removeById(4);

        Ticket[] actual = manager.findAll(fourth.getFrom(), fourth.getTo(), Comparator.comparing(Ticket::getTravelTime));
        Ticket[] expected = new Ticket[]{third, sixth, first, second, fifth};

        assertArrayEquals(expected, actual);
    }
}
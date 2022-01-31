package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    TicketRepository repository = new TicketRepository();
    TicketManager manager = new TicketManager(repository);

    Ticket first = new Ticket(1, 20000, "AAQ", "ALC", 1700);
    Ticket second = new Ticket(2, 15000, "AAQ", "ALC", 2000);
    Ticket third = new Ticket(3, 9000, "AAQ", "ATH", 1000);
    Ticket fourth = new Ticket(4, 12000, "AVN", "ATH", 1300);
    Ticket fifth = new Ticket(5, 16000, "AVN", "ATH", 1200);
    Ticket sixth = new Ticket(6, 17000, "AVN", "ATH", 1200);
    Ticket seventh = new Ticket(7, 16000, "AVN", "ATH", 1200);
    Ticket eight = new Ticket(8, 18000, "AVN", "ATH", 1200);
    Ticket ninth = new Ticket(9, 15000, "AVN", "ATH", 1200);
    Ticket tenth = new Ticket(10, 16000, "AVN", "ATH", 1200);


    @Test
    public void shouldSearchByFromAndByToIfMatch() {

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
        manager.add(seventh);
        manager.add(eight);
        manager.add(ninth);
        manager.add(tenth);

        Ticket[] actual = manager.findAll(eight.getFrom(), eight.getTo());
        Ticket[] expected = new Ticket[]{fourth, ninth, fifth, seventh, tenth, sixth, eight};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByFromAndByToIfOne() {

        manager.add(ninth);

        Ticket[] actual = manager.findAll(ninth.getFrom(), ninth.getTo());
        Ticket[] expected = new Ticket[]{ninth};

        assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldNotSearchIfEmpty() {

        Ticket[] actual = manager.findAll(first.getFrom(), first.getTo());
        Ticket[] expected = new Ticket[]{};

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
        manager.add(eight);
        manager.add(ninth);
        manager.add(tenth);

        repository.removeById(4);

        Ticket[] actual = manager.findAll(fourth.getFrom(), fourth.getTo());
        Ticket[] expected = new Ticket[]{ninth, fifth, seventh, tenth, sixth, eight};

        assertArrayEquals(expected, actual);
    }
}
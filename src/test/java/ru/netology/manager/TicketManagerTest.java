package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.domain.TicketByTravelTimeComparator;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    TicketRepository repository = new TicketRepository();
    TicketManager manager = new TicketManager(repository);
    TicketByTravelTimeComparator comparator = new TicketByTravelTimeComparator();

    Ticket first = new Ticket(1, 20000, "AAQ", "ALC", 2000);
    Ticket second = new Ticket(2, 15000, "AAQ", "ALC", 2000);
    Ticket third = new Ticket(3, 9000, "AAQ", "ATH", 1000);
    Ticket fourth = new Ticket(4, 12000, "AVN", "ATH", 1300);
    Ticket fifth = new Ticket(5, 16000, "AVN", "ATH", 1200);
    Ticket sixth = new Ticket(6, 16000, "ALC", "AAQ", 1200);
    Ticket seventh = new Ticket(7, 16000, "ALC", "AAQ", 1300);

    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
        manager.add(seventh);
    }

    @Test
    public void shouldSortByPrice() {
        Ticket[] actual = manager.findAll(first.getFrom(), first.getTo(), Comparator.comparing(Ticket::getTravelTime));
        Ticket[] expected = new Ticket[]{second, first};

        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortByPriceIfEqual() {
        Ticket[] actual = manager.findAll(sixth.getFrom(), sixth.getTo(), Comparator.comparing(Ticket::getTravelTime));
        Ticket[] expected = new Ticket[]{sixth, seventh};

        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortByTravelTime() {
        Ticket[] actual = manager.findAll(fourth.getFrom(), fourth.getTo(), Comparator.comparing(Ticket::getTravelTime));
        Ticket[] expected = new Ticket[]{fifth, fourth};

        Arrays.sort(actual, comparator);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortByTravelTimeIfEqual() {
        Ticket[] actual = manager.findAll(first.getFrom(), first.getTo(), Comparator.comparing(Ticket::getTravelTime));
        Ticket[] expected = new Ticket[]{first, second};

        Arrays.sort(actual, comparator);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchIfNotExists() {

        repository.removeById(4);

        Ticket[] actual = manager.findAll(fourth.getFrom(), fourth.getTo(), Comparator.comparing(Ticket::getTravelTime));
        Ticket[] expected = new Ticket[]{fifth};

        assertArrayEquals(expected, actual);
    }
}
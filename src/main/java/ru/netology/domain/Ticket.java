package ru.netology.domain;

public class Ticket implements Comparable<Ticket> {
    private int id;
    private int price;
    private String from;
    private String to;
    private int travelTime;

    public int getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }


    public Ticket(int id, int price, String from, String to, int travelTime) {
        this.id = id;
        this.price = price;
        this.from = from;
        this.to = to;
        this.travelTime = travelTime;
    }

    @Override
    public int compareTo(Ticket o) {
        return price - o.price;
    }
}

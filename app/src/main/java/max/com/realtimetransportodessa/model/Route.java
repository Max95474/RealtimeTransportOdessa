package max.com.realtimetransportodessa.model;

import java.util.List;

public class Route {
    private int id;
    private int number;
    private String type;
    private String title;
    private String color;
    private double distance;
    private double cost;
    private String language;
    private List<Segment> segments;
    private List<Transport> transport;

    public Route() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public List<Transport> getTransport() {
        return transport;
    }

    public void setTransport(List<Transport> transport) {
        this.transport = transport;
    }

    public static Builder newBuilder() {
        return new Route().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Builder setId(int id) {
            Route.this.id = id;
            return this;
        }

        public Builder setNumber(int number) {
            Route.this.number = number;
            return this;
        }

        public Builder setType(String type) {
            Route.this.type = type;
            return this;
        }

        public Builder setTitle(String title) {
            Route.this.title = title;
            return this;
        }

        public Builder setColor(String color) {
            Route.this.color = color;
            return this;
        }

        public Builder setDistance(double distance) {
            Route.this.distance = distance;
            return this;
        }

        public Builder setCost(double cost) {
            Route.this.cost = cost;
            return this;
        }

        public Builder setSegments(List<Segment> segments) {
            Route.this.segments = segments;
            return this;
        }

        public Builder setTransport(List<Transport> transport) {
            Route.this.transport = transport;
            return this;
        }

        public Route build() {
            return Route.this;
        }
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Number: " + number + ", Type: " + type + ", Title: " + title +
                ", Color: " + color + ", Distance: " + distance + ", Cost: " + cost + "\n";
    }
}

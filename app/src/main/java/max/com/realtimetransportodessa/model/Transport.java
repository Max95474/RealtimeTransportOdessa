package max.com.realtimetransportodessa.model;

public class Transport {
    private String id;
    private String inventoryNumber;
    private String url;
    private String routeId;
    private String tram;
    private int seats;
    private String title;
    private State state;

    public Transport() {}

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getTram() {
        return tram;
    }

    public void setTram(String tram) {
        this.tram = tram;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static Builder newBuilder() {
        return new Transport().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Builder setId(String id) {
            Transport.this.id = id;
            return this;
        }

        public Builder setInventoryNumber(String inventoryNumber) {
            Transport.this.inventoryNumber = inventoryNumber;
            return this;
        }

        public Builder setUrl(String url) {
            Transport.this.url = url;
            return this;
        }

        public Builder setRouteId(String routeId) {
            Transport.this.routeId = routeId;
            return this;
        }

        public Builder tram(String tram) {
            Transport.this.tram = tram;
            return this;
        }

        public Builder setSeats(int seats) {
            Transport.this.seats = seats;
            return this;
        }

        public Builder setTitle(String title) {
            Transport.this.title = title;
            return this;
        }

        public Transport build() {
            return Transport.this;
        }
    }
}

package max.com.realtimetransportodessa.model;

import java.util.List;

public class Segment {
    private int built;
    private int direction;
    private int id;
    private int position;
    private int routeId;
    private int stopping;
    private List<Point> points;

    public Segment() {}

    public int getBuilt() {
        return built;
    }

    public void setBuilt(int built) {
        this.built = built;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getStopping() {
        return stopping;
    }

    public void setStopping(int stopping) {
        this.stopping = stopping;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public class Builder {
        private Builder() {}

        public Builder setBuilt(int built) {
            Segment.this.built = built;
            return this;
        }

        public Builder setDirection(int direction) {
            Segment.this.direction = direction;
            return this;
        }

        public Builder setId(int id) {
            Segment.this.id = id;
            return this;
        }

        public Builder setPosition(int position) {
            Segment.this.position = position;
            return this;
        }

        public Builder setRouteId(int routeId) {
            Segment.this.routeId = routeId;
            return this;
        }

        public Builder setStopping(int stopping) {
            Segment.this.stopping = stopping;
            return this;
        }

        public Builder setPoints(List<Point> points) {
            Segment.this.points = points;
            return this;
        }

        public Segment build() {
            return Segment.this;
        }
    }

    public static Builder newBuilder() {
        return new Segment().new Builder();
    }
}

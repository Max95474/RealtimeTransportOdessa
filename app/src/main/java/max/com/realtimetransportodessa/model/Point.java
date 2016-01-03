package max.com.realtimetransportodessa.model;

public class Point {
    private int id;
    private double lat;
    private double lng;
    private int position;
    private int segmentId;

    public Point() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(int segmentId) {
        this.segmentId = segmentId;
    }

    public class Builder {
        private Builder() {}

        public Builder setId(int id) {
            Point.this.id = id;
            return this;
        }

        public Builder setLat(double lat) {
            Point.this.lat = lat;
            return this;
        }

        public Builder setLng(double lng) {
            Point.this.lng = lng;
            return this;
        }

        public Builder setPosition(int position) {
            Point.this.position = position;
            return this;
        }

        public Builder setSegmentId(int segmentId) {
            Point.this.segmentId = segmentId;
            return this;
        }

        public Point build() {
            return Point.this;
        }
    }

    public static Builder newBuilder() {
        return new Point().new Builder();
    }
}

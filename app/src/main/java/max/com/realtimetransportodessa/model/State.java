package max.com.realtimetransportodessa.model;

public class State {
    private double lat;
    private double lng;
    private String ts;
    private int speed;
    private int azimuth;
    private int ignit;
    private int gsmPower;
    private String imei;

    public State() {}

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

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(int azimuth) {
        this.azimuth = azimuth;
    }

    public int getIgnit() {
        return ignit;
    }

    public void setIgnit(int ignit) {
        this.ignit = ignit;
    }

    public int getGsmPower() {
        return gsmPower;
    }

    public void setGsmPower(int gsmPower) {
        this.gsmPower = gsmPower;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public static Builder newBuilder() {
        return new State().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Builder setLat(double lat) {
            State.this.lat = lat;
            return this;
        }

        public Builder setLng(double lng) {
            State.this.lng = lng;
            return this;
        }

        public Builder setTs(String ts) {
            State.this.ts = ts;
            return this;
        }

        public Builder setSpeed(int speed) {
            State.this.speed = speed;
            return this;
        }

        public Builder setAzimuth(int azimuth) {
            State.this.azimuth = azimuth;
            return this;
        }

        public Builder setIgnit(int ignit) {
            State.this.ignit = ignit;
            return this;
        }

        public Builder setGsmPower(int gsmPower) {
            State.this.gsmPower = gsmPower;
            return this;
        }

        public Builder setImei(String imei) {
            State.this.imei = imei;
            return this;
        }

        public State build() {
            return State.this;
        }
    }
}

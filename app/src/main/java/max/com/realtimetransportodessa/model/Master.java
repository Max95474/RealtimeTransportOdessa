package max.com.realtimetransportodessa.model;

public class Master {
    private int id;
    private String title;

    public Master() {}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public class Builder {
        private Builder() {}

        public Builder setId(int id) {
            Master.this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            Master.this.title = title;
            return this;
        }

        public Master build() {
            return Master.this;
        }
    }

    public static Builder newBuilder() {
        return new Master().new Builder();
    }
}

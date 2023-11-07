package Domain;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Reference {
    private final AlphanumericString ref;
    private final AlphanumericString name;
    private final AlphanumericString description;
    private final int prix;

    public Reference(String ref, String name, String description, int prix) {
        if (prix < 0) throw new IllegalArgumentException("prix must be greather or equal than 0.");

        this.ref = new AlphanumericString(ref, 20);
        this.name = new AlphanumericString(name, 20);
        this.description = new AlphanumericString(description, 220);
        this.prix = prix;
    }

    public String getRef() { return ref.getValue(); }
    public String getName() { return name.getValue(); }
    public String getDescription() { return description.getValue(); }
    public int getPrix() { return prix; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reference reference = (Reference) o;
        return prix == reference.prix && Objects.equals(ref, reference.ref) && Objects.equals(name, reference.name) && Objects.equals(description, reference.description);
    }

    @Override public int hashCode() { return Objects.hash(ref, name, description, prix); }

    @Override public String toString() {
        return "Reference(ref=" + ref +
                ", name=" + name +
                ", description=" + description +
                ", prix=" + prix + ')';
    }

    static final class AlphanumericString {
        private static final String REGEX = "^[a-zA-Z0-9]";
        private final String value;

        public AlphanumericString(String value, int maxSize) {
            if (!isValid(value, maxSize)) throw new IllegalArgumentException("Invalid value");
            this.value = value;
        }

        private static boolean isValid(String value, int maxSize) {
            String regex = REGEX + "{1," + maxSize + "}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }

        public String getValue() { return value; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AlphanumericString that = (AlphanumericString) o;
            return Objects.equals(value, that.value);
        }

        @Override public int hashCode() { return Objects.hash(value); }
        @Override public String toString() { return "AlphanumericString(value=" + value + ')'; }
    }
}

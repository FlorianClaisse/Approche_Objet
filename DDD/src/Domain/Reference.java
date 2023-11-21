package Domain;

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
    }
}

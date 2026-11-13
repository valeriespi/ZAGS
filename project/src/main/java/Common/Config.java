package Common;

public final class Config {

    private Config() {
    }

    public static String standUser() {
        return required("stand.user", "STAND_USER");
    }

    public static String standPassword() {
        return required("stand.password", "STAND_PASSWORD");
    }

    public static String dbUser() {
        return required("db.user", "DB_USER");
    }

    public static String dbPassword() {
        return required("db.password", "DB_PASSWORD");
    }

    public static String standUrl() {
        return "https://" + standUser() + ":" + standPassword() + "@regoffice.senla.eu";
    }

    public static String selenoidUrl() {
        String value = System.getProperty("selenoid.url");
        if (value == null || value.isBlank()) {
            value = System.getenv("SELENOID_URL");
        }
        if (value == null || value.isBlank()) {
            value = "http://localhost:4444/wd/hub";
        }
        return value;
    }

    private static String required(String property, String envVariable) {
        String value = System.getProperty(property);
        if (value == null || value.isBlank()) {
            value = System.getenv(envVariable);
        }
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Не задан параметр '" + property + "': передайте системное свойство -D" + property
                            + "=<значение> или переменную окружения " + envVariable);
        }
        return value;
    }
}

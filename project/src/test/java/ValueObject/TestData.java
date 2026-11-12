package ValueObject;

import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class TestData {

    private static final Faker FAKER = new Faker(new Locale("ru", "RU"));
    private static final DateTimeFormatter UI_DATE = DateTimeFormatter.ofPattern("ddMMyyyy");

    private TestData() {
    }

    public static ApplicantDataFormData applicantData() {
        return new ApplicantDataFormData(
                maleSurname(), maleName(), malePatronymic(),
                phone(), passport(), city());
    }

    public static MarriageRegistrationData marriageRegistrationData() {
        String groomSurname = maleSurname();
        return new MarriageRegistrationData(
                groomSurname, maleName(), malePatronymic(),
                birthDate().format(UI_DATE), passport(), "Male",
                city(), marriageDate().format(UI_DATE), groomSurname,
                femaleSurname(), femaleName(), femalePatronymic(),
                birthDate().format(UI_DATE), passport());
    }

    public static BirthRegistrationData birthRegistrationData() {
        return new BirthRegistrationData(
                maleSurname(), maleName(), malePatronymic(),
                birthDate().format(UI_DATE), passport(), "Male",
                city(), city(),
                femaleName(), maleName(), femaleName(), maleName());
    }

    public static DeathRegistrationData deathRegistrationData() {
        return new DeathRegistrationData(
                maleSurname(), maleName(), malePatronymic(),
                city(), birthDate().format(UI_DATE), passport(), "Male",
                deathDate().format(UI_DATE), city());
    }

    public static AdminRegistrationData adminData() {
        return new AdminRegistrationData(
                maleSurname(), maleName(), malePatronymic(),
                phone(), passport(), birthDate().format(UI_DATE));
    }

    public static String maleSurname() {
        return FAKER.expression("#{Name.male_last_name}");
    }

    public static String maleName() {
        return FAKER.name().maleFirstName();
    }

    public static String malePatronymic() {
        return FAKER.expression("#{Name.male_middle_name}");
    }

    public static String femaleSurname() {
        return FAKER.expression("#{Name.female_last_name}");
    }

    public static String femaleName() {
        return FAKER.name().femaleFirstName();
    }

    public static String femalePatronymic() {
        return FAKER.expression("#{Name.female_middle_name}");
    }

    public static String passport() {
        return FAKER.number().digits(8);
    }

    public static String phone() {
        return "7" + FAKER.number().digits(10);
    }

    public static String city() {
        return FAKER.address().city();
    }

    public static String isoDate() {
        return birthDate().toString();
    }

    public static String adminDateOfBirth() {
        return birthDate() + "T00:00:00.000Z";
    }

    private static LocalDate birthDate() {
        return randomDate(1970, 2000);
    }

    private static LocalDate marriageDate() {
        return randomDate(2000, 2026);
    }

    private static LocalDate deathDate() {
        return randomDate(2015, 2026);
    }

    private static LocalDate randomDate(int minYear, int maxYear) {
        return LocalDate.of(FAKER.number().numberBetween(minYear, maxYear),
                1 + FAKER.number().numberBetween(0, 12),
                1 + FAKER.number().numberBetween(0, 28));
    }
}

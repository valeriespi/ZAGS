package ValueObject;

public record AdminRegistrationData(
        String lastNameAdmin,
        String nameAdmin,
        String patronymicAdmin,
        String phoneAdmin,
        String passportAdmin,
        String dateOfBirthAdmin
) {

    public AdminRegistrationData {

        if (lastNameAdmin == null || lastNameAdmin.isBlank()) {
            throw new IllegalArgumentException("Фамилия администратора обязательна");
        }
        if (lastNameAdmin.length() > 100) {
            throw new IllegalArgumentException("Фамилия администратора не более 100 символов");
        }
        if (nameAdmin == null || nameAdmin.isBlank()) {
            throw new IllegalArgumentException("Имя администратора обязательно");
        }
        if (nameAdmin.length() > 100) {
            throw new IllegalArgumentException("Имя администратора не более 100 символов");
        }
        if (patronymicAdmin == null || patronymicAdmin.isBlank()) {
            throw new IllegalArgumentException("Отчество администратора обязательно");
        }
        if (patronymicAdmin.length() > 100) {
            throw new IllegalArgumentException("Отчество администратора не более 100 символов");
        }
        if (!patronymicAdmin.matches("^[A-Za-z]+$")) {
            throw new IllegalArgumentException("Отчество администратора: только латиница без спецсимволов");
        }
        if (phoneAdmin == null || phoneAdmin.isBlank()) {
            throw new IllegalArgumentException("Телефон администратора обязателен");
        }
        if (phoneAdmin.length() > 11) {
            throw new IllegalArgumentException("Телефон администратора не более 11 символов");
        }
        if (!phoneAdmin.matches("^[A-Za-z0-9]+$")) {
            throw new IllegalArgumentException("Телефон администратора: доступны только латиница и цифры");
        }
        if (passportAdmin == null || passportAdmin.isBlank()) {
            throw new IllegalArgumentException("Номер паспорта администратора обязателен");
        }
        if (!passportAdmin.matches("^[А-Яа-яЁё0-8]{1,8}$")) {
            throw new IllegalArgumentException("Паспорт администратора: до 8 символов, только русские буквы и цифры 0-8");
        }
    }
}
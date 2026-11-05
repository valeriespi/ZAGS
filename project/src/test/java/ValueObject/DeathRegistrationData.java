package ValueObject;

public record DeathRegistrationData(
        String citizenLastName,
        String citizenName,
        String citizenPatronymic,
        String citizenAddress,
        String citizenDateOfBirth,
        String citizenPassport,
        String citizenGender,
        String serviceDateOfDeath,
        String servicePlaceOfDeath
) {

    public DeathRegistrationData {
        if (citizenLastName == null || citizenLastName.isBlank()) {
            throw new IllegalArgumentException("Фамилия гражданина обязательна");
        }
        if (citizenLastName.length() > 150) {
            throw new IllegalArgumentException("Фамилия гражданина не более 150 символов");
        }
        if (citizenName == null || citizenName.isBlank()) {
            throw new IllegalArgumentException("Имя гражданина обязательно");
        }
        if (citizenName.length() > 100) {
            throw new IllegalArgumentException("Имя гражданина не более 100 символов");
        }
        if (citizenPatronymic == null || citizenPatronymic.isBlank()) {
            throw new IllegalArgumentException("Отчество гражданина обязательно");
        }
        if (citizenPatronymic.length() > 200) {
            throw new IllegalArgumentException("Отчество гражданина не более 200 символов");
        }
        if (citizenAddress == null || citizenAddress.isBlank()) {
            throw new IllegalArgumentException("Адрес прописки обязателен");
        }
        if (citizenAddress.length() > 30) {
            throw new IllegalArgumentException("Адрес прописки не более 30 символов");
        }
        if (citizenDateOfBirth == null || citizenDateOfBirth.isBlank()) {
            throw new IllegalArgumentException("Дата рождения обязательна");
        }
        if (citizenPassport == null || citizenPassport.isBlank()) {
            throw new IllegalArgumentException("Номер паспорта обязателен");
        }
        if (citizenPassport.length() > 8) {
            throw new IllegalArgumentException("Паспорт не более 8 символов");
        }
        if (citizenGender == null) {
            throw new IllegalArgumentException("Пол обязателен");
        }
        if (serviceDateOfDeath == null || serviceDateOfDeath.isBlank()) {
            throw new IllegalArgumentException("Дата смерти обязательна");
        }
        if (servicePlaceOfDeath == null || servicePlaceOfDeath.isBlank()) {
            throw new IllegalArgumentException("Место смерти обязательно");
        }
        if (servicePlaceOfDeath.length() > 50) {
            throw new IllegalArgumentException("Место смерти не более 50 символов");
        }
    }
}
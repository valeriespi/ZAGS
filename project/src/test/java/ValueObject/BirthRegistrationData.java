package ValueObject;

public record BirthRegistrationData(
        String citizenLastName,
        String citizenName,
        String citizenPatronymic,
        String citizenDateOfBirth,
        String citizenPassport,
        String citizenGender,
        String citizenAddress,
        String servicePlaceOfBirth,
        String serviceMother,
        String serviceFather,
        String serviceGrandmother,
        String serviceGrandfather
) {

    public BirthRegistrationData {

        if (citizenLastName == null || citizenLastName.isBlank()) {
            throw new IllegalArgumentException("Фамилия гражданина обязательна");
        }
        if (citizenLastName.length() > 100) {
            throw new IllegalArgumentException("Фамилия гражданина не более 100 символов");
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
        if (citizenPatronymic.length() > 100) {
            throw new IllegalArgumentException("Отчество гражданина не более 100 символов");
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
        if (servicePlaceOfBirth == null || servicePlaceOfBirth.isBlank()) {
            throw new IllegalArgumentException("Место рождения обязательно");
        }
        if (servicePlaceOfBirth.length() > 50) {
            throw new IllegalArgumentException("Место рождения не более 50 символов");
        }
        if (serviceMother == null || serviceMother.isBlank()) {
            throw new IllegalArgumentException("Данные матери обязательны");
        }
        if (serviceFather == null || serviceFather.isBlank()) {
            throw new IllegalArgumentException("Данные отца обязательны");
        }
        if (serviceGrandmother == null || serviceGrandmother.isBlank()) {
            throw new IllegalArgumentException("Данные бабушки обязательны");
        }
        if (serviceGrandfather == null || serviceGrandfather.isBlank()) {
            throw new IllegalArgumentException("Данные дедушки обязательны");
        }
    }
}
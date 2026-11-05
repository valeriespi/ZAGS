package ValueObject;

public record MarriageRegistrationData(
        String lastNameCitizenData,
        String nameCitizenData,
        String patronymicCitizenData,
        String dateOfBirthCitizenData,
        String passportCitizenData,
        String genderCitizenData,
        String addressCitizenData,
        String dateOfRegistrationServiceData,
        String newLastNameServiceData,
        String spousesLastNameServiceData,
        String spousesNameServiceData,
        String spousesPatronymicServiceData,
        String spousesDateOfBirthServiceData,
        String spousesPassportServiceData
) {

    public MarriageRegistrationData {
        if (lastNameCitizenData == null || lastNameCitizenData.isBlank())
            throw new IllegalArgumentException("Фамилия гражданина обязательна");
        if (lastNameCitizenData.length() > 100)
            throw new IllegalArgumentException("Фамилия гражданина не более 100 символов");
        if (nameCitizenData == null || nameCitizenData.isBlank())
            throw new IllegalArgumentException("Имя гражданина обязательно");
        if (nameCitizenData.length() > 100) throw new IllegalArgumentException("Имя гражданина не более 100 символов");
        if (patronymicCitizenData == null || patronymicCitizenData.isBlank())
            throw new IllegalArgumentException("Отчество гражданина обязательно");
        if (patronymicCitizenData.length() > 100)
            throw new IllegalArgumentException("Отчество гражданина не более 100 символов");
        if (dateOfBirthCitizenData == null || dateOfBirthCitizenData.isBlank())
            throw new IllegalArgumentException("Дата рождения обязательна");
        if (passportCitizenData == null || passportCitizenData.isBlank())
            throw new IllegalArgumentException("Паспорт гражданина обязателен");
        if (passportCitizenData.length() > 8)
            throw new IllegalArgumentException("Паспорт гражданина не более 8 символов");
        if (genderCitizenData == null) {
            throw new IllegalArgumentException("Пол обязателен");
        }
        if (addressCitizenData != null && !addressCitizenData.isBlank()) {
            if (addressCitizenData.length() > 50)
                throw new IllegalArgumentException("Адрес гражданина не более 50 символов");
        }
        if (dateOfRegistrationServiceData == null || dateOfRegistrationServiceData.isBlank()) {
            throw new IllegalArgumentException("Дата регистрации обязательна");
        }
        if (dateOfRegistrationServiceData.length() > 10)
            throw new IllegalArgumentException("Дата регистрации: не более 10 символов");
        if (spousesLastNameServiceData == null || spousesLastNameServiceData.isBlank()) {
            throw new IllegalArgumentException("Фамилия супруга обязательна");
        }
        if (spousesLastNameServiceData.length() > 50) {
            throw new IllegalArgumentException("Фамилия супруга не более 50 символов");
        }
        if (spousesNameServiceData == null || spousesNameServiceData.isBlank()) {
            throw new IllegalArgumentException("Имя супруга обязательно");
        }
        if (spousesNameServiceData.length() > 20) {
            throw new IllegalArgumentException("Имя супруга не более 20 символов");
        }
        if (spousesPatronymicServiceData == null || spousesPatronymicServiceData.isBlank()) {
            throw new IllegalArgumentException("Отчество супруги обязательно");
        }
        if (spousesPatronymicServiceData.length() > 20) {
            throw new IllegalArgumentException("Отчество супруги не более 20 символов");
        }
        if (spousesDateOfBirthServiceData == null || spousesDateOfBirthServiceData.isBlank()) {
            throw new IllegalArgumentException("Дата рождения супруга обязательна");
        }
        if (spousesDateOfBirthServiceData.length() > 10)
            throw new IllegalArgumentException("Дата рождения супруга: не более 10 символов");
        if (spousesPassportServiceData == null || spousesPassportServiceData.isBlank()) {
            throw new IllegalArgumentException("Паспорт супруги обязателен");
        }
        if (spousesPassportServiceData.length() > 8) {
            throw new IllegalArgumentException("Паспорт супруги не более 8 символов");
        }
        if (newLastNameServiceData != null && newLastNameServiceData.length() > 100) {
            throw new IllegalArgumentException("Новая фамилия не более 100 символов");
        }
    }
}
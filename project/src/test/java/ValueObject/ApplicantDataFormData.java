package ValueObject;

public record ApplicantDataFormData(
        String lastNameDataApplicant,
        String nameDataApplicant,
        String patronymicDataApplicant,
        String phoneDataApplicant,
        String passportDataApplicant,
        String addressDataApplicant) {

    public ApplicantDataFormData {
        if (lastNameDataApplicant == null || lastNameDataApplicant.isBlank()) {
            throw new IllegalArgumentException("Фамилия заявителя обязательна");
        }
        if (lastNameDataApplicant.length() > 100) {
            throw new IllegalArgumentException("Фамилия заявителя не более 100 символов");
        }
        if (nameDataApplicant == null || nameDataApplicant.isBlank()) {
            throw new IllegalArgumentException("Имя заявителя обязательно");
        }
        if (nameDataApplicant.length() > 100) {
            throw new IllegalArgumentException("Имя заявителя не более 100 символов");
        }
        if (patronymicDataApplicant == null || patronymicDataApplicant.isBlank()) {
            throw new IllegalArgumentException("Отчество заявителя обязательно");
        }
        if (patronymicDataApplicant.length() > 100) {
            throw new IllegalArgumentException("Отчество заявителя не более 100 символов");
        }
        if (phoneDataApplicant != null && !phoneDataApplicant.isBlank()) {
            if (phoneDataApplicant.length() > 11) {
                throw new IllegalArgumentException("Телефон не более 11 символов");
            }
        }
        if (passportDataApplicant == null || passportDataApplicant.isBlank()) {
            throw new IllegalArgumentException("Паспорт заявителя обязателен");
        }
        if (passportDataApplicant.length() > 8) {
            throw new IllegalArgumentException("Паспорт заявителя не более 8 символов");
        }
        if (addressDataApplicant != null && !addressDataApplicant.isBlank()) {
            if (addressDataApplicant.length() > 50) {
                throw new IllegalArgumentException("Адрес заявителя не более 50 символов");
            }
        }
    }
}

Feature: Marriage application registration

  Scenario: Successful marriage application registration
    Given открыта главная страница ЗАГС и выполнен вход как пользователь
    When заполнена форма заявителя случайными данными и нажата кнопка Далее
    And выбрана услуга регистрации брака и заполнены данные жениха и невесты
    Then на странице статуса отображается номер созданной заявки

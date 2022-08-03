package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {
    @Test
    public void shouldDeliveryCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[placeholder=\"Город\"]").val("Ижевск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder=\"Дата встречи\"]").val(testDate);
        $(byName("name")).val("Александр Иванов-Иванов");
        $(byName("phone")).val("+79099095577");
        $("[data-test-id=\"agreement\"].checkbox").click();
        $(byText("Забронировать")).click();
        $(By.className("notification__content"))
                .shouldHave(Condition.text("Встреча успешно забронирована на " + testDate), Duration.ofSeconds(15))
                .shouldHave(appear);
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String testDate = generateDate(5);
}

package ru.netology;

import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.Test;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String minDate = LocalDate.now().plusDays(3).format(formatter);


    @Test
    public void shouldDeliveryCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[placeholder=\"Город\"]").val("Ижевск");
        $("[placeholder=\"Дата встречи\"]").val(minDate);
        $(byName("name")).val("Александр Иванов-Иванов");
        $(byName("phone")).val("+79099095577");
        $("[data-test-id=\"agreement\"].checkbox").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"notification\"]").shouldHave(appear, Duration.ofSeconds(15));
    }
}

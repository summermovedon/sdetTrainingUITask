package com.gismatullin.sdettraininguitest.tests;

import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.clean;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.prepareDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gismatullin.sdettraininguitest.pages.MailBoxPage;
import com.gismatullin.sdettraininguitest.pages.MainPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MailTest {
    
    @BeforeEach
    public void tearUp() {
        prepareDriver();
    }

    @Test
    public void sendingTestEmail() {
        MailBoxPage mailBoxPage = MainPage.open().login();
        int currentNumber = mailBoxPage.countTestEmails();
        mailBoxPage.sendEmail(String.format("Найдено %d писем\\ьма", currentNumber));
        int newNumber = mailBoxPage.countTestEmails();
        int actualNumber = newNumber - currentNumber;
        mailBoxPage.logout();
        assertEquals(actualNumber, 1);
    }

    @AfterEach
    public void tearDown() {
        clean();
    }
}

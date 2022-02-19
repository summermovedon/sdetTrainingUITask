package com.gismatullin.sdettraininguitest.tests;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gismatullin.sdettraininguitest.pages.MailBoxPage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MailTest extends BaseTest {

    @Test
    @DisplayName("Test of sending email")
    public void sendEmail() {
        MailBoxPage mailBoxPage = MailBoxPage.create(driver);
        int currentNumber = mailBoxPage.countTestEmails();
        mailBoxPage.sendEmail(String.format("Найдено %d писем\\ьма", currentNumber));
        int newNumber = mailBoxPage.countTestEmails();
        int actualNumber = newNumber - currentNumber;
        assertEquals(actualNumber, 1, "Wrong number of test emails!");
    }
    
}

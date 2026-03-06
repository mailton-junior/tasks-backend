package br.ce.wcaquino.taskbackend.utils;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DateUtilsTest {

    @Test
    public void returnTrueToFutureDate(){
        LocalDate date = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),LocalDate.now().plusDays(1).getDayOfMonth());
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void returnFalseToFutureDate(){
        LocalDate date = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),LocalDate.now().minusDays(1).getDayOfMonth());
        Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void returnTrueIfDateIsNow(){
        LocalDate date = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth());
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
    }
}

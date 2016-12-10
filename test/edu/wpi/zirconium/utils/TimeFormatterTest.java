package edu.wpi.zirconium.utils;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeFormatterTest {
    private SimpleIntegerProperty secondsValue;
    private StringBinding formattedValue;

    @Before
    public void setUp() {
        secondsValue = new SimpleIntegerProperty(0);
        formattedValue = TimeFormatter.forValue(secondsValue);
    }

    @Test
    public void testZero() {
        assertEquals(":00", formattedValue.get());
    }

    @Test
    public void testOne() {
        secondsValue.set(1);
        assertEquals(":01", formattedValue.get());
    }

    @Test
    public void testMany() {
        secondsValue.set(35);
        assertEquals(":35", formattedValue.get());
    }

    @Test
    public void testOneMinute() {
        secondsValue.set(60);
        assertEquals("1:00", formattedValue.get());
    }

    @Test
    public void testMinutesAndSeconds() {
        secondsValue.set(2*60 + 35);
        assertEquals("2:35", formattedValue.get());
    }
}
package model;

import model.exceptions.OutOfBoundsException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BombTest {
    private Bomb bomb;

    @BeforeEach
    public void setup() {
        bomb = Bomb.getInstance();
        bomb.setFrk(false);
        bomb.setCar(false);
        bomb.setVowelInSerial(false);
        bomb.setParallelPort(false);
        try {
            bomb.setNumBatteries(0);
            bomb.setStrikes(0);
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    @Order(1)
    public void testConstructor() {
        try {
            bomb.setLastDigit(0);
        } catch (OutOfBoundsException e) {
            fail();
        }
        assertFalse(bomb.hasLitFrk());
        assertFalse(bomb.hasLitCar());
        assertFalse(bomb.hasParallelPort());
        assertFalse(bomb.hasVowelInSerial());
        assertEquals(0, bomb.getLastDigitOfSerial());
        assertEquals(0, bomb.getNumBatteries());
        assertEquals(0, bomb.getStrikes());
    }

    @Test
    public void testBooleanSetters() {
        bomb.setFrk(true);
        assertTrue(bomb.hasLitFrk());
        bomb.setCar(true);
        assertTrue(bomb.hasLitCar());
        bomb.setParallelPort(true);
        assertTrue(bomb.hasParallelPort());
        bomb.setVowelInSerial(true);
        assertTrue(bomb.hasVowelInSerial());
        bomb.setFrk(false);
        assertFalse(bomb.hasLitFrk());
        bomb.setCar(false);
        assertFalse(bomb.hasLitCar());
        bomb.setParallelPort(false);
        assertFalse(bomb.hasParallelPort());
        bomb.setVowelInSerial(false);
        assertFalse(bomb.hasVowelInSerial());
    }

    @Test
    public void testDigitSetter() {
        try {
            bomb.setLastDigit(5);
            assertEquals(5, bomb.getLastDigitOfSerial());
        } catch (OutOfBoundsException e) {
            fail("Should not have thrown exception.");
        }
        try {
            bomb.setLastDigit(10);
            fail("Should have thrown exception.");
        } catch (OutOfBoundsException e) {
            assertEquals("That is not a valid digit, please try again.", e.getMessage());
            try {
                bomb.setLastDigit(-1);
                fail();
            } catch (OutOfBoundsException e2) {
                assertEquals("That is not a valid digit, please try again.", e2.getMessage());
            }
        }
    }

    @Test
    public void testNumBatterySetter() {
        try {
            bomb.setNumBatteries(3);
            assertEquals(3, bomb.getNumBatteries());
            try {
                bomb.setNumBatteries(11);
                fail("Should have thrown exception.");
            } catch (OutOfBoundsException e) {
                assertEquals("The bomb cannot have that amount of batteries, please try again.", e.getMessage());
                try {
                    bomb.setNumBatteries(-1);
                    fail();
                } catch (OutOfBoundsException e2) {
                    assertEquals("The bomb cannot have that amount of batteries, please try again.", e2.getMessage());
                }
            }
        } catch (OutOfBoundsException e) {
            fail("Should not have thrown exception.");
        }
    }

    @Test
    public void testStrikesSetter() {
        try {
            bomb.setStrikes(2);
            assertEquals(2, bomb.getStrikes());
            try {
                bomb.setStrikes(3);
                fail("Should have thrown exception.");
            } catch (OutOfBoundsException e) {
                assertEquals("The bomb cannot have that amount of strikes, please try again.", e.getMessage());
                try {
                    bomb.setStrikes(-1);
                    fail();
                } catch (OutOfBoundsException e2) {
                    assertEquals("The bomb cannot have that amount of strikes, please try again.", e2.getMessage());
                }
            }
        } catch (OutOfBoundsException e) {
            fail("Should not have thrown exception.");
        }
    }

    @Test
    public void testDigitIsOdd() {
        try {
            bomb.setLastDigit(4);
            assertFalse(bomb.digitIsOdd());
            bomb.setLastDigit(5);
            assertTrue(bomb.digitIsOdd());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDigitIsEven() {
        try {
            bomb.setLastDigit(3);
            assertFalse(bomb.digitIsEven());
            bomb.setLastDigit(8);
            assertTrue(bomb.digitIsEven());
        } catch (Exception e) {
            fail();
        }
    }
}

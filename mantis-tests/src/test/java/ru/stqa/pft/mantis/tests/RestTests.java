package ru.stqa.pft.mantis.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class RestTests extends TestBase{

    @Test
    public void testNothingToCheckTry() {
        try {
            bugifySkipIfNotFixed(5983); //status="open"
            //skipIfNotFixed(0000002);
        } catch (SkipException e) {
            e.printStackTrace();
        }
    }

}

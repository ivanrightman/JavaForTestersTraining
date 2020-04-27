package project;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTests {

    @Test
    public void testArea() {
        Point p1 = new Point(10,5);
        Point p2 = new Point(5,10);
        Assert.assertEquals(p1.distance(p2), 7.0710678118654755);
    }
}

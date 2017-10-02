package se.cygni.npe;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {
    @Test
    public void testCleanup() {
      Assert.assertEquals("hejhej", App.cleanup("hej\nhej"));
    }
}

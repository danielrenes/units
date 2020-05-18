package com.github.danielrenes.units;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class TimeUnitTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {1000, TimeUnit.NANOSECOND, TimeUnit.SECOND, 0.000001},
                {100, TimeUnit.SECOND, TimeUnit.HOUR, 0.0277777}
        });
    }

    @Parameter(0)
    public double originalValue;

    @Parameter(1)
    public TimeUnit originalTimeUnit;

    @Parameter(2)
    public TimeUnit targetTimeUnit;

    @Parameter(3)
    public double expectedValue;

    @Test
    public void testConvert() {
        assertEquals(expectedValue, originalTimeUnit.convert(originalValue, targetTimeUnit), 10e-6);
    }
}
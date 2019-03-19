package cn.edu.tju;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import cn.edu.tju.demo;
import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertTrue;

@RunWith(Parameterized.class)
public class demoTest {

	private demo demo1;
    private int input;

    public demoTest(int input) {
        this.input = input;
    }

    @Before
    public void setUp() {
        demo1 = new demo();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
            {90}, {50}, {35}, {0}  
        });
    }

    @Test  
    public void Testcharge() {
        assertTrue(demo1.charge(input));
    }
}




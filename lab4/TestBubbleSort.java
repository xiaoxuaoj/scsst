import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

import java.lang.reflect.Array;

public class TestBubbleSort {
	@Test
	public void testSolution() {
		int arr[] = new int[]{1,9,2,2,4};
		int result[] = {1,2,2,4,9};
        assertEquals(Arrays.toString(result), Arrays.toString(BubbleSort.BubbleSort(arr)));
	}
}
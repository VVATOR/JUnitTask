/**
 * 
 */
package com.epam.training.ws.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.epam.training.ws.service.client.OuterClient;
import com.epam.training.ws.service.exception.HookNegativeSumException;

/**
 * @author Vikhliayeu
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceSOAPImplTest {
	@Mock
	private OuterClient client;

	@InjectMocks
	private ServiceSOAPImpl serviceSOAPImpl;

	@Test
	public void getAveraget_PositiveAverage_Calculated() {
		double expectedAVG = 1.0;
		double[] testDoubleArray = new double[] {};
		try {
			when(client.getAverage(testDoubleArray)).thenReturn(expectedAVG);
			assertTrue(serviceSOAPImpl.getAverage(testDoubleArray) == expectedAVG);
		} catch (HookNegativeSumException e) {
			fail("Not expected exception in this place \t=>\t\n" + e);
		}
	}

	@Test(expected = HookNegativeSumException.class)
	public void getAverage_NegativeAverage_HookNegativeSumExceptionThrown() throws HookNegativeSumException {
		double[] testDoubleArray = new double[] { -1.0 };
		when(client.getAverage(testDoubleArray)).thenCallRealMethod();
		System.out.println(serviceSOAPImpl.getAverage(testDoubleArray));
		fail("This test must throw Exception \t=>\t\n");
	}

}
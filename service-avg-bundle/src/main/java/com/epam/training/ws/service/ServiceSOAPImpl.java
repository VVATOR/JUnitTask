package com.epam.training.ws.service;

import org.apache.log4j.Logger;

import com.epam.training.ws.service.client.OuterClient;
import com.epam.training.ws.service.exception.HookNegativeSumException;

public class ServiceSOAPImpl implements ServiceSOAP {
	private static final Logger LOG = Logger.getLogger(ServiceSOAPImpl.class);

	private OuterClient client = OuterClient.getInstance();

	public ServiceSOAPImpl() {
		super();
	}

	@Override
	public double getAverage(final double[] arrayOfDouble) throws HookNegativeSumException {
		LOG.info("ServiceSOAPImpl.getAverage request ");
		double avg = client.getAverage(arrayOfDouble);
		LOG.info("ServiceSOAPImpl.getAverage response: " + avg + "\n");
		return avg;
	}
}

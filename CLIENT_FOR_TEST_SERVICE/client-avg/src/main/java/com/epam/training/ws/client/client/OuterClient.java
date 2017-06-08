package com.epam.training.ws.client.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.training.ws.service.ServiceSOAPImplService;

public final class OuterClient {
	private static final Logger LOG = Logger.getLogger(OuterClient.class);

	private enum Singleton {
		INSTANCE(new OuterClient());

		private OuterClient clientInstance;

		Singleton(final OuterClient clientInstance) {
			this.clientInstance = clientInstance;
		}

		public OuterClient getClientInstance() {
			LOG.info("OuterClient.getClientInstance as Singleton");
			return clientInstance;
		}
	}

	private static ServiceSOAPImplService service = new ServiceSOAPImplService();;

	public static OuterClient getInstance() {
		LOG.info("OuterClient.getInstance");
		return Singleton.INSTANCE.getClientInstance();
	}

	private OuterClient() {
		super();
	}

	public double getGetAverageResponse(final double[] arrayDouble) throws Exception {
		LOG.info("OuterClient.getGetAverageResponse");
		LOG.info("\tOuterClient Send requect to service");
		return service.getServiceSOAPImplPort().getAverage(convertDoubleArrayListDouble(arrayDouble));
	}

	private List<Double> convertDoubleArrayListDouble(final double[] doubleArray) {
		LOG.info("\tOuterClient.convertDoubleArrayListDouble");
		List<Double> list = new ArrayList<>();
		for (double doubleValue : doubleArray) {
			list.add(doubleValue);
			LOG.info("\targument: " + doubleValue);
		}
		return list;
	}
}

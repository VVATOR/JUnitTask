package com.epam.training.ws.service.client;

import javax.xml.ws.Holder;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;

import com.epam.training.ws.service.exception.HookNegativeSumException;

import net.webservicex.ArrayOfDouble;
import net.webservicex.GetStatisticsResponse;
import net.webservicex.StatisticsSoap;

public class OuterClient {
	private static final Logger LOG = Logger.getLogger(OuterClient.class);
	private static final String ADDRESS_OUTER_SERVICE_WSDL = "http://www.webservicex.net/Statistics.asmx?WSDL";
	private static final double DEFAULT_DOUBLE = 0.0;
	private static StatisticsSoap statistic;

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

	public static OuterClient getInstance() {
		LOG.info("OuterClient.getInstance");
		return Singleton.INSTANCE.getClientInstance();
	}

	private OuterClient() {
		super();
		LOG.info("OuterClient.OuterClient()");
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(StatisticsSoap.class);
		factory.setAddress(ADDRESS_OUTER_SERVICE_WSDL);
		statistic = factory.create(StatisticsSoap.class);
	}

	private GetStatisticsResponse getStatisticResponse(final double[] arrayDouble) throws HookNegativeSumException {
		LOG.info("OuterClient.GetStatisticsResponse");
		Holder<Double> sums = new Holder<>(DEFAULT_DOUBLE);
		Holder<Double> average = new Holder<>(DEFAULT_DOUBLE);
		Holder<Double> standardDeviation = new Holder<>(DEFAULT_DOUBLE);
		Holder<Double> skewness = new Holder<>(DEFAULT_DOUBLE);
		Holder<Double> kurtosis = new Holder<>(DEFAULT_DOUBLE);

		ArrayOfDouble arrayOfDouble = convertDoubleArrayToArrayOfDoubleObject(arrayDouble);
		statistic.getStatistics(arrayOfDouble, sums, average, standardDeviation, skewness, kurtosis);

		if (sums.value < DEFAULT_DOUBLE) {
			LOG.error("OuterClient.GetStatisticsResponse throw error sum argument less than 0");
			throw new HookNegativeSumException();
		}
		GetStatisticsResponse response = new GetStatisticsResponse();
		response.setSums(sums.value);
		response.setAverage(average.value);
		response.setStandardDeviation(standardDeviation.value);
		response.setSkewness(skewness.value);
		response.setKurtosis(kurtosis.value);
		return response;
	}

	private ArrayOfDouble convertDoubleArrayToArrayOfDoubleObject(final double[] arrayDouble) {
		LOG.info("OuterClient.convertDoubleArrayToArrayOfDoubleObject");
		ArrayOfDouble arrayOfDouble = new ArrayOfDouble();
		for (double doubleValue : arrayDouble) {
			arrayOfDouble.getDouble().add(doubleValue);
			LOG.info("\targument: " + doubleValue);
		}
		return arrayOfDouble;
	}

	public double getAverage(final double[] arrayOfDouble) throws HookNegativeSumException {
		LOG.info("ServiceSOAPImpl.getAverage request ");
		double avg = getStatisticResponse(arrayOfDouble).getAverage();
		LOG.info("ServiceSOAPImpl.getAverage response: " + avg + "\n");
		return avg;
	}

}

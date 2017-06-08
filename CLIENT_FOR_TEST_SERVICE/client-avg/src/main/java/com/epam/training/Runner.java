package com.epam.training;

import org.apache.log4j.Logger;

import com.epam.training.ws.client.client.OuterClient;

public class Runner {

    private static final Logger LOG = Logger.getLogger(Runner.class);
    private static final String STRING_NEW_LINE = "\n";

    public static void main(String[] args) {
        LOG.info("CLIENT START");

        OuterClient client = OuterClient.getInstance();

        try {
            LOG.info("Test:");
            double result = client.getGetAverageResponse(new double[] { 1160.20, 3440.50, -4144.0, 2345.30 });
            LOG.info("Service return Average: " + result + STRING_NEW_LINE);

            LOG.info("Test:");
            result = client.getGetAverageResponse(new double[] { 12.20, 155.0, 234.0, 4332.30 });
            LOG.info("Service return Average: " + result + STRING_NEW_LINE);

            LOG.info("Test:");
            result = client.getGetAverageResponse(new double[] { 1160.20, 30.50, 982.1, 360.50, 2.0, 332.30, -287.3 });
            LOG.info("Service return Average: " + result + STRING_NEW_LINE);

            LOG.info("Test with error (if sum numbers less than 0):");
            result = client.getGetAverageResponse(new double[] { -100.0, -30.50, 52.0, 10.30, 30.50, -155.9 });
            LOG.info("Service return Average: " + result + STRING_NEW_LINE);

        } catch (Exception e) {
            LOG.error("Client return exception: \t=>\t " + e);
        }
        LOG.info("CLIENT STOP");
    }

}

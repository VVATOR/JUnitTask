package com.epam.training.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ServiceSOAP {

	@WebMethod
	double getAverage(@WebParam(name = "arrayOfDouble") double[] arrayOfDouble) throws Exception;

}

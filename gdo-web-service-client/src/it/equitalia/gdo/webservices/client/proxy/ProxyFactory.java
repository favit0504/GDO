package it.equitalia.gdo.webservices.client.proxy;


import it.equitalia.gdo.webservices.client.NewsFrontendWebServicePortProxy;

public class ProxyFactory {

	static NewsFrontendWebServicePortProxy newsFrontendServiceproxy;


	public static NewsFrontendWebServicePortProxy getNewsFrontendServiceproxy(String endPoint) {
		if(newsFrontendServiceproxy == null){
			newsFrontendServiceproxy = new NewsFrontendWebServicePortProxy();
			newsFrontendServiceproxy._getDescriptor().setEndpoint(endPoint);
		}
		return newsFrontendServiceproxy;
	}

}

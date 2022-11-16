package it.equitalia.gdo.svecchiamento.client.proxy;


import it.equitalia.gdo.svecchiamento.client.SvecchiamentoWebServicePortProxy;

public class ProxyFactory {

	static SvecchiamentoWebServicePortProxy svecchiamentoServiceproxy;


	public static SvecchiamentoWebServicePortProxy getSvecchiamentoServiceproxy(String endPoint) {
		if(svecchiamentoServiceproxy == null){
			svecchiamentoServiceproxy = new SvecchiamentoWebServicePortProxy();
			svecchiamentoServiceproxy._getDescriptor().setEndpoint(endPoint);
		}
		return svecchiamentoServiceproxy;
	}

}

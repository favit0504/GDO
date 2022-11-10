package it.equitalia.gdo.webservices;

import it.equitalia.gdo.commons.exception.BusinessException;


@javax.jws.WebService (targetNamespace="http://webservices.gdo.equitalia.it/", serviceName="SvecchiamentoWebServiceService", portName="SvecchiamentoWebServicePort", wsdlLocation="WEB-INF/wsdl/SvecchiamentoWebServiceService.wsdl")
public class SvecchiamentoWebServiceDelegate{

    it.equitalia.gdo.webservices.SvecchiamentoWebService _svecchiamentoWebService = null;

    public boolean invocaSvecchiamento() throws BusinessException {
        return _svecchiamentoWebService.invocaSvecchiamento();
    }

    public boolean invocaSvecchiamentoDocumenti() throws BusinessException {
        return _svecchiamentoWebService.invocaSvecchiamentoDocumenti();
    }

    public boolean invocaSvecchiamentoNews() throws BusinessException {
        return _svecchiamentoWebService.invocaSvecchiamentoNews();
    }

    public SvecchiamentoWebServiceDelegate() {
        _svecchiamentoWebService = new it.equitalia.gdo.webservices.SvecchiamentoWebService(); }

}
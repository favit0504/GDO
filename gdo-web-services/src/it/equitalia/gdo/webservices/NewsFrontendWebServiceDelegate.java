package it.equitalia.gdo.webservices;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.webservices.valueobjects.ListaNews;


@javax.jws.WebService (targetNamespace="http://webservices.gdo.equitalia.it/", serviceName="NewsFrontendWebServiceService", portName="NewsFrontendWebServicePort", wsdlLocation="WEB-INF/wsdl/NewsFrontendWebServiceService.wsdl")
public class NewsFrontendWebServiceDelegate{

    it.equitalia.gdo.webservices.NewsFrontendWebService _newsFrontendWebService = null;

    public ListaNews recuperaNewsAttiveDestinateAdUtenteEnte(String chiaveUtente) throws BusinessException {
        return _newsFrontendWebService.recuperaNewsAttiveDestinateAdUtenteEnte(chiaveUtente);
    }

    public ListaNews recuperaNewsAttiveDestinateAdUtenteAgente(String chiaveUtente) throws BusinessException {
        return _newsFrontendWebService.recuperaNewsAttiveDestinateAdUtenteAgente(chiaveUtente);
    }

    public NewsFrontendWebServiceDelegate() {
        _newsFrontendWebService = new it.equitalia.gdo.webservices.NewsFrontendWebService(); }

}
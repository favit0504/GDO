package it.equitalia.gdo.webservices.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

public class NewsFrontendWebServicePortProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private it.equitalia.gdo.webservices.client.NewsFrontendWebServiceService _service = null;
        private it.equitalia.gdo.webservices.client.NewsFrontendWebServiceDelegate _proxy = null;
        private Dispatch<Source> _dispatch = null;

        public Descriptor() {
            _service = new it.equitalia.gdo.webservices.client.NewsFrontendWebServiceService();
            initCommon();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new it.equitalia.gdo.webservices.client.NewsFrontendWebServiceService(wsdlLocation, serviceName);
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getNewsFrontendWebServicePort();
        }

        public it.equitalia.gdo.webservices.client.NewsFrontendWebServiceDelegate getProxy() {
            return _proxy;
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("http://webservices.gdo.equitalia.it/", "NewsFrontendWebServicePort");
                _dispatch = _service.createDispatch(portQName, Source.class, Service.Mode.MESSAGE);

                String proxyEndpointUrl = getEndpoint();
                BindingProvider bp = (BindingProvider) _dispatch;
                String dispatchEndpointUrl = (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
                if (!dispatchEndpointUrl.equals(proxyEndpointUrl))
                    bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, proxyEndpointUrl);
            }
            return _dispatch;
        }

        public String getEndpoint() {
            BindingProvider bp = (BindingProvider) _proxy;
            return (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
        }

        public void setEndpoint(String endpointUrl) {
            BindingProvider bp = (BindingProvider) _proxy;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);

            if (_dispatch != null ) {
                bp = (BindingProvider) _dispatch;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);
            }
        }
    }

    public NewsFrontendWebServicePortProxy() {
        _descriptor = new Descriptor();
    }

    public NewsFrontendWebServicePortProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public ListaNews recuperaNewsAttiveDestinateAdUtenteEnte(String arg0) throws BusinessException_Exception {
        return _getDescriptor().getProxy().recuperaNewsAttiveDestinateAdUtenteEnte(arg0);
    }

    public ListaNews recuperaNewsAttiveDestinateAdUtenteAgente(String arg0) throws BusinessException_Exception {
        return _getDescriptor().getProxy().recuperaNewsAttiveDestinateAdUtenteAgente(arg0);
    }

    public ListaNews recuperaNewsAttivePerAltriUtenti(String arg0) throws BusinessException_Exception {
        return _getDescriptor().getProxy().recuperaNewsAttivePerAltriUtenti(arg0);
    }
    public ListaNews recuperaNewsAttivePerUtenteEsterno(String arg0) throws BusinessException_Exception {
        return _getDescriptor().getProxy().recuperaNewsAttivePerUtenteEsterno(arg0);
    }

}
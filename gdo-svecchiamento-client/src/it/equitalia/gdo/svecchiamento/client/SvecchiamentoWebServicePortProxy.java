package it.equitalia.gdo.svecchiamento.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

public class SvecchiamentoWebServicePortProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private it.equitalia.gdo.svecchiamento.client.SvecchiamentoWebServiceService _service = null;
        private it.equitalia.gdo.svecchiamento.client.SvecchiamentoWebServiceDelegate _proxy = null;
        private Dispatch<Source> _dispatch = null;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new it.equitalia.gdo.svecchiamento.client.SvecchiamentoWebServiceService(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            _service = new it.equitalia.gdo.svecchiamento.client.SvecchiamentoWebServiceService();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getSvecchiamentoWebServicePort();
        }

        public it.equitalia.gdo.svecchiamento.client.SvecchiamentoWebServiceDelegate getProxy() {
            return _proxy;
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("http://webservices.gdo.equitalia.it/", "SvecchiamentoWebServicePort");
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

    public SvecchiamentoWebServicePortProxy() {
        _descriptor = new Descriptor();
    }

    public SvecchiamentoWebServicePortProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public boolean invocaSvecchiamento() throws BusinessException_Exception {
        return _getDescriptor().getProxy().invocaSvecchiamento();
    }

    public boolean invocaSvecchiamentoDocumenti() throws BusinessException_Exception {
        return _getDescriptor().getProxy().invocaSvecchiamentoDocumenti();
    }

    public boolean invocaSvecchiamentoNews() throws BusinessException_Exception {
        return _getDescriptor().getProxy().invocaSvecchiamentoNews();
    }

}
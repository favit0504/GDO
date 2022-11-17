package it.equitalia.gdo.webservices.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

public class GeuGetUserProfilePortProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private it.equitalia.gdo.webservices.client.GeuGetUserProfileService _service = null;
        private it.equitalia.gdo.webservices.client.GeuGetUserProfileDelegate _proxy = null;
        private Dispatch<Source> _dispatch = null;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new it.equitalia.gdo.webservices.client.GeuGetUserProfileService(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            _service = new it.equitalia.gdo.webservices.client.GeuGetUserProfileService();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getGeuGetUserProfilePort();
        }

        public it.equitalia.gdo.webservices.client.GeuGetUserProfileDelegate getProxy() {
            return _proxy;
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("http://service.gestionaleutenze.equitaliaservizi.it/", "GeuGetUserProfilePort");
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

    public GeuGetUserProfilePortProxy() {
        _descriptor = new Descriptor();
    }

    public GeuGetUserProfilePortProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public UserProfile getUserProfile(UserProfileRequest getUserProfileRequest) {
        return _getDescriptor().getProxy().getUserProfile(getUserProfileRequest);
    }

}
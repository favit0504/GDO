//
// Generated By:JAX-WS RI IBM 2.0_03-07/07/2008 01:00 PM(foreman)-fcs (JAXB RI IBM 2.0.5-01/09/2012 05:11 AM(foreman)-fcs)
//


package it.equitalia.gdo.webservices.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

@WebServiceClient(name = "GeuGetUserProfileService", targetNamespace = "http://service.gestionaleutenze.equitaliaservizi.it/", wsdlLocation = "wsdl/GeuGetUserProfileService.wsdl")
public class GeuGetUserProfileService
    extends Service
{

    private final static URL GEUGETUSERPROFILESERVICE_WSDL_LOCATION;

    static {
        URL url = null;
        try {
            url = GeuGetUserProfileService.class.getClassLoader().getResource("wsdl/GeuGetUserProfileService.wsdl");
            if (url == null) throw new MalformedURLException("/wsdl/GeuGetUserProfileService.wsdl does not exist in the module.");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        GEUGETUSERPROFILESERVICE_WSDL_LOCATION = url;
    }

    public GeuGetUserProfileService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GeuGetUserProfileService() {
        super(GEUGETUSERPROFILESERVICE_WSDL_LOCATION, new QName("http://service.gestionaleutenze.equitaliaservizi.it/", "GeuGetUserProfileService"));
    }

    /**
     * 
     * @return
     *     returns GeuGetUserProfileDelegate
     */
    @WebEndpoint(name = "GeuGetUserProfilePort")
    public GeuGetUserProfileDelegate getGeuGetUserProfilePort() {
        return (GeuGetUserProfileDelegate)super.getPort(new QName("http://service.gestionaleutenze.equitaliaservizi.it/", "GeuGetUserProfilePort"), GeuGetUserProfileDelegate.class);
    }

}

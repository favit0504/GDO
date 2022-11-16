//
// Generated By:JAX-WS RI IBM 2.0_03-07/07/2008 01:00 PM(foreman)-fcs (JAXB RI IBM 2.0.5-01/09/2012 05:11 AM(foreman)-fcs)
//


package it.equitalia.gdo.svecchiamento.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.equitalia.gdo.webservices package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _InvocaSvecchiamento_QNAME = new QName("http://webservices.gdo.equitalia.it/", "invocaSvecchiamento");
    private final static QName _InvocaSvecchiamentoDocumenti_QNAME = new QName("http://webservices.gdo.equitalia.it/", "invocaSvecchiamentoDocumenti");
    private final static QName _InvocaSvecchiamentoNewsResponse_QNAME = new QName("http://webservices.gdo.equitalia.it/", "invocaSvecchiamentoNewsResponse");
    private final static QName _InvocaSvecchiamentoNews_QNAME = new QName("http://webservices.gdo.equitalia.it/", "invocaSvecchiamentoNews");
    private final static QName _InvocaSvecchiamentoDocumentiResponse_QNAME = new QName("http://webservices.gdo.equitalia.it/", "invocaSvecchiamentoDocumentiResponse");
    private final static QName _InvocaSvecchiamentoResponse_QNAME = new QName("http://webservices.gdo.equitalia.it/", "invocaSvecchiamentoResponse");
    private final static QName _BusinessException_QNAME = new QName("http://webservices.gdo.equitalia.it/", "BusinessException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.equitalia.gdo.webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InvocaSvecchiamentoResponse }
     * 
     */
    public InvocaSvecchiamentoResponse createInvocaSvecchiamentoResponse() {
        return new InvocaSvecchiamentoResponse();
    }

    /**
     * Create an instance of {@link InvocaSvecchiamentoNews }
     * 
     */
    public InvocaSvecchiamentoNews createInvocaSvecchiamentoNews() {
        return new InvocaSvecchiamentoNews();
    }

    /**
     * Create an instance of {@link InvocaSvecchiamentoNewsResponse }
     * 
     */
    public InvocaSvecchiamentoNewsResponse createInvocaSvecchiamentoNewsResponse() {
        return new InvocaSvecchiamentoNewsResponse();
    }

    /**
     * Create an instance of {@link InvocaSvecchiamentoDocumenti }
     * 
     */
    public InvocaSvecchiamentoDocumenti createInvocaSvecchiamentoDocumenti() {
        return new InvocaSvecchiamentoDocumenti();
    }

    /**
     * Create an instance of {@link InvocaSvecchiamentoDocumentiResponse }
     * 
     */
    public InvocaSvecchiamentoDocumentiResponse createInvocaSvecchiamentoDocumentiResponse() {
        return new InvocaSvecchiamentoDocumentiResponse();
    }

    /**
     * Create an instance of {@link BusinessException }
     * 
     */
    public BusinessException createBusinessException() {
        return new BusinessException();
    }

    /**
     * Create an instance of {@link InvocaSvecchiamento }
     * 
     */
    public InvocaSvecchiamento createInvocaSvecchiamento() {
        return new InvocaSvecchiamento();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvocaSvecchiamento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.gdo.equitalia.it/", name = "invocaSvecchiamento")
    public JAXBElement<InvocaSvecchiamento> createInvocaSvecchiamento(InvocaSvecchiamento value) {
        return new JAXBElement<InvocaSvecchiamento>(_InvocaSvecchiamento_QNAME, InvocaSvecchiamento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvocaSvecchiamentoDocumenti }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.gdo.equitalia.it/", name = "invocaSvecchiamentoDocumenti")
    public JAXBElement<InvocaSvecchiamentoDocumenti> createInvocaSvecchiamentoDocumenti(InvocaSvecchiamentoDocumenti value) {
        return new JAXBElement<InvocaSvecchiamentoDocumenti>(_InvocaSvecchiamentoDocumenti_QNAME, InvocaSvecchiamentoDocumenti.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvocaSvecchiamentoNewsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.gdo.equitalia.it/", name = "invocaSvecchiamentoNewsResponse")
    public JAXBElement<InvocaSvecchiamentoNewsResponse> createInvocaSvecchiamentoNewsResponse(InvocaSvecchiamentoNewsResponse value) {
        return new JAXBElement<InvocaSvecchiamentoNewsResponse>(_InvocaSvecchiamentoNewsResponse_QNAME, InvocaSvecchiamentoNewsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvocaSvecchiamentoNews }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.gdo.equitalia.it/", name = "invocaSvecchiamentoNews")
    public JAXBElement<InvocaSvecchiamentoNews> createInvocaSvecchiamentoNews(InvocaSvecchiamentoNews value) {
        return new JAXBElement<InvocaSvecchiamentoNews>(_InvocaSvecchiamentoNews_QNAME, InvocaSvecchiamentoNews.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvocaSvecchiamentoDocumentiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.gdo.equitalia.it/", name = "invocaSvecchiamentoDocumentiResponse")
    public JAXBElement<InvocaSvecchiamentoDocumentiResponse> createInvocaSvecchiamentoDocumentiResponse(InvocaSvecchiamentoDocumentiResponse value) {
        return new JAXBElement<InvocaSvecchiamentoDocumentiResponse>(_InvocaSvecchiamentoDocumentiResponse_QNAME, InvocaSvecchiamentoDocumentiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvocaSvecchiamentoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.gdo.equitalia.it/", name = "invocaSvecchiamentoResponse")
    public JAXBElement<InvocaSvecchiamentoResponse> createInvocaSvecchiamentoResponse(InvocaSvecchiamentoResponse value) {
        return new JAXBElement<InvocaSvecchiamentoResponse>(_InvocaSvecchiamentoResponse_QNAME, InvocaSvecchiamentoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusinessException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.gdo.equitalia.it/", name = "BusinessException")
    public JAXBElement<BusinessException> createBusinessException(BusinessException value) {
        return new JAXBElement<BusinessException>(_BusinessException_QNAME, BusinessException.class, null, value);
    }

}


package br.com.pco002.leitor;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.pco002.leitor package. 
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

    private final static QName _ReadFeed_QNAME = new QName("http://leitor.pco002.com.br/", "readFeed");
    private final static QName _ReadFeedResponse_QNAME = new QName("http://leitor.pco002.com.br/", "readFeedResponse");
    private final static QName _FeedMessage_QNAME = new QName("http://leitor.pco002.com.br/", "FeedMessage");
    private final static QName _Feed_QNAME = new QName("http://leitor.pco002.com.br/", "Feed");
    private final static QName _ReadTopic_QNAME = new QName("http://leitor.pco002.com.br/", "readTopic");
    private final static QName _ReadTopicResponse_QNAME = new QName("http://leitor.pco002.com.br/", "readTopicResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.pco002.leitor
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Feed }
     * 
     */
    public Feed createFeed() {
        return new Feed();
    }

    /**
     * Create an instance of {@link FeedMessage }
     * 
     */
    public FeedMessage createFeedMessage() {
        return new FeedMessage();
    }

    /**
     * Create an instance of {@link ReadFeedResponse }
     * 
     */
    public ReadFeedResponse createReadFeedResponse() {
        return new ReadFeedResponse();
    }

    /**
     * Create an instance of {@link ReadFeed }
     * 
     */
    public ReadFeed createReadFeed() {
        return new ReadFeed();
    }

    /**
     * Create an instance of {@link ReadTopicResponse }
     * 
     */
    public ReadTopicResponse createReadTopicResponse() {
        return new ReadTopicResponse();
    }

    /**
     * Create an instance of {@link ReadTopic }
     * 
     */
    public ReadTopic createReadTopic() {
        return new ReadTopic();
    }

    /**
     * Create an instance of {@link Feed.Entries }
     * 
     */
    public Feed.Entries createFeedEntries() {
        return new Feed.Entries();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadFeed }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://leitor.pco002.com.br/", name = "readFeed")
    public JAXBElement<ReadFeed> createReadFeed(ReadFeed value) {
        return new JAXBElement<ReadFeed>(_ReadFeed_QNAME, ReadFeed.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadFeedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://leitor.pco002.com.br/", name = "readFeedResponse")
    public JAXBElement<ReadFeedResponse> createReadFeedResponse(ReadFeedResponse value) {
        return new JAXBElement<ReadFeedResponse>(_ReadFeedResponse_QNAME, ReadFeedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FeedMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://leitor.pco002.com.br/", name = "FeedMessage")
    public JAXBElement<FeedMessage> createFeedMessage(FeedMessage value) {
        return new JAXBElement<FeedMessage>(_FeedMessage_QNAME, FeedMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Feed }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://leitor.pco002.com.br/", name = "Feed")
    public JAXBElement<Feed> createFeed(Feed value) {
        return new JAXBElement<Feed>(_Feed_QNAME, Feed.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadTopic }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://leitor.pco002.com.br/", name = "readTopic")
    public JAXBElement<ReadTopic> createReadTopic(ReadTopic value) {
        return new JAXBElement<ReadTopic>(_ReadTopic_QNAME, ReadTopic.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadTopicResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://leitor.pco002.com.br/", name = "readTopicResponse")
    public JAXBElement<ReadTopicResponse> createReadTopicResponse(ReadTopicResponse value) {
        return new JAXBElement<ReadTopicResponse>(_ReadTopicResponse_QNAME, ReadTopicResponse.class, null, value);
    }

}

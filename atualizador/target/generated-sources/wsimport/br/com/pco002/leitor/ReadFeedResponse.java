
package br.com.pco002.leitor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de readFeedResponse complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="readFeedResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="feed" type="{http://leitor.pco002.com.br/}Feed" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "readFeedResponse", propOrder = {
    "feed"
})
public class ReadFeedResponse {

    protected Feed feed;

    /**
     * Obtém o valor da propriedade feed.
     * 
     * @return
     *     possible object is
     *     {@link Feed }
     *     
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     * Define o valor da propriedade feed.
     * 
     * @param value
     *     allowed object is
     *     {@link Feed }
     *     
     */
    public void setFeed(Feed value) {
        this.feed = value;
    }

}

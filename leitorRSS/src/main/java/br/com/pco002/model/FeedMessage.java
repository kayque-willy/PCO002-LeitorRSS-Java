package br.com.pco002.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "FeedMessage")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FeedMessage", propOrder = {"title", "description", "link", "author", "guid", "date"})
public class FeedMessage implements Serializable {

    @XmlElement(name = "title", required = true)
    String title;
    @XmlElement(name = "description")
    String description;
    @XmlElement(name = "link", required = true)
    String link;
    @XmlElement(name = "author")
    String author;
    @XmlElement(name = "guid")
    String guid;
    @XmlElement(name = "date", required = true)
    Date date;

    public FeedMessage() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        this.date = format.parse(date);
    }

    @Override
    public String toString() {
        return "FeedMessage{" + "title=" + title + ", description=" + description + ", link=" + link + ", author=" + author + ", guid=" + guid + ", date=" + date + '}';
    }

}

package br.com.pco002.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Feed")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "Feed", propOrder = {"title", "link", "description",  "language", "copyright", "pubDate", "entries"})
public class Feed implements Serializable {

    @XmlElement(name = "title", required = true)
    final String title;
    @XmlElement(name = "link", required = true)
    final String link;
    @XmlElement(name = "description", required = true)
    final String description;
    @XmlElement(name = "language", required = true)
    final String language;
    @XmlElement(name = "copyright", required = true)
    final String copyright;
    @XmlElement(name = "pubDate", required = true)
    final String pubDate;

    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entrie")
    final List<FeedMessage> entries = new ArrayList<FeedMessage>();

    public Feed() {
        this.title = "";
        this.link = "";
        this.description = "";
        this.language = "";
        this.copyright = "";
        this.pubDate = "";
    }

    public Feed(String title, String link, String description, String language, String copyright, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.language = language;
        this.copyright = copyright;
        this.pubDate = pubDate;
    }

    public List<FeedMessage> getMessages() {
        return entries;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getPubDate() {
        return pubDate;
    }

    @Override
    public String toString() {
        return "Feed [copyright=" + copyright
                + ", description=" + description
                + ", language=" + language
                + ", link=" + link
                + ", pubDate=" + pubDate
                + ", title=" + title + "]";
    }

}

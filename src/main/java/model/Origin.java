package model;

public class Origin {
        private String citationLink;
        private String citationDate;

    public Origin() {

    }

    public Origin(String citationLink, String citationDate) {
        this.citationDate = citationDate;
        this.citationLink = citationLink;
    }

    public String getCitationLink() {
        return this.citationLink;
    }

    public String getCitationDate() {
        return this.citationDate;
    }

    public void setCitationLink(String citationLink) {
        this.citationLink = citationLink;
    }

    public void setCitationDate(String citationDate) {
        this.citationDate = citationDate;
    }
}

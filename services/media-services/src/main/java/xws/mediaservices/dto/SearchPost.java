package xws.mediaservices.dto;

public class SearchPost {
    private String tag;
    private String location;

    public SearchPost(){}

    public SearchPost(String tag, String location){
        this.tag=tag;
        this.location=location;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

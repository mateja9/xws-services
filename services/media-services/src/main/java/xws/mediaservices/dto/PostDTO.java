package xws.mediaservices.dto;

import java.util.List;

public class PostDTO {
    private String tags;
    private String location;
    private String description;

    public PostDTO(String tags, String location, String description) {
        super();
        this.tags = tags;
        this.location = location;
        this.description = description;
    }


    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getLocation() {

        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {

        return description;
    }
    public void setDescription(String description) {

        this.description = description;
    }



}


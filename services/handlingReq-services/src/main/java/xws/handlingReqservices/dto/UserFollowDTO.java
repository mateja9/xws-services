package xws.handlingReqservices.dto;

public class UserFollowDTO {
    private Long userFrom; // ja
    private Long userTo; //na koga sam kliknuo da ga pratim

    public Long getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(Long userFrom) {
        this.userFrom = userFrom;
    }

    public Long getUserTo() {
        return userTo;
    }

    public void setUserTo(Long userTo) {
        this.userTo = userTo;
    }
}

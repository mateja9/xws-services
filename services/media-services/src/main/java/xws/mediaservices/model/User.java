package xws.mediaservices.model;
import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "UserName", nullable = false)
    private String unerName;

    @Column(name = "FreeForTagging", nullable = false)
    private boolean freeForTagging;

    // proveriti kako se on pravi posto treba da se replicira prilikom registracije na user servisu
    public User(Long id, String name, String unerName, boolean freeForTagging) {
        this.id = id;
        this.name = name;
        this.unerName = unerName;
        this.freeForTagging = freeForTagging;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnerName() {
        return unerName;
    }

    public void setUnerName(String unerName) {
        this.unerName = unerName;
    }

    public boolean isFreeForTagging() {
        return freeForTagging;
    }

    public void setFreeForTagging(boolean freeForTagging) {
        this.freeForTagging = freeForTagging;
    }
}

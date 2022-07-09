package gallery;

import user.User;
import util.LinkedList;

import java.io.Serializable;

public class Galery implements Serializable {
    private LinkedList<Album> albums;
    private User user;


    public LinkedList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(LinkedList<Album> albums) {
        this.albums = albums;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

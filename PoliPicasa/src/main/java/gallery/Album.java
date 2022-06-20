package gallery;

import java.util.List;

public class Album <T> {

    private String albumName;
    private String albumDescription;

    public Album(String albumName,String albumDescription){
        this.albumName = albumName;
        this.albumDescription = albumDescription;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
    }
}

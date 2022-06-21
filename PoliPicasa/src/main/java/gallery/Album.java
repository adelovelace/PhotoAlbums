package gallery;

import user.Person;

import java.util.ArrayList;
import java.util.List;

public class Album <T> {



    private String albumName;
    private String albumDescription;

    private ArrayList<Photo> photosOnAlbum;

    public Album(String albumName,String albumDescription){
        this.albumName = albumName;
        this.albumDescription = albumDescription;
    }


    public boolean showPhotos(ArrayList photosOnAlbum){

        if(photosOnAlbum ==null){
            return false;
        }

        this.photosOnAlbum = photosOnAlbum;

        for (Photo photo: this.photosOnAlbum) {
            //console testing
            System.out.println("Photo Info on " + this.albumName + " Album");
            System.out.println("Photo description:" + photo.getDescriptionPhoto());
            System.out.println("Place:" + photo.getPlacePhoto());
            System.out.println("Date:" + photo.getDatePhoto());

            for (Person person: photo.getPersonsOnAlbum()) {
                System.out.println("Name of the Person:" + person.getPersonName());
            }
        }

        return true;
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

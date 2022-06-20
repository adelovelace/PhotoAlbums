import java.util.ArrayList;
import java.util.Date;

public class Photo {

    private String descriptionPhoto;
    private String placePhoto;
    private Date datePhoto;
    private ArrayList<Person> personsOnAlbum;
    private Album albumRelated;


    public Photo(String descriptionPhoto,  String placePhoto, Date datePhoto,ArrayList<Person> personsOnAlbum,  Album albumRelated){
        this.descriptionPhoto =descriptionPhoto;
        this.placePhoto= placePhoto;
        this.datePhoto=datePhoto;
    }


    public String getDescriptionPhoto() {
        return descriptionPhoto;
    }

    public void setDescriptionPhoto(String descriptionPhoto) {
        this.descriptionPhoto = descriptionPhoto;
    }

    public String getPlacePhoto() {
        return placePhoto;
    }

    public void setPlacePhoto(String placePhoto) {
        this.placePhoto = placePhoto;
    }

    public Date getDatePhoto() {
        return datePhoto;
    }

    public void setDatePhoto(Date datePhoto) {
        this.datePhoto = datePhoto;
    }

    public ArrayList<Person> getPersonsOnAlbum() {
        return personsOnAlbum;
    }

    public void setPersonsOnAlbum(ArrayList<Person> personsOnAlbum) {
        this.personsOnAlbum = personsOnAlbum;
    }

    public Album getAlbumRelated() {
        return albumRelated;
    }

    public void setAlbumRelated(Album albumRelated) {
        this.albumRelated = albumRelated;
    }
}

package gallery;

import user.Person;
import util.ArrayList;
import util.CircularDoublyLinkedList;
import validator.EmailValidator;

import java.io.Serializable;


public class Album<T> implements Serializable {

    private String ID;
    private String albumName;
    private String albumDescription;

    private CircularDoublyLinkedList<Photo> photosOnAlbum;

    public Album(String albumName, String albumDescription) {
        this.albumName = albumName;
        this.albumDescription = albumDescription;
        this.photosOnAlbum = new CircularDoublyLinkedList<>();
        ID = EmailValidator.generateCode();
    }

    public String getID() {
        return ID;
    }

    public boolean showPhotos(CircularDoublyLinkedList photosOnAlbum) {

        if (photosOnAlbum == null) {
            return false;
        }

        this.photosOnAlbum = photosOnAlbum;

        for (int i = 0; i < this.photosOnAlbum.size(); i++) {

            Photo photo = this.photosOnAlbum.get(i);
            //console testing
            System.out.println("Photo Info on " + this.albumName + " Album");
            System.out.println("Photo description:" + photo.getDescriptionPhoto());
            System.out.println("Place:" + photo.getPlacePhoto());
            System.out.println("Date:" + photo.getDatePhoto());

            for (Person person : photo.getPersonsOnAlbum()) {
                System.out.println("Name of the Person: " + person.getPersonName());
            }
        }

        return true;
    }

    public boolean addPhotos(CircularDoublyLinkedList<Photo> photosOnAlbum, ArrayList<Photo> newPhotos) {

        this.photosOnAlbum = photosOnAlbum;

        if (newPhotos == null) {
            System.out.println("Problem adding new Photo(s), it is Empty!");
            return false;
        }

        //by default it is inserted as the last one
        for (Photo photo : newPhotos) {
            if (this.photosOnAlbum.addLast(photo)) {
                System.out.println("Adding Photo successfully!");
            } else {
                System.out.println("Adding Photo NO successfully!");
            }
        }

        return true;
    }

    public boolean addPhoto(Photo photo) {

        if (photo == null) {
            System.out.println("Problem adding new Photo, it is Empty!");
            return false;
        }

        if (this.photosOnAlbum.addLast(photo)) {
            System.out.println("Adding Photo successfully!");
        } else {
            System.out.println("Adding Photo NO successfully!");
        }

        return true;
    }

    public boolean deletePhoto(CircularDoublyLinkedList<Photo> photosOnAlbum, Photo selectedPhoto) {

        this.photosOnAlbum = photosOnAlbum;

        if (selectedPhoto == null) {
            System.out.println("Problem deleting the selected Photo!");
            return false;
        }

        System.out.println("Indice de la foto seleccionada "+ this.photosOnAlbum.indexOf(selectedPhoto));

        int indexSelectedPhoto = this.photosOnAlbum.indexOf(selectedPhoto);

        System.out.println("Index of the photo: " + indexSelectedPhoto);

        System.out.println("Info of the selected Photo, for being deleted!");
        System.out.println("Description: " + this.photosOnAlbum.get(indexSelectedPhoto).getDatePhoto());

        this.photosOnAlbum.remove(indexSelectedPhoto);

        System.out.println("Remove successfully!!!");

        return true;
    }

    public CircularDoublyLinkedList<Photo> searchByPlace(String placePhoto) {
        CircularDoublyLinkedList<Photo> photosByPlace = new CircularDoublyLinkedList<>();
        System.out.println("Searching by place: " + placePhoto);
        for (int i = 0; i < this.photosOnAlbum.size(); i++) {
            System.out.println("Photo Info on " + this.albumName + " Album");
            Photo photo = this.photosOnAlbum.get(i);
            String placeOnPhoto = photo.getPlacePhoto();
            System.out.println("Place Photo:" + placeOnPhoto);
            System.out.println("Place Searched"+placePhoto);
            if (placeOnPhoto.equals(placePhoto)) {
                photosByPlace.addLast(photo);
            }
        }

        return photosByPlace;
    }

    public CircularDoublyLinkedList<Photo> searchByPersons( ArrayList<Person> personsOnAlbum) {
         CircularDoublyLinkedList<Photo> photosByPersons = new CircularDoublyLinkedList<>();
        if (personsOnAlbum != null) {
            for (int i = 0; i < this.photosOnAlbum.size(); i++) {
                Photo photo = this.photosOnAlbum.get(i);
                for (Person person : photo.getPersonsOnAlbum()) {
                    //System.out.println(personsOnAlbum.contains(person));
                    for (Person personOnAlbum : personsOnAlbum) {
                        if (person.getPersonName().equals( personOnAlbum.getPersonName()) ){
                            photosByPersons.addLast(photo);
                        }
                    }
                }

            }
        }

        return photosByPersons;
    }

    public CircularDoublyLinkedList<Photo> searchByPlaceAndByPersons(CircularDoublyLinkedList<Photo> photosOnAlbum, String placePhoto, ArrayList<Person> personsOnAlbum) {

        this.photosOnAlbum = photosOnAlbum;
        CircularDoublyLinkedList<Photo> photosByPlaceAndByPersons = new CircularDoublyLinkedList<>();

        for (int i = 0; i < this.photosOnAlbum.size(); i++) {
            Photo photo = this.photosOnAlbum.get(i);
            String placeOnPhoto = photo.getPlacePhoto();
            for (Person person : photo.getPersonsOnAlbum()) {
                //System.out.println(personsOnAlbum.contains(person));
                for (Person personOnAlbum : personsOnAlbum) {
                    if (person.getPersonName().equals( personOnAlbum.getPersonName()) ){
                        photosByPlaceAndByPersons.addLast(photo);
                    }
                }
            }


        }

        return photosByPlaceAndByPersons;
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

    public CircularDoublyLinkedList<Photo> getPhotosOnAlbum() {
        return photosOnAlbum;
    }
}

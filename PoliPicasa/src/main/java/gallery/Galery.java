package gallery;

import user.Person;
import user.User;
import util.ArrayList;
import util.CircularDoublyLinkedList;
import util.LinkedList;

import java.io.Serializable;

public class Galery implements Serializable {
    private LinkedList<Album<Photo>> albums;



    public Galery() {
        this.albums = new LinkedList<Album<Photo>>();
    }


    public LinkedList<Album<Photo>> getAlbums() {
        return albums;
    }

    public void setAlbums(LinkedList<Album<Photo>> albums) {
        this.albums = albums;
    }



    public boolean addAlbum(Album<Photo> album){
        if (album != null) {
            this.albums.addLast(album);
            return true;
        }
        return false;
    }

    public boolean deleteAlbum(Album<Photo> album){
        if (album != null) {
            this.albums.remove(albums.indexOf(album));
            return true;
        }
        return false;
    }

    public boolean addAlbumAppear(Album<Photo> album){
        if (album != null) {
            this.albums.addFirst(album);
            return true;
        }
        return false;
    }


    public Album<Photo> findAlbumByPlace(String placeToFind){
        Album<Photo> album = new Album<>("Album from " + placeToFind,"Album from " + placeToFind);
        for (Album<Photo> albums1 : albums) {
            System.out.println(albums1.getAlbumName()+"galery.class");
            CircularDoublyLinkedList<Photo> photoByPlace = albums1.searchByPlace(placeToFind);
            for (int i = 0; i < photoByPlace.size(); i++) {
                album.addPhoto(photoByPlace.get(i));
            }
        }
        return album;
    }
    public Album<Photo> findAlbumByPersons(ArrayList<Person> persons) {
        Album<Photo> album = new Album<>("Album from " + persons.toString(),"Album from " + persons.toString());
        for (Album<Photo> albums1 : albums) {
            CircularDoublyLinkedList<Photo> photoByPerson = albums1.searchByPersons(persons);
            for (int i = 0; i < photoByPerson.size(); i++) {
                album.addPhoto(photoByPerson.get(i));
            }
        }
        return album;
    }
    public Album<Photo> findAlbumByPlaceAndPersons(String place, ArrayList<Person> persons) {
        Album<Photo> album = new Album<>("Album from " + place + " and " + persons.toString(),"Album from " + place + " and " + persons.toString());
        for (Album<Photo> albums1 : albums) {
            CircularDoublyLinkedList<Photo> photosOnAlbum = albums1.getPhotosOnAlbum();
            CircularDoublyLinkedList<Photo> photoByPlaceAndPerson = albums1.searchByPlaceAndByPersons(photosOnAlbum, place, persons);
            for (int i = 0; i < photoByPlaceAndPerson.size(); i++) {
                album.addPhoto(photoByPlaceAndPerson.get(i));
            }
        }
        return album;
    }
}

package gallery;

import user.Person;
import util.ArrayList;
import util.LinkedList;

import java.io.Serializable;
import java.util.Date;


public class Photo implements Serializable {

    private String name;
    private String descriptionPhoto;
    private String placePhoto;
    private String datePhoto;
    private ArrayList<Person> personsOnAlbum;
    private Album albumRelated;
    private String route;
    private String Camera = "";
    private LinkedList<String> tag = new LinkedList<>();


    public Photo(String title, String descriptionPhoto, String placePhoto, String datePhoto, ArrayList<Person> personsOnAlbum, Album albumRelated, String route){
        this.name = title;
        this.descriptionPhoto =descriptionPhoto;
        this.placePhoto= placePhoto;
        this.datePhoto=datePhoto;
        this.personsOnAlbum=personsOnAlbum;
        this.albumRelated=albumRelated;
        this.setRoute(route);
        Camera = "No Camera Info";
        tag = new LinkedList<>();
        tag.addFirst("#NoTag");
    }



    public String getName() {
        return name;
    }
    public boolean addPerson(ArrayList<Person> helloPersons) {
        if(helloPersons.isEmpty()){return false;}
        for(Person people: helloPersons){
            personsOnAlbum.addLast(people);
        }
        return true;
    }

    public boolean deletedPerson(ArrayList<Person> byePersons){
        if(byePersons.isEmpty()){return false;}
        if(personsOnAlbum.isEmpty()){
            System.out.println("En esta foto no aparecen personas");
        }
        for(Person people: byePersons){
            int indexPeople = personsOnAlbum.indexOf(people);
            personsOnAlbum.remove(indexPeople);
        }
        return true;
    }


    public boolean modifyPerson(Person PersonSelected, String Nombre){
        if(personsOnAlbum.isEmpty()){
            System.out.println("En esta foto no aparecen personas");
        }
        for(Person people: personsOnAlbum){
            if(PersonSelected.equals(people)){
                people.setPersonName(Nombre);
            }
        }
        return true;
    }

    public String getRoute() {
        return route;
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

    public String getDatePhoto() {
        return datePhoto;
    }

    public void setDatePhoto(String datePhoto) {
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

    @Override
    public String toString() {
        return "Descripcion de la imagen=" + descriptionPhoto +
                "\nLugar de la imagen=" + placePhoto +
                "\nfecha de la foto=" + datePhoto +
                "\npersonas en el album=" + personsOnAlbum +
                "\nalbumRelated=" + albumRelated;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getCamera() {
        return Camera;
    }

    public void setCamera(String camera) {
        Camera = camera;
    }

    public LinkedList<String> getTag() {
        return tag;
    }

    public void setTag(LinkedList<String> tag) {
        this.tag = tag;
    }

    public String getPersons(){
        String persons = "";
        if (personsOnAlbum.isEmpty()){
            persons = "No Person in this photo";}
        for(Person people: personsOnAlbum){
            persons += people.getPersonName() + ", ";
        }
        return persons;
    }

    public String getTags(){
        String hashtag = "";
        for (String tag : tag) {
            hashtag +="#" +tag + " ";
        }
        return hashtag;
    }
}

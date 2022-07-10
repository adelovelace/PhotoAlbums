package gallery;

import user.Person;
import util.ArrayList;

import java.io.Serializable;
import java.util.Date;


public class Photo implements Serializable {

    private String descriptionPhoto;
    private String placePhoto;
    private Date datePhoto;
    private ArrayList<Person> personsOnAlbum;
    private Album albumRelated;
    private String route;


    public Photo(String descriptionPhoto, String placePhoto, Date datePhoto, ArrayList<Person> personsOnAlbum, Album albumRelated, String route){
        this.descriptionPhoto =descriptionPhoto;
        this.placePhoto= placePhoto;
        this.datePhoto=datePhoto;
        this.personsOnAlbum=personsOnAlbum;
        this.albumRelated=albumRelated;
        this.route=route;
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

    @Override
    public String toString() {
        return "Descripcion de la imagen=" + descriptionPhoto +
                "\nLugar de la imagen=" + placePhoto +
                "\nfecha de la foto=" + datePhoto +
                "\npersonas en el album=" + personsOnAlbum +
                "\nalbumRelated=" + albumRelated;
    }
}

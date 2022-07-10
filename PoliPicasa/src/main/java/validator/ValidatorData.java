package validator;

import gallery.Album;
import gallery.Galery;
import gallery.Photo;
import user.Person;
import user.Session;
import user.User;
import util.ArrayList;
import util.CircularDoublyLinkedList;
import util.LinkedList;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ValidatorData {

    private static String pathUser = "src/doc/users.dat";

    private static LinkedList<User> readUsers() {
        LinkedList<User> users = new LinkedList<User>();
        try {
            File file = new File(pathUser);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (LinkedList<User>) ois.readObject();
            return users;
        } catch (Exception e) {
            System.out.println("Error reading file");
            return users;
        }
    }

    public static void createUserFile() {
        try {
            File file = new File(pathUser);
            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new LinkedList<User>());
            oos.close();
        } catch (IOException e) {
            System.out.println("Error creating file");
        }
    }

    public static void deleteUser(User user) {
        LinkedList<User> users = readUsers();
        for (User userl : users) {
            System.out.println(user.getEmail());
            System.out.println(userl.getEmail());
            if (userl.equals(user)) {
                users.remove(users.indexOf(userl));
            }
        }
        writeUsers(users);
    }

    public static void addUser(User user) {
        LinkedList<User> users = readUsers();
        users.addLast(user);
        writeUsers(users);
    }

    public static void writeUsers(LinkedList<User> users) {
        try {
            File file = new File(pathUser);
            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error creating file");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing file");
        }
    }


    public static Session createSession(String email, String password) {
        Session session = null;
        LinkedList<User> users = readUsers();
        for (User user : users) {
            if (user.getEmail().equals(email) && user.comparePassword(password)) {
                session = new Session(user);
            }
        }
        return session;
    }

    public static boolean existEmail(String email) {
        LinkedList<User> users = readUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static boolean saveAlbumInFile(Album<Photo> album, User user) {

        LinkedList<User> users = readUsers();
        for (User user2 : users) {
            if (user2.getEmail().equals(user.getEmail())) {
                user2.getGalery().addAlbum(album);
                user.getGalery().addAlbum(album);
            }
        }
        writeUsers(users);
        return true;
    }


    public static boolean editAlbumInfoInFile(Album<Photo> album, User user, String name, String description) {

        LinkedList<User> users = readUsers();
        for (User user2 : users) {
            if (user2.getEmail().equals(user.getEmail())) {
                LinkedList<Album> galery = user2.getGalery().getAlbums();
                for (Album album2 : galery) {
                    if (album2.getID().equals(album.getID())) {
                        album2.setAlbumName(name);
                        album2.setAlbumDescription(description);
                    }
                }
            }
        }
        writeUsers(users);
        return true;
    }

    public static void addPhotoToAlbum(Photo photo, User user, Album<Photo> album) {
        LinkedList<User> users = readUsers();
        for (User user1 : users) {
            if (user1.getEmail().equals(user.getEmail())) {
                LinkedList<Album> galery = user.getGalery().getAlbums();
                for (Album album2 : galery) {
                    if (album2.getID().equals(album.getID())) {
                        album2.addPhoto(photo);
                        album.addPhoto(photo);
                    }
                }
            }
        }
        writeUsers(users);
    }
    public static void editPhotoInFile(Photo photo, User user, Album<Photo> album,String description, String place,String date,String persons, String tags, String camera) {
        ArrayList<Person> personsList = new ArrayList<Person>();
        LinkedList<String> tagsList = new LinkedList<String>();
        if(!persons.equals("")) {
            String[] personsArray = persons.split(",");
            for(String person : personsArray) {
                personsList.addLast(new Person(person));
            }
        }
        if(!tags.equals("")) {
            String[] tagsArray = tags.split("#");
            for(String tag : tagsArray) {
                tagsList.addLast(tag);
            }
        }

        LinkedList<User> users = readUsers();
        for (User user1 : users) {
            if (user1.getEmail().equals(user.getEmail())) {
                LinkedList<Album> galery = user.getGalery().getAlbums();
                for (Album album2 : galery) {
                    if (album2.getID().equals(album.getID())) {
                        CircularDoublyLinkedList<Photo> photos = album2.getPhotosOnAlbum();
                        for(int i = 0; i < photos.size(); i++) {
                            Photo photo2 = photos.get(i);
                            if(photo2.getRoute().equals(photo.getRoute())) {
                                photo2.setPersonsOnAlbum(personsList);
                                photo.setPersonsOnAlbum(personsList);
                                photo2.setTag(tagsList);
                                photo.setTag(tagsList);
                                if(description != null) {
                                    photo2.setDescriptionPhoto(description);
                                    photo.setDescriptionPhoto(description);
                                }else {
                                    photo2.setDescriptionPhoto("No description");
                                    photo.setDescriptionPhoto("No description");
                                }
                                if(!place.equals("")) {
                                    photo2.setPlacePhoto(place);
                                    photo.setPlacePhoto(place);
                                }
                                else {
                                    photo2.setPlacePhoto("No place");
                                    photo.setPlacePhoto("No place");
                                }
                                if(!date.equals("")) {
                                    photo2.setDatePhoto(date);
                                    photo.setDatePhoto(date);
                                }else{
                                    photo2.setDatePhoto("No date");
                                    photo.setDatePhoto("No date");
                                }
                                if (!camera.equals("")) {
                                    photo2.setCamera(camera);
                                    photo.setCamera(camera);
                            }else {
                                photo2.setCamera("No Camera");
                                photo.setCamera("No Camera");
                            }
                            }
                        }

                    }
                }
            }
        }
        writeUsers(users);
    }


}

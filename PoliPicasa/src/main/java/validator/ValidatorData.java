package validator;

import gallery.Album;
import gallery.Galery;
import gallery.Photo;
import user.Session;
import user.User;
import util.LinkedList;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;



public class ValidatorData {
private static String pathGallery= "src/doc/gallery.dat";
private static String pathUser= "src/doc/users.dat";


    public static void createGaleryFile() {
        try {
            File file = new File(pathGallery);
            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new LinkedList<Galery>());
            oos.close();
        } catch (IOException e) {
            System.out.println("Error creating file");
        }
    }

    /*public static void addGalery(Galery galery) {
        try {
            File file = new File(pathGallery);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            ObjectInputStream ois = new ObjectInputStream(fis);
            LinkedList<Galery> galeries = (LinkedList<Galery>) ois.readObject();
            galeries.addLast(galery);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(galeries);
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error creating file");
        }  catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing file");
        }
    }

    public static Galery findGalery(User user) {
        try {
            File file = new File(pathGallery);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            ObjectInputStream ois = new ObjectInputStream(fis);
            LinkedList<Galery> galleries = (LinkedList<Galery>) ois.readObject();
            for (Galery gallery : galleries) {
                if (gallery.getUser().equals(user)) {
                    return gallery;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file");
            return null;
        }
        return new Galery();
    }

    public static void deleteGalery(Galery galery) {
        try {
            File file = new File(pathGallery);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            ObjectInputStream ois = new ObjectInputStream(fis);
            LinkedList<Galery> galleries = (LinkedList<Galery>) ois.readObject();
            for (Galery gallery : galleries) {
                if (gallery.getUser().equals(galery.getUser())) {
                    galleries.remove(galleries.indexOf(gallery));
                }
            }
            FileOutputStream fos = new FileOutputStream(pathGallery);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(galleries);
            oos.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }}*/

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
        try {
            File file = new File(pathUser);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            ObjectInputStream ois = new ObjectInputStream(fis);
            LinkedList<User> users = (LinkedList<User>) ois.readObject();
            for (User userl : users) {
                System.out.println(user.getEmail());
                System.out.println(userl.getEmail());
                if (userl.equals(user)) {
                    users.remove(users.indexOf(userl));
                }
            }
            writeUsers(users);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(User user) {
        try {
            File file = new File(pathUser);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            ObjectInputStream ois = new ObjectInputStream(fis);
            LinkedList<User> users = (LinkedList<User>) ois.readObject();
            users.addLast(user);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error creating file");
        }  catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing file");
        }
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

    public static void saveUsers(User user) {
        try {
            File file = new File(pathUser);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            ObjectInputStream ois = new ObjectInputStream(fis);
            LinkedList<User> users = (LinkedList<User>) ois.readObject();
            users.addLast(user);
            writeUsers(users);
            for(User userl: users){
                System.out.println(userl.toString());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    public static Session createSession(String email, String password) {
        Session session = null;
        try {
            File file = new File(pathUser);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            ObjectInputStream ois = new ObjectInputStream(fis);
            LinkedList<User> users = (LinkedList<User>) ois.readObject();
            for (User user : users) {
                if (user.getEmail().equals(email) && user.comparePassword(password)) {
                    session = new Session(user);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error reading file");
            return null;
        }
        return session;
    }

    public static boolean existEmail(String email) {
        try {
            File file = new File(pathUser);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            ObjectInputStream ois = new ObjectInputStream(fis);
            LinkedList<User> users = (LinkedList<User>) ois.readObject();
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    return true;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file");
            return false;
        }
        return false;
    }

    public static boolean saveAlbumInFile(Album<Photo> album, User user) {
        try{
            File file = new File(pathUser);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            ObjectInputStream ois = new ObjectInputStream(fis);
            LinkedList<User> users = (LinkedList<User>) ois.readObject();
            for(User user2: users){
                if(user2.getEmail().equals(user.getEmail())){
                    user2.getGalery().addAlbum(album);
                }
            }
            writeUsers(users);
        }catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file");
            return false;
        }
        return true;
    }
    public static boolean editAlbumInfoInFile(Album<Photo> album, User user, String name, String description) {
        try{
            File file = new File(pathUser);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            ObjectInputStream ois = new ObjectInputStream(fis);
            LinkedList<User> users = (LinkedList<User>) ois.readObject();
            for(User user2: users){
                if(user2.getEmail().equals(user.getEmail())){
                    LinkedList<Album> galery = user2.getGalery().getAlbums();
                    for(Album album2: galery){
                        if(album2.getID().equals(album.getID())){
                            album2.setAlbumName(name);
                            album2.setAlbumDescription(description);
                        }
                    }
                }
            }
            writeUsers(users);
        }catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file");
            return false;
        }
        return true;
    }


}

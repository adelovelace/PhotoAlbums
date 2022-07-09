package validator;

import gallery.Album;
import gallery.Galery;
import user.Session;
import user.User;
import util.LinkedList;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static jdk.nashorn.internal.runtime.Context.printStackTrace;

public class ValidatorData {
private static String pathGallery= "Polipicasa/src/doc/gallery.dat";
private static String pathUser= "Polipicasa/src/doc/users.dat";


    /*public static void createGaleryFile() {
        try {
            File file = new File(pathGallery);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new LinkedList<Galery>());
            oos.close();
        } catch (IOException e) {
            System.out.println("Error creating file");
        }
    }*/
    public static void createUserFile() {
        try {
            File file = new File(pathUser);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new LinkedList<User>());
            oos.close();
        } catch (IOException e) {
            System.out.println("Error creating file");
        }
    }

    public static Session createSession(String email, String password) {
        Session session = null;
        try {
            File file = new File(pathUser);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            LinkedList<User> users = (LinkedList<User>) ois.readObject();
            for (User user : users) {
                if (user.getEmail().equals(email) && user.comparePassword(password)) {
                    session = new Session(user);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file");
            return null;
        }
        return session;
    }
    public static void writeUsers(LinkedList<User> users) {
        try {
            File file = new File(pathUser);
            FileOutputStream fos = new FileOutputStream(file);
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
    public static void addGalery(Galery galery) {
        try {
            File file = new File(pathGallery);
            FileInputStream fis = new FileInputStream(file);
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
    public static void saveUsers(User user) {
        try {
            FileInputStream fis = new FileInputStream(pathUser);
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

    public static Galery findGalery(User user) {
        try {
            File file = new File(pathGallery);
            FileInputStream fis = new FileInputStream(file);
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
    public static void deleteUser(User user) {
        try {
            FileInputStream fis = new FileInputStream(pathUser);
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
    public static boolean existEmail(String email) {
        try {
            FileInputStream fis = new FileInputStream(pathUser);
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

}

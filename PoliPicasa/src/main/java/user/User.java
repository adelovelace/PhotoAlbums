package user;

import gallery.Galery;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.Serializable;

/***
 * @author Luis Alejandro Donoso Bravo
 */
public class User implements Serializable {
    private Person person;
    private String email;
    private String password;
    private Galery galery;

    public User(String personName, String email, String password) {
        this.person = new Person(personName);
        this.email = email;
        this.password = password;
        this.galery = new Galery();
    }


    public boolean equals(User user2) {
        if (this.getEmail().equals(user2.getEmail())) {
            return true;
        } else {
            return false;
        }
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = convertToSHA256(password);
    }

    public Person getPerson() {
        return person;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public Galery getGalery() {
        return galery;
    }
    public void setGalery(Galery galery) {
        this.galery = galery;
    }

    private String convertToSHA256(String password){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public boolean comparePassword(String password){
        String codPassword = convertToSHA256(password);
        return this.password.equals(codPassword);
    }

}

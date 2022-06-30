package user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/***
 * @author Luis Alejandro Donoso Bravo
 */
public class User {
    private Person person;
    private String email;
    private String password;

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

package user;

import java.io.Serializable;

public class Person implements Serializable {

    private String personName;

    public Person(String personName){
        this.personName = personName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }



    public boolean equals(Person person2) {

        System.out.println("Person.equals"+person2.getPersonName());
        return this.getPersonName().equals(person2.getPersonName());
    }
}

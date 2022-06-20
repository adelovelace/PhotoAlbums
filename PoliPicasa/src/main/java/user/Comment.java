package user;

/***
 * @author Luis ALejandro Donoso Bravo
 */
public class Comment {
    private Person person;
    private String content;

    public Comment(Person person,String content){
        this.person=person;
        this.content=content;
    }

    public Person getPerson() {
        return person;
    }

    public String getContent() {
        return content;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

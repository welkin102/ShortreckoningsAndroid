package rgp.com.shortreckonings.model;

/**
 * Created by khyagupt on 14-05-2016.
 */
public class Person {

    public Person(){

    }

    public Person(String name, String nickname, String comment){
        this.name = name;
        this.nickname = nickname;
        this.comment = comment;
    }
    public String name;
    public String nickname;
    public String comment;
}

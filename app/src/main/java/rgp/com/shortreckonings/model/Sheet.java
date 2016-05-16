package rgp.com.shortreckonings.model;

/**
 * Created by khyagupt on 14-05-2016.
 */
public class Sheet {

    public Sheet() {
    }

    public Sheet (String title) {
        this.title = title;
        this.date = "23/05/2019";
        this.people = "Rohan, Mike, Steve, Saurabh, Anas";
        this.amount = getRandom();
    }
    public String title;
    public String date;
    public String people;
    public double amount;

    private double getRandom(){
        return  round(500 + (Math.random() * -700),2);
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}

package rgp.com.shortreckonings.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by khyagupt on 14-05-2016.
 */
public class Expense implements Parcelable{
    /* TODO : datecreated and dateUpdated should be Date datatype */
    public String title;
    public String dateCreated;
    public String dateUpdated;
    public double amount;
    public String paidBy;
    public String peopleInvolved;
    public String comment;

    public Expense(){

    }

    public Expense(String title, String paidBy,double amount ){
        this.title = title;
        this.paidBy = paidBy;
        this.amount = amount;
        this.dateCreated = "25/08/2016";
        this.peopleInvolved = "Jon, Don, Ravi, Neeraj, Gerry";
    }


    public Expense(Parcel in) {
        title = in.readString();
        dateCreated = in.readString();
        dateUpdated = in.readString();
        paidBy = in.readString();
        peopleInvolved = in.readString();
        comment = in.readString();
    }

    public static final Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(dateCreated);
        dest.writeString(dateUpdated);
        dest.writeString(paidBy);
        dest.writeString(peopleInvolved);
        dest.writeString(comment);
    }
}

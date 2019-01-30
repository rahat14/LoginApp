package anull.com.loginapp;

public class uploadHelper {

    String title ;
    String details ;


// keyboard ALT and INSERT button
    public uploadHelper() {


    }

    public uploadHelper(String title, String details) {
        this.title = title;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }
}

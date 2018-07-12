package com.burakod.rahatla;

public class BookShelf {


    public String getBookShelfID() {
        return bookShelfID;
    }

    public void setBookShelfID(String bookShelfID) {
        this.bookShelfID = bookShelfID;
    }

    public String getBookShelfName() {
        return bookShelfName;
    }

    public void setBookShelfName(String bookShelfName) {
        this.bookShelfName = bookShelfName;
    }

    public String getBookShelfImage() {
        return bookShelfImage;
    }

    public void setBookShelfImage(String bookShelfImage) {
        this.bookShelfImage = bookShelfImage;
    }
    String bookShelfID;
    String bookShelfName;
    String bookShelfImage;

    BookShelf(){}
}

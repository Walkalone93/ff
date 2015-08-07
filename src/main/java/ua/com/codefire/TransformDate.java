package ua.com.codefire;

public class TransformDate {

    public String invertDate(String date) {
        // dd-mm-yyyy --> yyyy-mm-dd
        String year = date.substring(6, 10);
        String month = date.substring(3, 5);
        String day = date.substring(0, 2);
        String newDate = year + "-" + month + "-" + day;
        return newDate;
    }

    public String reInvertDate(String date) {
        // yyyy-mm-dd --> dd-mm-yyyy
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);
        String newDate = day + "-" + month + "-" + year;
        return newDate;
    }
}
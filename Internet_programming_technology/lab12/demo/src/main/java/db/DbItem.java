package db;

import java.sql.Date;

public class DbItem {
    public String Name;
    public String getName() {
        return Name;
    }
    public Date DateOfFound;

    public Date getDateOfFound() {
        return DateOfFound;
    }

    public String getFounder() {
        return Founder;
    }

    public String Founder;
}

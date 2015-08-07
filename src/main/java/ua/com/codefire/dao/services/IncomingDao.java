package ua.com.codefire.dao.services;

import java.util.List;
import ua.com.codefire.entity.Incoming;

public interface IncomingDao {
    
    public Incoming saveIncoming(Incoming i);
    public void removeIncoming(Incoming i);
    public List<Incoming> findByDateIncoming(String date);
    public List<Incoming> findByRangeIncoming(String date1, String date2);

}
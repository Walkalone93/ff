package ua.com.codefire.dao.services;

import java.util.List;
import ua.com.codefire.entity.Spending;

public interface SpendingDao {
    
    public Spending saveSpending(Spending s);
    public void removeSpending(Spending s);
    public List<Spending> findByDateSpending(String date);
    public List<Spending> findByRangeSpending(String date1, String date2);
}
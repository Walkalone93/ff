package ua.com.codefire.dao.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import ua.com.codefire.dao.repositories.SpendingRepository;
import ua.com.codefire.dao.services.SpendingDao;
import ua.com.codefire.dao.services.SpendingDao;
import ua.com.codefire.entity.Spending;

@Transactional
@Controller("spendingDao")
public class SpendingDaoImpl implements SpendingDao {

    @Autowired
    private SpendingRepository spendingRepository;

    @Override
    public Spending saveSpending(Spending s) {
        return spendingRepository.saveAndFlush(s);
    }

    @Override
    public List<Spending> findByDateSpending(String date) {
        return spendingRepository.find(date);
    }

    @Override
    public void removeSpending(Spending s) {
        String date = s.getDate();
        String description = s.getDescription();
        float sum = s.getSum();
        String currency = s.getCurrency();

        spendingRepository.remove(date, description, sum, currency);
    }

    @Override
    public List<Spending> findByRangeSpending(String date1, String date2) {
        return spendingRepository.findByRange(date1, date2);
    }
 
}
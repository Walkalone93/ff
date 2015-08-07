package ua.com.codefire.dao.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import ua.com.codefire.dao.repositories.IncomingRepository;
import ua.com.codefire.dao.services.IncomingDao;
import ua.com.codefire.dao.services.IncomingDao;
import ua.com.codefire.entity.Incoming;

@Transactional
@Controller("incomingDao")
public class IncomingDaoImpl implements IncomingDao {

    @Autowired
    private IncomingRepository incomingRepository;

    @Override
    public Incoming saveIncoming(Incoming i) {
        return incomingRepository.saveAndFlush(i);
    }

    @Override
    public List<Incoming> findByDateIncoming(String date) {
        return incomingRepository.find(date);
    }

    @Override
    public void removeIncoming(Incoming i) {
        String date = i.getDate();
        String description = i.getDescription();
        float sum = i.getSum();
        String currency = i.getCurrency();

        incomingRepository.remove(date, description, sum, currency);
    }

    @Override
    public List<Incoming> findByRangeIncoming(String date1, String date2) {
         return incomingRepository.findByRange(date1, date2);
    }

}
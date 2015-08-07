package ua.com.codefire.dao.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.codefire.entity.Incoming;

public interface IncomingRepository extends JpaRepository<Incoming, Integer> {

    @Query("SELECT i FROM Incoming i WHERE i.date =:date")
    public List<Incoming> find(@Param("date") String date);

    @Query("SELECT i FROM Incoming i WHERE i.date BETWEEN ?1 AND ?2 ORDER BY date")
    public List<Incoming> findByRange(String date1, String date2);

    @Modifying
    @Query("DELETE FROM Incoming i WHERE i.date =:date AND "
            + "i.description =:description AND "
            + "i.sum =:sum AND i.currency =:currency")
    public void remove(@Param("date") String date,
            @Param("description") String description,
            @Param("sum") float sum,
            @Param("currency") String currency);

}
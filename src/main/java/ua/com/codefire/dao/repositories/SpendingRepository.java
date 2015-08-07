package ua.com.codefire.dao.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.codefire.entity.Spending;

public interface SpendingRepository extends JpaRepository<Spending, Integer> {

    @Query("SELECT s FROM Spending s WHERE s.date =:date")
    public List<Spending> find(@Param("date") String date);
    
        @Query("SELECT s FROM Spending s WHERE s.date BETWEEN ?1 AND ?2 ORDER BY date")
    public List<Spending> findByRange(String date1, String date2);
    
    @Modifying
    @Query("DELETE FROM Spending s WHERE s.date =:date AND "
            + "s.description =:description AND "
            + "s.sum =:sum AND s.currency =:currency")
    public void remove(@Param("date") String date,
            @Param("description") String description,
            @Param("sum") float sum,
            @Param("currency") String currency);

}
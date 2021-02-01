package com.devilray.repository;

import com.devilray.model.Accounts;
import com.devilray.model.Flights;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlightsRepository extends JpaRepository <Flights, Long> {

    @Query("SELECT SUM(m.day) FROM Flights m WHERE m.accounts=?1")
    double daytotals(Accounts account);

    @Query("SELECT SUM(m.nvg) FROM Flights m WHERE m.accounts=?1")
    double nvgtotals(Accounts account);

    @Query("SELECT SUM(m.night) FROM Flights m WHERE m.accounts=?1")
    double nighttotals(Accounts account);

    @Query("SELECT SUM(m.weather) FROM Flights m WHERE m.accounts=?1")
    double weathertotals(Accounts account);

    @Query("SELECT SUM(m.hood) FROM Flights m WHERE m.accounts=?1")
    double hoodtotals(Accounts account);


    List<Flights> findByAccountsOrderByDateofflightAsc(Accounts account);

//    @Query(value="SELECT * FROM devilraylz.flight_time WHERE flights_id=?1 AND dateofflight " +
//            "BETWEEN DATE_SUB((SELECT dateofflight FROM devilraylz.flight_time ORDER BY dateofflight DESC LIMIT 1), INTERVAL 60 DAY) " +
//            "AND (SELECT dateofflight FROM devilraylz.flight_time ORDER BY dateofflight DESC LIMIT 1);", nativeQuery = true)
//    public List<Flights> nvgvalidhours(long id);

    //public List<Flights> findFlightsByIdAndDateofflightLessThanEqualAndDateofflightGreaterThanEqual(long id, LocalDate toDate, LocalDate fromDate);


    @Query(value="SELECT * FROM devilraylz.flight_time WHERE flights_id=?1 AND dateofflight " +
            "BETWEEN DATE_SUB((SELECT CURDATE()), INTERVAL 60 DAY) " +
            "AND CURDATE();", nativeQuery = true)
    public List<Flights> nvgvalidhours(long id);


    @Query(value="Select * FROM devilraylz.flight_time f where f.flights_id=?1 and f.dateofflight between ?2 and ?3 ;",nativeQuery = true)
    public List<Flights> minimums(long id, LocalDate start, LocalDate end);



    }

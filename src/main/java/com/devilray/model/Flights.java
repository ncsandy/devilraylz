package com.devilray.model;

//import org.joda.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name="flightTime")
public class Flights {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    @NumberFormat
    private double day;
    @Column(nullable = false)
    @NumberFormat
    private double night;
    @Column(nullable = false)
    @NumberFormat
    private double nvg;
    @Column(nullable = false)
    @NumberFormat
    private double hood;
    @Column(nullable = false)
    @NumberFormat
    private double weather;
    @Column(nullable = false)
    @NumberFormat
    private double sim;
    // @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate dateofflight;


    @ManyToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="flights_id")
    private Accounts accounts;


    public Flights() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDay() {
        return day;
    }

    public void setDay(double day) {
        this.day = day;
    }

    public double getNight() {
        return night;
    }

    public void setNight(double night) {
        this.night = night;
    }

    public double getNvg() {
        return nvg;
    }

    public void setNvg(double nvg) {
        this.nvg = nvg;
    }

    public double getHood() {
        return hood;
    }

    public void setHood(double hood) {
        this.hood = hood;
    }

    public double getWeather() {
        return weather;
    }

    public void setWeather(double weather) {
        this.weather = weather;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public LocalDate getDateofflight() {
        return dateofflight;
    }

    public double getSim() {
        return sim;
    }

    public void setSim(double sim) {
        this.sim = sim;
    }

    public void setDateofflight(LocalDate dateofflight) {
        this.dateofflight = dateofflight;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }


    @Override
    public String toString() {
        return "Flights{" +
                "id=" + id +
                ", day=" + day +
                ", night=" + night +
                ", nvg=" + nvg +
                ", hood=" + hood +
                ", weather=" + weather +
                ", dateofflight=" + dateofflight +
                ", accounts=" + accounts +
                '}';
    }


}

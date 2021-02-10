package com.devilray.webutils;

import com.devilray.model.Flights;
import com.devilray.repository.AccountsRepository;
import com.devilray.repository.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.MONTHS;

@Component
public class MinimumsUtil {



    public static LocalDate currentPeriodStart;
    public static LocalDate currentPeriodEnd;

    private List<Flights> weekly;
    private List<Flights> monthly;
    private List<Flights> yearly;

    private int weeklyDay;
    private int weeklyNVG;
    private int weeklyNight;
    private int weeklyWeather;
    private int weeklyHood;

    private int monthlyDay;
    private int monthlyNVG;
    private int monthlyNight;
    private int monthlyWeather;
    private int monthlyHood;

    private int yearlyDay;
    private int yearlyNVG;
    private int yearlyNight;
    private int yearlyWeather;
    private int yearlyHood;



    public static void minimums (LocalDate period1start) {



        System.out.println(period1start);
        int thisyear = LocalDate.now().getYear();

        LocalDate today = LocalDate.now();

        int birthday =  period1start.getMonthValue();
        int monthsBetween = today.getMonthValue() - birthday;

        period1start = period1start.withYear(thisyear);
        period1start = period1start.plusMonths(1);


        period1start = period1start.withDayOfMonth(1);

        if(period1start.getYear() > thisyear){
            period1start = period1start.withYear(thisyear);
        }


        System.out.println(monthsBetween);



        LocalDate period1end = period1start.plusMonths(5);


        period1end = period1end.withDayOfMonth(period1end.lengthOfMonth());
        LocalDate period2start = period1end.plusDays(1);
        LocalDate period2end = period2start.plusMonths(5);
        period2end = period2end.withDayOfMonth(period2end.lengthOfMonth());

        int monthdiff = period1start.getMonthValue()-today.getMonthValue();
        int yeardiff = period1start.getYear()-today.getYear();


        System.out.println(monthdiff + " " + yeardiff );

        if(period1start.isAfter(today)  || period2start.isAfter(today)){
            if(monthsBetween == 0){
                currentPeriodStart = period2start.minusYears(1);
                currentPeriodEnd = period2end.minusYears(1);
            }
            else if(monthsBetween < 0){
                if(monthdiff > 6){
                    currentPeriodStart = period1start.minusYears(1);
                    currentPeriodEnd = period1end.minusYears(1);
                }
                else {
                    if(monthdiff < 0){
                        currentPeriodStart = period1start;
                        currentPeriodEnd = period1end;
                    }
                    else{
                        currentPeriodStart = period2start.minusYears(1);
                        currentPeriodEnd = period2end.minusYears(1);
                    }
                }
            }
            else{

                currentPeriodStart = period1start;
                currentPeriodEnd = period1end;
            }

        }





        System.out.println("This year: your period1 is " + period1start + " to " + period1end);
        System.out.println("This year: your period2 is " + period2start + " to " + period2end);

        System.out.println("Your current period is " + currentPeriodStart + " " + currentPeriodEnd);


    }

    public void weeklyCalc(List<Flights> weeklylist){
       weeklyDay = 0;
       weeklyNVG = 0;
       weeklyNight = 0;
       weeklyWeather = 0;
       weeklyHood = 0;


        for(Flights f: weeklylist){
            if(f.getDay() > 0.0){
                weeklyDay++;
            }
            if(f.getNvg() > 0.0){
                weeklyNVG++;
            }
            if(f.getNight() > 0.0){
                weeklyNight++;
            }
            if(f.getWeather() > 0.0){
                weeklyWeather++;
            }
            if(f.getHood() > 0.0){
                weeklyHood++;
            }


        }

    }

    public void monthlyCalc(List<Flights> monthlylist){
        monthlyDay = 0;
        monthlyNVG = 0;
        monthlyNight = 0;
        monthlyWeather = 0;
        monthlyHood = 0;

        for(Flights f: monthlylist){

            if(f.getDay() > 0.0){
               monthlyDay++;
            }
            if(f.getNvg() > 0.0){
                monthlyNVG++;
            }
            if(f.getNight() > 0.0){
                monthlyNight++;
            }
            if(f.getWeather() > 0.0){
                monthlyWeather++;
            }
            if(f.getHood() > 0.0){
                monthlyHood++;
            }


        }

    }
    public void yearlyCalc(List<Flights> yearlylist){
        yearlyDay = 0;
        yearlyNVG = 0;
        yearlyNight = 0;
        yearlyWeather = 0;
        yearlyHood = 0;


        for(Flights f: yearlylist){
            if(f.getDay() > 0.0){
                yearlyDay++;
            }
            if(f.getNvg() > 0.0){
                yearlyNVG++;
            }
            if(f.getNight() > 0.0){
               yearlyNight++;
            }
            if(f.getWeather() > 0.0){
                yearlyWeather++;
            }
            if(f.getHood() > 0.0){
               yearlyHood++;
            }


        }

    }


    public LocalDate getCurrentPeriodStart() {
        return currentPeriodStart;
    }

    public void setCurrentPeriodStart(LocalDate currentPeriodStart) {
        this.currentPeriodStart = currentPeriodStart;
    }

    public LocalDate getCurrentPeriodEnd() {
        return currentPeriodEnd;
    }

    public void setCurrentPeriodEnd(LocalDate currentPeriodEnd) {
        this.currentPeriodEnd = currentPeriodEnd;
    }

    public List<Flights> getWeekly() {
        return weekly;
    }

    public void setWeekly(List<Flights> weekly) {
        this.weekly = weekly;
    }

    public List<Flights> getMonthly() {
        return monthly;
    }

    public void setMonthly(List<Flights> monthly) {
        this.monthly = monthly;
    }

    public List<Flights> getYearly() {
        return yearly;
    }

    public void setYearly(List<Flights> yearly) {
        this.yearly = yearly;
    }

    public int getWeeklyDay() {
        return weeklyDay;
    }

    public void setWeeklyDay(int weeklyDay) {
        this.weeklyDay = weeklyDay;
    }

    public int getWeeklyNVG() {
        return weeklyNVG;
    }

    public void setWeeklyNVG(int weeklyNVG) {
        this.weeklyNVG = weeklyNVG;
    }

    public int getWeeklyNight() {
        return weeklyNight;
    }

    public void setWeeklyNight(int weeklyNight) {
        this.weeklyNight = weeklyNight;
    }

    public int getWeeklyWeather() {
        return weeklyWeather;
    }

    public void setWeeklyWeather(int weeklyWeather) {
        this.weeklyWeather = weeklyWeather;
    }

    public int getWeeklyHood() {
        return weeklyHood;
    }

    public void setWeeklyHood(int weeklyHood) {
        this.weeklyHood = weeklyHood;
    }

    public int getMonthlyDay() {
        return monthlyDay;
    }

    public void setMonthlyDay(int monthlyDay) {
        this.monthlyDay = monthlyDay;
    }

    public int getMonthlyNVG() {
        return monthlyNVG;
    }

    public void setMonthlyNVG(int monthlyNVG) {
        this.monthlyNVG = monthlyNVG;
    }

    public int getMonthlyNight() {
        return monthlyNight;
    }

    public void setMonthlyNight(int monthlyNight) {
        this.monthlyNight = monthlyNight;
    }

    public int getMonthlyWeather() {
        return monthlyWeather;
    }

    public void setMonthlyWeather(int monthlyWeather) {
        this.monthlyWeather = monthlyWeather;
    }

    public int getMonthlyHood() {
        return monthlyHood;
    }

    public void setMonthlyHood(int monthlyHood) {
        this.monthlyHood = monthlyHood;
    }

    public int getYearlyDay() {
        return yearlyDay;
    }

    public void setYearlyDay(int yearlyDay) {
        this.yearlyDay = yearlyDay;
    }

    public int getYearlyNVG() {
        return yearlyNVG;
    }

    public void setYearlyNVG(int yearlyNVG) {
        this.yearlyNVG = yearlyNVG;
    }

    public int getYearlyNight() {
        return yearlyNight;
    }

    public void setYearlyNight(int yearlyNight) {
        this.yearlyNight = yearlyNight;
    }

    public int getYearlyWeather() {
        return yearlyWeather;
    }

    public void setYearlyWeather(int yearlyWeather) {
        this.yearlyWeather = yearlyWeather;
    }

    public int getYearlyHood() {
        return yearlyHood;
    }

    public void setYearlyHood(int yearlyHood) {
        this.yearlyHood = yearlyHood;
    }
}

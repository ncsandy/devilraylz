package com.devilray.webutils;

import com.devilray.model.Accounts;
import com.devilray.model.Flights;
import com.devilray.repository.AccountsRepository;
import com.devilray.repository.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class NotificationService {
    @Autowired
    private FlightsRepository flightsRepository;
    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private EmailManager emailManager;


    LocalDate today = LocalDate.now();

    @Scheduled(cron="0 0 0 * * ?")
    @Transactional
    public void findFlights() {
        List<Accounts> list = accountsRepository.allpilots();
        List<Flights> accflight;
        long aDays = 0;
        long nDays = 0;
        long daysLeft = 0;

        for (Accounts a : list) {
            accflight = flightsRepository.findByAccountsOrderByDateofflightAsc(a);
            if (accflight.size() == 0) {
                System.out.println("No flights logged..");
                continue;
            }

            //Finding our last flight
            Flights lastFlight = accflight.get(accflight.size() - 1);


            //getting the aircraft uncurrent days...
            aDays = ChronoUnit.DAYS.between(lastFlight.getDateofflight(), today);


                //check its more than 60 days
                if (aDays > 60) {
                    System.out.println("You are uncurrent! It has been " + aDays + " days since your last flight");
                    //email user if not notified already
                    try {
                        if (!a.getNotified()) {
                            emailManager.sendMail(a.getEmail(), "You are uncurrent! Your last flight was " + aDays + " days ago. On " + lastFlight.getDateofflight(), "Currency reminder");
                            a.setNotified(true);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                        System.out.println("Email failed to send!");
                    }
                    continue;
                }
               //This checks for a valid flight, what if the user logged an invalid flight? Why would we notify them?
                if (aDays >= 46) {
                    if (lastFlight.getDay() == 0.0 && lastFlight.getNvg() == 0.0 && lastFlight.getNight() == 0.0 && lastFlight.getWeather() == 0.0 && lastFlight.getHood() == 0.0) {
                        System.out.println("You have a flight logged but not valid");
                        continue;
                    }

                    System.out.println(a.getfName() + " " + a.getlName() + " you're coming up on uncurrency");

                    //If we find a valid flight, we will notify the user letting them know they have 2 weeks

                    try {
                        if (!a.getNotified()) {
                            daysLeft = 60 - aDays;
                            emailManager.sendMail(a.getEmail(), "You have two weeks or less until uncurrency! Last flight was: " + lastFlight.getDateofflight() + ". You have " + daysLeft + " days left.", "Currency reminder");
                            a.setNotified(true);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                        System.out.println("Email failed to send!");
                    }

                    //user is more than 2 weeks away from being uncurrent, no need to notify them.

                } else {
                    System.out.println("You're good to go! Last flight was " + lastFlight.getDateofflight());

                }


            }
        }


    }



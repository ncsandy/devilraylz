package com.devilray.controller;

import com.devilray.model.Accounts;
import com.devilray.model.Flights;
import com.devilray.model.Role;
import com.devilray.repository.AccountsRepository;
import com.devilray.repository.FlightsRepository;
import com.devilray.repository.RoleRepository;
import com.devilray.webutils.DataValidation;
import com.devilray.webutils.EmailManager;
import com.devilray.webutils.MinimumsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

import java.text.DecimalFormat;
import java.util.*;

@Controller
@SessionAttributes({"loggedInuser"})
public class AppController {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private FlightsRepository flightsRepository;

    @Autowired
    private DataValidation dataValidation;

    @Autowired
    private MinimumsUtil minimumsUtil;

    @Autowired
    private EmailManager emailManager;

    @GetMapping({"home", "/", "index"})
    public String home(Model model) {
        model.addAttribute("msg", "Please enter credentials");
        return "login";
    }

    @PostMapping("login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        Optional<Accounts> acc = accountsRepository.login(email.trim(), password.trim());
        if (acc.isPresent()) {
            model.addAttribute("loggedInuser", email);
            return "redirect:profile";
        }
        model.addAttribute("error", "Invalid credentials!");
        return "login";
    }

    @GetMapping("profile")
    public String profile(Model model, @SessionAttribute(required = false) String loggedInuser) {

        if (userinfo(model, loggedInuser)) return "login";

        Accounts a = accountsRepository.findByEmail(loggedInuser).get();

        List<Flights> flightsList = flightsRepository.findByAccountsOrderByDateofflightAsc(a);
        model.addAttribute("flights", flightsList);

        LocalDate today = LocalDate.now();
        LocalDate week = today.minusWeeks(1);
        LocalDate month = today.minusMonths(1);
        LocalDate year = today.minusYears(1);

        List<Flights> weekly = flightsRepository.minimums(a.getId(), week, today);
        List<Flights> monthly = flightsRepository.minimums(a.getId(),month,today);
        List<Flights> yearly = flightsRepository.minimums(a.getId(),year,today);




        minimumsUtil.weeklyCalc(weekly);
        minimumsUtil.monthlyCalc(monthly);
        minimumsUtil.yearlyCalc(yearly);




        //adding weekly stats for charts
        model.addAttribute("weeklyDay",minimumsUtil.getWeeklyDay());
        model.addAttribute("weeklyNVG",minimumsUtil.getWeeklyNVG());
        model.addAttribute("weeklyNight",minimumsUtil.getWeeklyNight());
        model.addAttribute("weeklyWeather",minimumsUtil.getWeeklyWeather());
        model.addAttribute("weeklyHood",minimumsUtil.getWeeklyHood());
        //adding monthly stats for chart
        model.addAttribute("monthlyDay",minimumsUtil.getMonthlyDay());
        model.addAttribute("monthlyNVG",minimumsUtil.getMonthlyNVG());
        model.addAttribute("monthlyNight",minimumsUtil.getMonthlyNight());
        model.addAttribute("monthlyWeather",minimumsUtil.getMonthlyWeather());
        model.addAttribute("monthlyHood",minimumsUtil.getMonthlyHood());
        //adding yearly stats for chart
        model.addAttribute("yearlyDay",minimumsUtil.getYearlyDay());
        model.addAttribute("yearlyNVG",minimumsUtil.getYearlyNVG());
        model.addAttribute("yearlyNight",minimumsUtil.getYearlyNight());
        model.addAttribute("yearlyWeather",minimumsUtil.getYearlyWeather());
        model.addAttribute("yearlyHood",minimumsUtil.getYearlyHood());

        List<Flights> validFlights = flightsRepository.nvgvalidhours(a.getId());

        //intializing total
        double total = 0;

        //looping through to get hours in the past 60 days
        for(Flights f : validFlights){
            total += f.getNvg();
        }

        List<Flights> flightList = flightsRepository.findByAccountsOrderByDateofflightAsc(a);

        //getting the last flight flown
        Flights lastFlight = null;
        LocalDate uncurrentflight = null;


        //looping through looking for valid flight

        for(int i = flightList.size()-1; i>=0; i--){
            if(flightList.get(i).getNvg() > 0.0 || flightList.get(i).getDay() > 0.0 ||
                    flightList.get(i).getWeather() > 0.0 || flightList.get(i).getHood() > 0.0 ||
                    flightList.get(i).getNight() > 0.0) {
                lastFlight = flightList.get(i);
                break;
            }
        }

        if(lastFlight == null){
            model.addAttribute("current",false);
        }else {
            uncurrentflight = lastFlight.getDateofflight().plusDays(60);
        }

        long days = 0;

        //getting current date and uncurrent date value to put in progress bar..

        if (uncurrentflight != null) {
            LocalDate currentDate = LocalDate.now();
            //variable for progress bar
            days = ChronoUnit.DAYS.between(currentDate, uncurrentflight);
        }

        if(total >= 1.0 || days >= 1){
            model.addAttribute("current",true);
        }


        return "profile";

    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("accounts", new Accounts());

        return "signup";
    }

    @GetMapping("logbook")
    public String logbook(Model model, @SessionAttribute(required = false) String loggedInuser) {


        if (userinfo(model, loggedInuser)) return "login";

        Accounts a = accountsRepository.findByEmail(loggedInuser).get();

       List<Flights> flightsList = flightsRepository.findByAccountsOrderByDateofflightAsc(a);
       model.addAttribute("addflight", new Flights());
        model.addAttribute("flights", flightsList);

        DecimalFormat df = new DecimalFormat("#.#");


        if(flightsList.size() > 0) {

            double totalhours = flightsRepository.daytotals(a)+ flightsRepository.nvgtotals(a) + flightsRepository.nighttotals(a)+
                    flightsRepository.weathertotals(a) + flightsRepository.hoodtotals(a) + flightsRepository.simtotals(a);
            String thours = df.format(totalhours);

            model.addAttribute("daytotal", df.format(flightsRepository.daytotals(a)));
            model.addAttribute("nvgtotal", df.format(flightsRepository.nvgtotals(a)));
            model.addAttribute("nighttotal", df.format(flightsRepository.nighttotals(a)));
            model.addAttribute("weathertotal", df.format(flightsRepository.weathertotals(a)));
            model.addAttribute("hoodtotal", df.format(flightsRepository.hoodtotals(a)));
            model.addAttribute("simtotals",df.format(flightsRepository.simtotals(a)));
            model.addAttribute("totalhours", thours);
        }

        return "logbook";

    }

    private boolean userinfo(Model model, @SessionAttribute(required = false) String loggedInuser) {
        try {
            if(loggedInuser==null || loggedInuser.isEmpty()) {
                model.addAttribute("error", "Please login/Session Expired ");
                return true;
            }

            Accounts user= accountsRepository.findByEmail(loggedInuser).get();

            //user.getFlights().sort(Comparator.comparing(Flights::getDateofflight));

            model.addAttribute("user_account", user);


        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }


    @PostMapping("register")
    String register(@ModelAttribute Accounts account, BindingResult result, Model model) {
        try {
            dataValidation.validate(account, result);
            if (result.hasErrors()) {
                return "signup";
            }

            // save users and put the in session/login
            account.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByRole("USER"))));
            accountsRepository.saveAndFlush(account);
            model.addAttribute("loggedInuser", account.getEmail());
            model.addAttribute("role", "USER");
        } catch (Exception e) {

            e.printStackTrace();
        }


        return "redirect:profile";

    }

    @PostMapping("addflight")
    String addflight(@ModelAttribute Flights flights, @SessionAttribute(required = false) String loggedInuser) {


        flightsRepository.saveAndFlush(flights);


        return "redirect:logbook";


    }

    @GetMapping("currency")
    public String currency(Model model, @SessionAttribute(required = false) String loggedInuser) {

        if (userinfo(model, loggedInuser)) return "login";

        //specific user
        Accounts a = accountsRepository.findByEmail(loggedInuser).get();

        //getting flights from current user
        List<Flights> flightList = flightsRepository.findByAccountsOrderByDateofflightAsc(a);

        //getting the last flight flown
        Flights lastFlight = null;
        LocalDate uncurrentflight = null;


        //looping through looking for valid flight

        for(int i = flightList.size()-1; i>=0; i--){
            if(flightList.get(i).getNvg() > 0.0 || flightList.get(i).getDay() > 0.0 ||
                    flightList.get(i).getWeather() > 0.0 || flightList.get(i).getHood() > 0.0 ||
            flightList.get(i).getNight() > 0.0) {
                lastFlight = flightList.get(i);
                break;
            }
        }

        if(lastFlight == null){
            model.addAttribute("noflight","We don't have an flight on file for you");
        }else {
            uncurrentflight = lastFlight.getDateofflight().plusDays(60);
            model.addAttribute("uncurrentflight", uncurrentflight);
            model.addAttribute("lastflightdate",lastFlight.getDateofflight());

        }

        long days = 0;

        //getting current date and uncurrent date value to put in progress bar..

        if (uncurrentflight != null) {
            LocalDate currentDate = LocalDate.now();
            //variable for progress bar
            days = ChronoUnit.DAYS.between(currentDate, uncurrentflight);
        }




        //create variable for last nvg flight
        Flights lastNvgFlight = null;

        //Go through loop to find last VALID nvg flight
        for(int i = flightList.size()-1; i>=0; i--){
            if(flightList.get(i).getNvg() > 0.0){
                lastNvgFlight = flightList.get(i);
                break;
            }
        }

        //if null let the pilot know we don't have a flight on file
        if(lastNvgFlight == null){
            model.addAttribute("nonvg","We don't have an NVG flight on file for you");
        }else {
            LocalDate uncurrentNvg = lastNvgFlight.getDateofflight().plusDays(60);
            model.addAttribute("lastnvgdate", lastNvgFlight.getDateofflight());
            model.addAttribute("nvguncurrent",uncurrentNvg);
        }




        //add attributes to the session so we can display it
        model.addAttribute("lastflight", lastFlight);
        //model.addAttribute("uncurrentdate",uncurrentdate);
        model.addAttribute("daysleft", days);

        //List<Flights> validFlights = flightsRepository.findFlightsByIdAndDateofflightLessThanEqualAndDateofflightGreaterThanEqual(a.getId(),lastNvGFlightDate,lastNvgFlightDate60);

        List<Flights> validFlights = flightsRepository.nvgvalidhours(a.getId());

        //intializing total
        double total = 0;

        //looping through to get hours in the past 60 days
        for(Flights f : validFlights){
            total += f.getNvg();
        }


        model.addAttribute("total",total);

        if(total >= 1.0){
            model.addAttribute("current","You are current");
        }else{
            model.addAttribute("uncurrent","You are goggle uncurrent!");
        }


        return "currency";

    }
    @GetMapping("deleteflight")
    public String deleteflight(Long id){
        //deleting flight
        flightsRepository.deleteById(id);

        return "redirect:logbook";

    }
    @GetMapping("minimums")
    public String minimums(Model model, @SessionAttribute(required = false) String loggedInuser){

        if (userinfo(model, loggedInuser)) return "login";

        Accounts a = accountsRepository.findByEmail(loggedInuser).get();

        MinimumsUtil.minimums(a.getpBirthday());

        Month birthmonth =  a.getpBirthday().getMonth();

        List <Flights> semiannualperiod = flightsRepository.minimums(a.getId(),minimumsUtil.getCurrentPeriodStart(),minimumsUtil.getCurrentPeriodEnd());

        double semiannualhours = 0;
        int totalflights = 0;
        DecimalFormat df = new DecimalFormat("#.#");


        for(Flights flight : semiannualperiod){
              semiannualhours += flight.getDay();
              semiannualhours += flight.getNvg();
              semiannualhours += flight.getNight();
              semiannualhours += flight.getHood();
              semiannualhours += flight.getWeather();

              if(flight.getDay() > 0.0 || flight.getNvg() > 0.0 || flight.getNight() > 0.0 || flight.getHood() > 0.0 || flight.getWeather() > 0.0){
                  totalflights++;
              }
        }

        String thours = df.format(semiannualhours);


        model.addAttribute("birthmonth",birthmonth);
        model.addAttribute("currentstart",minimumsUtil.getCurrentPeriodStart());
        model.addAttribute("currentend",minimumsUtil.getCurrentPeriodEnd());
        model.addAttribute("semiannualhours",thours);
        model.addAttribute("totalflights", totalflights);




        System.out.println(minimumsUtil.getCurrentPeriodStart() + " " + minimumsUtil.getCurrentPeriodEnd());


        return "minimums";
    }

    @ModelAttribute("rank")
    public List<String> ranks() {
        List<String> rank = new ArrayList<String>();
        rank.add("PV1");
        rank.add("PV2");
        rank.add("PFC");
        rank.add("SPC");
        rank.add("SGT");
        rank.add("SSG");
        rank.add("SFC");
        rank.add("MSG");
        rank.add("1SG");
        rank.add("SGM");
        rank.add("CSM");
        rank.add("2LT");
        rank.add("1LT");
        rank.add("CPT");
        rank.add("MAJ");
        rank.add("LTC");
        rank.add("COL");
        rank.add("WO1");
        rank.add("CW2");
        rank.add("CW3");
        rank.add("CW4");
        rank.add("CW5");
        return rank;
    }

    @GetMapping("logout")
    public String logout(Model model, HttpSession session) {

        session.invalidate();
        model.addAttribute("loggedInuser", "");
        model.addAttribute("msg", "You have been logged out.");


        return "login";
    }


    @PostMapping("passwordrecovery")
    public String getpassword(@RequestParam String recoveryemail, Model model){
        Optional<Accounts> acc = accountsRepository.findByEmail(recoveryemail);


        if (acc.isPresent()) {
            System.out.println("account found");
            String uEmail = accountsRepository.findByEmail(recoveryemail).get().getEmail();
            String uPassword = accountsRepository.findByEmail(recoveryemail).get().getPassword();
            String message = "You requested a password request. Password is: " + uPassword;

            try{
                emailManager.sendMail(uEmail,message,"Devilray LZ password recovery");
            }catch(Exception e){
               e.printStackTrace();
                System.out.println("email failed.....");
            }



            model.addAttribute("success", true);

        }
        else{
            model.addAttribute("success",false);
            System.out.println("not found");
        }

        return "login";
    }

    @GetMapping("publications")
    public String publications(Model model,@SessionAttribute(required = false) String loggedInuser) {
        if (userinfo(model,loggedInuser)) return "login";



        return "publications";
    }

}

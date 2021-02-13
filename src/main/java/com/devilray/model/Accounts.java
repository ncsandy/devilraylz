package com.devilray.model;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="pilot")
public class Accounts {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String pilotRank;
    private String fName;
    private String lName;
    private String password;
    private LocalDate pBirthday;
    @Column(nullable = false)
    private boolean notified;
    @Transient
    private String password2;
    private String email;
    private String role;



    @OneToMany(mappedBy="accounts", cascade=CascadeType.ALL)
    private List<Flights> flights;

    @ManyToMany(cascade = CascadeType.DETACH )
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles =new HashSet <Role>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPilotRank() {
        return pilotRank;
    }

    public void setPilotRank(String pilotRank) {
        this.pilotRank = pilotRank;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Flights> getFlights() {
        return flights;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setFlights(List<Flights> flights) {
        this.flights = flights;
    }

    public LocalDate getpBirthday() {
        return pBirthday;
    }

    public void setpBirthday(LocalDate pBirthday) {
        this.pBirthday = pBirthday;
    }

    public boolean getNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }
}

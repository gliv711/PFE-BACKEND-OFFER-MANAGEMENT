package com.ennajim.demandsmanagement.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Table @NoArgsConstructor @AllArgsConstructor @Data
public class Demand {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String email ;
    private String title ;
    private String jobTitle ;
    private String contractType ;
    private String place ;
    private String Salary ;
    private String details ;
    private String experience ;
    private String regime ;


}

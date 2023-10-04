package Employeepay.ZohoTechCorp;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employeedetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int empId;
    private String empName;
    private  String empUsername;
    private String empPassword;
    private  String empDesignation;
    private int empExperience;
    @Column(name = "perannum")
    private double empSalary;

    //fetch(LAZY,EAGER)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Nullable
    @JoinTable(name="Allrecords" , joinColumns = @JoinColumn(name="empId"), inverseJoinColumns = @JoinColumn(name="payslipId"))
    private Collection<PayslipDetailsEntity> mypayslip=new ArrayList<PayslipDetailsEntity>();
}

package Employeepay.ZohoTechCorp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employeedetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int empId;
    private String empName;
    private  String empUsername;
    private String empPassword;
    private  String empDesignation;
    private int empExperience;
    @Column(name = "perannum")
    private double empSalary;

}

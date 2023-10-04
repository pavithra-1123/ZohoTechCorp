package Employeepay.ZohoTechCorp;

import jakarta.persistence.*;
import lombok.Data;

@Entity
public class PayslipDetailsEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int payslipId;
    private Data payslipDate;
    private int payslipBasicsalary;
    private int payslipTds;
    private int payslipAllowance;
    private int payslipTakehome;

    @ManyToOne
    @JoinColumn(name="empId")
    private Employeedetails employeeDetails;


}

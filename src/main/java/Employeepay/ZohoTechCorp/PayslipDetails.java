package Employeepay.ZohoTechCorp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payslipdetails")
public class PayslipDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int payslipId;
    private Date payslipDate;
    private int payslipBasicsalary;
    private int payslipTds;
    private int payslipAllowance;
    private int payslipTakehome;

    @ManyToOne
    @Nullable
    @JoinColumn(name="empId")
    @JsonBackReference
    private Employeedetails employeeDetails;


}

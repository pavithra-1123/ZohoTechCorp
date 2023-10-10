package Employeepay.ZohoTechCorp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayslipDetailService
{
    @Autowired
    PayslipDetailsRepository prepo;

    public PayslipDetails newpayslip(PayslipDetails payslip)
    {
        return prepo.save(payslip);
    }

    public List<PayslipDetails> getbyempdetails(Employeedetails emp)
    {
        return prepo.findAllByEmployeeDetails(emp);
    }
}

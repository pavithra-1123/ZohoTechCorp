package Employeepay.ZohoTechCorp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailsService
{
    @Autowired //object creation
    EmployeedetailsRepository repo;

    public Employeedetails create (Employeedetails emp)
    {
        return repo.save(emp);
    }
}

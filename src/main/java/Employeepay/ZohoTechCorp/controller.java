package Employeepay.ZohoTechCorp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller
{
    @Autowired
    EmployeeDetailsService serv;
    //url mapping - post(create),put(update),get(list,read),delete
    //http://localhost:8081/create
    @PostMapping("/create")
    public String Makecreate(@RequestBody Employeedetails emp)
    {
        return serv.create(emp).getEmpName()+ " has been added in your database ";
    }
}

package Employeepay.ZohoTechCorp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/")
    public List<Employeedetails> listall()
    {

         return serv.viewall();
    }

    @PutMapping("/update") //in postman use "empId"=1 for update particular value
    public String updating(@RequestBody Employeedetails emp)
    {
        Employeedetails temp=serv.create(emp);
        return temp.getEmpId()+" has been updated in your database";
    }

    @DeleteMapping("/deleteone/{id}")
    public String remove(@PathVariable("id") int id)
    {
        return serv.remove(id) + " ";
    }

    @GetMapping("/readone/{empid}")
    public Optional<Employeedetails> readingone(@PathVariable("empid") int empid)
    {
        return serv.readone(empid);
    }
}

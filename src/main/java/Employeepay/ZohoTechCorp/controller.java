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

    @Autowired
    PayslipDetailService pserv; // payslip

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

    @GetMapping("/readbyname/{name}")
    public Employeedetails readingbyname(@PathVariable("name")String name)
    {
        return serv.readbyname(name);
    }

    @GetMapping("/greatestsalaries/{useramount}")
    public List<Employeedetails> gettingtopsalarypeople(@PathVariable("useramount")double useramount)
    {
        return serv.readgreatestsalarypeople(useramount);
    }

    @PutMapping("/updateyoursalary/{empname}")
    public  Employeedetails hikesalary(@PathVariable("empname")String empname)
    {
        return  serv.incrementbysalary(empname);
    }

    @PostMapping("/createpayslip")
    public PayslipDetails newpayslip(@RequestBody PayslipDetails payslip)
    {
        Employeedetails temp=serv.gettingexactid(payslip.getEmployeeDetails().getEmpId());


        double monthlysalary=temp.getEmpSalary()/12;

        double basicsalary=monthlysalary+(monthlysalary*(payslip.getPayslipAllowance()/100));

        payslip.setPayslipBasicsalary((int)basicsalary);

        basicsalary=basicsalary-(basicsalary*payslip.getPayslipTds()/100);

        payslip.setPayslipTakehome((int)basicsalary);

        temp.getMypayslip().add(payslip);//one payslip get in my payslip

        pserv.newpayslip(payslip);//creating an new payslip in payslip table

        serv.create(temp);//updation-added one payslip in your empdetails

        return payslip;
    }
    @GetMapping("getallpayslip/{empid}")
    public List<PayslipDetails> callbyallpayslip(@PathVariable("empid") int empid)
    {
        Employeedetails emp=serv.gettingexactid(empid);
        return pserv.getbyempdetails(emp);
    }

}

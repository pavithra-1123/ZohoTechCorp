package Employeepay.ZohoTechCorp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.beans.Encoder;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ZohoTech")
@CrossOrigin(origins = "http://localhost:3000")
public class controller
{
    @Autowired
    EmployeeDetailsService serv;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    PayslipDetailService pserv; // payslip

    //url mapping - post(create),put(update),get(list,read),delete
    //http://localhost:8081/create
    @PostMapping("/create")
    public String Makecreate(@RequestBody Employeedetails emp)
    {
        String temp = encoder.encode(emp.getPassword());
        emp.setEmpPassword(temp);
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

    @DeleteMapping("/deleteone/{user}")
    public String remove(@PathVariable("user") String user)
    {
        Employeedetails emp=purpose(user);
        return serv.remove(emp.getEmpId())+" ";
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

    @PostMapping("/createpayslip/{user}")
    public PayslipDetails newpayslip(@PathVariable ("user") String user ,@RequestBody PayslipDetails payslip)
    {
        //Employeedetails temp=serv.gettingexactid(payslip.getEmployeeDetails().getEmpId());

        Employeedetails temp=purpose(user);
        double monthlysalary=temp.getEmpSalary()/12;

        //double basicsalary=monthlysalary+(monthlysalary*(payslip.getPayslipAllowance()/100));
        double basicsalary=monthlysalary-(monthlysalary*(payslip.getPayslipAllowance()/100));

        payslip.setPayslipBasicsalary((int)basicsalary);

        //basicsalary=basicsalary-(basicsalary*payslip.getPayslipTds()/100);
        monthlysalary=basicsalary-(monthlysalary*payslip.getPayslipTds()/100);

        //payslip.setPayslipTakehome((int)basicsalary);
        payslip.setPayslipTakehome((int)monthlysalary);

        temp.getMypayslip().add(payslip);//one payslip get in my payslip

        //pserv.newpayslip(payslip);//creating an new payslip in payslip table
        payslip.setEmployeeDetails(temp);
        pserv.newpayslip(payslip);

       // serv.create(temp);//updation-added one payslip in your empdetails

        return payslip;
    }
    @GetMapping("getallpayslip/{empid}")
    public List<PayslipDetails> callbyallpayslip(@PathVariable("empid") int empid)
    {
        Employeedetails emp=serv.gettingexactid(empid);
        return pserv.getbyempdetails(emp);
    }

    @GetMapping("/{user}")//http://localhost:8082/ZealousEmpDetails/ManoHari
    public Employeedetails purpose(@PathVariable("user")String user)
    {
        Employeedetails emp=(Employeedetails) serv.loadUserByUsername(user);
        return emp;
    }
    @GetMapping("/fetch/{user}")
    public List<PayslipDetails> getbyEmployee(@PathVariable("user")String user)
    {
        return  pserv.getbyempdetails(purpose(user));
    }

}

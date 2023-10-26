package Employeepay.ZohoTechCorp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDetailsService implements UserDetailsService//pre defined interface class
{
    @Autowired //object creation
    EmployeedetailsRepository repo;


    public Employeedetails create (Employeedetails emp)
    {
        return repo.save(emp);
    }

    public List<Employeedetails> viewall()
    {
        return (List<Employeedetails>) repo.findAll();
    }

    public String remove(int id)
    {
        Employeedetails temp=repo.findById(id).orElse(new Employeedetails());
        repo.delete(temp);
        return temp.getEmpName()+" has been deleted successfully";
    }

    public Optional<Employeedetails> readone(int id)
    {
        return repo.findById(id);
    }

    public Employeedetails readbyname(String name)
    {
        return  repo.findAllByEmpName(name);
    }

    public List<Employeedetails> readgreatestsalarypeople(double Salary)
    {
        return  repo.findAllByUserGreatestSalary(Salary);
    }

    public Employeedetails incrementbysalary(String username)
    {
        return repo.findAllByHikeSalary(username);
    }

    public Employeedetails gettingexactid(int id )
    {
        return repo.findById(id).orElse(new Employeedetails());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Employeedetails emp = repo.findByEmpUsername(username);
        if(emp == null)
        {
            throw new UsernameNotFoundException(username);
        }
        return emp;
    }
}



package Employeepay.ZohoTechCorp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeedetailsRepository extends JpaRepository<Employeedetails,Integer>
{
    public Employeedetails findAllByEmpName(String Name);
    //if we want to search the value by using employee name, we create userdefined no implement method by using findAllByEmpName
}

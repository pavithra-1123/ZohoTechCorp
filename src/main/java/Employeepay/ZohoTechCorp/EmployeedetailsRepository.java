package Employeepay.ZohoTechCorp;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeedetailsRepository extends JpaRepository<Employeedetails,Integer>
{
    public Employeedetails findAllByEmpName(String Name);
    //if we want to search the value by using employee name, we create userdefined no implement method by using findAllByEmpName

    //    HQL-Hibernate Query Languages
    //    select * from employeedetails where Empsalary>=500000.0;
    @Query("from Employeedetails where empSalary>=:usercheck")
    public List<Employeedetails> findAllByUserGreatestSalary(double usercheck);

    @Transactional
    @Modifying
    @Query("update Employeedetails set empSalary=empSalary+(empSalary*15/100) where empName=:employeename")
    public Employeedetails findAllByHikeSalary(String employeename);
}

package Employeepay.ZohoTechCorp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayslipDetailsRepository extends JpaRepository<Employeedetails,Integer>
{

}

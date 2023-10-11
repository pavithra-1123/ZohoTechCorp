package Employeepay.ZohoTechCorp;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class ZohoTechCorpApplicationTests {

	@MockBean
	EmployeedetailsRepository repo;
	@Autowired
	EmployeeDetailsService serv;

	@Test
	public void basictest()
	{
		Employeedetails emp1 = new Employeedetails(1,"pavithra","pavi123","psdn21","software developer",3,8.3,null);
		Employeedetails emp2 = new Employeedetails(2,"Thara","thara123","wdee1","web developer",4,6.4,null);
		Employeedetails emp3 = new Employeedetails(3,"vasanth","vasa123","trhhr1","software engineer",6,4.5,null);
		Mockito.when(repo.findAll()).thenReturn(Stream.of(emp1,emp2,emp3).collect(Collectors.toList()));
		//Assert.assertSame(4,serv.viewall().size());
		//assertNotSame(44,serv.viewall().size());
		//assertNull(serv.viewall().get(2));
	}



}

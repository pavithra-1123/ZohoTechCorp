package Employeepay.ZohoTechCorp;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
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
		when(repo.findAll()).thenReturn(Stream.of(emp1,emp2,emp3).collect(Collectors.toList()));
		//Assert.assertSame(4,serv.viewall().size());
		//assertNotSame(44,serv.viewall().size());
		//assertNull(serv.viewall().get(2));
	}

	@Test
	public void TestingDelete()
	{
		Employeedetails emp1 = new Employeedetails(1,"pavithra","pavi123","psdn21","software developer",3,8.3,null);
		repo.delete(emp1);
		verify(repo,times(1)).delete(emp1);
	}

	@Test
	public void TestingCreate()
	{
		Employeedetails emp1 = new Employeedetails(1,"pavithra","pavi123","psdn21","software developer",3,8.3,null);
		Employeedetails emp2 = new Employeedetails(2,"Thara","thara123","wdee1","web developer",4,6.4,null);

		when(repo.save(emp1)).thenReturn(emp1);
		when(repo.save(emp2)).thenReturn(emp2);
		assertTrue(serv.create(emp2).getEmpName().equals("Thara"));

	}

	@Test
	public void TestingRead()
	{
		Optional<Employeedetails> emp1 = Optional.of(new Employeedetails(101,"pavithra","pavi123","psdn21","software developer",3,8.3,null));
		Optional<Employeedetails> emp2 = Optional.of(new Employeedetails(201,"Thara","thara123","wdee1","web developer",4,6.4,null));
		when(repo.findById(101)).thenReturn(emp1);
		when(repo.findById(201)).thenReturn(emp2);
		//assertEquals(emp1,serv.readone(201));
		assertTrue(serv.readone(101).get().getEmpUsername().equals(emp1.get().getEmpUsername()));
	}


}

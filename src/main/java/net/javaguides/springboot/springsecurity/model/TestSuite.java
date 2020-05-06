package net.javaguides.springboot.springsecurity.model;





import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "TestSuite")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TestSuite {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "idTestSuite")
	    private Long idTestSuite;

	    private String TestSuiteName;
	    private String TestSuiteDescription;
	    private String TestSuiteCreator;
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date TestSuiteCreatedDate ;
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date TestSuiteUpdatedDate ;
	   



	   // @ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	    @ManyToOne
	    @JoinColumn(name ="id")
	    private User user ;
	    
	    @OneToMany(mappedBy = "testSuite", cascade = CascadeType.ALL)
	    private List<TestCase> testCase;
	    
	    public TestSuite() {}
	    
	/*	public TestSuite(String testSuiteName, String testSuiteDescription, String testSuiteCreator, User users) {
			super();
			TestSuiteName = testSuiteName;
			TestSuiteDescription = testSuiteDescription;
			TestSuiteCreator = testSuiteCreator;
			this.user = user;
			
		} */
		
		public TestSuite(String testSuiteName, String testSuiteDescription) {
			
			TestSuiteName = testSuiteName;
			TestSuiteDescription = testSuiteDescription;
	
		}


		

		public Long getIdTestSuite() {
			return idTestSuite;
		}

		public void setIdTestSuite(Long idTestSuite) {
			this.idTestSuite = idTestSuite;
		}

		public String getTestSuiteName() {
			return TestSuiteName;
		}

		public void setTestSuiteName(String testSuiteName) {
			TestSuiteName = testSuiteName;
		}

		public String getTestSuiteDescription() {
			return TestSuiteDescription;
		}

		public void setTestSuiteDescription(String testSuiteDescription) {
			TestSuiteDescription = testSuiteDescription;
		}

		public String getTestSuiteCreator() {
			return TestSuiteCreator;
		}

		public void setTestSuiteCreator(String testSuiteCreator) {
			TestSuiteCreator = testSuiteCreator;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
		public Date getTestSuiteCreatedDate() {
			return TestSuiteCreatedDate;
		}

		public void setTestSuiteCreatedDate(Date testSuiteCreatedDate) {
			TestSuiteCreatedDate = testSuiteCreatedDate;
		}
		

		public Date getTestSuiteUpdatedDate() {
			return TestSuiteUpdatedDate;
		}

		public void setTestSuiteUpdatedDate(Date testSuiteUpdatedDate) {
			TestSuiteUpdatedDate = testSuiteUpdatedDate;
		}
}

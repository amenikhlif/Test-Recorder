package net.javaguides.springboot.springsecurity.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;




@Entity
@Table(name = "TestCase")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TestCase{
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "idTestCase")
	    private Long idTestCase;

	    private String TestCaseName;
	    private String TestCaseDescription;
	    
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date TestCaseCreatedDate ;
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date TestCaseUpdatedDate ;
	    private String navigator ;
	    private String url;
	    
	    
	    @ManyToOne
	    @JoinColumn(name ="idTestSuite")
	    private TestSuite testSuite ;
	   
	    @OneToMany(mappedBy = "testCase", cascade = CascadeType.ALL)
	    private List<ScenarioTS> scenarioTS;	    
		
		public TestCase() {}
		
		public TestCase(String testCaseName, String testCaseDescription, String navigator, String url) {
			
			this.TestCaseName = testCaseName;
			this.TestCaseDescription = testCaseDescription;
			this.navigator = navigator;
			this.url = url;
		}
	    
		public Long getIdTestCase() {
			return idTestCase;
		}
		public void setIdTestCase(Long idTestCase) {
			this.idTestCase = idTestCase;
		}
		public String getTestCaseName() {
			return TestCaseName;
		}
		public void setTestCaseName(String testCaseName) {
			TestCaseName = testCaseName;
		}
		public String getTestCaseDescription() {
			return TestCaseDescription;
		}
		public void setTestCaseDescription(String testCaseDescription) {
			TestCaseDescription = testCaseDescription;
		}
		public Date getTestCaseCreatedDate() {
			return TestCaseCreatedDate;
		}
		public void setTestCaseCreatedDate(Date testCaseCreatedDate) {
			TestCaseCreatedDate = testCaseCreatedDate;
		}
		public Date getTestCaseUpdatedDate() {
			return TestCaseUpdatedDate;
		}
		public void setTestCaseUpdatedDate(Date testCaseUpdatedDate) {
			TestCaseUpdatedDate = testCaseUpdatedDate;
		}
		public String getNavigator() {
			return navigator;
		}
		public void setNavigator(String navigator) {
			this.navigator = navigator;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
	

		public TestSuite getTestSuite() {
			return testSuite;
		}

		public void setTestSuite(TestSuite testSuite) {
			this.testSuite = testSuite;
		}

}

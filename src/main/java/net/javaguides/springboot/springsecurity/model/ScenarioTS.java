package net.javaguides.springboot.springsecurity.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "ScenarioTS")
public class ScenarioTS {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idScenario")
    private Long idScenario;

	private String path ;
	private String commande ;
	private String value ;
	private String url ;
	
 

	@ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name ="idTestCase")
    private TestCase testCase ;	

	


	

	public ScenarioTS() {}

	public ScenarioTS(String commande,String path,  String value, String url) {
		this.commande = commande;
		this.path = path;
		this.value = value;
		this.url=url;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	public String getCommande() {
		return commande;
	}

	public void setCommande(String commande) {
		this.commande = commande;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getIdScenario() {
		return idScenario;
	}

	public void setIdScenario(Long idScenario) {
		this.idScenario = idScenario;
	}
	
	
	   public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	
	public TestCase getTestCase() {
		return testCase;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}


}

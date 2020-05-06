package net.javaguides.springboot.springsecurity.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import org.openqa.selenium.support.ui.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.springsecurity.service.TestCaseService;


import net.javaguides.springboot.springsecurity.model.ScenarioTS;
import net.javaguides.springboot.springsecurity.model.TestCase;
import net.javaguides.springboot.springsecurity.service.ScenarioTSService;

@Controller
public class ScenarioTSController {
	 @Autowired  
		private final ScenarioTSService scenarioTSService;
		 private final TestCaseService testCaseService;
		  
		  public ScenarioTSController(ScenarioTSService scenarioTSService,TestCaseService testCaseService) {
		        this.scenarioTSService = scenarioTSService;
		        this.testCaseService = testCaseService;
		        
		    }
		  
			 	public  long testIF;
			    static String data;
				public static WebDriver driver;
				
				public static List<ScenarioTS>Sels= new ArrayList<ScenarioTS>();		  
				static String generateXPATH(WebElement childElement, String current) {
				    String childTag = childElement.getTagName();
				    if(childTag.equals("html")) {
				        return "/html[1]"+current;
				    }
				    WebElement parentElement = childElement.findElement(By.xpath("..")); 
				    List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
				    int count = 0;
				    for(int i=0;i<childrenElements.size(); i++) {
				        WebElement childrenElement = childrenElements.get(i);
				        String childrenElementTag = childrenElement.getTagName();
				        if(childTag.equals(childrenElementTag)) {
				            count++;
				        }
				        if(childElement.equals(childrenElement)) {
				            return generateXPATH(parentElement, "/" + childTag + "[" + count + "]"+current);
				        }
				    }
				    return null;
				}

				public static String getXPath(WebElement element,WebDriver driver)
				{
				    return (String) ((JavascriptExecutor) driver).executeScript(
				            "function absoluteXPath(element) {"+
				                    "var comp, comps = [];"+
				                    "var parent = null;"+
				                    "var xpath = '';"+
				                    "var getPos = function(element) {"+
				                    "var position = 1, curNode;"+
				                    "if (element.nodeType == Node.ATTRIBUTE_NODE) {"+
				                    "return null;"+
				                    "}"+
				                    "for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {"+
				                    "if (curNode.nodeName == element.nodeName) {"+
				                    "++position;"+
				                    "}"+
				                    "}"+
				                    "return position;"+
				                    "};"+

				                    "if (element instanceof Document) {"+
				                    "return '/';"+
				                    "}"+

				                    "for (; element && !(element instanceof Document); element = element.nodeType == Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode) {"+
				                    "comp = comps[comps.length] = {};"+
				                    "switch (element.nodeType) {"+
				                    "case Node.TEXT_NODE:"+
				                    "comp.name = 'text()';"+
				                    "break;"+
				                    "case Node.ATTRIBUTE_NODE:"+
				                    "comp.name = '@' + element.nodeName;"+
				                    "break;"+
				                    "case Node.PROCESSING_INSTRUCTION_NODE:"+
				                    "comp.name = 'processing-instruction()';"+
				                    "break;"+
				                    "case Node.COMMENT_NODE:"+
				                    "comp.name = 'comment()';"+
				                    "break;"+
				                    "case Node.ELEMENT_NODE:"+
				                    "comp.name = element.nodeName;"+
				                    "break;"+
				                    "}"+
				                    "comp.position = getPos(element);"+
				                    "}"+

				                    "for (var i = comps.length - 1; i >= 0; i--) {"+
				                    "comp = comps[i];"+
				                    "xpath += '/' + comp.name;"+
				                    "if (comp.position !== null) {"+
				                    "xpath += '[' + comp.position + ']';"+
				                    "}"+
				                    "}"+

				                    "return xpath;"+

				                    "} return absoluteXPath(arguments[0]);", element);
				}

				 public static String getXPath2(WebElement element,  WebDriver driver)
				    {
				        return (String) ((JavascriptExecutor) driver).executeScript(
				        "getXPath=function(node)" +
				        "{" +
				            "if (node.id !== '')" +
				            "{" +
				                "return '//' + node.tagName + '[@id=\"' + node.id + '\"]'"
				+
				            "}" +
	            
				            "if (node === document.body)" +
				            "{" +
				                "return node.tagName" +
				            "}" +

				            "var nodeCount = 0;" +
				            "var childNodes = node.parentNode.childNodes;" +

				            "for (var i=0; i<childNodes.length; i++)" +
				            "{" +
				                "var currentNode = childNodes[i];" +

				                "if (currentNode === node)" +
				                "{return getXPath(node.parentNode) + '/' + node.tagName+ '[' + (nodeCount+1) + ']'" +
				                "}" +

				                "if (currentNode.nodeType === 1 && " +
				                    "currentNode.tagName === node.tagName)"
				+
				                "{" +
				                    "nodeCount++" +
				                "}" +
				            "}" +
				        "};" +

				        "return getXPath(arguments[0]);", element);
				    }
				 
				 public static void choice(String browser){
						//Check if parameter passed from TestNG is 'firefox'
						if(browser.equals("Firefox")){
						//create firefox instance
							
							System.setProperty("webdriver.gecko.driver", ".\\geckodriver.exe");
							driver = new FirefoxDriver();
						}
						//Check if parameter passed as 'chrome'
						else if(browser.equals("Google Chrome")){
							DesiredCapabilities dc = new DesiredCapabilities();
							dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
							//set path to chromedriver.exe
							System.setProperty("webdriver.chrome.driver", "C:\\Users\\Asus\\Downloads\\Newww\\New\\demopfe124755\\chromedriver.exe");
							//create chrome instance
							driver = new ChromeDriver(dc);
						}
						//Check if parameter passed as 'Edge'
								else if(browser.equals("Internet Explorer")){
									//set path to Edge.exe
									System.setProperty("webdriver.edge.driver",".\\MicrosoftWebDriver.exe");
									//create Edge instance
									driver = new EdgeDriver();
								}
						
								else if(browser.equals("Opera")){
									//set path to Edge.exe
									System.setProperty("webdriver.opera.driver",".\\.exe");
									//create Edge instance
									driver = new OperaDriver();
								}
								else if(browser.equals("Safari")){
									//set path to Edge.exe
									System.setProperty("webdriver.safari.driver",".\\.exe");
									//create Edge instance
									driver = new SafariDriver();
								}
						
						
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				

				 public void recorder(String url,String navigator) throws InterruptedException
				 
				 {	
					 
					
					choice(navigator);
					try {
						
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.get(url);
					driver.manage().window().maximize();
				
					JavascriptExecutor js =(JavascriptExecutor)driver;
					js.executeScript("alert('wait!');");
					driver.switchTo().alert();
					 Thread.sleep(2000);
					 driver.switchTo().alert().accept();
					List<ScenarioTS>S1=new ArrayList<ScenarioTS>();
					List<WebElement> r=new ArrayList<WebElement>();
					
					//((JavascriptExecutor) driver).executeScript("alert("+"Hello! I am an alert box!!"+");");
					S1=affiche(url);
					
                    r=rec(url);
                    js.executeScript("alert('Start!');");
					driver.switchTo().alert();
					 Thread.sleep(2000);
					 driver.switchTo().alert().accept();
					// Get attribute of current active element
					String commande="";
					commande="open";
					//String url=driver.getCurrentUrl();
					ScenarioTS s = new ScenarioTS(commande,url,"","");
						Sels.add(s);
					String anc=" ";
					//String anc1=" a";
					String value="";
					String att="";
				String ancurl=url;
				String ancurl2=url;
				String driverurl="";
				Boolean ch=false;
					while (driver!=null)
					{	
						driverurl=driver.getCurrentUrl();
				
					WebElement ee=driver.switchTo().activeElement();
					String path1=getXPath(ee,driver);
					//System.out.println(path.toLowerCase());
			if((recherche(path1.toLowerCase(),S1).getPath().equals("")==false)&&(Sels.get(Sels.size()-1).getPath().equals(path1.toLowerCase())==false))
			{
				System.out.println("je cherchee"+recherche(path1.toLowerCase(),S1).getPath());
			ScenarioTS so=recherche(path1.toLowerCase(),S1);
			so.setUrl(driver.getCurrentUrl());
			Sels.add(so);
			ch=true;
			System.out.println("j'ai ajouté1");
			anc=path1;
			//ancurl=driver.getCurrentUrl();
			}
			
				
						for(int i =0;i<2;i++)

						
						
					{System.out.println("chaimaa");}	
							
						
for(int i =0;i<3;i++)

{			driverurl=driver.getCurrentUrl();

					}
		
					for(int i=0;i<100;i++)
					{System.out.println("thecurrent url is sans boucle"+driver.getCurrentUrl());}
					//Thread.sleep(3);
				//	WebElement		ee=driver.switchTo().activeElement();
					System.out.println("the active element");
					System.out.println("the ancien url is"+ancurl2);
					String activUrl=driver.getCurrentUrl();
					if(driver.getCurrentUrl().equals(ancurl2)==false)
{ System.out.println("thecurrent url is"+driver.getCurrentUrl());

//if(ee.getAttribute("id").equals(""))
/*{System.out.println("jevais changer l'elemtn");
	//ee=rec(driver.getCurrentUrl()).get(1);
//ancurl=urll;
//System.out.println("the new active element"+ee.getAttribute("id"));
}*/

for(int i=0;i<2;i++)
{System.out.println("----");}
System.out.println("je vais afficher");
//WebDriverWait wait = new WebDriverWait(driver,30);

//wait.
//Thread.sleep(2000);
System.out.println("the driver when affichage"+driver.getCurrentUrl());
js.executeScript("alert('wait!');");
driver.switchTo().alert();
 Thread.sleep(2000);
 driver.switchTo().alert().accept();
S1=affiche(driver.getCurrentUrl());
js.executeScript("alert('start!');");
driver.switchTo().alert();
 Thread.sleep(2000);
 driver.switchTo().alert().accept();
//String urll=driver.getCurrentUrl();

//Thread.sleep(2);

System.out.println("j'ai terminer l'affichage");
			ancurl2=driver.getCurrentUrl();
ee=driver.switchTo().activeElement();
						
							
						
}
				
				//driver.navigate().refresh();
			
					System.out.println("je cherche un autre");
					Boolean test=false;
					String id="";
					String path2="";
					
					while((test==false)&&(ch==false))
					{//System.out.println("je cherche ...");
			ee=driver.switchTo().activeElement();
			if(getXPath(ee,driver).equals("")==false)
			{id=getXPath(ee,driver);
			test=true;}
			}
					
					 path2=getXPath(ee,driver);	
					System.out.println("path2"+path2);
					
					if(path2.equals("")==false)
					{ //if(id.equals("")==true)
				ch=false;
					//ancurl=driver.getCurrentUrl();
					//System.out.println("the ancien driveris"+ancurl);
				System.out.println("je commence le test");
						if((recherche(path2.toLowerCase(),S1).getPath().equals("")==true)||(recherche2(path2.toLowerCase(),Sels,driver.getCurrentUrl()).getPath().equals("")==true))
					{
					System.out.println("anc est"+anc);
					
					 if(((path2.equals(anc))==false) )
							
	
			
						{ 
						 System.out.println("je teste avec id="+id+"anc="+anc);
						 String path=getXPath(ee,driver);
						 //String path=getXPath(ee,driver);
						
						//String path=generateXPATH(ee, "");
						String var="";
						String a=id;
						//System.out.println(path.toLowerCase());
				if((recherche(path.toLowerCase(),S1).getPath().equals("")==false)&&(recherche2(path.toLowerCase(),Sels,driver.getCurrentUrl()).getPath().equals("")==true))
				{String urll=driver.getCurrentUrl();
					System.out.println("je cherche"+recherche(path.toLowerCase(),S1).getPath());
				
					ScenarioTS so=recherche(path.toLowerCase(),S1);
					so.setUrl(driver.getCurrentUrl());
					Sels.add(so);
				  anc=	path;
				
				// ancurl=urll;
				// System.out.println("driver url"+ancurl);
								}
				else
				{ 
			
					
				
				 if 	( (path.toLowerCase().contains("body")   || (path.toLowerCase().contains("form")))&&((path.toLowerCase().contains("input"))==false)&& ((path.toLowerCase().contains("select"))==false)) {
					System.out.println("je suis in body and form");
					
					// value=driver.findElement(By.xpath(path)).getAttribute("value");
					//System.out.println("la valeur est "+value );
					 commande= "click" ;
					 value="nan value";
					 Sels.add(new ScenarioTS(commande,path,"",driver.getCurrentUrl()));
					 ancurl=driver.getCurrentUrl();
					 anc=path;
						//Scenario s3 = new Scenario(commande,path,value);
					
				}
				else {	
					System.out.println("je chercher sur "+id);// a=driver.switchTo().activeElement().getAttribute("id");
				String anc1=path2;
				String anc2=path;
				//anc=driver.switchTo().activeElement().getAttribute("id");
					while(anc2.equals(anc1))
					{	
						System.out.println("j'ai entréddans la boucle");
				//	if((driver.findElement(By.xpath(path)).getAttribute("value").equals(anc))==false)
					//{//Sel s = new Sel(path);
					//Sels.add(s);
				
						if (path.toLowerCase().contains("select")) {
							Select select = new Select(driver.findElement(By.xpath(path.toLowerCase())));
							WebElement option = select.getFirstSelectedOption();
							String defaultItem = option.getText();
							System.out.println(defaultItem );
							 commande= "click" ;
						
							 value=defaultItem;
							 System.out.println("defaut valueof selectis"+value);
							 ancurl=driver.getCurrentUrl();
							
								 ancurl=driver.getCurrentUrl();
							ee=	 driver.switchTo().activeElement();
									anc1=getXPath(ee,driver);
								//System.out.println("la valeur est "+value );
							//Scenario s2 = new Scenario(commande,path,value);
								//Sels.add(s2);
								
								//anc1=driver.switchTo().activeElement().getAttribute("id");
						}
					

						else if ((path.toLowerCase().contains("input") )&& (!(ee.getAttribute("type").equals("submit")))) 
							//|| (path.contentEquals("form"))) 
					{
						/*if (driver.findElement(By.xpath(path)).getAttribute("type").equals("submit")) 
						{driver.findElement(By.xpath(path)).click();
						value=driver.findElement(By.xpath(path)).getAttribute("value");
						System.out.println("la valeur est "+value );
						 commande= "click" ;
						 System.out.println("the value is+++++"+value);
						 System.out.println("the path is+++++"+path);
						 Scenario sc= new Scenario(commande,path,value);
						 Sels.add(sc);
							anc1=driver.switchTo().activeElement().getAttribute("id");
						 Thread.sleep(1000);
							url=driver.getCurrentUrl();
							System.out.println("new url"+url);
							//driver.get(url);
							//System.out.println("new url222"+driver.getCurrentUrl());
							
							 
						}*/
						System.out.println("je cherche la val "+value );
						System.out.println("le path2 iis++++"+path2);
						String pp="";
						if((nbr_min(path)>0)&&(nbr_maj(path)>0))
							
						{ System.out.println("j'ai entree daans la condition 1");
						System.out.println("nb min est" +nbr_min(path) + "nb maj est"+nbr_maj(path));
							pp=path;}
						if((nbr_maj(path)==0))
						{System.out.println("j'ai entree daans la condition 2");
						System.out.println("nb min est" +nbr_min(path) + "nb maj est"+nbr_maj(path));
							pp=path.toLowerCase();
							int lg=pp.length()-1;
						if (pp.charAt(lg)==']')	
						pp=pp.substring(0, lg-2);
						}
						value=driver.findElement(By.xpath(pp)).getAttribute("value");
						System.out.println("la valeur est "+value );
						 commande= "click" ;
						 ancurl=driver.getCurrentUrl();
					ee=	 driver.switchTo().activeElement();
							anc1=getXPath(ee,driver);
							System.out.println("j'ai terminé");
							//Time.sleep(10);
							
						//	Thread.sleep(3);
							//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							//Scenario s3 = new Scenario(commande,path,value);
						
					}
					
					/*if (path.contains("button")) {
						String type=driver.findElement(By.xpath(path)).getAttribute("type");
						String val=driver.findElement(By.xpath(path)).getAttribute("value");
						System.out.println("je suis un bouton");
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						 value=driver.findElement(By.xpath(path)).getAttribute("type");
						driver.findElement(By.xpath(path)).click();
						 commande= "click" ;
						 System.out.println("the value is+++++"+value);
						 System.out.println("the path is+++++"+path);
						 ScenarioTS sc= new ScenarioTS(commande,path,value);
						 Sels.add(sc);
						 anc1=driver.switchTo().activeElement().getAttribute("id");
						 url=driver.getCurrentUrl();
						 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						//System.out.println("new url"+url);
					
						//testt2(url,navigator);
							Thread.sleep(10);
							driver.get(url);
						//driver.get(url);
						//System.out.println("new url222"+driver.getCurrentUrl());
						//driver.get(url);
					
						 //System.out.println("la valeur est "+value );
						
						 //value="";
							//Scenario s3 = new Scenario(commande,path,value);
						
					}*/
					
				
					
				/*	if (path.toLowerCase().contains("/a") && (!(ee.getAttribute("href").equals(""))))
					{anc1=driver.switchTo().activeElement().getAttribute("id");
						commande= "link" ;
						value=driver.findElement(By.xpath(path)).getAttribute("href");
						
					}*/
					//anc1=driver.switchTo().activeElement().getAttribute("id");
					//String path2=getXPath(ee,driver);
					/*if(recherche(path2.toLowerCase(),S1).getPath().equals("")==false)
					{System.out.println("je cherche"+recherche(path2.toLowerCase(),S1).getPath());
					
						Sels.add(recherche(path2.toLowerCase(),S1));
				System.out.println("anc1 is"+anc1);
				//System.out.println("path2 is"+path2);
				//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					}*/
				
					}
					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
					
							
							//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					//}
			System.out.println("the value is+++++"+value);
			System.out.println("the path is+++++"+path);
			ScenarioTS sc= new ScenarioTS(commande,path.toLowerCase(),value,driver.getCurrentUrl());
			Sels.add(sc);
			System.out.println("j'ai ajouté3");
				}	}	
				
					
				System.out.println("anc is "+anc);
						
					// Thread.sleep(3);
					
						
						 }
					 anc=path2;
					}}
				//ancurl2=driverurl;	 
					}
					
					}catch (StaleElementReferenceException e) 
					{System.out.println("erreuuuuuuuuuuurrrrr****");
					System.out.println(e);
					//testt2(driver.getCurrentUrl(),navigator);
					}
					
			 
				 }

	
				 @RequestMapping(value = "/saveScST")
				 public String saveSTDB() throws Exception{
				 	
				 	System.out.println("j'ai entrée");
				 	System.out.println("je suis id"+testIF);
				 	TestCase tc = testCaseService.getTestCaseById(testIF);
				 System.out.println(tc.toString());

				 if(Sels.size()>0)
				 {//	scenarioService.save(Sels.get(0), tc);
				 	System.out.println(Sels.size());
				 //driver.quit();
				 List<ScenarioTS>Sel= new ArrayList<ScenarioTS>();
				 Sel=Sels;
				 	
				 	for (int i=0;i<Sel.size();i++) {
				 	scenarioTSService.save(Sels.get(i), tc);
				 	System.out.println(Sel.get(i).getPath());
				 	//scenarioService.save(Sel.get(i), tc);

				 	}
					Traitment(tc);
					remplirStep(tc);
				 return "redirect:/scenariots_lists?idTestCase=" +tc.getIdTestCase();
				 }

				 else {
				 	return "redirect:/scenariots_lists?idTestCase=" +tc.getIdTestCase();
				 	}
				 }

				 @RequestMapping("/scenariots_lists")
				 public String viewHomePage(Model model,Long idTestCase,HttpSession session) {
				   session.setAttribute("idTestCase", idTestCase);
				   TestCase TestCase=testCaseService.getTestCaseById(idTestCase);
				   model.addAttribute("scenariots_lists", scenarioTSService.findTestCaseScenario(TestCase));
				   testIF=idTestCase;
				   return "scenariots";
				 } 
			 
				 @RequestMapping("/AddScenarioTS")
				public String insert(Model model,Long idTestCase, HttpSession session) {
				    	
				    
				   	 session.setAttribute("idTestSuite", idTestCase);
				   	 if(testIF==0)
				   	 {testIF=idTestCase;}
				  	// this.tes();
				    	model.addAttribute("AddScenarioTS", Sels);
				        return "AddScenarioTS";
				    } 
				
				    @RequestMapping("AddScenarioTS/sa")
				public String insert2(Model model) throws InterruptedException {
				    	//session.setAttribute("idTestSuite", idTestCase);
				    	TestCase TestCase=testCaseService.getTestCaseById(testIF);
				    	 String navigator=TestCase.getNavigator();
				    	 String url=TestCase.getUrl(); 
				    	this.recorder(url,navigator);
				    	//  this.x(url,navigator);
				  	return "AddScenarioTS";
				    } 
		  

	public List<WebElement> rec(String url)
	{	
		
		List<WebElement> elements = driver.findElements(By.xpath("//*"));
		return elements;
	}
	
	
	public List<ScenarioTS> affiche(String url) throws InterruptedException {
	List<ScenarioTS>S=new ArrayList<ScenarioTS>();
	List<WebElement> elements=new ArrayList<WebElement>();
	
	 Thread.sleep(300);
	 //driver.navigate().refresh();
	 //Thread.sleep(10);
	 for(int i=0;i<100;i++)
	 {
		 elements=rec(url);}
		System.out.println("j'ai terminé la recup des web elements"+url+"lataille est"+elements.size());
		if(elements.size()>0)
		{for (WebElement we: elements)
		{
			String cmd="click";
			
			//String x=generateXPATH(we, "");
			String x=getXPath(we,driver);
			//System.out.println(x);
			if(x.equals("")==false)
			{
			//System.out.println(x);
			if((x.toLowerCase().contains("button")  ||(x.toLowerCase().contains("/a"))|| (x.toLowerCase().contains("input")&&(we.getAttribute("type").equals("submit"))))  )
			{
				//String val=we.getAttribute("value");
			
			//String val="";
			ScenarioTS x1 = new ScenarioTS(cmd,x.toLowerCase(),"","");
			S.add(x1);
			}}}
		}
		
		
		for (int i=0;i<S.size();i++) {
			System.out.println("Path"+S.get(i).getPath());
			//System.out.println("Value"+S.get(i).getValue());
			
		}
		
		return S;
	}


	public ScenarioTS recherche (String path,List<ScenarioTS>ab)
	
	{
		ScenarioTS i1=new ScenarioTS("","","","");
		for (int i=0;i<ab.size();i++) {
		{
			if (ab.get(i).getPath().equals(path))
			{
			i1.setCommande(ab.get(i).getCommande());	
			i1.setPath(ab.get(i).getPath());
			i1.setValue(ab.get(i).getValue());
			
			}
			}
			
		}
		return i1;
		
		
	}
public ScenarioTS recherche2 (String path,List<ScenarioTS>ab,String url)
	
	{
		ScenarioTS i1=new ScenarioTS("","","","");
		for (int i=0;i<ab.size();i++) {
		{
			if ((ab.get(i).getPath().equals(path))&&(ab.get(i).getUrl().equals(url)))
			{
			i1.setCommande(ab.get(i).getCommande());	
			i1.setPath(ab.get(i).getPath());
			i1.setValue(ab.get(i).getValue());
			i1.setUrl(ab.get(i).getUrl());
			
			}
			}
			
		}
		return i1;
		
		
	}
	
	

	private  int nbr_maj(String chaine) {
		  int compteur=0;
		  for(int i = 0; i<chaine.length(); i++){
		   char ch = chaine.charAt(i);
		   if(Character.isLowerCase(ch))
		    compteur++;
		  }
		  return compteur;
		 }

		 private  int nbr_min(String chaine) {
		  int compteur=0;
		  for(int i = 0; i<chaine.length(); i++){
		   char ch = chaine.charAt(i);
		   if(Character.isUpperCase(ch))
		    compteur++;
		  }
		  return compteur;
		 }


		 
		 public void Traitment (TestCase tc1) throws Exception {
			 String instru="";
			  List<ScenarioTS> sce=scenarioTSService.findTestCaseScenario(tc1);
			  String NameFile=tc1.getTestCaseName().trim().replace(' ', '_')+tc1.getIdTestCase();
			 File fichier = new File("./FeaturesTR/"+NameFile+".feature");
			     if (fichier.createNewFile())
			       System.out.println("Le fichier a été créé");
			     else
			       System.out.println("Erreur, Impossible de créer ce fichier");

			     
			     FileOutputStream fout=new FileOutputStream("./FeaturesTR/"+NameFile+".feature",true);    
			     String s="Feature: "+tc1.getTestCaseName()  +"\n"+"\n";    
			     byte b[]=s.getBytes();//converting string into byte array    
			     fout.write(b); 
			     String scenario="Scenario: "+tc1.getTestCaseDescription()  +"\n";    
			     byte scenariob[]=scenario.getBytes();//converting string into byte array    
			     fout.write(scenariob); 
			     
			     String browser="  Given User Launch "+'"'+tc1.getNavigator() +'"'+"\n";    
			     byte browserb[]=browser.getBytes();//converting string into byte array    
			    fout.write(browserb); 

			   for (ScenarioTS we: sce) {
			 	  
			 	  instru=remplirF(we);
			 	  byte xx[]=instru.getBytes();//converting string into byte array    
			 	   fout.write(xx); 
			   }
			   
			   
			   String close="  And close browser"; 
			   byte closeb[]=close.getBytes();//converting string into byte array    
			   fout.write(closeb);
			     fout.close();    
			     System.out.println("success...");  
			     
			     
			    // return "Traitment";
			 }
			 	
			 public String remplirF (ScenarioTS s) {
			 	String instruction="";
			 	if(s.getCommande().equals("open")) {
			 		instruction="When User opens URL "+'"'+s.getPath()+'"'+"\n";
			 	}
			 	
			 	//else if ((s.getPath().contains("input"))&& (!(s.getValue().equals("")))&& (driver.findElement(By.xpath(s.getPath())).getAttribute("type").equals("radio")))
			// 	{instruction="  And User clic in input radio has xpath "+'"'+s.getPath()+'"'+"\n" ;	}

			 	
			 	else if ((s.getPath().contains("input"))&& (!(s.getValue().equals(""))))
			 	{
			 		instruction="  And User clic in input has xpath "+'"'+s.getPath()+'"'+" and Value as "+'"'+s.getValue()+'"'+"\n" ;	
			 	}

			 	
			 	
			 	
			 	else if ((s.getPath().contains("input"))&& (s.getValue().equals("")))
			 	{	
			 		instruction="  And User clic in input submit has xpath "+'"'+s.getPath()+'"'+"\n" ;	
			 	}
			 	else if (s.getPath().contains("button"))
			 	{
			 		instruction="  And User clic in button has xpath "+'"'+s.getPath()+'"'+"\n" ;	
			 	}
			 	else if ((s.getPath().contains("select"))&& (s.getValue()!=""))
			 	{
			 		instruction="  And User clic in select has xpath "+'"'+s.getPath()+'"'+" and Value as "+'"'+s.getValue()+'"'+"\n" ;	
			 	}
			 	else if (s.getPath().contains("a[1]"))
			 	{
			 		instruction="  And User clic in lien has xpath "+'"'+s.getPath()+'"'+"\n" ;	
			 	}
			 	else {
			 		instruction="  And User clic on element has xpath "+'"'+s.getPath()+'"'+"\n" ;	
			 	
			 	}
			 	
			 	return instruction;

			 }
			 		


			 public void remplirStep(TestCase tc1) throws Exception {
			 	String line;
			 	String instructionS="";
			 String S="";
			 byte b[];
			 String func="";
			 String Brow=choixBrowserStep(tc1);
			 //DependentTestCase tc1 = dependenttestCaseService.getDependentTestCaseById((long) 868);
			 List<ScenarioTS> sce=scenarioTSService.findTestCaseScenario(tc1);
			 String FolderName=tc1.getTestCaseName().trim().replace(' ', '_')+tc1.getIdTestCase();
			 new File("./src/test/java/"+FolderName).mkdir();
			 String StepFileName="Step_"+tc1.getTestCaseName().trim().replace(' ', '_')+tc1.getIdTestCase();
			 File fichier = new File("./src/test/java/"+FolderName+StepFileName+".java");
			 if (fichier.createNewFile())
			   System.out.println("Le fichier a été créé");
			 else
			   System.out.println("Erreur, Impossible de créer ce fichier");

			 FileOutputStream fout=new FileOutputStream("./src/test/java/"+FolderName+"/"+StepFileName+".java",true);    
			 String Debut ="package "+FolderName+";\r\n" + 
			 		"\r\n" + 
			 		"\r\n" + 
			 		"\r\n" + 
			 		"import org.openqa.selenium.By;\r\n" + 
			 		"import org.openqa.selenium.WebDriver;\r\n" + 
			 		"import org.openqa.selenium.chrome.ChromeDriver;\r\n" + 
			 		"import org.openqa.selenium.support.ui.Select;\r\n"+
			 		"\r\n" + 
			 		"import io.cucumber.java.en.Given;\r\n" + 
			 		"import io.cucumber.java.en.Then;\r\n" + 
			 		"import io.cucumber.java.en.When;\r\n"
			 		+ "public class "+StepFileName+" {\r\n" + 
			 		"	public WebDriver driver ;\r\n ";



			 List<String>Tab=new ArrayList<String>();
			 byte c[]=Debut.getBytes();//converting string into byte array    
			 fout.write(c);

			 BufferedReader lecteurAvecBuffer = null;

			 String NameFile=tc1.getTestCaseName().trim().replace(' ', '_')+tc1.getIdTestCase();

			 try
			   {
			 lecteurAvecBuffer = new BufferedReader(new FileReader("./FeaturesTR/"+NameFile+".feature"));
			   }
			 catch(FileNotFoundException exc)
			   {
			 System.out.println("Erreur d'ouverture");
			   }
			 Tab.add("chayma");
			 while ((line = lecteurAvecBuffer.readLine()) != null)
			 {

			 	if ((line.contains("Given User Launch"))&& (Tab.indexOf("Given User Launch")==-1))
			 	{
			 		S="User Launch {string}";
			 		func="public void user_Launch(String string) {\r\n" + 
			 		Brow+
			 				"	   \r\n" + 
			 				"	}";

			 		instructionS= "@Given("+'"'+S+'"'+")" +"\n"+func+"\n" ;    
			 		    b=instructionS.getBytes();//converting string into byte array    
			 		    fout.write(b);
			 		    Tab.add("Given User Launch");
			 				}
			 	else if ((line.contains("When User opens URL"))&& (Tab.indexOf("When User opens URL")==-1))
			 	{
			  S="User opens URL {string}";
			  func="public void user_opens_URL(String url) {\r\n" + 
			  		"	   \r\n" + 
			  		"		driver.get(url);\r\n" + 
			  		"	}";
			 		instructionS= "@When("+'"'+S+'"'+")" +"\n"+func+"\n" ;
			 			     
			 		    b=instructionS.getBytes();//converting string into byte array    
			 		    fout.write(b);	
			 		    Tab.add("When User opens URL");
			 				}
			 	
			 	else if ((line.contains("And User clic in input has xpath"))&&(line.contains("and Value as")) &&(Tab.indexOf("User click in input with value")==-1))
			 	{
			 		
			 		
			 		
			  S="User clic in input has xpath {string} and Value as {string}";
			  func="	public void user_clic_in_input_has_xpath_and_Value_as(String string, String string2) {\r\n" + 
			 			"		driver.findElement(By.xpath(string)).clear(); \r\n" + 
			 			"		driver.findElement(By.xpath(string)).sendKeys(string2);}" ; 
			 		instructionS= "@When("+'"'+S+'"'+")" +"\n" +func+"\n";
			 			  
			 		    b=instructionS.getBytes();//converting string into byte array    
			 		    fout.write(b);			
			 			Tab.add("User click in input with value");	
			 		
			 				}
			 	
			 	
			 	else if ((line.contains("And User clic in input radio has xpath"))&&(line.contains("and Value as")) &&(Tab.indexOf("User click in input radio")==-1))
			 	{
			 		
			 		
			 		
			  S="User clic in input radio has xpath {string} and Value as {string}";
			  func="	public void user_clic_in_input_radio_has_xpath_and_Value_as(String string, String string2) {\r\n" + 
			 			"		driver.findElement(By.xpath(string)).click(); \r\n" + 
			 			"		;}" ; 
			 		instructionS= "@When("+'"'+S+'"'+")" +"\n" +func+"\n";
			 			  
			 		    b=instructionS.getBytes();//converting string into byte array    
			 		    fout.write(b);			
			 			Tab.add("User click in input radio");	
			 		
			 				}
			 	
			 	
			 	else if ((line.contains("And User clic in input submit has xpath"))&&(Tab.indexOf("And User clic in input submit has xpath")==-1))
			 	{
			 		S="User clic in input submit has xpath {string}";
			 		func="public void user_clic_in_input_submit_has_xpath(String string) throws Exception {\r\n" + 
			 				"		driver.findElement(By.xpath(string)).click();\r\n" +
			 				"Thread.sleep(2000);\r\n"+
			 				"	}\r\n" ;

			 		instructionS= "@When("+'"'+S+'"'+")" +"\n"+func+"\n" ;
			 		b=instructionS.getBytes();//converting string into byte array    
			 	    fout.write(b);
			 	    Tab.add("And User clic in input submit has xpath");
			 	}
			 	else if ((line.contains("And User clic in button has xpath"))&& (Tab.indexOf("And User clic in button has xpath")==-1))
			 	{
			 		S="User clic in button has xpath {string}";
			 		func="	public void user_clic_in_button_has_xpath(String string) throws Exception {\r\n" + 
			 				"		driver.findElement(By.xpath(string)).click();\r\n" + 
			 				"Thread.sleep(2000);\r\n"+
			 				"	}";
			 		instructionS= "@When("+'"'+S+'"'+")" +"\n"+func+"\n" ;
			 		b=instructionS.getBytes();//converting string into byte array    
			 	    fout.write(b);
			 	    Tab.add("And User clic in button has xpath");
			 	}
			 	else if ((line.contains("And User clic in select has xpath"))&& (line.contains("and Value as"))&&(Tab.indexOf("User click in select xpath with value")==-1))     
			 	{		func="public void user_clic_in_select_has_xpath(String string,String string2)  \r\n" + 
			 			"{\r\n" + 
			 			"	Select a = new Select(driver.findElement(By.xpath(string)));\r\n" + 
			 			"    a.selectByVisibleText(string2);\r\n" + 
			 			"}\r\n" ;
			 		S="User clic in select has xpath {string} and Value as {string}";

			 		instructionS= "@When("+'"'+S+'"'+")" +func+"\n" ;
			 		b=instructionS.getBytes();//converting string into byte array    
			 	    fout.write(b);
			 	    Tab.add("User click in select xpath with value");
			 	}
			 	else if ((line.contains("And User clic in lien has xpath"))&&(Tab.indexOf("And User clic in lien has xpath")==-1))
			 	{
			 		S="User clic in lien has xpath {string}";	
			 		func="public void user_clic_in_lien_has_xpath(String string) throws Exception {\r\n" + 
			 				"		driver.findElement(By.xpath(string)).click();\r\n" +
			 				"Thread.sleep(2000);\r\n"+
			 				"	}";
			 		instructionS= "@When("+'"'+S+'"'+")" +func+"\n" ;
			 		b=instructionS.getBytes();//converting string into byte array    
			 	    fout.write(b);
			 	    Tab.add("And User clic in lien has xpath");
			 	}
			 	
			 	else if ((line.contains("And close browser"))&& (Tab.indexOf("And close browser")==-1))
			 	{
			  S="close browser";
			  func="public void close_browser() {\r\n" + 
			 			"		driver.quit();\r\n" +  
			 			"	}";
			 		instructionS= "@When("+'"'+S+'"'+")" +"\n"+func+"\n" ;
			 			    
			 		    b=instructionS.getBytes();//converting string into byte array    
			 		    fout.write(b);
			 		    Tab.add("And close browser");
			 				}
			 	
			 	else if ((line.contains("Feature:")) || (line.contains("Scenario:"))||(line.contains("")))
			 	{
			 		S="";
			 		instructionS= "" ;
			 			    
			 		    b=instructionS.getBytes();//converting string into byte array    
			 		    fout.write(b);			
			 				}

			 	else {
			 		if (Tab.indexOf("User clic on element has xpath")==-1)
			 		{S="User clic on element has xpath {string}" ;
			 		func="public void user_clic_on_element_has_xpath(String string) {\r\n" + 
			 				"		driver.findElement(By.xpath(string)).click();\r\n" + 
			 				"	}";
			 		
			 		instructionS= "@When("+'"'+S+'"'+")" +"\n"+func+"\n" ;
			 		b=instructionS.getBytes();//converting string into byte array    
			 	    fout.write(b);
			 	    Tab.add("User clic on element has xpath");
			 	}}
			 	

			 }
			 instructionS= "}" ;
			 b=instructionS.getBytes();//converting string into byte array    
			 fout.write(b);
			 lecteurAvecBuffer.close();

			 	
			 }


			 public String choixBrowserStep(TestCase tc1) {
			 	//DependentTestCase tc1 = dependenttestCaseService.getDependentTestCaseById((long) 868);
			 	String Browser=tc1.getNavigator();
			 	String Brow="";
			 	if (Browser.equals("Google Chrome")) 
			 	{Brow=	"		System.setProperty(\"webdriver.chrome.driver\",System.getProperty(\"user.dir\")+\"//Drivers/chromedriver.exe\") ; \r\n" + 
			 			"		driver=new ChromeDriver(); \r\n";
			 	} if (Browser.equals("Firefox") )
			 	{Brow=	"		System.setProperty(\"webdriver.gecko.driver\",System.getProperty(\"user.dir\")+\"//Drivers/geckodriver.exe\") ; \r\n" + 
			 			"		driver=new FirefoxDriver(); \r\n";
			 	}
			 	
			 	return Brow;
			 }


		 
		 
}



			
			
		





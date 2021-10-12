package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.ecommerce.DBConnection;
import com.mysql.cj.protocol.Resultset;

public class swag {

	public static void main(String[] args) throws InterruptedException,ClassNotFoundException, SQLException {
		

		System.setProperty("webdriver.chrome.driver","chromedriver");
		
		
		WebDriver dr =new ChromeDriver();
		//WebDriver drf =new FirefoxDriver();
		
		dr.get("https://www.saucedemo.com/"); 
		
		dr.manage().window().maximize();
		dr.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		
		DBConnection conn;
		String username= "";
		String password= "";
		String name="";
		float price=0;
		
		
			
		conn=new DBConnection("jdbc:mysql://localhost:3306/ecommerce","root","root");
		Statement st= conn.getConnection().createStatement();
		Statement st1=conn.getConnection().createStatement();
		

		ResultSet c= st.executeQuery("select username,password from login_details;");
		ResultSet c1= st1.executeQuery("select name,price from eproduct where id='9';");
		
		
		c.next();
		username=c.getString("username");
		password=c.getString("password");
		
		c1.next();
		name=c1.getString("name");
		price=c1.getFloat("price");			
		
		
		
		conn.closeConnection();
		
	
		WebElement userna =dr.findElement(By.xpath("//input[@name='user-name']"));
		userna.sendKeys(username);
		
		WebElement passwd =dr.findElement(By.xpath("//input[@name='password']"));
		passwd.sendKeys(password);
		
		WebElement loginbuttn =dr.findElement(By.xpath("//input[@id='login-button']"));
		loginbuttn.click();
		
		WebElement prod = dr.findElement(By.xpath("//a/div[starts-with(text(),'"+name+"')]"));
		prod.click();
		
		WebElement add=dr.findElement(By.xpath("//button[text()='Add to cart']"));
		add.click();
		WebElement cart_add = dr.findElement(By.xpath("//a[@class='shopping_cart_link']"));
		cart_add.click();
		WebElement checkout = dr.findElement(By.xpath("//button[@name='checkout']"));
		checkout.click();
		WebElement first = dr.findElement(By.xpath("//input[@id='first-name']"));
		first.sendKeys("Rajesh");
		WebElement last = dr.findElement(By.xpath("//input[@id='last-name']"));
		last.sendKeys("patil");
		WebElement postal = dr.findElement(By.xpath("//input[@id='postal-code']"));
		postal.sendKeys("492392");
		WebElement conti = dr.findElement(By.xpath("//input[@id='continue']"));
		conti.click();
		
		WebElement fin = dr.findElement(By.xpath("//button[@id='finish']"));
		fin.click();
		
	
		String str1="Your order has been dispatched, and will arrive just as fast as the pony can get there!";
		String check = dr.findElement(By.cssSelector("div[class=complete-text]")).getText();
//		System.out.println(check);
		
		String verify ="Your ordered has been dispatched, and will arrive just as fast as the pony can get there!";
	
		if (check.equals(verify)) {
			System.out.println("passed test!!");
		}
		else {
			System.out.println("failed test!!");
		}
		
	
	
	}
}
		
		
		
		
		

		
	

	 
import java.util.Scanner;
import java.sql.*;

public class Dealer 
{
	public static void main (String [] args) throws Exception
	{
		try
		{
			//establish a connection to the database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dealershipnetwork","root","1234");
			
			//variable declaration
			Statement state1 = con.createStatement();
			Statement state2 = con.createStatement();
			Statement state3 = con.createStatement();
			Statement state4 = con.createStatement();
			Statement state5 = con.createStatement();
			Statement state6 = con.createStatement();
			Scanner kbd = new Scanner(System.in);
			int x = 0;
			
			//user input
			System.out.println("Hello and welcome to the Java-based application for the Dealership Network!");
			System.out.println("We have a set of 6 queries that can be run, those being:");
			System.out.println("1. See how many red cars are in stock across all dealerships.");
			System.out.println("2. See the name and location of all dealerships.");
			System.out.println("3. See all vehicles in Dealership South's Stock");
			System.out.println("4. See the average price of all vehicles in Dealership East's Stock");
			System.out.println("5.List all Toyotas and their price in Dealership West's Stock");
			System.out.println("6.List the Customer ID and VIN # of all vehicles purchased in Dealership West");
			System.out.println("7.Exit application");
			System.out.println("Please press the key that corresponds with the number.");
			
			
			x = kbd.nextInt();
			
			if (x==1)
			{
				ResultSet result1 = state1.executeQuery("SELECT COUNT(Color) as Amount FROM Stock WHERE Color = 'Red';");
				
				while (result1.next())
				{
					String AmountOf = result1.getString("Amount");
					
					System.out.println("The amount of red cars across all dealerships is: " + AmountOf);
					
				}
				
			}
			else if (x==2)
			{
				ResultSet result2 = state2.executeQuery("SELECT DName, DAddress FROM Dealership;");
				
				while (result2.next())
				{
					String Name = result2.getString("DName");
					String Address = result2.getString("DAddress");
					
					System.out.println("Dealership name: " + Name + " Dealership Location: " + Address);
				}
			}
			else if (x==3)
			{
				ResultSet result3 = state3.executeQuery("SELECT Make, Model, vehicle_year, Stock.Did FROM Stock where did = 2;");

				
				while (result3.next())
				{
					String Make = result3.getString("Make");
					String Model = result3.getString("Model");
					
					int Year = result3.getInt("vehicle_year");
					
					System.out.println("South Stock Make: " + Make + " South Stock Model: " + Model + " Year: " + Year );
				}
			}
			else if (x==4)
			{
				
				ResultSet result4 = state4.executeQuery("Select AVG(Price) as Average_Price , Stock.Did from Stock WHERE Did = 2;");
																	
				while (result4.next())
				{
					int AVGPrice = result4.getInt("Average_Price");
					System.out.println("The average price of all vehicles in Dealership East is: $"+ AVGPrice);
				}
			}
			else if (x==5)
			{
				
				ResultSet result5 = state5.executeQuery("SELECT Make, Model, Price, Stock.Did from Stock Join Dealership on Stock.Did = 1 = Dealership.Did = 1 where Make = 'Toyota';");
				
				while (result5.next())
				{
					String Make = result5.getString("Make");
					String Model = result5.getString("Model");
					int Price = result5.getInt("Price");
					int Did = result5.getInt("Did");
					System.out.println("Make: " + Make + " Model: " + Model + " Price: $" + Price);
				}
				
			}
			else if (x==6)
			{
				ResultSet result6 = state6.executeQuery("select Purchase.CID, Purchase.Did, Purchase.VIN FROM Purchase Join Customer on Purchase.Cid = Customer.CID Join Dealership on Purchase.Did = Dealership.Did Join Stock on Purchase.VIN = Stock.VIN;");
				
				
				
				while (result6.next())
				{
					int Cid = result6.getInt("Cid");
					int VIN = result6.getInt("VIN");
					
					System.out.println("Customer ID#: "+ Cid + " VIN: " + VIN);
				}
			}
			else if (x==7)
			{
				System.exit(0);
			}
		
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}

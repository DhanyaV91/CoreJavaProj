package com.manipal.corejavaproj;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.manipal.corejavaproj.model.Admin;
import com.manipal.corejavaproj.model.Customer;
import com.manipal.corejavaproj.model.Toy;
import com.manipal.corejavaproj.model.ToyRental;
import com.manipal.corejavaproj.service.CustomerService;
import com.manipal.corejavaproj.service.CustomerServiceImpl;
import com.manipal.corejavaproj.service.ToyRentalService;
import com.manipal.corejavaproj.service.ToyRentalServiceImpl;
import com.manipal.corejavaproj.service.ToyService;
import com.manipal.corejavaproj.service.ToyServiceImpl;

public class TestApp {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		int count = 0, count1 = 0, count2 = 0, count3 = 0;
		int num;
		Scanner s = new Scanner(System.in);
		
		while(count == 0) {
			CustomerService custService = new CustomerServiceImpl();
			ToyService toyService = new ToyServiceImpl();
			ToyRentalService toyRentalService = new ToyRentalServiceImpl();
			System.out.println("You can choose any from 1 to 4 as given below:");
			System.out.println("1.Get Toy Details");
			System.out.println("2.Book a Toy");
			System.out.println("3.Return a Toy");
			System.out.println("4.Update/Delete Customer Details");
			System.out.println("5.Get Rental Details");
			System.out.println("6.Get Toy Rental Details");
			System.out.println("7.Admin Operations");
			num = s.nextInt();
			switch(num) {
			case 1:
			  List<Toy>toyList = toyService.getToyDetails(); 
			  if(!toyList.isEmpty()) {
				  for(Toy toy:toyList) {
					  System.out.println(toy);
				  }
			  }else {
				  System.out.println("Sorry!There are no toy records!");
			  }					
			  break;
			case 2: System.out.println("Seems you want to book a toy!Great proceed ahead...");
					System.out.println("Are you a new customer or existing?Please enter new or existing.");
					String ans = s.next();
					if(ans.matches("new")) {
						System.out.println("Enter Customer Name:");
						String custname = s.next();
						System.out.println("Enter Password:");
						String pass = s.next();
						System.out.println("Enter City:");
						String city = s.next();
						System.out.println("Enter State:");
						String state = s.next();
						System.out.println("Enter Zip Code:");
						int zip = s.nextInt();
						System.out.println("Enter Country:");
						String country = s.next();
						try {
							Customer c = new Customer(custname, pass, city, state, zip, country);
							int res = custService.createCustomer(c); 
							if(res>0) {
								System.out.println("Customer added!Here are your details:");
								List<Customer> custList = custService.search(custname);
								  for(Customer customer:custList) {
									  System.out.println(customer);
								  }
								while(count1 == 0) {
									System.out.println("Enter the Toy ID of the toy you want to rent:");
									int toyid = s.nextInt();
									List<Toy> toydet = toyService.search(toyid);									
									if(!toydet.isEmpty()) {
										System.out.println("How many days you want to rent the toy:"); 
										int days = s.nextInt();
										System.out.println("Enter your customer ID provided by us:");
										int custid= s.nextInt(); int booktoy = toyRentalService.bookToy(toyid, custid, days); 
										if(booktoy > 0) {
											System.out.println("You have booked the toy!"); count1=1; 
										}else {
											System.out.println("Sorry!It seems that someone else has rented the toy!");
											count1=0; 
										} 
									}else { 
										System.out.println("Sorry! no toys available!"); 
									}
									 
								}
							}else {
								System.out.println("Customer could not be added!"); 
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						
					}else if(ans.matches("existing")){
						while(count2 == 0) {
							System.out.println("Please provide your name here:");
							String name = s.next();
							  List<Customer> custList = custService.search(name);
							  if(!custList.isEmpty()) {
								  for(Customer customer:custList) {
									  System.out.println(customer);
								  }
									System.out.println("Enter the Toy ID of the toy you want to rent:");
									int toyid = s.nextInt();
									List<Toy> toydet = toyService.search(toyid);
									if(!toydet.isEmpty()) {
										System.out.println("How many days you want to rent the toy:");
										int days = s.nextInt();
										System.out.println("Enter your customer ID provided by us:");
										int custid= s.nextInt();									
										int booktoy = toyRentalService.bookToy(toyid, custid, days);
										if(booktoy > 0) {
											System.out.println("You have booked the toy!");
										}else {
											System.out.println("Sorry!It seems that someone else has rented the toy!");
										}								  
									  count2=1;
									}else {
										System.out.println("Sorry no toys available!");
									}
							  }else {
								  System.out.println("Sorry!There are no customer records!");
							  }
							  System.out.println("Press 0 to continue or Press 1 to exit!");
							  count2=s.nextInt();
						}
					}else {
						System.out.println("Invalid data provided!");
					} 
				break;
			case 3: System.out.println("Proceed to return toy rented!");
					System.out.println("Enter Toy ID which you want to return:");
					int tid = s.nextInt();
					int ret = toyRentalService.returnToy(tid);
					if(ret > 0) {System.out.println("Toy returned successfully");}else {System.out.println("Could not process request!");}						
				break;
			case 4: System.out.println("Please proceed to Update/Delete your customer records!");
					  System.out.println("Enter your Customer ID you want to update or delete records of:");
					  int cid = s.nextInt();
					  System.out.println("Do you want to update or delete records?Please type update or delete below:");
					  String ans1 = s.next();
					  if(ans1 == "update") {
							System.out.println("Enter Customer ID of the customer whose details you want to update:");
							int custid = s.nextInt();
							System.out.println("Enter Customer Name you want to update(Note: If you dont want to update this field just enter null keyword.):");
							String cname = s.next();
							System.out.println("Enter Password you want to update(Note: If you dont want to update this field just enter null keyword.):");
							String pwd = s.next();
							System.out.println("Enter City you want to update(Note: If you dont want to update this field just enter null keyword.):");
							String cty = s.next();
							System.out.println("Enter State you want to update(Note: If you dont want to update this field just enter null keyword.):");
							String sta = s.next();
							System.out.println("Enter Zip code you want to update(Note: If you dont want to update this field just enter 0. DO NOT ENTER SPACE OR KEEP BLANK.):");
							int zipcd = s.nextInt();
							System.out.println("Enter State you want to update(Note: If you dont want to update this field just enter null keyword.):");
							String ctry = s.next();				
							try {
								Customer cu = new Customer(cname, pwd, cty, sta, zipcd, ctry);
								int update = custService.updateCustomer(custid, cu);
								if(update > 0) {System.out.println("Record Updated!");}else {System.out.println("Record could not be Updated!");}			
							} catch (Exception ie) {
								ie.printStackTrace();
							}						  
					  }else if(ans1 == "delete"){
						  int delc = custService.deleteCustomer(cid);
						  if(delc>0) {
							  System.out.println("Record deleted!");
						  }else{
							  System.out.println("Record could not be deleted!");
						  }
					  }else {
						  System.out.println("Invalid Option given!");
					  }
				break;
			case 5: System.out.println("Get Rental Details!");
					System.out.println("Enter the customer id to get rental details:");
					int cust_id = s.nextInt();
					  List<Customer> toyRentalList = toyRentalService.getRentalDetails(cust_id); 
					  if(!toyRentalList.isEmpty()) {
						  for(Customer toyRent:toyRentalList) {
							  System.out.println(toyRent);
						  }
					  }else {
						  System.out.println("There are no toy rental records for the customer!");
					  }			
				break;
			case 6: System.out.println("Get Rental Details of Toy you have rented:");
				System.out.println("Enter the toy id to get rental details:");
				int toy_id = s.nextInt();
				  List<ToyRental> toyRentalList1 = toyRentalService.toyRentDetails(toy_id); 
				  if(!toyRentalList1.isEmpty()) {
					  for(ToyRental toyRent:toyRentalList1) {
						  System.out.println(toyRent);
					  }
				  }else {
					  System.out.println("There are no toy rental records for the customer!");
				  }			
				break;
			case 7: System.out.println("This section is for Admin Operations!");
				while(count3 == 0) {
					System.out.println("Are you an admin?Enter your name:");
					String admin_name = s.next();
					System.out.println("Enter your password:");
					String admin_pass = s.next();
					Admin admin = new Admin();
					int res = admin.authorise(admin_name, admin_pass);
					if(res > 0) {
						System.out.println("Please select 1 to 4 from the below options:");
						System.out.println("1. Insert Toy details");
						System.out.println("2. Delete Toy details");
						System.out.println("3. Update Toy details");
						System.out.println("4. Search for specific Toy details");
						
						int ans2 = s.nextInt();
						switch(ans2) {
						case 1: System.out.println("Insert Toy Details!");
						  System.out.println("Enter Toy Name:"); 
						  String toyname = s.next();
						  System.out.println("Enter Toy Type:"); 
						  String toytype = s.next();
						  System.out.println("Enter Min Age:"); 
						  int minage = s.nextInt();
						  System.out.println("Enter Max Age:"); 
						  int maxage = s.nextInt();
						  System.out.println("Enter Price:"); 
						  int price = s.nextInt();
						  System.out.println("Enter Quantity:"); 
						  int qty = s.nextInt();
						  System.out.println("Enter Rental Amount:"); 
						  int rentamt = s.nextInt(); 
						  try {
							  Toy t = new Toy(toyname, toytype, minage, maxage, price, qty, rentamt); 
							  int res1 = toyService.insertToyDetails(t); 
							  if(res1>0) {
								  System.out.println("Toy data added!"); 
							  }else {
								  System.out.println("Toy data could not be added!"); } 
						  } catch (Exception e) {
							  e.printStackTrace(); 
						  } 
						  break;
						case 2: System.out.println("Delete Toy Details!"); 
							System.out.println("Enter Toy ID of the toy whose details you want to delete:"); 
							int toyId = s.nextInt(); 
							int delt = toyService.deleteToyDetails(toyId);
							if(delt>0) { 
								System.out.println("Record deleted!"); 
							}else{
								System.out.println("Record could not be deleted!"); 
							}
						break;
						case 3: System.out.println("Update Toy Details!"); 
							System.out.println("Enter Toy ID of the customer whose details you want to update:");
							int toyid = s.nextInt();
							System.out.println("Enter Toy Name you want to update(Note: If you dont want to update this field just enter null keyword.):"); 
							String tname = s.next(); 
							System.out.println("Enter Toy type you want to update(Note: If you dont want to update this field just enter null keyword.):");
							String ttype = s.next();
							System.out.println("Enter min age you want to update(Note: If you dont want to update this field just enter 0.. DO NOT ENTER SPACE OR KEEP BLANK.):");
							int mage = s.nextInt(); 
							System.out.println("Enter max age you want to update(Note: If you dont want to update this field just enter 0.. DO NOT ENTER SPACE OR KEEP BLANK.):");
							int mxage = s.nextInt();
							System.out.println("Enter price code you want to update(Note: If you dont want to update this field just enter 0. DO NOT ENTER SPACE OR KEEP BLANK.):");
							int pri = s.nextInt();
							System.out.println("Enter quantity code you want to update(Note: If you dont want to update this field just enter 0. DO NOT ENTER SPACE OR KEEP BLANK.):");
							int quan = s.nextInt();
							System.out.println("Enter rental amount code you want to update(Note: If you dont want to update this field just enter 0. DO NOT ENTER SPACE OR KEEP BLANK.):");
							int rent_amt = s.nextInt();
							try { 
								Toy tu = new Toy(tname, ttype, mage, mxage, pri, quan, rent_amt);
								int tupd = toyService.updateToyDetails(toyid, tu);
								if(tupd > 0) {
									System.out.println("Record Updated!");
								}else{
									System.out.println("Record could not be Updated!");
								} 
							} catch (Exception ie){
								ie.printStackTrace(); 
							}
						break;
						case 4: System.out.println("Search for Toy details");
							System.out.println("Enter toy id you want to search of:");
							int toy = s.nextInt();
							List<Toy> searchRes = toyService.search(toy);
							  for(Toy toydetail:searchRes) {
								  System.out.println(toydetail);
							  }
						}
					}else {
						System.out.println("Invalid data!Please check the entered username or password!");
					}
					System.out.println("Press 0 to try again or Press 1 to exit!");
					count3 = s.nextInt();
				}
			default: System.out.println("Invalid Option!");		
			}
		System.out.println("If you want to continue with this prgram press 0 or press 1 to exit from this complete program:");
		count = s.nextInt();		 
		}
		s.close();
	}

}

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


class customerinfo
{
	private int account_number;
	private String name;
	private double acctBal;
	public int getAccount_number() {
		return account_number;
	}
	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAcctBal() {
		return acctBal;
	}
	public void setAcctBal(double acctBal) {
		this.acctBal = acctBal;
	}
	
	public customerinfo(int accountnumber,String name,double acctbal)
	{
		this.account_number=accountnumber;
		this.name=name;
		this.acctBal=acctbal;
	}
	
}

class bankoperation
{
	int accountNumber;
	String name;
	double balance;
	customerinfo info;
	Scanner sc = new Scanner(System.in);
	HashMap<Integer,customerinfo> db=new HashMap<Integer,customerinfo>();
	
	public void creat()
	{
		Random rand= new Random();
		accountNumber=rand.nextInt(10000);
		
	  System.out.println("Enter your Name");
	  name=sc.next();
	  balance=0;
	  info = new customerinfo(accountNumber,name,balance);
	  db.put(accountNumber, info);
	  System.out.println("Hi!"+name+"\nyour account No:"+accountNumber);
	  System.out.println("Thanks for banking with us! Good Bye\n\n");
	  
	}
	
	public void addAmount()
	{
		int accountno;
		System.out.println("Enter the Account Number");
		accountno=sc.nextInt();
		if(checkAccount(accountno)==true)
		{
			System.out.println("Enter Amount for deposit");
			double bal=sc.nextInt();
			double avalBal= db.get(accountno).getAcctBal();
			double deposit=avalBal+bal;
			info=new customerinfo(accountno,db.get(accountno).getName(),deposit);
			db.replace(accountno, info);
			
		}
		
	}
	
	
	public void withdraw()
	{
		int accNumber;
		System.out.println("Enter the account Number");
		accNumber=sc.nextInt();
		
		if(checkAccount(accNumber)==true)
		{
			System.out.println("Enter the withdraw amount");
			double tranBal=sc.nextDouble();
			
			double accBal=db.get(accNumber).getAcctBal();
			if(accBal==0 || accBal<tranBal)
			{
				System.out.println("You do'nt have suffecient balance");
				return;
				
			}
			
			else
			{
				accBal=accBal-tranBal;
				info= new customerinfo(accNumber,db.get(accNumber).getName(),accBal);
				db.replace(accNumber, info);
				System.out.println("Transaction sucessfull");
			}
		}
	}
	
	public void transfer()
	{
		int accNumber;
		int transerAcc;
		System.out.println("Enter the account number");
		accNumber=sc.nextInt();
		
		if(checkAccount(accNumber)==true)
		{
			
			double transferAmount;
			double accBal=db.get(accNumber).getAcctBal();
			System.out.println("Enter the account number for transfer");
			transerAcc=sc.nextInt();
			if(checkAccount(transerAcc)==true)
			{
				System.out.println("Enter the transfer ammount");
				transferAmount=sc.nextDouble();
				if(accBal==0 || accBal<transferAmount)
				{
					System.out.println("Sorry!you don't have suffecient account balance");
					return;
				}
				else
				{
					accBal=accBal-transferAmount;
					info= new customerinfo(accNumber,db.get(accNumber).getName(),accBal);
					db.replace(accNumber, info);
					
					info= new customerinfo(transerAcc,db.get(transerAcc).getName(),db.get(transerAcc).getAcctBal()+transferAmount);
					db.replace(transerAcc, info);
					System.out.println("Transaction sucessfull");
					System.out.println("Thanks for banking with us");
					
				}
			}
			else
			{
				System.out.println("Enter a proper account Number");
			}
		}
	}
	
	
	
	
	
	public void display()
	{
		int accountNo;
		System.out.println("Enter the account Number");
		accountNo=sc.nextInt();
		if(checkAccount(accountNo)==true)
		{
			System.out.println("Name:"+db.get(accountNo).getName()+"\n"+
		"Account Number:"+accountNo+"\n Balance: "+db.get(accountNo).getAcctBal());
		}
		
	}
	
	public boolean checkAccount(int accountNo)
	{
		if(db.containsKey(accountNo))
		{
			return true;
		}
		else
			return false;
	}
	
	
	
	
}




public class minibank {
	
	public static void startsession()
	{
		System.out.println("Welcome to Sandy payment bank");
		System.out.println("We welcome you!");
		System.out.println("----------------------------------");
		Scanner sc = new Scanner(System.in);
		bankoperation op= new bankoperation();
		while(true)
		{
			System.out.println("\n\nEnter Choice:\n----------------\n"
					+ "1: Creat Account\n2:Deposit\n3.Withdraw\n4:Transfer\n5:display");
			int choice=sc.nextInt();
			
			switch(choice)
			{
			case 1:
				op.creat();
				break;
			case 2:
				op.addAmount();
				break;
			case 3:
				op.withdraw();
				break;
			case 4:
				op.transfer();
				break;
			case 5:
				op.display();
				break;
				
			}
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		startsession();

	}

}

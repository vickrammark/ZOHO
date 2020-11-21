
import java.nio.file.Path;
import java.util.*;
class Passenger
{
	String name;
	int age;
	int id;
	String destiantion;
	
	public Passenger(String name, int age, int id,String destination) {
		super();
		this.name = name;
		this.age = age;
		this.id = id;
		this.destiantion=destination;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDestiantion() {
		return destiantion;
	}
	public void setDestiantion(String destiantion) {
		this.destiantion = destiantion;
	}
	 	
}
public class HyperloopPassengerBooking {
 
	private static int node;
	private static LinkedList<Integer>adj[];
	private static List<Passenger> passengers=new LinkedList<Passenger>();
	private static boolean VisitedStation[]; 
	private static HyperloopPassengerBooking booking;
	private  ArrayList<Integer> shortestPathList=new ArrayList<Integer>();
	private static List<Passenger>printQList=new LinkedList<Passenger>();
	
	
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		booking=new HyperloopPassengerBooking();
		int id=0;
		int a=1;
		while(a==1)
		{
		String intialize=sc.nextLine();
		String intializeArray[]=intialize.split(" ");
		String sourceString;
		int source=0;
		if(intializeArray[0].equals("INIT"))
		{
		booking.getInterConnection(intializeArray[0],intializeArray[1]);
		booking.setInterConnection(sc);
		sourceString=intializeArray[2];
		source=(int)sourceString.charAt(0)-65;
		}
		else if(intializeArray[0].equals("ADD_PASSENGER"))
		{
		 int count=Integer.parseInt(intializeArray[1]);
		 
		 booking.mainProcessAddPassenger(id,count,sc);
		 id=id+1;
		}
		else if(intializeArray[0].equals("START_POD"))
		{
		  int n=Integer.parseInt(intializeArray[1]);
		  VisitedStation=new boolean[node];
		  Arrays.fill(VisitedStation, false);
		  booking.stratingPod(n, source);
		}
		else if(intializeArray[0].equals("PRINT_Q"))
		{
			booking.remainingInQueue();
		}
		else if(intializeArray[0].equals("EXIT"))
		{
			System.out.println("Hey buddy!! thank you for using out app,Come again soon");
			a=0;
		}
		else 
		{
			System.out.println("Sorry Command Not Recongonized!!");
		}
		}
	}
	
	
	
	public LinkedList<Integer>[] getInterConnection(String command,String nodes)
	{
		   if(passengers.size()>0)
		   {
			   System.out.println("Hey you have already provided a connection to intialize again type 'EXIT' command and re-run your app");
			   return null;
		   }
			node=Integer.parseInt(nodes);
			adj=new LinkedList[node];
			for(int i=0;i<node;i++)
			{
				adj[i]=new LinkedList<Integer>();
			}
			return adj;
		
	}
	
	
	
	
	public void setInterConnection(Scanner sc)
	{
		if(passengers.size()<=0)
		{
		 for(int i=0;i<node;i++)
		 {
			 String coordinates=sc.nextLine();
			 String coordinatesArray[]=coordinates.split(" ");
			 String uTemp=coordinatesArray[0];
			 String vTemp=coordinatesArray[1];
			 int u=(int)uTemp.charAt(0)-65;
			 int v=(int)vTemp.charAt(0)-65;
			 adj[u].add(v);
		 }
		}

	}
	
	
	
	
	
	public void addPassenger(Passenger passenger)
	{
		
		       passengers.add(passenger);
		       printQList.add(passenger);
				if(printQList.size()==0)
				{
					System.out.println("Sorry you cannot add passengers without intializing any interconnections!!");
				}

		       Passenger temp;
		       if(passengers.size()!=0)
		       {
		       for (int i = 0; i < passengers.size(); i++) {     
		            for (int j = i+1; j <passengers.size(); j++) {     
		               if(passengers.get(i).getAge() < passengers.get(j).getAge()) {    
		                   temp = passengers.get(i);    
		                   passengers.set(i,passengers.get(j));    
		                   passengers.set(j, temp);
		               }     
		            }     
		        }
		       }
		       else
		       {
		    	   passengers.add(passenger);
		       }
		
	}

	
	
	
	
	public void mainProcessAddPassenger(int idMain,int count,Scanner sc)
	{

		for(int i=0;i<count;i++)
		 {
			 String passenger=sc.nextLine();
			 String passengerArray[]=passenger.split(" ");
			 if(passengerArray[0].equals("PRINT_Q")||passengerArray[0].equals("START_POD")||passengerArray[0].equals("ADD_PASSENGER")||passengerArray[0].equals("INIT"))
			 {
				 System.out.println("Please provide the passsenger details you can't do any operation now !!");
			 }
			 else
			 {
			 int id=idMain;
			 String name=passengerArray[0];
			 int age=Integer.parseInt(passengerArray[1]);
			 String destination=passengerArray[2];
			 Passenger p=new Passenger(name, age, id,destination);
			 booking.addPassenger(p);
			 }
		 }
	}
	
	
	
	
	public void stratingPod(int n,int source)
	{
		
		if(printQList.size()==0)
		{
			System.out.println("Sorry you cannot start pod without intializing any interconnections!!");
		}
		else if(passengers.size()==0)
		{
			System.out.println("Sorry you have not provided any passengers!!!");
		}
		else
		{
		List<Passenger>rideEligbilePassenger=new LinkedList<Passenger>();
		for(int i=0;i<n;i++)
		{
			rideEligbilePassenger.add(passengers.get(i));
		}
		for(int i=n-1;i>=0;i--)
		{
			passengers.remove(i);
		}
		for(int j=0;j<n;j++)
		{
			Passenger passenger=rideEligbilePassenger.get(j);
			for(int k=0;k<printQList.size();k++)
			{
				if(passenger.getId()==printQList.get(k).getId())
				{
					printQList.remove(k);
				}
			}
			
		}
		booking.buildPath(rideEligbilePassenger,source);
		}
	}
	
	
	
	
	
	public void buildPath(List<Passenger> rideEligbilePassenger,int source)
	{
		for(int i=0;i<rideEligbilePassenger.size();i++)
		{
		String destination=rideEligbilePassenger.get(i).getDestiantion();
		int dest=(int)destination.charAt(0)-65;
		ArrayList<Integer> pathList = new ArrayList<>(); 
		pathList.add(source);
		booking.startPathFinding(source, dest, pathList);
		String path="";

		for(int j=0;j<shortestPathList.size();j++)
		{
			int charachter=shortestPathList.get(j)+65;
			path=path+String.valueOf((char)(charachter))+" ";
		}
		System.out.println("Path="+path+" ,dest="+destination);
		if(!path.contains(destination) || path.equals(""))	
		{
			  System.out.println("Sorry there is no connections provided till now for destination");	
		}
		else
		{
		System.out.println(rideEligbilePassenger.get(i).getName()+" "+path);
		}
		
		}
	}
	
	
	
	
	
	
	public void startPathFinding(int source,int destination,ArrayList<Integer> PathList)
	{
		if(source==destination) {
			int min=Integer.MAX_VALUE;
			if(PathList.size()<min && PathList.size()>1)
			{
		     min=PathList.size();		
			 shortestPathList=(ArrayList<Integer>) PathList.clone();	
			}
			return;
		}
		VisitedStation[source]=true;
		
		for(Integer i:adj[source])
		{   
		if(!VisitedStation[i])
			{
				PathList.add(i);
				startPathFinding(i, destination, PathList);
				PathList.remove(i);
			}

		}
		VisitedStation[source]=false;
	}
	
	
	
	
	
	public void remainingInQueue()
	{
		if(printQList.size()==0)
		{
			System.out.println("Sorry you cannot print without intializing any interconnections!!");
		}
		else
		{
		System.out.println(printQList.size());
	   for(int i=0;i<printQList.size();i++) {	
		   System.out.println(printQList.get(i).getName()+" "+printQList.get(i).getAge());
	    }
		}
	}
	
	
	
	
	
}

package simpledb.test;

import simpledb.plan.Plan;
import simpledb.plan.Planner;
import simpledb.query.Scan;
import simpledb.server.SimpleDB;
import simpledb.tx.Transaction;
import java.util.Scanner;


public class FindMajors {
   public static void main(String[] args) {
      System.out.print("Enter a department name: ");
      Scanner sc = new Scanner(System.in);
      String major = sc.next();
      sc.close();
      System.out.println("Here are the " + major + " majors");
      System.out.println("Name\tGradYear");

      
      String qry = "select sname, gradyear "
            + "from student, dept "
            + "where did = majorid "
            + "and dname = '" + major + "'";
       
      try {
    	  
    	  SimpleDB db = new SimpleDB("studentdb");
          Transaction tx = db.newTx();
          Planner planner = db.planner();
          
          Plan p = planner.createQueryPlan(qry, tx);
          Scan s = p.open();
    	 
         while (s.next()) {
            String sname = s.getString("sname");
            int gradyear = s.getInt("gradyear");
            System.out.println(sname + "\t" + gradyear);
         }
      }
      catch(Exception e) {
         e.printStackTrace();
      }
   }
}

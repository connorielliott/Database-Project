import java.sql.*;

/*
jdbc_insert_item.java    // java program that is called by php that just does the insert; calls jdbc_db.java to connect and do the actual insert
jdbc_db.java // class (no main program) that has useful methods
*/

public class jdbc_api {
   // member variables
   private jdbc_db myDB;

   // constructor
   public jdbc_api() throws SQLException{
      String username = "cielliot"; // Change to your own username
      String mysqlPassword = "Il9ohsho"; // Change to your own mysql Password

      // Connect to the database
      myDB = new jdbc_db();
      myDB.connect(username, mysqlPassword);
      myDB.initDatabase();
   }

   public static void main(String[] args)  {
      jdbc_api myAPI = new jdbc_api();
      
      // For debugging purposes: Show the database before the insert
      // StringBuilder builder = new StringBuilder();
      // String query1 = "SELECT * from ITEM";
      // builder.append("<br> Table ITEM before:" + myDB.query(query1) + "<br>");

      // Parse input string to get restauranrestaurant Name and Address
      String command;
      String name;
      String supplier_id;
      String quantity;
      String unit_price;

      // Read command line arguments
      // args[0] is the first parameter
      command = args[0];
      name = args[1];
      supplier_id = args[2];
      quantity = args[3];
      unit_price = args[4];

      // 1) Add an investor

      // 2) Add a cryptocurrency

      // 3) Buy an investment. This should always add a new record.

      // 4) Sell an investment. This should update a record and display profit/loss.

      // 4) View all investments (and total value) for a given investor. Sort in descending order by value. This should aggregate the total investments by cryptocurrency.

      // 5) View all investors (and total shares for each) for a given cryptocurrency. Sort in ascending order by name.
      switch (command) {
         case "addInvestor": 
            myAPI.insertInvestor(args);
            break;
         case "addCrypto":
            break;
         case "buyInvestment":
            break;
         case "sellInvestment":
            break;
         case "viewInvestments":
            break;
         case "viewInvestors":
            break;
         default:
            break;
      }
      // Get the next id
      String q = "select IFNULL(max(ID), 0) as max_id from ITEM";
      ResultSet result = myDB.rawQuery(q);
      int next_id = 1;
      if (result.next()) // get first row of result set
         next_id += result.getInt("max_id");

      // Insert the new restaurant
      String input = "'" + next_id + "','" + name + "','" + supplier_id + "','" + quantity + "','" + unit_price + "'";
      myDB.insert("ITEM", input); // insert new restaurant

      // For debugging purposes: Show the database after the insert
      builder.append("<br><br><br> Table ITEM after:" + myDB.query(query1));
      System.out.println(builder.toString());

      myDB.disConnect();
   }

   private void insertInvestor(String[] args) {
      String firstName = args[1];
      String lastName = args[2];
      String ID = args[3];
      String email = args[4];
   }
}

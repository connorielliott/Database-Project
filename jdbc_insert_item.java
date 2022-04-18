import java.sql.*;

/*
jdbc_insert_item.java    // java program that is called by php that just does the insert; calls jdbc_db.java to connect and do the actual insert
jdbc_db.java // class (no main program) that has useful methods
*/

public class jdbc_insert_item {
   // The main program that inserts a restaurant
   public static void main(String[] args) throws SQLException {
      String Username = "MYMYSQLUSERNAME"; // Change to your own username
      String mysqlPassword = "MYMYSQLPASS"; // Change to your own mysql Password

      // Connect to the database
      jdbc_db myDB = new jdbc_db();
      myDB.connect(Username, mysqlPassword);
      myDB.initDatabase();

      // For debugging purposes: Show the database before the insert
      StringBuilder builder = new StringBuilder();
      String query1 = "SELECT * from ITEM";
      builder.append("<br> Table ITEM before:" + myDB.query(query1) + "<br>");

      // Parse input string to get restauranrestaurant Name and Address
      String name;
      String supplier_id;
      String quantity;
      String unit_price;

      // Read command line arguments
      // args[0] is the first parameter
      name = args[0];
      supplier_id = args[1];
      quantity = args[2];
      unit_price = args[3];

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
}

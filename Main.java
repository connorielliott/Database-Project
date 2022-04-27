import java.sql.SQLException;
import java.util.Arrays;

public class Main {
    private jdbc_db cryptoDB;

    public Main() {
        cryptoDB = new jdbc_db();
    }

    // connect to the database
    public void connect() throws SQLException {
        String username = "cielliot"; // Change to your own username
        String mysqlPassword = "Il9ohsho"; // Change to your own mysql Password

        // Connect to the database
        jdbc_db myDB = new jdbc_db();
        myDB.connect(username, mysqlPassword);
        myDB.initDatabase();

        // For debugging purposes: Show the database before the insert
        StringBuilder builder = new StringBuilder();
        String query1 = "SELECT * from ITEM";
        builder.append("<br> Table ITEM before:" + myDB.query(query1) + "<br>");
    }

    // adds a new investor to the database
    private void addInvestor(String[] params) {
        // add investor to database
        String id = params[0];
        String name = params[1];
        String email = params[2];
        String values = id + ", '" + name + "', '" + email + "'";
        cryptoDB.insert("Investor", values);
    }

    // adds a new cryptocurrency to the database
    private void addCrypto(String[] params) {
        // add crypto to database
        String id = params[0];
        String name = params[1];
        String currentValue = params[2];
        String values = id + ", '" + name + "', '" + currentValue + "'";
        cryptoDB.insert("Cryptocurrency", values);
    }

    // adds new investment to the database
    private void buyInvestment(String[] params) {
        // add investment to database
        String investorID = params[0];
        String cryptoID = params[1];
        String amount = params[2];
        String values = investorID + ", " + cryptoID + ", " + amount;
        cryptoDB.insert("Investment", values);
    }

    // sell crypto and display profit/loss
    private void sellInvestment(String[] params) {
        // sell investment
        String investorID = params[0];
        String cryptoID = params[1];
        String amount = params[2];
        String values = investorID + ", " + cryptoID + ", " + amount;
        cryptoDB.insert("Investment", values);
    }

    // view all investments for given investor (decending by current value)
    private void viewInvestments(String[] params) {
        // display investments
        String investorID = params[0];
        String query = "SELECT * FROM Investment WHERE InvestorID = " + investorID + " ORDER BY CurrentValue DESC";
        cryptoDB.query(query);
    }

    // // view all investors for given currency
    // private void viewInvestors(String[] params) {
    // // display investors
    // String cryptoID = params[0];
    // String query = "SELECT * FROM Investment WHERE cryptoID = " + cryptoID;
    // cryptoDB.select(query);
    // }

    public void run(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("No command line arguments");
        }

        // split command and parameters
        String command = args[0];
        String[] params = Arrays.copyOfRange(args, 1, args.length);

        switch (command) {
            // 1. add investor
            case "addInvestor":
                System.out.println("Adding new investor...");
                addInvestor(params);
                break;
            // 2. add cryptocurrency
            case "addCrypto":
                addCrypto(params);
                break;
            // 3. buy investment
            case "buyInvestment":
                buyInvestment(params);
                break;
            // 4. sell investment
            case "sellInvestment":
                sellInvestment(params);
                break;
            // 5. view all investments for given investor
            case "viewInvestments":
                viewInvestments(params);
                break;
            // 6. view all investors for gien cryptocurrency
            case "viewInvestors":
                // viewInvestors(params);
                break;

            default:
                break;
        }

    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run(args);
    }
}

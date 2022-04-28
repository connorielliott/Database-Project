import java.sql.*;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

public class Main {
    private jdbc_db cryptoDB;

    public Main() {

    }

    // connect to the database
    public void connect() throws SQLException {
        // Connect to the database
        cryptoDB = new jdbc_db();
        cryptoDB.connect("cielliot", "Il9ohsho");
        cryptoDB.initDatabase();

        // // For debugging purposes: Show the database before the insert
        // StringBuilder builder = new StringBuilder();
        // String query1 = "SELECT * from ITEM";
        // builder.append("<br> Table ITEM before:" + myDB.query(query1) + "<br>");
    }

    // adds a new investor to the database
    private void addInvestor(String[] params) {
        // add investor to database
        String id = params[0];
        String name = params[1];
        String email = params[2];
        String values = id + ", '" + name + "', '" + email + "'";
        try {
            cryptoDB.insert("Investor", values);
        } catch (SQLException e) {
            System.err.println("Error adding investor");
        }

        // show Investor table
        StringBuilder builder = new StringBuilder();
        String query = "SELECT * from Investor";
        builder.append("<br> Table Investor after:" + cryptoDB.query(query) + "<br>");
        System.out.println(builder.toString());
    }

    // adds a new cryptocurrency to the database
    private void addCrypto(String[] params) {
        // add crypto to database
        String id = params[0];
        String name = params[1];
        String currentValue = params[2];
        String values = id + ", '" + name + "', '" + currentValue + "'";
        try {
            cryptoDB.insert("Cryptocurrency", values);
        } catch (SQLException e) {
            System.err.println("Cannot add cryptocurrency");
        }

        // show Crypto table
        StringBuilder builder = new StringBuilder();
        builder.append("<br> Table Cryptocurrency after:" + cryptoDB.query("SELECT * from Cryptocurrency") + "<br>");
        System.out.println(builder.toString());
    }

    // adds new investment to the database
    private void buyInvestment(String[] params) {
        // add investment to database
        String investorID = params[0];
        String cryptoID = params[1];
        String numShares = params[2];
        String purchasePrice = params[3];
        String values = investorID + ", " + cryptoID + ", " + numShares + ", " + purchasePrice + "," + "TRUE";
        try {
            cryptoDB.insert("Investments", values);
        } catch (SQLException e) {
            System.err.println("Cannot add investment");
        }

        // show Investments table
        StringBuilder builder = new StringBuilder();
        builder.append("<br> Table Investments after:" + cryptoDB.query(
                "SELECT iv.Name, c.CryptoName, i.NumShares, i.PurchasePrice from Investments i, Investor iv, Cryptocurrency c WHERE i.InvestorID = iv.InvestorId AND i.CryptocurrencyId = c.CryptocurrencyID")
                + "<br>");
        System.out.println(builder.toString());
    }

    // sell crypto and display profit/loss
    private void sellInvestment(String[] params) {
        // sell investment
        String investorId = params[0];
        String cryptoId = params[1];
        String numShares = params[2];

        // find profit/loss
        try {
            ResultSet rs = cryptoDB.rawQuery(
                    "SELECT c.CurrentValue, i.PurchasePrice FROM Cryptocurrency c, Investments i WHERE c.CryptocurrencyId = "
                            + cryptoId + " AND i.InvestorId = " + investorId);
            rs.next();
            float currentValue = rs.getFloat(1);
            float purchasePrice = rs.getFloat(2);

            // show difference between current value and purchase price and format to
            // dollars
            float profitLoss = (currentValue - purchasePrice) * Float.parseFloat(numShares);
            NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(profitLoss);

            // print profit/loss
            StringBuilder builder = new StringBuilder();
            builder.append("<br> Profit/Loss: " + profitLoss + "<br>");
            System.out.println(builder.toString());

        } catch (SQLException e) {
            System.err.println("Cannot get get profit/loss");
            e.printStackTrace();
            return;
        }

        // update number of shares in investments table
        String query = "UPDATE Investments SET NumShares = NumShares - " + numShares
                + " WHERE InvestorId = " + investorId + " AND CryptocurrencyId = " + cryptoId;
        // if NumShares is 0, StillOwned = FALSE
        String query2 = "UPDATE Investments SET StillOwned = FALSE WHERE NumShares = 0";

        try {
            cryptoDB.update(query);
            cryptoDB.update(query2);
        } catch (SQLException e) {
            System.err.println("Cannot update number of shares");
            e.printStackTrace();
        }
    }

    // view all investments for given investor (decending by current value)
    private void viewInvestments(String[] params) {
        // display investments
        String investorId = params[0];
        StringBuilder builder = new StringBuilder();
        String query = "SELECT c.CryptoName, (SUM(i.NumShares) * c.CurrentValue) AS 'Total Value' FROM Investments i, Cryptocurrency c WHERE InvestorId = "
                + investorId
                + " AND i.CryptocurrencyId = c.CryptocurrencyId GROUP BY c.CryptoName, i.CryptocurrencyId ORDER BY (SUM(i.NumShares) * c.CurrentValue) DESC";
        builder.append("<br> Investment Table :" + cryptoDB.query(query) + "<br>");
        System.out.println(builder.toString());
        // need to aggregate the total investments by cryptocurrency.
    }

    // view all investors for given currency
    private void viewInvestors(String[] params) {
        // display investors
        String cryptoID = params[0];
        // show Investments table (accending order by investor name)
        StringBuilder builder = new StringBuilder();
        String query = "SELECT y.Name, SUM(x.NumShares) AS 'Total Shares' FROM Investments x, Investor y WHERE x.CryptocurrencyId = "
                + cryptoID
                + " GROUP BY y.Name ORDER BY name ASC";
        builder.append("<br> Investors Table :" + cryptoDB.query(query) + "<br>");
        System.out.println(builder.toString());
    }

    public void run(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("No command line arguments");
        }

        try {
            connect();
        } catch (SQLException e) {
            System.err.println("Cannot connect to database");
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
                viewInvestors(params);
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

import java.util.Arrays;

public class Main {
    private jdbc_db cryptoDB;

    public Main() {
        cryptoDB = new jdbc_db();
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

    public void run(String[] args) {
        // split command and parameters
        String command = args[0];
        String[] params = Arrays.copyOfRange(args, 1, args.length);

        switch (command) {
            // 1. add investor
            case "addInvestor":
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
            // 5. view all investments
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

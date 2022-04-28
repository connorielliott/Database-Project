mysql <<EOFMYSQL

use cielliot;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS Cryptocurrency, Investor, Investments;

EOFMYSQL
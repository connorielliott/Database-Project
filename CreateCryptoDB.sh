#!/bin/bash
# Remember to do turing$ chmod 755 SampleShellScript.sh
# to make it executable;
#
# To run:  turing$ ./SampleShellScript.sh
# Create the textbook example tables in your own database on turings
# Replace "sgauch" with your own username

mysql << EOFMYSQL
use cielliot;

CREATE TABLE Investor(
    InvestorId int,
    Name char(50) NOT NULL,
    Email varchar(50) NOT NULL,
    PRIMARY KEY (InvestorId)
);

CREATE TABLE Cryptocurrency(
    CryptocurrencyId int,
    CryptoName char(50) NOT NULL,
    CurrentValue float NOT NULL,
    PRIMARY KEY (CryptocurrencyId)
);

CREATE TABLE Investments(
    InvestorId int,
    CryptocurrencyId int,
    NumShares int NOT NULL,
    PurchasePrice float NOT NULL,
    StillOwned boolean,
    CHECK (NumShares >= 0),
    Foreign Key (InvestorId) references Investor(InvestorId),
    Foreign Key (CryptocurrencyId) references Cryptocurrency(CryptocurrencyId)
);

show tables;
EOFMYSQL
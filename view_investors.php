<!DOCTYPE html>
<html>
	<head>
		<title>View Investors</title>
		<style type="text/css">
			h1 {
				text-align: center;
				font-size: 35px;
			}
			h2 {
				text-align: left;
				font-size: 25px;
			}
		</style>
	</head>
	<body style="background-color: gainsboro">
		<h1>View Investors <br /></h1>
		<h2>
		<form method="post">
				<!-- GO TO HOMEPAGE -->
				<input type=button onClick="location.href='homepage.html'"
					value='Homepage'> <br />
					<!-- Input from user -->	
					<br><label style="color: black;font-weight: 600;"
					for="cryptoID">Cryptocurrency ID:</label>

				<input type="text" id="cryptoID" name="cryptoID" /><br />
					<!-- Submit -->
					<input type="submit" name="submit" value="submit">
			</form>
		</h2>
	</body>
</html>
<?php
$cryptoID = "";

if (isset($_POST['submit'])) 
{
    // replace ' ' with '\ ' in the strings so they are treated as single command line args
	$cryptoID = escapeshellarg($_POST['cryptoID']);

	echo "<h2>Your Input:</h2>";
	echo "<h3>Cryptocurrency ID: $cryptoID</h3>";

    //$command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar jdbc_insert_item ' . $cryptoID;

    // remove dangerous characters from command to protect web server
    // $escaped_command = escapeshellcmd($command);
    // echo "<p>command: $command <p>"; 
    // // run jdbc_insert_item.exe
    // system($escaped_command);  
	

	// basic select statement to get the data from the database
	//NOT DONE
	$query = "SELECT * FROM Investor";
	echo "<b> <center>Database Output</center> </b> <br> <br>";

	if ($result = $mysqli->query($query)) {
    while ($row = $result->fetch_assoc()) {
        $investorID = $row["col1"];
        $name = $row["col2"];
		$investorEmail = $row["col3"];
        echo $investorID.'<b>'; 
		echo $name.'</b>'; 
        echo $investorEmail;
    }
	$result->free();
}
}
?>
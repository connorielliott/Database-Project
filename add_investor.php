<!DOCTYPE html>
<html lang="en">

<head>
	<title>Add Investor</title>
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
	<h1>Add Investor <br /></h1>
	<h2>
		<form action="jdbc_insert_item.php" method="post">
			<!-- GO TO HOMEPAGE -->
			<input type=button onClick="location.href='homepage.html'" value='Homepage'> <br />
			<!-- Input from user -->
			<br><label style="color: black;font-weight: 600;" for="name">Name:</label>
			<input type="text" id="name" name="name" /><br />

			<br><label style="color: black;font-weight: 600;" for="investorID">Investor ID:</label>
			<input type="text" id="investorID" name="investorID" /><br />

			<br><label style="color: black;font-weight: 600;" for="investorEmail">Investor Email:</label>
			<input type="text" id="investorEmail" name="investorEmail" /><br />

			<!-- Submit -->
			<input type="submit" name="submit" value="submit">
		</form>
	</h2>
</body>

</html>

<?php
$name = $investorID = $investorEmail = "";

if (isset($_POST['submit'])) {
	// replace ' ' with '\ ' in the strings so they are treated as single command line args
	 $name = escapeshellarg($_POST['name']);
	 $investorID = escapeshellarg($_POST['investorID']);
	 $investorEmail = escapeshellarg($_POST['investorEmail']);

	 echo "<h2>Your Input:</h2>";
	 echo "Name: ", $name, "<br />";
	 echo "Investor ID: ",$investorID, "<br />";
	 echo "Investor Email: ",$investorEmail;

	$command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar Main addInvestor '. $name . ' ' . $investorID. ' ' . $investorEmail;

	//remove dangerous characters from command to protect web server
	 $escaped_command = escapeshellcmd($command);
	 //echo "<p>command: $command <p>"; 
	 // run jdbc_insert_item.exe
	 system($escaped_command);           
}
?>
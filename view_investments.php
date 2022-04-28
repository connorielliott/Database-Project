<!DOCTYPE html>
<html lang="en">

<head>
	<title>View Investments</title>
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
	<h1>View Investments<br /></h1>
	<h2>
		<form method="post">
			<!-- GO TO HOMEPAGE -->
			<input type=button onClick="location.href='homepage.html'" value='Homepage'> <br />
			<!-- Input from user -->
			<br><label style="color: black;font-weight: 600;" for="investorID">Investor ID:</label>
			<input type="text" id="investorID" name="investorID" /><br />

			<br><label style="color: black;font-weight: 600;" for="name">Name:</label>
			<input type="text" id="name" name="name" /><br /><br>
			<!-- Submit -->
			<input type="submit" name="submit" value="submit">
		</form>
	</h2>
</body>

</html>
<?php
$investorID = $name = "";
if (isset($_POST['submit'])) {
	// replace ' ' with '\ ' in the strings so they are treated as single command line args
	$investorID = escapeshellarg($_POST['investorID']);
	$name = escapeshellarg($_POST['name']);
	//repeat user input 
	echo "<h2>Your Input:</h2>";
	echo "Investor ID: ", $investorID, "<br />";
	echo "Name: ", $name, "<br />";

	$command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar Main viewInvestments ' . $investorID . ' ' . $name;

	// remove dangerous characters from command to protect web server
	$escaped_command = escapeshellcmd($command);
	echo "<p>command: $command <p>";
	// run jdbc_insert_item.exe
	system($escaped_command);
}
?>
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
			<input type=button onClick="location.href='homepage.html'" value='Homepage'> <br />
			<!-- Input from user -->
			<br><label style="color: black;font-weight: 600;" for="cryptoID">Cryptocurrency ID:</label>

			<input type="text" id="cryptoID" name="cryptoID" /><br /><br>
			<!-- Submit -->
			<input type="submit" name="submit" value="submit">
		</form>
	</h2>
</body>

</html>
<?php
$cryptoID = "";
if (isset($_POST['submit'])) {
	// replace ' ' with '\ ' in the strings so they are treated as single command line args
	$cryptoID = escapeshellarg($_POST['cryptoID']);
	//repeat user input 
	echo "<h2>Your Input:</h2>";
	echo "Cryptocurrency ID: ", $cryptoID, "<br />";

	$command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar Main viewInvestors ' . $cryptoID;

	// remove dangerous characters from command to protect web server
	$escaped_command = escapeshellcmd($command);
	echo "<p>command: $command <p>";
	// run jdbc_insert_item.exe
	system($escaped_command);
}
?>
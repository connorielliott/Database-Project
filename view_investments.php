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
			<form>
				<!-- GO TO HOMEPAGE -->
				<input type=button onClick="location.href='homepage.html'"
					value='Homepage'> <br />
				<!-- Input from user -->		
				<br><label style="color: black;font-weight: 600;"
					for="investorID">Investor ID:</label>
				<input type="text" id="investorID" name="investorID" /><br />

				<br><label style="color: black;font-weight: 600;"
					for="name">Name:</label>
				<input type="text" id="name" name="name" /><br />
		
				<!-- Submit -->
				<input name="submit" type="submit" >
			</form>
		</h2>
	</body>
</html>
<?php
if (isset($_POST['submit'])) 
{
    // replace ' ' with '\ ' in the strings so they are treated as single command line args
	$investorID = escapeshellarg($_POST[investorID]);
	$name = escapeshellarg($_POST[name]);

    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar jdbc_insert_item ' . $investorID . ' ' . $name;

    // remove dangerous characters from command to protect web server
    $escaped_command = escapeshellcmd($command);
    echo "<p>command: $command <p>"; 
    // run jdbc_insert_item.exe
    system($escaped_command);           
}
?>
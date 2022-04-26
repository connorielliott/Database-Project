<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Add Cryptocurrency</title>
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
		<h1>Add Cryptocurrency<br /></h1>
		<h2>
			<form>
				<!-- GO TO HOMEPAGE -->
				<input type=button onClick="location.href='homepage.html'"
					value='Homepage'> <br />
				<!-- Input from user -->
				<br><label style="color: black;font-weight: 600;"
					for="cryptoID">Cryptocurrency ID:</label>
				<input type="text" id="cryptoID" name="cryptoID" /><br />

				<br><label style="color: black;font-weight: 600;"
					for="cryptoName">Cryptocurrency Name:</label>
				<input type="text" id="cryptoName" name="cryptoName" /><br />

				<br><label style="color: black;font-weight: 600;"
					for="cryptoValue">Cryptocurrency Current Value:</label>
				<input type="text" id="cryptoValue" name="cryptoValue" /><br />
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
    $cryptoID = escapeshellarg($_POST[cryptoID]);
	$cryptoName = escapeshellarg($_POST[cryptoName]);
	$cryptoValue = escapeshellarg($_POST[cryptoValue]);

    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar jdbc_insert_item ' . $cryptoID . ' ' . $cryptonName . ' ' . $cryptoValue;

    // remove dangerous characters from command to protect web server
    $escaped_command = escapeshellcmd($command);
    echo "<p>command: $command <p>"; 
    // run jdbc_insert_item.exe
    system($escaped_command);           
}
?>
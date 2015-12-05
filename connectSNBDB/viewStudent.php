<?PHP
	include_once("conn.php");	
	$query = "SELECT id, firstname, lastname, course, year FROM student";
	
	$result = mysql_query($query) 
		or die('Error in query: $query' . mysql_error());
		
	while($row = mysql_fetch_assoc($result)){
		$output[] = $row;
	}
	print (json_encode($output));
?>
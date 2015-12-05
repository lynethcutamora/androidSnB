<?PHP
include_once("conn.php");
if(isset($_POST['userId'])){
	$userId = $_POST['userId'];

	$query = "SELECT * FROM user_dtl WHERE userId = '$userId'";

	$result = mysql_query($query) 
		or die('Error in query: $query' . mysql_error());

	$output = array();
	while($row = mysql_fetch_assoc($result)){
		$output[] = $row;
	}
	print (json_encode($output));
}	
?>
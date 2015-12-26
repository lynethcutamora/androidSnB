<?PHP
include_once("conn.php");
$query;
if(isset($_GET['userId'])){
	$userId = $_GET['userId'];

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
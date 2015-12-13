<?PHP
include_once("conn.php");
$query;	
if(isset($_GET['emailAdd'])){
	$emailadd = $_GET['emailAdd'];

	$query = "SELECT * FROM user_md WHERE user_emailAdd = '$emailadd'";

	$result = mysql_query($query)
		or die('Error in query: $query' . mysql_error());
		
	$output = array();
	while($row = mysql_fetch_assoc($result)){
		$output[] = $row;
	}
	print (json_encode($output));
}
?>
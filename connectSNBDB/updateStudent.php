<?PHP
	include_once("conn.php");

	if(isset($_POST['id'])){
		$id = $_POST['id'];
		$fname = $_POST['fname'];
		$lname = $_POST['lname'];
		$course = $_POST['course'];
		$year = $_POST['year'];
		
		mysql_query("UPDATE student SET firstname = '$fname', lastname = '$lname', course = '$course', year = '$year' WHERE id = '$id'");
		echo "Data updated";
}	
?>
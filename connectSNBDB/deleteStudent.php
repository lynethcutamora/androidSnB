<?PHP
	include_once("conn.php");
	if(isset($_POST['id'])){

			$id = $_POST['id'];

		mysql_query("DELETE FROM student WHERE id = '$id'");

		echo "Data Deleted";
	}
?>
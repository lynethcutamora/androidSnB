<?PHP
	include_once("conn.php");
	
	if(isset($_POST['btnpic'])){
		$id = $_POST['id'];
		$filename = $_POST['fileName'];
		$imgname = trim($filename);
		$avatarid = $_POST['avatarid'];

		mysql_query("INSERT INTO avatar_dtl(userId,avatar_name,avatar_id) VALUES ('$id','$imgname','$avatarid')");
		echo "Data Inserted";
	}
?>
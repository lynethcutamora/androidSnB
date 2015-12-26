<?PHP
include_once("conn.php");
	$query = "SELECT * FROM userpost a LEFT JOIN user_dtl b on b.userId = a.userId LEFT JOIN company_dtl c on c.userId = a.userId";
	
	//user_dtl.user_lName, user_post.postDate, voteBadge, postContent, avatar_name

	$result = mysql_query($query) 
		or die('Error in query: $query' . mysql_error());
		
	while($row = mysql_fetch_assoc($result)){
		$output[] = $row;
	}
	print (json_encode($output));
?>
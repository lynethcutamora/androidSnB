<?PHP
	include_once("conn.php");
	
	if(isset($_POST['btnideator'])){
		$userId = uniqid('id');
		$fname = $_POST['fname'];
		$lname = $_POST['lname'];
		$mi = $_POST['mi'];
		$age = $_POST['age'];
		$gender = $_POST['gender'];
		$email = $_POST['email'];
		$password = md5($_POST['password']);
		$picid=uniqid('pi');

		mysql_query("INSERT INTO user_md(userId,user_Type,user_dateRegistered,user_emailAdd,user_password,user_profilePicId) VALUES ('$userId','Ideator',NOW(),'$email','$password','$picid')");
		mysql_query("INSERT INTO user_dtl(userId,user_lName,user_fName,user_midInit,user_age,user_gender) VALUES ('$userId','$lname','$fname','$mi','$age','$gender')");
		echo "Data Inserted";

	}else if(isset($_POST['btninvestor'])){

		$userId = uniqid('id');
		$fname = $_POST['fname'];
		$lname = $_POST['lname'];
		$mi = $_POST['mi'];
		$age = $_POST['age'];
		$gender = $_POST['gender'];
		$email = $_POST['email'];
		$password = md5($_POST['password']);
		$picid=uniqid('pi');

		mysql_query("INSERT INTO user_md(userId,user_Type,user_dateRegistered,user_emailAdd,user_password,user_profilePicId) VALUES ('$userId','Investor',NOW(),'$email','$password','$picid')");
		mysql_query("INSERT INTO user_dtl(userId,user_lName,user_fName,user_midInit,user_age,user_gender) VALUES ('$userId','$lname','$fname','$mi','$age','$gender')");
		echo "Data Inserted";
	}else if(isset($_POST['btncompany'])){

		$userId = uniqid('id');
		$companyname = $_POST['companyname'];
		$email = $_POST['email'];
		$password = md5($_POST['password']);
		$businesstype = $_POST['businesstype'];
		$yearfounded = $_POST['yearfounded'];
		$description = $_POST['description'];
		$picid=uniqid('pi');

		mysql_query("INSERT INTO user_md(userId,user_Type,user_dateRegistered,user_emailAdd,user_password,user_profilePicId) VALUES ('$userId','Company',NOW(),'$email','$password','$picid')");
		mysql_query("INSERT INTO company_dtl(userId,company_name,company_businessType,company_yearFounded,company_about) VALUES ('$userId','$companyname','$businesstype','$yearfounded','$description')");
	}
?>
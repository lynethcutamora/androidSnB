<?PHP
	include_once("conn.php");

	if(isset($_POST['submitfullideator'])){
		$id = $_POST['id'];
		$address1 = $_POST['address1'];
		$address2 = $_POST['address2'];
		$city = $_POST['city'];
		$state = $_POST['state'];
		$zipcode = $_POST['zipcode'];
		$country = $_POST['country'];
		$selfdescription = $_POST['selfdescription'];
		
		mysql_query("UPDATE user_dtl SET user_shortSelfDescription = '$selfdescription' WHERE userId = '$id'");
		mysql_query("UPDATE location_dtl SET location_address1 = '$address1', location_address2 = '$address2', location_city = '$city', location_prov = '$state', location_zipcode = '$zipcode', location_country = '$country' WHERE userId = '$id'");
		echo "Data updated";
	}else if(isset($_POST['submitfullinvestor'])){
		$id = $_POST['id'];
		$address1 = $_POST['address1'];
		$address2 = $_POST['address2'];
		$city = $_POST['city'];
		$state = $_POST['state'];
		$zipcode = $_POST['zipcode'];
		$country = $_POST['country'];
		$selfdescription = $_POST['selfdescription'];
		$nameofbusiness = $_POST['nameofbusiness'];
		$typeofbusiness = $_POST['typeofbusiness'];

		mysql_query("UPDATE location_dtl SET location_address1 = '$address1', location_address2 = '$address2', location_city = '$city', location_prov = '$state', location_zipcode = '$zipcode', location_country = '$country' WHERE userId = '$id'");
		mysql_query("UPDATE user_dtl SET user_shortSelfDescription = '$selfdescription', user_nameOfBusiness = '$nameofbusiness', user_businessType = '$typeofbusiness' WHERE userId = '$id'");

		echo "Data Updated";
	}else if(isset($_POST['submitfullcompany'])){
		$id = $_POST['id'];
		$lname = $_POST['lname'];
		$fname = $_POST['fname'];
		$mi = $_POST['mi'];

		mysql_query("UPDATE company_dtl SET company_lName = '$lname', company_fName = '$fname', company_midInit = '$mi' WHERE userId = '$id'");
		echo "Data Updated";
	}
?>
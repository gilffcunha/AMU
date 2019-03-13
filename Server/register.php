<?php

	require_once "connect.php";

	if(!$con){
		echo "Falha na conexão à Base de Dados";
	}else{
		if($_SERVER['HTTP_USER_AGENT'] == "LuxApp"){
			if($_SERVER["REQUEST_METHOD"] == "POST") {
				$name = $_POST['name'];
				$email = $_POST['email'];
				$password = $_POST['password'];

				if($name == "" || $email == "" || $password == ""){
					echo "Alguns campos estão vazios. Por favor, preencha todos os campos de forma válida.";
				}else{
					$qry_dupe = "SELECT * FROM User WHERE email = '$email'";
					$dupe_results = mysqli_query($con, $qry_dupe);
					if(mysqli_num_rows($dupe_results) >0){
						echo "Este e-mail já está a ser utilizado noutra conta...";
					}else{
						$enc_pw = md5($password);
						$sql = "INSERT INTO User (name, email, password) VALUES ('$name','$email','$enc_pw')";
						if(mysqli_query($con,$sql)){
							echo "Registado com sucesso!";
						}else{
							echo "Falha no registo...";
						}
						mysqli_close($con);
					}
				}
			}else{
			echo "Erro no método do pedido. O método deve ser POST. Os seus dados não foram inseridos.";
			}
		}
	} 

?>
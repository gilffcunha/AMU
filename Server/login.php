<?php

	require_once "connect.php";

	if(!$con){
		echo "Falha na conexão à Base de Dados";
	}else{
		if($_SERVER['HTTP_USER_AGENT'] == "LuxApp"){
			if($_SERVER["REQUEST_METHOD"] == "POST") {
				$email = $_POST['email'];
				$password = $_POST['password'];

				if($email == "" || $password == ""){
					echo "Alguns campos estão vazios. Por favor, preencha todos os campos de forma válida.";
				}else{
					$enc_pw = md5($password);
					$qry_dupe = "SELECT * FROM User WHERE email = '$email' AND password = '$enc_pw'";
					$dupe_results = mysqli_query($con, $qry_dupe);
					if(mysqli_num_rows($dupe_results) >0){
						$sql2 = "SELECT max(id) FROM User";
						$result_id = mysqli_query($con,$sql2);
						$row = mysqli_fetch_array($result_id);
                        $data = $row[0];
                    
                       if($data){
                          echo $data;
                       }
					}else{
						echo "Falha no login, os campos são inválidos!";
					}
					mysqli_close($con);
				}
			}else{
			echo "Erro no método do pedido. O método deve ser POST. Os seus dados não foram inseridos.";
			}
		}
	} 
?>
<?php

	require_once "connect.php";

	if(!$con){
		echo "Falha na conexão à Base de Dados";
	}else{
		if($_SERVER['HTTP_USER_AGENT'] == "LuxApp"){
			if($_SERVER["REQUEST_METHOD"] == "POST") {
				$email = $_POST['email'];

				$qry_dupe = "SELECT id FROM User WHERE email = '$email'";
				$result_id = mysqli_query($con, $qry_dupe);

				if(mysqli_num_rows($result_id) >0){
					$id = mysqli_fetch_array($result_id);
                    $data = $id[0];
                
                    echo $data;
				}else{
					$sql = "INSERT INTO User (email) VALUES ('$email')";
					if(mysqli_query($con,$sql)){
						echo "Registado com sucesso!";
					}else{
						echo "Falha no registo...";
					}
					mysqli_close($con);
				}
			}else{
			echo "Erro no método do pedido. O método deve ser POST. Os seus dados não foram inseridos.";
			}
		}
	} 

?>
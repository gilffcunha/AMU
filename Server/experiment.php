<?php

	require_once "connect.php";

	if(!$con){
		echo "Falha na conexão à Base de Dados";
	}else{
		if($_SERVER['HTTP_USER_AGENT'] == "LuxApp"){
			if($_SERVER["REQUEST_METHOD"] == "POST") {
				$android_version = $_POST['android_version'];
				$model = $_POST['model'];
				$brand = $_POST['brand'];
				$user_id = $_POST['user_id'];
				$protocol_id = $_POST['protocol_id'];

				if($android_version == "" || $model == "" || $brand == "" || $user_id == "" || $protocol_id == ""){
					echo "Falha no envio de dados, alguns campos estão vazio...";
				}else{
					$sql = "INSERT INTO Experiment (AndroidVersion, Brand, Model, User_ID, Protocol_ID) VALUES ('$android_version', '$brand', '$model', '$user_id', '$protocol_id');";
					if(mysqli_query($con,$sql)){
						$sql2 = "SELECT max(id) FROM Experiment";
						$result_id = mysqli_query($con,$sql2);
						$row = mysqli_fetch_array($result_id);
                        $data = $row[0];
                    
                       if($data){
                          echo $data;
                       }
					}else{
						echo "Falha no envio dos dados...";
					}
					mysqli_close($con);
				}
			}else{
			echo "Erro no método do pedido. O método deve ser POST. Os seus dados não foram inseridos.";
			}
		}
	} 
?>
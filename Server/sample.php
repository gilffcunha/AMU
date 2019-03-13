<?php

	require_once "connect.php";

	if(!$con){
		echo "Falha na conexão à Base de Dados";
	}else{
		if($_SERVER['HTTP_USER_AGENT'] == "LuxApp"){
			if($_SERVER["REQUEST_METHOD"] == "POST") {
				$latitude = $_POST['latitude'];
				$longitude = $_POST['longitude'];
				$luminosity = $_POST['luminosity'];
				$timestamp = $_POST['timestamp'];
				$experimentID = $_POST['experimentID'];

				if($latitude == "" || $longitude == "" || $luminosity == "" || $timestamp == "" || $experimentID == ""){
					echo "Falha no envio de dados, alguns campos estão vazio...";
				}else{
					$sql = "INSERT INTO Sample (Latitude, Longitude, Luminosity, Timestamp, Experiment_ID) VALUES ('$latitude', '$longitude', '$luminosity', '$timestamp', '$experimentID');";
					if(mysqli_query($con,$sql)){
                          echo "Dados enviados com sucesso!";
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
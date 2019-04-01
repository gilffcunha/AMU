<?php

	require_once "connect.php";

	if(!$con){
		echo "Falha na conexão à Base de Dados";
	}else{
		if($_SERVER['HTTP_USER_AGENT'] == "LuxApp"){
			if($_SERVER["REQUEST_METHOD"] == "POST") {
				$experimentID = $_POST['experimentID'];
				$lux = $_POST['lux'];

				$sql = "UPDATE Experiment SET Avg_Lux = '$lux' WHERE ID = '$experimentID';";
				
				if(mysqli_query($con,$sql))
				    echo $experimentID;

				mysqli_close($con);
				
			}else{
			echo "Erro no método do pedido. O método deve ser POST. Os seus dados não foram inseridos.";
			}
		}
	} 
?>
<?php

	require_once "connect.php";

	if(!$con){
		echo "Falha na conexão à Base de Dados";
	}else{
		if($_SERVER['HTTP_USER_AGENT'] == "LuxApp"){
			if($_SERVER["REQUEST_METHOD"] == "POST") {
				$experimentID = $_POST['experimentID'];

				$sql = "SET @row_number = 0;";
				$sql2 = "SELECT num FROM (SELECT (@row_number:=@row_number + 1) AS num, Avg_Lux, ID FROM Experiment ORDER BY Avg_Lux) as T WHERE ID = '$experimentID';";

				if(mysqli_query($con,$sql)){
					$rank = mysqli_query($con,$sql2);
					$row = mysqli_fetch_array($rank);
	                $data = $row[0];
	            
	               	if($data){
	                  echo $data;
					}else{
						echo "Falha na conexão...";
					}
				}
				mysqli_close($con);
				
			}else{
			echo "Erro no método do pedido. O método deve ser POST. Os seus dados não foram inseridos.";
			}
		}
	} 
?>
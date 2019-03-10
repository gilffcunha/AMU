package DataBase;

import java.sql.SQLException;

public class TestConnection
{
    public static void main(String[] args) throws SQLException {

        // Connection
        ConnectionDB conn = new ConnectionDB();

        // Insert
        InsertionDB i = new InsertionDB(conn);


        // Entities

        // user
        System.out.println("Create user");
        User user = new User();
        user.setName("joe");
        user.setEmail("joe@email.com");
        user.setPassword("123");

        System.out.println("Insert user"); i.insertUser(user);

        // protocol
        System.out.println("Create protocol");
        Protocol protocol = new Protocol();
        protocol.setType("Luminosidade nas passadeiras");
        protocol.setDescription("1º passo: a;\n2º passo: b;\n3º passo: c;\n");

        System.out.println("Insert protocol"); i.insertProtocol(protocol);

        // experiment
        System.out.println("Create experiment");
        Experiment experiment = new Experiment();
        experiment.setAndroidVersion("android");
        experiment.setBrand("wiko");
        experiment.setModel("cink five");
        experiment.setUserId(user.getId());
        experiment.setProtocol(protocol.getId());

        System.out.println("Insert experiment"); i.insertExperiment(experiment);

        // sample
        System.out.println("Create sample");
        Sample sample = new Sample();
        sample.setLatitude(41.125712);
        sample.setLongitude(-8.600869);
        sample.setLuminosity(50);
        sample.setExperimentID(experiment.getId());

        System.out.println("Insert sample"); i.insertSample(sample);


        System.out.println("Validate Login: " + i.validateLogin("joe@email.com","123"));

    }
}

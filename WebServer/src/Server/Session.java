package Server;

import DataBase.DBConnection;
import DataBase.DataBase_API;

public class Session
{
    private Utilizador user;

    public boolean userExists(String email, String password)
    {
        return DataBase_API.existUser(email,password);
    }


}

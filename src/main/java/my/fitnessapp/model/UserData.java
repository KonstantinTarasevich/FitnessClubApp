package my.fitnessapp.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserData extends User {


    public UserData(String username,
                    String password,
                    Collection<? extends GrantedAuthority> authorities

    ) {
        super(username, password, authorities);

    }


}

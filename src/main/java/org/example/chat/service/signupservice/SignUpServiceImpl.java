package org.example.chat.service.signupservice;


import org.example.chat.forms.signinform.SignUpForm;
import org.example.chat.model.RoleEnum;
import org.example.chat.model.StateEnum;
import org.example.chat.model.User;
import org.example.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private   PasswordEncoder passwordEncoder;


    @Override
    public void signUp(SignUpForm signUpForm) {
        String hashPassword = passwordEncoder.encode(signUpForm.getPassword());
        String role = RoleEnum.User.name();
        String state = StateEnum.Active.name();
        User user = new User.Builder()
                .setFirstName(signUpForm.getFirstName())
                .setLastName(signUpForm.getLastName())
                .setLogin(signUpForm.getLogin())
                .setHashPassword(hashPassword)
                .setRole(role)
                .setState(state)
                .build();

         userRepository.save(user);

    }
}

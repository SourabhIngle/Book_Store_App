package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.DTO.UserLoginDTO;
import com.bridgelabz.bookstoreapp.DTO.response.ResponseDTO;
import com.bridgelabz.bookstoreapp.DTO.UserDTO;
import com.bridgelabz.bookstoreapp.exception.CustomException;
import com.bridgelabz.bookstoreapp.model.UserModel;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.util.EmailService;
import com.bridgelabz.bookstoreapp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;


@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService userService;

    @Autowired
    private JWTToken jwtToken;

    //====================================================================
    @Override
    public UserModel register(UserDTO userDTO) {
        // Check if the email is already registered
        UserModel userModel = new UserModel(userDTO);
        //Generate otp and sending to the mail.
        Random random = new Random();

        int OTP = random.nextInt(100000, 1000000);
        userModel.setOtp(OTP);
        userService.sendEmail(userDTO.getEmailId(), "Please use the OTP to verify your email information on Book Store app",
                "This OTP " + OTP + " is valid for 10 minutes");

        //Set registration date and update date.
        LocalDate currentDate = LocalDate.now();
        userModel.setRegisteredDate(currentDate);
        userModel.setUpdatedDate(currentDate);
        return userRepository.save(userModel);

    }

    //------------------------------------------------------------------------
    @Override
    public ResponseDTO login(UserLoginDTO userLoginDTO) {
        String email = userLoginDTO.emailId;
        String password = userLoginDTO.password;
        UserModel userModel = userRepository.findByEmail(email);

        if (userModel != null) {
            if (userModel.getEmailId().equals(email)) {
                if (userModel.getPassword().equals(password)) {
                    {
                        String token = jwtToken.createToken(userModel.getUser_id());
                        userModel.setVerify(true);
                        userRepository.save(userModel);
                        return new ResponseDTO(token, userModel);
                    }
                }
                return new ResponseDTO("Check password ", password);
            }
        }
        return new ResponseDTO("This Email id not exist in database. Check email id ", email);
    }

    @Override
    public UserModel loginByToken(String token) {
        Long id = jwtToken.decodeToken(token);
        return getById(id);
    }

    //------------------------------------------------------------------------
    @Override
    public boolean verification(String email, Integer otp) {
        UserModel userEmail = userRepository.findByEmail(email);

        if (userEmail != null && userEmail.getOtp().equals(otp)) {
            return true;
        }
        return false;
    }

    //------------------------------------------------------------------------
    @Override
    public UserModel getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new CustomException("This uses id " + id + " do not exist"));
    }

    //------------------------------------------------------------------------
    @Override
    public void delete(long id) {
        getById(id);
        userRepository.deleteById(id);
    }

    //------------------------------------------------------------------------
    @Override
    public UserModel update(Long id, UserDTO userDTO) {
        UserModel getId = getById(id);
        if (getId != null) {
            LocalDate currentDate = LocalDate.now();
            getId.updateUserData(userDTO);
            getId.setUpdatedDate(currentDate);
            return userRepository.save(getId);
        }
        return null;
    }

    //------------------------------------------------------------------------
    @Override
    public boolean uniqueEmailId(String email) {
        UserModel duplicateEmail = userRepository.findByEmail(email);
        return duplicateEmail == null;
    }

    //------------------------------------------------------------------------
    @Override
    public List<UserModel> getAll() {
        return userRepository.findAll();
    }


}

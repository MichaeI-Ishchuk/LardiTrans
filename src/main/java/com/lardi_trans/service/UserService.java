package com.lardi_trans.service;

import com.lardi_trans.domain.User;
import com.lardi_trans.dto.UserDTO;
import org.springframework.stereotype.Service;



import java.util.List;

/**
 * Created by nata on 19.03.2017.
 */


public interface UserService {

   public void registrationUser(UserDTO userDTO);

  public User verify(String login, String password);
//
//   public List<StudentDTO> getStudentsBySubject(String subject);
//
//   public void setMarkStudent(StudentDTO studentDTO);
//
//   public int getMarkStudent(Student student);

}

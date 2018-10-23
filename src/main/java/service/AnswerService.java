package service;

import model.Answer;

public class AnswerService {
    UserService userService = new UserService();
    Answer answer = new Answer(userService.getAllUsers());

}

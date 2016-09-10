package br.com.nadod.designpatterns.mediator;

public interface ChatMediator {
    String sendMessage(String msg, User user, MediatorActivity.UserType to);
    void addUser(User user);
}

package br.com.nadod.designpatterns.mediator;

import java.util.ArrayList;
import java.util.List;

public class ChatMediatorImpl implements ChatMediator {
    private List<User> users;

    public ChatMediatorImpl(){
        this.users=new ArrayList<>();
    }

    @Override
    public String sendMessage(String msg, User user, MediatorActivity.UserType to) {
        String msgResult = "";
        if (user.getType() == MediatorActivity.UserType.Teacher) {
            for (User u : this.users) {
                if (u.getType() == MediatorActivity.UserType.Student)
                    msgResult += "\n" + u.receive(msg);
            }
        } else if (user.getType() == MediatorActivity.UserType.Student) {
            if (to == MediatorActivity.UserType.Student) {
                for (User u : this.users) {
                    if (u != user && u.getType() == to) {
                        msgResult += "\n" + u.receive(msg);
                    }
                }
            } else if (to == MediatorActivity.UserType.Teacher) {
                for (User u : this.users) {
                    if (u.getType() == to) {
                        msgResult += "\n" + u.receive(msg);
                    }
                }
            }
        }
        return msgResult;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}

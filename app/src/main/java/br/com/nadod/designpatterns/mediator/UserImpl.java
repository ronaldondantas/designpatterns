package br.com.nadod.designpatterns.mediator;

public class UserImpl extends User {
    public UserImpl(ChatMediator med, String name, MediatorActivity.UserType type) {
        super(med, name, type);
    }

    @Override
    public String send(String msg, MediatorActivity.UserType to){
        return "O " + getType(this.type) + " " + this.name + " enviou a mensagem: " + msg + "\n" +
                mediator.sendMessage(msg, this, to);
    }
    @Override
    public String receive(String msg) {
        return "O " + getType(this.type) + " " + this.name + " recebeu a mensagem: " + msg;
    }

    private String getType(MediatorActivity.UserType type) {
        if (type == MediatorActivity.UserType.Student) return "aluno";
        if (type == MediatorActivity.UserType.Teacher) return "professor";
        return "";
    }
}

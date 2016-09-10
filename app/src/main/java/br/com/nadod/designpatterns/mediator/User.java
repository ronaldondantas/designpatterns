package br.com.nadod.designpatterns.mediator;

public abstract class User {
    protected ChatMediator mediator;

    protected String name;

    public MediatorActivity.UserType getType() {
        return type;
    }

    public void setType(MediatorActivity.UserType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChatMediator getMediator() {
        return mediator;
    }

    public void setMediator(ChatMediator mediator) {
        this.mediator = mediator;
    }

    protected MediatorActivity.UserType type;

    public User(ChatMediator med, String name, MediatorActivity.UserType type){
        this.mediator = med;
        this.name = name;
        this.type = type;
    }

    public abstract String send(String msg, MediatorActivity.UserType to);

    public abstract String receive(String msg);
}

package sourcecode.springexample.test;

public class InjectSelf {

    private InjectSelf self;

    public InjectSelf getSelf() {
        return self;
    }

    public void setSelf(InjectSelf self) {
        this.self = self;
    }

}

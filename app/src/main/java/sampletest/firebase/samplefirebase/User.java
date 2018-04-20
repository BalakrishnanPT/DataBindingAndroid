package sampletest.firebase.samplefirebase;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class User extends BaseObservable {

    private String name;
    private String email;
    private String phno;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Bindable
    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}



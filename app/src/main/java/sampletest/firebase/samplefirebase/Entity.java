package sampletest.firebase.samplefirebase;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class Entity extends BaseObservable implements Message {

    private String name;
    private String email;
    private String phoNo;

    public Entity(){};

    public Entity(String text, String userName,String photoUrl) {
        this.name = text;
        this.email = userName;
        this.phoNo = photoUrl;
    }
    @Bindable
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
    this.name=name;
    }

    @Bindable
    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
    this.email=email;
    }

    @Bindable
    @Override
    public String getPhoNo() {
        return this.phoNo;
    }

    @Override
    public void setPhoNo(String phoNo) {
    this.phoNo=phoNo;
    }

    @Override
    public String toString() {
       return  "Name :"+this.getName()+
               "\tEmail :"+this.getEmail()+
               "\tPho_no :"+this.getPhoNo();
    }
}
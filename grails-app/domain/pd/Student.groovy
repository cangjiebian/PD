package pd

import javax.persistence.FetchType
import javax.persistence.OneToMany

class Student{
    
    String sid //学号
    String email
    String password
    String clazz
    String name
    enum Gender{
        MAN,WOMAN
    }
    Gender gender
    Date registerTime
    
    List<Paper> papers=[]

    
    
    String  cookieId //**
    String  lastIp //*
    Boolean autologin = false //*
    String  uuid //**
    
    static constraints = {
        sid matches:/[0-9]{1,20}/,unique:true,nullable:false
        email email:true,nullable:false
    }
    static hasMany = [papers:Paper]
    //todo:!!!需要理解
    static mapping={
        papers fetch: 'join'
    }
    
    def beforeInsert(){
        registerTime=new Date()
    }
    
    @Override
    String toString(){ "{id: $id, sid:$sid, password:$password" }
}
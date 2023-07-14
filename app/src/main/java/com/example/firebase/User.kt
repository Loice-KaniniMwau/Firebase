package com.example.firebase

class User {
    var email:String?=null
            var password:String?=null
                    var uid:String?=null
    constructor(){}
    constructor(email:String?,password:String?,uid:String?){
        this.email=email
        this.password=password
        this.uid=uid
    }

}
package com.example.mindha.aplikasiediklatpegawai;

public class DataLoginRegister {

    //URL to our login.php file, url bisa diganti sesuai dengan alamat server kita
    public static final String LOGIN_URL = "http://ediklat.pe.hu/login.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_NAME = "nama_lengkap";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String IS_LOGGED_IN = "true";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";


}

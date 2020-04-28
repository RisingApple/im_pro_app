package com.personalpro.im_app;

public class Constant {


    public static String IP = "192.168.1.154";
    public static String CLOUD_IP = "47.113.118.54";
    public static String CURRENT_IP = CLOUD_IP;
    public static String PORT = "8080";
    public static String BASE_URL = "http://"+CURRENT_IP+":"+PORT;
    public static String SESSION_KEY = "session_id";
    public static String USER_NAME_KEY = "username";
    public static String SEND_MESSAGE_KEY = "send";
    public static String WEB_SOCKET_ADDRESS = "ws://"+CURRENT_IP+"/ws?username=";
    public static String CHAT_MESSAGE_KEY = "message";

    public static final String BROAD_CAST_SOCKET_OPEN = "broad_cast_socket_open";
    public static final String BROAD_CAST_SOCKET_MESSAGE = "broad_cast_socket_message";
    public static final String BROAD_CAST_SOCKET_CLOSE = "broad_cast_socket_close";
    public static final String BROAD_CAST_SOCKET_ERROR = "broad_cast_socket_error";
    public static final String BROAD_CAST_SEND_MESSAGE = "broad_cast_send_message";

}

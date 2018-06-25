package com.techhome.pitsan;

/**
 * Created by USER on 9/12/2016.
 */
public class Config {
    //--boo.ogeniuspriority.com
    //public static String IP_ADDRESS=" http://www.boo.ogeniuspriority.com/";

    public static String IP_ADDRESS = "http://192.168.43.206/";
    //public static String IP_ADDRESS = "http://www.nezaservice.com/";
    public static String SEND_S_pit_OR_CROWDSOURCING_REQUEST = IP_ADDRESS
            + "PITSAN/mobile_app/pistan_send_client_request_SIngleOrCloud.php";
    public static String SEND_TRUCK_DRIVER_REQUEST = IP_ADDRESS
            + "PITSAN/mobile_app/pistan_send_truck_driver_request.php";
    public static String PITSAN_LOG_USING_REF_ID_FOR_CLIENT = IP_ADDRESS
            + "PITSAN/mobile_app/pitsan_CommonUserLogIn.php";
    public static String PITSAN_LOG_FOR_TRUCK_DRIVER = IP_ADDRESS
            + "PITSAN/mobile_app/pitsan_admin_user_or_truck_userLOgin.php";
    //------------------------------------The load data--------
    public static String PITSAN_SINGLE_PIT_DATA_LOAD_FEEDS = IP_ADDRESS
            + "PITSAN/mobile_app/pitsan_load_single_pit_data_for_driver.php";
    public static String PITSAN_CROWDSOURCING_PIT_DATA_LOAD_FEEDS = IP_ADDRESS
            + "PITSAN/mobile_app/pitsan_load_crowdsourcing_data_for_driver.php";
    public static String PITSAN_MESSAGES_PIT_DATA_LOAD_FEEDS = IP_ADDRESS
            + "PITSAN/mobile_app/pitsan_load_messages_to_pitsan.php";
    public static String PITSAN_REF_ID_OFFERS_PIT_DATA_LOAD_FEEDS = IP_ADDRESS
            + "PITSAN/mobile_app/pitsan_load_ref_id_offers_data.php";


}




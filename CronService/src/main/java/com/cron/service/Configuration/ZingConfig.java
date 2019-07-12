package com.cron.service.Configuration;

public class ZingConfig {
    //    Mobile version
    public static final String M_DOMAIN = "https://m.zingmp3.vn";
    public static final String M_API = "https://m.zingmp3.vn/xhr";
    public static final String M_GET_SONG_API = "https://m.zingmp3.vn/xhr/media/get-source?type=audio&key=";
    public static final String M_CHART = "https://m.zingmp3.vn/xhr/chart-realtime?chart=song&time=-1&count=100";
    public static final String M_TOP100 = "https://m.zingmp3.vn/top100/index.html";
    public static final String M_TOPIC = "https://m.zingmp3.vn/chu-de";


    //    PC Version
    public static final String API_GET_SONG_DETAIL = "https://zingmp3.vn/api/song/get-streamings";
    public static final String API_GET_PLAYLIST_DETAIL = "https://zingmp3.vn/api/playlist/get-playlist-detail?";// params(id,ctime,sig,api_key)
    public static final String API_KEY = "38e8643fb0dc04e8d65b99994d3dafff"; // public user key
    public static final String CTIME = "1559196945";
    public static final String SIG = "badb66705575b4735c53612b58b56aef0b70df72bad56387533105400d5ca7ee103ec9d60e88ccaaf88d50fced8bdf4b848f103d1e8482a2f9fb50862acb08f7";
}

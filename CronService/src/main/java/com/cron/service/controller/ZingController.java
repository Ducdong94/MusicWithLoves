package com.cron.service.controller;

import com.cron.service.Configuration.ZingConfig;
import com.cron.service.Utils.ResponseUtils;
import com.cron.service.entity.Category;
import com.cron.service.entity.Playlist;
import com.cron.service.entity.Song;
import com.cron.service.entity.Source;
import com.cron.service.repository.SongRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.*;


@Configuration
@EnableScheduling
public class ZingController {

    @Autowired
    SongRepository songRepository;

//    @Scheduled(fixedDelay = 86400000)
    public void getZingChartMobileScheduler() {
        System.out.println("Start crawl zingChartMobileScheduler ZingMp3 at " + new Date());
        try {
            Category category = new Category("Nhac-Tre", "Nhạc Trẻ");
            Playlist playlist = new Playlist("ZING-CHART", "ZING-CHART");
            Connection.Response response = Jsoup.connect(ZingConfig.M_CHART).method(Connection.Method.GET).execute();
            if (ResponseUtils.requestFailed(response, ZingConfig.M_CHART)) {
                return;
            }
            JSONObject jsonObject = new JSONObject(response.body());
            JSONArray respSongs = jsonObject.getJSONObject("data").getJSONArray("song");
            Song song;
            List<Song> songs = new ArrayList<>();
            for (Object info : respSongs) {
                jsonObject = (JSONObject) info;
                song = getSongByKey(jsonObject.getString("code"));
                song.getSongPlaylist().add(playlist);
                song.getSongCategories().add(category);
                songs.add(song);
            }
            songRepository.saveAll(songs);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

//    @Scheduled(fixedDelay = 86400000)
    public void getTopicMobileScheduler() {
        System.out.println("Start crawl topicMobileScheduler ZingMp3 at " + new Date());
        try {
            Document document = Jsoup.connect(ZingConfig.M_TOPIC).get();
            Elements elements = document.getElementsByClass("link-obj");
            String path;
            Playlist playlist;
            Category category;
            String playlistTitle, playlistId, cateId, cateName;
            Elements elems, links;
            int COUNT = 0;
            for (int i = 0; i < elements.size() - 3; i++) {
                try {
                    path = elements.get(i).attr("href");
                    cateId = path.split("/")[2];
                    document = Jsoup.connect(ZingConfig.M_DOMAIN + path).get();
                    elems = document.getElementsByClass("link-obj");
                    cateName = document.getElementsByClass("title-main").get(0).text();
                    cateName = cateName.substring(cateName.indexOf("lọc") + 4, cateName.length());
                    category = new Category(cateId, cateName);
                    for (Element element : elems) {
                        path = element.attr("href");
                        document = Jsoup.connect(ZingConfig.M_DOMAIN + path).get();
                        links = document.getElementsByClass("link-obj");
                        category = new Category(cateId, cateName);
                        for (Element link : links) {
                            path = link.attr("href");
                            playlistId = path.substring(path.lastIndexOf("/") + 1, path.indexOf("."));
                            document = Jsoup.connect(ZingConfig.M_DOMAIN + path).get();
                            playlistTitle = document.getElementsByClass("title-main-song").get(0).text();
                            playlist = new Playlist(playlistId,playlistTitle);
                            path = document.getElementById("zplayerjs-wrapper").attr("data-source");
                            getSongFromAlbum(path,category, playlist);
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

    }

//    @Scheduled(fixedDelay = 86400000)
    public void getTop100MobileScheduler() {
        System.out.println("Start crawl top100MobileScheduler ZingMp3 at " + new Date());

        try {
            Document document = Jsoup.connect(ZingConfig.M_TOP100).get();
            Elements elements = document.getElementsByClass("module-allLinks");
            String playlistTitle, playlistId, href, cateId;
            Elements links;
            Playlist playlist;
            Category cate;
            for (Element element : elements) {
                links = element.getElementsByTag("a");
                for (Element link : links) {
                    if (!link.toString().contains("href=\"#\"")) {
                        try {
                            href = link.attr("href");
                            cateId = href.substring(href.indexOf("top100/"), href.lastIndexOf("/")).split("/")[1];
                            playlistTitle = element.getElementsByClass("title-main").text() + " " + link.attr("title");
                            playlistId = href.substring(href.lastIndexOf("/") + 1, href.indexOf("."));
                            playlist = new Playlist(playlistId, playlistTitle);
                            cate = new Category(cateId.toLowerCase(), link.attr("title"));
                            document = Jsoup.connect(ZingConfig.M_DOMAIN + href).get();
                            href = document.getElementsByClass("overlay-video").get(0).getElementsByTag("a").attr("href");
                            document = Jsoup.connect(ZingConfig.M_DOMAIN + href).get();
                            href = document.getElementById("zplayerjs-wrapper").attr("data-source");
                            getSongFromAlbum(href, cate, playlist);
                        } catch (Exception ex) {
                            System.err.println(link);
                            System.err.println(ex.getMessage());
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public void getSongFromAlbum(String path, Category cate, Playlist playlist) {
        try {
            System.out.println(path);
            Connection.Response response = Jsoup.connect(ZingConfig.M_API + path).method(Connection.Method.GET).execute();
            if (ResponseUtils.requestFailed(response, path)) {
                return;
            }
            JSONObject jsonObject = new JSONObject(response.body());
            JSONArray items = jsonObject.getJSONObject("data").getJSONArray("items");
            String id, name, code, thumbnail, artistsNames, source, link;
            int duration;
            List<Song> songs = new LinkedList<>();
            for (int i = 0; i < items.length(); i++) {
                try {
                    jsonObject = items.getJSONObject(i);
                    id = jsonObject.getString("id");
                    name = jsonObject.getString("name");
                    code = jsonObject.getString("code");
                    artistsNames = jsonObject.getString("artists_names");
                    link = jsonObject.getString("link");
                    thumbnail = jsonObject.getString("thumbnail");
                    source = jsonObject.getJSONObject("source").getString("128");
                    duration = jsonObject.getInt("duration");
                    Song song = new Song(id, name, code, artistsNames, link, thumbnail, duration);
                    Source src = new Source(id + "-128", "128", source.substring(2));
                    src.setSong(song);
                    System.out.println(id + "==" + name);
                    song.getSongCategories().add(cate);
                    song.getSongPlaylist().add(playlist);
                    song.setSource(Arrays.asList(src));
                    songs.add(song);
                } catch (Exception e) {
                    System.out.println(jsonObject);
                    System.out.println(e.getMessage());
                }
            }
            songRepository.saveAll(songs);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public Song getSongByKey(String key) {
        Song song = new Song();
        try {
            String url = ZingConfig.M_GET_SONG_API + key;
            Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).execute();
            if (!ResponseUtils.requestFailed(response, url)) {
                JSONObject jsonObject = new JSONObject(response.body()).getJSONObject("data");
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String code = jsonObject.getString("code");
                String artistsNames = jsonObject.getString("artists_names");
                String link = jsonObject.getString("link");
                String thumbnail = jsonObject.getString("thumbnail");
                String source = jsonObject.getJSONObject("source").getString("128");
                int duration = jsonObject.getInt("duration");
                song = new Song(id, name, code, artistsNames, link, thumbnail, duration);
                Source src = new Source(id + "-128", "128", source.substring(2));
                src.setSong(song);
                song.setSource(Arrays.asList(src));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return song;
    }


}

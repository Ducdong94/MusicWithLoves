package com.cron.service.controller;

import com.cron.service.Configuration.ZingConfig;
import com.cron.service.entity.Category;
import com.cron.service.entity.Playlist;
import com.cron.service.entity.Song;
import com.cron.service.entity.Source;
import com.cron.service.repository.CategoryRepository;
import com.cron.service.repository.PlaylistRepository;
import com.cron.service.repository.SongRepository;
import com.cron.service.repository.SourceRepository;
import org.json.JSONException;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableScheduling
public class Top100ZingController {

    @Autowired
    SongRepository songRepository;
    @Autowired
    SourceRepository sourceRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    PlaylistRepository playlistRepository;

    private static String URL_1 = "https://m.zingmp3.vn/top100/vietnam";
    private static String URL_2 = "https://m.zingmp3.vn/top100/us-uk";
    private static String URL_3 = "https://m.zingmp3.vn/top100/chaua";
    private static String URL_4 = "https://m.zingmp3.vn/top100/hoatau";
    private static String HOST = "https://m.zingmp3.vn";

    @Scheduled(fixedDelay = 86400000)
    public void cron() {
        System.out.println("run tesst");
        List<String> listURL = new ArrayList<>();
//        listURL.add(URL_1);
        listURL.add(URL_2);
        listURL.add(URL_3);
        listURL.add(URL_4);
        for (int i = 0; i < listURL.size(); i++) {
            try {
                Document document1 = Jsoup.connect(listURL.get(i)).get();
                Elements elements = document1.select("div.col-album");
                for (Element element : elements) {
                    Playlist playlist = new Playlist();
                    Category category = new Category();
                    String link = element.select(".card-210").attr("href").trim();
                    String title = element.select(".title a").text().trim();
                    String linkImage = element.getElementsByTag("img").attr("src").trim();
                    playlist.setName(title);
                    playlist.setImage(linkImage);
                    playlist.setId(HOST + link);

                    category.setName(title);
                    category.setId(link);

                    Document doc = Jsoup.connect(HOST + link).get();
                    Elements ele = doc.select(".list-song-suggest .card-40");
                    for (Element element2 : ele) {
                        Song song = new Song();
                        Source source = new Source();
                        String linkBH = element2.select(".card-info .title a").attr("href").trim();
                        System.out.println(linkBH);
                        String tenBH = element2.select(".card-info .title a").text().trim();
                        String artist = element2.select(".card-info .artist a").text().trim();
                        song.setLink(HOST + linkBH);
                        song.setArtistsNames(artist);
                        song.setTitle(tenBH);
                        Document dcm = Jsoup.connect(HOST + linkBH).get();
                        String APISource = dcm.select(".hide-menubar #zplayerjs-wrapper").attr("data-source").trim();
                        String dataSource = Jsoup.connect(ZingConfig.M_API + APISource).ignoreContentType(true).method(Connection.Method.GET).execute().body();
                        JSONObject jsonObject = new JSONObject(dataSource);
                        JSONObject data = jsonObject.getJSONObject("data");
                        String id = data.getString("id");
                        song.setThumbnail(data.getString("thumbnail"));
                        song.setCode(data.getString("code"));
                        song.setId(id);
                        song.setDuration(data.getInt("duration"));

                        String src = data.getJSONObject("source").getString("128");
                        source.setId(id + "-128");
                        source.setQuality("128");
                        source.setPath(src.substring(2));
                        source.setSong(song);

                        song.setSource(Arrays.asList(source));
                        song.setTime(System.currentTimeMillis());
                        song.getSongCategories().add(category);

                        playlist.getPlaylistSong().add(song);

                        song.getSongPlaylist().add(playlist);


                        songRepository.save(song);
                        sourceRepository.save(source);
                    }
                    categoryRepository.save(category);
                    playlistRepository.save(playlist);
                    System.out.println("saved");

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException ex) {
                System.out.println("lol");
            }
        }


    }

}

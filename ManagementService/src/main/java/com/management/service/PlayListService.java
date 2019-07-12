package com.management.service;

import com.management.service.entity.Playlist;
import com.management.service.model.response.Result;

public interface PlayListService {
	Result update(Playlist playList);

	Result addSong(String idPlayList, String idSong);

	Result getPlayListById(String idPlayList);

	Result deletePlayList(String idPlayList);

	Result createPlayList(Playlist playList);
}

package com.management.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.service.PlayListService;
import com.management.service.entity.Playlist;
import com.management.service.entity.Song;
import com.management.service.model.response.Result;
import com.management.service.repository.PlayListRepsitory;
import com.management.service.repository.SongRepsitory;

@Service
public class PlayListServiceImpl implements PlayListService {
	@Autowired
	public PlayListRepsitory playListRepsitory;

	@Autowired
	public SongRepsitory songRepsitory;

	public Result addSong(String idPlayList, String idSong) {
//		Playlist playlist = playListRepsitory.findById(idPlayList).orElse(null);
//		Song song = songRepsitory.findById(idSong).orElse(null);
		Result result = null;
//		if (playlist == null) {
//			result = new Result(1, "Play list không tồn tại ", null);
//		} else if (song == null) {
//			result = new Result(1, "Song không tồn tại ", null);
//		} else {
//			List<Song> lstSong = playlist.getPlaylistSongs();
//			lstSong.add(song);
//			playlist.setPlaylistSongs(lstSong);
//			playListRepsitory.save(playlist);
//			result = new Result(200, "OK", playlist);
//		}
		return result;
	}

	@Override
	public Result getPlayListById(String idPlayList) {
		Playlist playlist = playListRepsitory.findById(idPlayList).orElse(null);
		Result result;
		if (playlist == null) {
			result = new Result(1, "Play list không tồn tại ", null);
		} else {
			result = new Result(200, "OK", playlist);
		}
		return result;
	}

	@Override
	public Result update(Playlist playList) {
		Playlist playlist = playListRepsitory.save(playList);
		Result result;
		if (playlist == null) {
			result = new Result(1, "update play list thất bại", null);
		} else {
			result = new Result(200, "OK", playlist);
		}
		return result;
	}

	@Override
	public Result deletePlayList(String idPlayList) {
		playListRepsitory.deleteById(idPlayList);
		Result result = new Result(200, "OK", "");
		return result;
	}

	@Override
	public Result createPlayList(Playlist playList) {
		Playlist playlist = playListRepsitory.save(playList);
		Result result;
		if (playlist == null) {
			result = new Result(1, "create play list thất bại", null);
		} else {
			result = new Result(200, "OK", playlist);
		}
		return result;
	}
}

$(function () {
    "use strict";
    home.setButtonEvent();


});

var home = {
    setButtonEvent: function () {
        $('.ms_play_icon').unbind();
        $('.btn-share').unbind();
        $('.show-share-modal').unbind();

        $('.ms_play_icon').click(function () {
            var info = $(this).parents('.w_top_song');
            var song = {};
            song.image = info.find('.img-fluid').attr('src');
            song.title = info.find('.w_tp_song_name a').text();
            song.artist = info.find('.w_tp_song_name p').text();
            song.mp3 = "http://" + $(this).attr('data-source');
            song.oga = "http://" + $(this).attr('data-source');
            song.option = JPlayer.playListOption;
            console.log(song)
            home.updatePlayList(song);
        });
        $('.show-share-modal').click(function () {
            songid = $(this).attr('data-song-id');
        });
        $('.btn-share').click(function () {
            var email = $('#share-email').val();
            var content = $('#share-content').val();

            var obj = {
                'emailTo': email,
                'note': content,
                'songId': songid
            };
            console.log(obj)
            $.ajax({
                url: '/send',
                type: 'POST',
                data: JSON.stringify(obj),
                dataType: 'json',
                contentType: 'application/json',
            }).done(function (resp) {
                $('#share-email').val('');
                $('#share-content').text('');
                $('#share_modal').modal('hide');
            }).fail(function () {
                $('#share-email').val('');
                $('#share-content').text('');
                $('#share_modal').modal('hide');
            });
        });


    },
    updatePlayList: function (song) {
        JPlayer.playList.add(song);
        // JPlayer.playList.play(JPlayer.playList.length-1);
    },
    playGift:function () {
        var song = {};
        var info = $('#gift');
        song.image = info.find('.img-fluid').attr('src');
        song.title = info.find('.w_tp_song_name a').text();
        song.artist = info.find('.w_tp_song_name p').text();
        song.mp3 = "http://" + $(this).attr('data-source');
        song.oga = "http://" + $(this).attr('data-source');
        song.option = JPlayer.playListOption;
        JPlayer.playList.add(song);
        JPlayer.playList.play(1);
    }
};
var songid;
$(function () {
    $('.ms_play_icon').click(function () {
alert($(this).attr('data-playlist-id'))
        window.location = '/detail?id=' + $(this).attr('data-playlist-id');
    });
});


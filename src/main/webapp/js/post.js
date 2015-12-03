$(function() {
  "use strict";

  var viewFiles = function(files) {
    var fileListArea = $('.file-list > tbody');
    var fileSizeArea = $('.file-size');
    if (!files || !files.length) {
      $('.selected-files').hide();
      disableSendButton();
      fileListArea.html('');
      fileSizeArea.text('');
      return;
    }

    enableSendButton();
    $('.selected-files').show();
    var fileInfo = [];
    var totalFileSize = 0;
    for (var i = 0; i < files.length; ++i) {
        var file = files[i];
        fileInfo.push('<tr>');
        fileInfo.push('<td>')
        fileInfo.push(file.name);
        fileInfo.push('</td><td>')
        fileInfo.push(file.type)
        fileInfo.push('</td><td>')
        fileInfo.push(Math.ceil(file.size/1024/1024));
        fileInfo.push('MB');
        fileInfo.push('</td>')
        fileInfo.push('</tr>');
        totalFileSize = totalFileSize + file.size;
    }
    fileListArea.html(fileInfo.join(''));
    fileSizeArea.html('ファイルサイズ合計 -> <span class="strong">' + Math.ceil(totalFileSize/1024/1024) + '</span> MB');
  };

  var setUploadProgress = function(jQueryProgressEvent) {
    var percent = (jQueryProgressEvent.originalEvent.loaded / jQueryProgressEvent.originalEvent.total * 100) + '';
    var pb = $('div.progress-bar');
    pb.attr('aria-valuenow', percent);
    pb.css({'width': percent+'%'});
    pb.text(( Math.ceil(percent * 10) / 10 ) + '%');
  };

  var disableSendButton = function() {
    $('.uploader-container').hide();
  };

  var enableSendButton = function() {
    $('.uploader-container').show();
  };

  var showProgressBar = function() {
    $('div.progress').show('normal');
  };

  var hideProgressBar = function() {
	    $('div.progress').hide('normal');
  };

  var showError = function(errorMessage) {
      $('.error-message-container').show();
      $('.error-message').text(errorMessage);
  };

  var uploadFiles = function(files) {
    if (!files.length || files.length === 0) {
      alert('動画ファイルを選択して下さい.');
      return;
    }

    for (var i = 0; i < files.length; ++i) {
      var type = files[i].type;
      if (type.indexOf('video/') !== 0) {
        alert('ファイル[' + files[i].name + ']は動画ではないようです.');
        return;
      }
    }

    var fd = new FormData();
    for (var i = 0; i < files.length; i++) {
      fd.append("files[]", files[i]);
    }
    fd.append("upload-person-name", $('input.upload-person-name').val())

    // Ajaxでアップロード処理をするファイルへ内容渡す
    $.ajax({
      url: '/ajax-post',
      type: 'POST',
      data: fd,
      processData: false,
      contentType: false,
      xhr: function() {
        var xhr = $.ajaxSettings.xhr();
        $(xhr.upload).on('progress', setUploadProgress);
        return xhr;
      },
      beforeSend: function() {
        disableSendButton();
        showProgressBar();
      },
      success: function(data) {
        alert('動画がアップロードされました。ご協力ありがとうございました。');
        $('a.refresher').get(0).click();
      },
      error: function(ajax, _, errorMessage) {
        showError(errorMessage);
      },
      complete: function() {
        enableSendButton();
        setTimeout(function() {
          hideProgressBar();
        }, 500);
      },
    });
  };

  $('input.upload-person-name').change(function() {
    $('a.refresher').attr('href', '?person=' + encodeURI($('input.upload-person-name').val()));
  });

  $('button.selector').click(function() {
    // ダミーボタンとinput[type="file"]を連動
    $('input[type="file"]').click();
  });

  $('input[type="file"]').change(function() {
    viewFiles(this.files);
  });

  $('button.uploader').click(function() {
    var files = $('input[type="file"]').get(0).files;
    if (!files || !window.FormData) {
      disableSendButton();
      $('button.hidden-uploader').click();
      return false;
    }
    uploadFiles(files);
    return false;
  });

  $('button.hidden-uploader').click(function() {
    $('body').append('<div class="screen">送信中...</div>');
    return true;
  });

  window.onerror = function (message, url, lineNumber, error) {
    showError(message + "\n" + error);
    alert("エラーが発生しました。ごめんなさい。画面一番下のエラーメッセージ欄の内容をともにお知らせして下さい。");
    return true;
  };

  viewFiles($('input[type="file"]').get(0).files);
});
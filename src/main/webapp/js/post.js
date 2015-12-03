$(function(){
  "use strict";

  var viewFiles = function(files) {
    var fileListArea = $('.file-list > tbody');
    var fileSizeArea = $('.file-size');
    if (!files || !files.length) {
      fileListArea.html('');
      fileSizeArea.text('');
      return;
    }
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
    console.log(fileInfo.join(''));
    fileListArea.html(fileInfo.join(''));
    fileSizeArea.text('ファイルサイズ合計 -> ' + Math.ceil(totalFileSize/1024/1024) + ' MB');
  };

  var setUploadProgress = function(jQueryProgressEvent) {
    var percent = (jQueryProgressEvent.originalEvent.loaded / jQueryProgressEvent.originalEvent.total * 100) + '';
    var pb = $('div.progress-bar');
    pb.attr('aria-valuenow', percent);
    pb.css({'width': percent+'%'});
    pb.text(percent+'%');
  };

  var clearUploadProgress = function() {
    var pb = $('div.progress-bar');
    pb.attr('aria-valuenow', '0');
    pb.css({'width': '0%'});
    pb.text('0%');
  };

  var showProgressBar = function() {
    $('div.progress').show('normal');
  };

  var hideProgressBar = function() {
	    $('div.progress').hide('normal');
  };

  var uploadFiles = function(files) {
    if (!files || !files.length ||  files.length === 0) {
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

    // Ajaxでアップロード処理をするファイルへ内容渡す
    $.ajax({
      url: '/post',
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
        showProgressBar();
      },
      success: function(data) {
        console.log('ファイルがアップロードされました。');
      },
      error: function() {
        console.log(arguments);
      },
      complete: function() {
        setTimeout(function() {
          hideProgressBar();
        }, 500);
      },
    });
  }

  /*================================================
    ダミーボタンを押した時の処理
  =================================================*/
  $('button.selector').click(function() {
    // ダミーボタンとinput[type="file"]を連動
    $('input[type="file"]').click();
  });

  $('input[type="file"]').change(function() {
    viewFiles(this.files);
  });

  $('button.uploader').click(function() {
    uploadFiles($('input[type="file"]').get(0).files);
  });

  viewFiles($('input[type="file"]').get(0).files);
});
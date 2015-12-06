<%@page import="info.jabara.wakadance.web.WebUtil"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
  <link rel="stylesheet" href="./css/post.css" >
  <title>わかこ披露宴動画 アップロードサイト</title>
</head>
<body>

  <div class="container">

    <h2>動画アップロード用のページです</h2>
    <p>ともです。みなさんの動画をここからアップロードして下さい。<p>
    <p>このページに関するお問い合わせはFacebookでどうぞ。<p>
    <p>※動画以外も投稿できるようにしました。</p>
    <p>うまくいかない人は<a href="./post2">こちら</a>も試して見て下さい。</p>

    <form method="post" enctype="multipart/form-data" action="./post">
      <div class="form-container">
        <label>お名前</label>
        <input type="text" class="form-control upload-person-name" name="upload-person-name" placeholder="何かあった時の連絡のために入力をお願いします" value="${param.person}">
      </div>
      <div class="form-container file-controls">
        <label>動画選択</label>
        <input type="file" name="files[]" multiple >
        <button type="button" class="selector btn btn-primary form-control">ここを押してファイルを選んで下さい</button>
        <fieldset class="selected-files">
          <p>あなたが選択しているのは以下の動画です。</p>
          <table class="file-list table table-striped">
            <tbody></tbody>
          </table>
        </fieldset>
        <span class="file-size"></span>
      </div>
      <hr/>
      
      <div class="uploader-container">
        <p>下のボタンでアップロードを開始して下さい。ファイルのサイズが大きい場合、Wi-Fiに接続してアップロードすることをオススメします。</p>
        <button type="button" class="uploader btn btn-danger form-control">アップロード開始！</button>
        <button type="submit" class="hidden-uploader btn btn-default form-control">アップロードがうまくいかない場合はこのボタンを押して！</button>
      </div>
    </form>

    <div class="progress">
      <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
      </div>
    </div>

    <hr/>
    <a href="" class="refresher btn btn-default form-control">最初からやり直し</a>

    <hr/>
    <div class="error-message-container">
      <p>エラーメッセージ欄</p>
      <div class="error-message"></div>
    </div>

  </div>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
  <script src="./js/post.js"></script>
  <script>
    var msg = '<%= WebUtil.popSessionMessage(request) %>';
    if (msg) {
      alert(msg);
    }
  </script>
</body>
</html>
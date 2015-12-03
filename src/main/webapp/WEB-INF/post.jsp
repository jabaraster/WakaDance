<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <link rel="stylesheet" href="./css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
  <link rel="stylesheet" href="./css/post.css" >
  <title>わかこ披露宴動画 投稿サイト</title>
</head>
<body>

  <div class="container">

    <form>
      <div class="form-container">
        <label>お名前</label>
        <input type="text" class="form-control" >
      </div>
      <div class="form-container file-controls">
        <label>動画選択</label>
        <input type="file" multiple >
        <button type="button" class="selector btn btn-default form-primary">選択</button>
        <table class="file-list table table-striped">
          <tbody></tbody>
        </table>
        <span class="file-size"></span>
      </div>
      <hr/>
      <button type="button" class="uploader btn btn-success">送信</button>
    </form>

    <div class="progress">
      <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
      </div>
    </div>

  </div>


  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
  <script src="./js/post.js"></script>
</body>
</html>
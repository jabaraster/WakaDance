<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
  <link rel="stylesheet" href="./css/post2.css" >
  <title>わかこ披露宴動画 アップロードサイトシンプル版</title>
</head>
<body>
  <div class="container">
    
    <form method="post" enctype="multipart/form-data" action="./post">
      <div class="form-container">
        <label>お名前</label>
        <input type="text" class="form-control upload-person-name" name="upload-person-name" placeholder="何かあった時の連絡のために入力をお願いします" value="${param.person}">
      </div>
      <div class="form-container file-controls">
        <label>動画選択</label>
        <input type="file" name="files[]" multiple >
      </div>
      <button type="submit" class="hidden-uploader btn btn-danger form-control">アップロード！</button>
    </form>
    
  </div>
</body>
</html>
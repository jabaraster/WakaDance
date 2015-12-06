<%@page import="java.util.List"%>
<%@page import="info.jabara.wakadance.entity.EUploadFile"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <link rel="stylesheet" href="./css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
  <link rel="stylesheet" href="./css/index.css" >
  <title>わかこ披露宴動画 アップロードファイル一覧</title>
</head>
<body>

  <div class="container">

    <div class="button-container">
      <a href="" class="btn btn-default"><span class="glyphicon glyphicon-refresh">リロード</span></a>
      <a href="./post" class="btn btn-primary">アップロード画面へ</a>
    </div>

    <h2>アップロードされた動画の一覧です</h2>
    <div class="button-container">
      <a href="?hidedownloaded=true" class="btn btn-default btn-sm">DOWNLOADEDを消す</a>
      <a href="?hidedownloaded=false" class="btn btn-default btn-sm">DOWNLOADEDも表示する</a>
    </div>

    <table class="table table-striped">
      <tbody>
        <c:forEach var="file" items="${files}">
          <tr>
            <td><button type="button" class="downloaded btn btn-success btn-sm" data-record-id="${file.id.value}"><span class="glyphicon glyphicon-ok"></span></button></td>
            <td>${file.personName}</td>
            <td>${file.uploadFileName}</td>
            <td>${file.contentType}</td>
            <td>${file.size / 1024 / 1024} MB</td>
            <td class="send-state-${file.id.value}">${file.sendState}</td>
            <td>${file.localFileName}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
  <script src="./js/index.js"></script>
</body>
</html>
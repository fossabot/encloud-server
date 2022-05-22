<%@ page contentType="text/html;charset=UTF-8" language="java" buffer="16kb" %>


<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>发布资料-资料小站</title>
  <script>
    function home(){location.href = "/homePage.jsp"}
  </script>
</head>

<body>
  <main>
    <section class="landing">
      <div class="content">
        <h1 id="logo">文件上传测试</h1>
        <form action="/test/uploadTest" class="upload" method="post" enctype="multipart/form-data" accept-charset="utf-8">
          <div class="box">选择文件<input type='file' name="file" multiple></div>
          <div class="box button"><input type="submit" value="提交"></div>
        </form>
      </div>
    </section>
  </main>
</body>
</html>
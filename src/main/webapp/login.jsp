<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ログインページ</title>
</head>
<body>
    <h1>ログイン</h1>
    <!-- エラーメッセージの表示 -->
    <% 
        String error = request.getParameter("error"); 
        if (error != null) { 
            if ("invalid".equals(error)) { 
    %>
                <p style="color: red;">ユーザー名またはパスワードが間違っています。</p>
    <% 
            } else if ("notfound".equals(error)) { 
    %>
                <p style="color: red;">ユーザー名が存在しません。</p>
    <% 
            } 
        } 
    %>

    <!-- ログインフォーム -->
    <form action="login_servlet" method="post">
        <label for="username">ユーザー名:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password">パスワード:</label>
        <input type="password" id="password" name="password" required>
        <br><br>
        <button type="submit">ログイン</button>
    </form>
</body>
</html>

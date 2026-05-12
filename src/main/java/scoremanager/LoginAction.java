package scoremanager;

// リクエスト・レスポンス関連クラスをインポート
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// Actionクラスをインポート
import tool.Action;

// ログイン画面表示用クラス
public class LoginAction extends Action {

	// executeメソッドをオーバーライド
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		// login.jspへ画面遷移する
		req.getRequestDispatcher("login.jsp").forward(req, res);
	}
}
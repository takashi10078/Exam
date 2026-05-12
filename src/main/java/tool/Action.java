package tool;
//リクエスト.レスポンスを扱うためのクラスをインポート
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class Action {
//executeメソッド
	public abstract void execute(
			HttpServletRequest req, HttpServletResponse res
		) throws Exception;

}

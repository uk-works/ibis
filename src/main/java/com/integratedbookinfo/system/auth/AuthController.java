package com.integratedbookinfo.system.auth;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;

import lombok.RequiredArgsConstructor;

/**
 * 認証関連のリクエストを受け付けるコントローラー
 * ログイン処理およびダッシュボードへの画面遷移を担当する
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	
	/**
	 * ログイン画面を表示する
	 * 初期表示用のフォームオブジェクトをModelに設定する
	 * 
	 * @param model 画面表示用データを保持するModel
	 * @return ログイン画面
	 */
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("form", new LoginUserRequestDTO());
		return "common/login";
	}
	
	/**
	 * ログイン処理を行う
	 * 入力値のバリデーションおよび認証処理を行い、
	 * 認証成功時はセッションにユーザー情報を保持する
	 *
	 * @param form ログイン入力情報
	 * @param errors バリデーションエラー情報
	 * @param session セッション情報
	 * @return ログイン成功時はダッシュボードへリダイレクト、失敗時はログイン画面
	 */
	@PostMapping("/login")
	public String login(
			@Validated @ModelAttribute("form") LoginUserRequestDTO form,
			Errors errors,
			HttpSession session) {
	
		if(errors.hasErrors()) {
			return "common/login";
		}
	
		LoginUserRequestDTO loginUser = authService.authenticate(form);
		
		if (loginUser == null) {
			errors.rejectValue("loginId", "error.login.invalid");
			return "common/login";
		}
		
		loginUser.setPassword(null);

		session.setAttribute("loginUser", loginUser);
		
		return "redirect:/dashboard";
	}
	
	/**
	 * ログアウト処理を行う
	 * 
	 * @param session セッション情報
	 * @return ログイン画面
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/auth/login";
	}

}

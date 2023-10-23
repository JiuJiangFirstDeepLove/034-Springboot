package com.disk.base.constant;

import java.util.Arrays;
import java.util.List;

/**
 * 系统运行时的常量
 * @author Administrator
 *
 */
public class RuntimeConstant {

	//登录拦截器无需拦截的url
	public static List<String> loginExcludePathPatterns = Arrays.asList(
			"/system/login",
			"/system/register",
			"/system/auth_order",
			"/share/sn/**",
			"/share/verify_pwd",
			"/admin/css/**",
			"/admin/fonts/**",
			"/admin/js/**",
			"/admin/layer/**",
			"/admin/images/**",
			"/error",
			"/photo/view",
			"/photo/view_file",
			"/download/download_file",
			"/cpacha/generate_cpacha"
		);
	//权限拦截器无需拦截的url
	public static List<String> authorityExcludePathPatterns = Arrays.asList(
			"/system/login",
			"/system/register",
			"/system/auth_order",
			"/system/index",
			"/share/sn/**",
			"/share/verify_pwd",
			"/share/save_file",
			"/system/no_right",
			"/admin/css/**",
			"/admin/fonts/**",
			"/admin/js/**",
			"/admin/layer/**",
			"/admin/images/**",
			"/error",
			"/cpacha/generate_cpacha",
			"/system/logout",
			"/system/update_userinfo",
			"/system/update_pwd",
			"/photo/view",
			"/upload/upload_photo",
			"/download/download_file",
			"/photo/view_file"
		);
}

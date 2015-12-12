package com.xxx.iss.http.action;

/**
 * 微信认证
 * @author 门士松  20121031
 * @version 1.0
 * @since
 */
public class WeiXinAction extends BaseAction {

	public ActionResult doDefault() {
			String echostr = getRequest().getParameter("echostr");
			this.getWriter().print(echostr);
			return null;
	}
}

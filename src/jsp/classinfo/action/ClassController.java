package jsp.classinfo.action;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;


public class ClassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Action> commandMap;

	public void init(ServletConfig config) throws ServletException {	
        loadProperties("jsp/classinfo/properties/ClassCommand");
    }

    //파일 경로 설정
    private void loadProperties(String filePath) {
    	
    	commandMap = new HashMap<String, Action>();
    	
		ResourceBundle rb = ResourceBundle.getBundle(filePath);
		Enumeration<String> actionEnum = rb.getKeys(); //키값을 가져온다.
    	
    	while(actionEnum.hasMoreElements()) {
    		//명령어를 가져온다.
    		String command = actionEnum.nextElement();
    		//명령어에 해당하는 action 클래스 이름을 가져온다.
    		String className = rb.getString(command);
    		
    		try {
    			Class actionClass = Class.forName(className); //클래스 생성
    			Action actionInstance = (Action)actionClass.newInstance(); //클래스 객체 생성
    			
    			//화면전환 action인지 확인
    			if(className.equals("jsp.classinfo.action.ClassFormChangeAction")){
    				ClassFormChangeAction bf = (ClassFormChangeAction)actionInstance;
					 bf.setCommand(command);
				 }
    			//맵에 명령어와 action을 담는다.
    			commandMap.put(command, actionInstance);
    		}catch(Exception e) {
    			System.out.println(e.getMessage());
    		}
    	}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}

	/**
     * 명령어에 따른 해당 Action을 지정해 준다.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doProcess(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
    	String requestURI = request.getRequestURI();
		int cmdIdx = requestURI.lastIndexOf("/") + 1;
		String command = requestURI.substring(cmdIdx);
    	
    	System.out.println("Class cmd : " + command);
    	
    	ActionForward forward = null;
    	Action action = null;
    	
    	try {
    		action = commandMap.get(command);
    		if(action == null) {
    			System.out.println("명령어 : " + command + "는 잘못된 명령어입니다.");
    			return;
    		}
    		forward = action.execute(request, response);
    		/*
             * 화면이동 - isRedirext() 값에 따라 sendRedirect 또는 forward를 사용
             * sendRedirect : 새로운 페이지에서는 request와 response객체가 새롭게 생성된다.
             * forward : 현재 실행중인 페이지와 forwad에 의해 호출될 페이지는 request와 response 객체를 공유
             */
    		
    		if(forward != null){
				if (forward.isRedirect()) {
					response.sendRedirect(forward.getNextPath());
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getNextPath());
					
					dispatcher.forward(request, response);
				}
			}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
